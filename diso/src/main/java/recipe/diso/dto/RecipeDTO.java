package recipe.diso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private Long id;
    private String title;
    private String description;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    List<IngredientDTO> ingredients;
}
