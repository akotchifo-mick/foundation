package recipe.diso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import recipe.diso.dto.IngredientDTO;
import recipe.diso.models.Ingredient;
import recipe.diso.models.Label;
import recipe.diso.repositories.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
        List<Ingredient> dbIngredients = repository.findAll();
        List<IngredientDTO> ingredientDTOS = new ArrayList<>();
        for (Ingredient ingredient : dbIngredients) {
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setName(ingredient.getName());
            ingredientDTO.setQuantity(ingredient.getQuantity());

            ingredientDTOS.add(ingredientDTO);
        }
        return new ResponseEntity<List<IngredientDTO>>(ingredientDTOS,HttpStatus.OK);
    }

    public ResponseEntity<Object> getIngredientById(Long id) {
        try{
            Ingredient ingredient = repository.findById(id).get();
            return new ResponseEntity<>(ingredient, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Ingredient does not exist", HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> getIngredientByName(String name) {
        try{
            Ingredient ingredient = repository.findByName(name).get();
            return new ResponseEntity<>(ingredient, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Ingredient does not exist", HttpStatus.NOT_FOUND);
        }
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public ResponseEntity<String> deleteIngredient(Long id) {
        try{
            Ingredient ingredient = repository.findById(id).get();
            repository.delete(ingredient);
            return new ResponseEntity<>("Ingredient successfully deleted", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Ingredient does not exist", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Ingredient> updateIngredient(Long id, Ingredient updatedIngredient) {
        try{
           Ingredient ingredient = repository.findById(id).get();
            repository.save(ingredient);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
