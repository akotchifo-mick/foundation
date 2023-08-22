package recipe.diso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import recipe.diso.dto.CommentDTO;
import recipe.diso.models.Comment;
import recipe.diso.repositories.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<Comment> rootComments = repository.findByParentIsNull();
        List<CommentDTO> commentDTOs = new ArrayList<>();

        for (Comment rootComment : rootComments) {
            CommentDTO commentDTO = mapCommentAndRepliesToDTO(rootComment);
            commentDTOs.add(commentDTO);
        }

        return new ResponseEntity<List<CommentDTO>>(commentDTOs, HttpStatus.OK);
    }
    private CommentDTO mapCommentAndRepliesToDTO(Comment comment) {
        CommentDTO commentDTO = mapCommentToDTO(comment);

        List<Comment> replies = repository.findByParent(comment);
        if (!replies.isEmpty()) {
            List<CommentDTO> replyDTOs = new ArrayList<>();
            for (Comment reply : replies) {
                CommentDTO replyDTO = mapCommentAndRepliesToDTO(reply); // Recursive call
                replyDTOs.add(replyDTO);
            }
            commentDTO.setReplies(replyDTOs);
        }

        return commentDTO;
    }

    private CommentDTO mapCommentToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        commentDTO.setCreated_at(comment.getCreated_at());
        commentDTO.setAuthorEmail(comment.getAuthorEmail());

        return commentDTO;
    }

    public CommentDTO getCommentById(Long id) {
        Optional<Comment> optionalComment = repository.findById(id);

        if (optionalComment.isEmpty()) {
            throw new NoSuchElementException("Comment not found");
        }

        Comment comment = optionalComment.get();
        return mapCommentAndRepliesToDTO(comment);
    }
    public CommentDTO saveComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setBody(commentDTO.getBody());
        comment.setAuthorEmail(commentDTO.getAuthorEmail());

        Comment savedComment = repository.save(comment);
        return mapCommentAndRepliesToDTO(savedComment);
    }

    public ResponseEntity<Object> deleteComment(Long id) {
        try{
            Comment comment = repository.findById(id).get();
            repository.delete(comment);
            return new ResponseEntity<>("Comment successfully deleted", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Comment does not exist", HttpStatus.NOT_FOUND);
        }
    }

}
