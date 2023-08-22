package recipe.diso.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe.diso.models.Label;
import recipe.diso.models.Recipe;
import recipe.diso.models.User;
import recipe.diso.repositories.UserRepository;
import recipe.diso.services.RecipeService;
import recipe.diso.services.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService service;
    private UserRepository urepo;

    @GetMapping
    public ResponseEntity<Object> getAllRecipes() {
        return service.allRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRecipeById(@PathVariable Long id) {
        return service.getRecipeById(id);
    }

    /*@GetMapping("/users/{id}")
    public ResponseEntity<List<Recipe>> getUserRecipe(@PathVariable Integer id) {
        try{
            User author = urepo.findById(id).get();
            List<Recipe> userRecipes = service.userRecipes(id);
            return new ResponseEntity<List<Recipe>>(userRecipes, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Recipe>>(HttpStatus.NOT_FOUND);
        }
    }*/

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getOneByName(@PathVariable String name) {
        return service.getRecipeByName(name);
    }

    @PostMapping("/save")
    public ResponseEntity<Recipe> addRecipe(@RequestBody @Valid Recipe recipe) {
        return service.saveRecipe(recipe);
    }
}
