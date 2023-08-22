package recipe.diso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe.diso.dto.IngredientDTO;
import recipe.diso.dto.RecipeDTO;
import recipe.diso.models.Ingredient;
import recipe.diso.services.IngredientService;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRecipeById(@PathVariable Long id) {
        return ingredientService.getIngredientById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getRecipeByName(@PathVariable String name) {
        return ingredientService.getIngredientByName(name);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveIngredient(@RequestBody Ingredient ingredient) {
        Ingredient saved = ingredientService.saveIngredient(ingredient);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

}
