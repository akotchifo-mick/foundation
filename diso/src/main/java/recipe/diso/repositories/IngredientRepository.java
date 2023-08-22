package recipe.diso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.diso.models.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);
}
