package recipe.diso.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import recipe.diso.models.Label;
import recipe.diso.services.LabelService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/labels")
public class LabelController {
    @Autowired
    private LabelService service;

    @GetMapping
    public ResponseEntity<List<Label>> getAllLabels() {
        return new ResponseEntity<List<Label>>(service.allLabels(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Label> getOneLabel(@PathVariable Long id) {
        try{
            Label label = service.oneLabel(id);
            return new ResponseEntity<Label>(label, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Label>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Label> getLabelByName(@PathVariable String name) {
        try {
            Label label = service.labelByName(name);
            return new ResponseEntity<Label>(label, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Label>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> add(@RequestBody  @Valid  Label label) {
        return service.save(label);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update (@RequestBody Label label, @PathVariable Long id) {
        return service.update(label, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete (@PathVariable Long id){
        return service.delete(id);
    }
}
