package recipe.diso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.diso.models.Comment;
import recipe.diso.models.Label;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByParentIsNull();

    List<Comment> findByParent(Comment comment);

}
