package recipe.diso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import recipe.diso.dto.RecipeDTO;
import recipe.diso.dto.UserDTO;
import recipe.diso.models.Recipe;
import recipe.diso.models.User;
import recipe.diso.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<Object> getAllUsers() {
        List<User> dbUsers = userRepository.findAll();
        if (dbUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : dbUsers) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());

            List<RecipeDTO> recipeDTOs = new ArrayList<>();
            for (Recipe recipe : user.getRecipes()) {
                RecipeDTO recipeDTO = new RecipeDTO();
                //recipeDTO.setId(recipe.getId());
                recipeDTO.setTitle(recipe.getTitle());
                recipeDTO.setDescription(recipe.getDescription());
                recipeDTOs.add(recipeDTO);
            }

            userDTO.setRecipes(recipeDTOs);

            userDTOs.add(userDTO);
        }

        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }


    public ResponseEntity<Object> getOneUser(Integer id) {
        User user = userRepository.findById(id).get();
        if (user != null) {
            return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
