package recipe.diso.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.diso.models.Comment;
import recipe.diso.models.Recipe;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String body;
    private String authorEmail;
    private LocalDateTime created_at;
    private List<CommentDTO> replies;
}
