package recipe.diso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import recipe.diso.models.User;
import recipe.diso.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return userService.getAllUsers();
    }

    /*public List<User> list() {
        return userService.getAllUsers();
    }*/

    @GetMapping("{id}")
    public ResponseEntity<Object> getOne(@PathVariable Integer id) {
        return userService.getOneUser(id);
    }


}
