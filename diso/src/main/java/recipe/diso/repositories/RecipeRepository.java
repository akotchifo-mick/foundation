package recipe.diso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.diso.models.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByTitle(String title);

    List<Recipe> findAllByAuthorId (Integer id);
}
