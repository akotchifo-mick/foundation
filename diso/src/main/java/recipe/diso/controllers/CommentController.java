package recipe.diso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe.diso.dto.CommentDTO;
import recipe.diso.models.Comment;
import recipe.diso.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService service;

    @GetMapping
    public ResponseEntity<?> allComments() {
        return service.getAllComments();
    }

    @GetMapping("/{id}")
    public CommentDTO getOne(@PathVariable Long id) {
        return service.getCommentById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> add(@RequestBody CommentDTO comment) {
        return new ResponseEntity<>(service.saveComment(comment), HttpStatus.OK);
    }

    //no recommended to delete a comment
    /*@DeleteMapping("/{id}")
    public ResponseEntity<Object> delete (@PathVariable Long id){
        return service.deleteComment(id);
    }*/
}
