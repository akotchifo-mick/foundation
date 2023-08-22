package recipe.diso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import recipe.diso.dto.IngredientDTO;
import recipe.diso.dto.RecipeDTO;
import recipe.diso.models.Ingredient;
import recipe.diso.models.Recipe;
import recipe.diso.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository repository;

    public ResponseEntity<Object> allRecipes() {
        List<Recipe> dbRecipes = repository.findAll();

        if (dbRecipes.isEmpty()) {
            return new ResponseEntity<>("No recipes yet", HttpStatus.OK);
        }

        List<RecipeDTO> recipeDTOs = new ArrayList<>();

        for (Recipe recipe : dbRecipes) {
            RecipeDTO recipeDTO = new RecipeDTO();
            recipeDTO.setId(recipe.getId());
            recipeDTO.setTitle(recipe.getTitle());
            recipeDTO.setDescription(recipe.getDescription());
            recipeDTO.setCreated_at(recipe.getCreated_at());
            recipeDTO.setUpdated_at(recipe.getUpdated_at());


            List<IngredientDTO> ingredientDTOs = new ArrayList<>();
            for (Ingredient ingredient : recipe.getIngredients()) {
                IngredientDTO ingredientDTO = new IngredientDTO();
                ingredientDTO.setName(ingredient.getName());
                ingredientDTO.setQuantity(ingredient.getQuantity());
                ingredientDTOs.add(ingredientDTO);
            }

            recipeDTO.setIngredients(ingredientDTOs);
            recipeDTOs.add(recipeDTO);
        }

        return new ResponseEntity<>(recipeDTOs, HttpStatus.OK);
    }

    public ResponseEntity<Object> getRecipeById(Long id) {
        try {
            Recipe recipe = repository.findById(id).get();
            RecipeDTO recipeDTO = new RecipeDTO();
            recipeDTO.setId(recipe.getId());
            recipeDTO.setTitle(recipe.getTitle());
            recipeDTO.setDescription(recipe.getDescription());

            List<IngredientDTO> ingredientDTOs = new ArrayList<>();
            for (Ingredient ingredient : recipe.getIngredients()) {
                IngredientDTO ingredientDTO = new IngredientDTO();
                ingredientDTO.setName(ingredient.getName());
                ingredientDTO.setQuantity(ingredient.getQuantity());
                ingredientDTOs.add(ingredientDTO);
            }

            recipeDTO.setIngredients(ingredientDTOs);

            return new ResponseEntity<>(recipeDTO, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Recipe not founded", HttpStatus.OK);
        }

    }

    public ResponseEntity<Object> getRecipeByName(String name) {
        try {
            Recipe recipe = repository.findByTitle(name).get();
            RecipeDTO recipeDTO = new RecipeDTO();
            recipeDTO.setId(recipe.getId());
            recipeDTO.setTitle(recipe.getTitle());
            recipeDTO.setDescription(recipe.getDescription());

            List<IngredientDTO> ingredientDTOs = new ArrayList<>();
            for (Ingredient ingredient : recipe.getIngredients()) {
                IngredientDTO ingredientDTO = new IngredientDTO();
                ingredientDTO.setName(ingredient.getName());
                ingredientDTO.setQuantity(ingredient.getQuantity());
                ingredientDTOs.add(ingredientDTO);
            }

            recipeDTO.setIngredients(ingredientDTOs);
            return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Recipe not founded", HttpStatus.OK);
        }
    }

    /*public List<Recipe> userRecipes(Integer id) {
        return repository.findAllByAuthorId(id);
    }*/

    public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                ingredient.setRecipe(recipe);
            }
        }

        Recipe savedRecipe = repository.save(recipe);

        return new ResponseEntity<Recipe>(savedRecipe, HttpStatus.CREATED);
    }







    /*public Recipe createRecipe(Recipe recipe) {if (recipeRepository.findByTitle(recipe.getTitle())) {
        throw new IllegalArgumentException("Recipe with the same name already exists");
    }
        return recipeRepository.save(recipe);
    }*/
}
