package recipe.diso.services;

import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import recipe.diso.models.Label;
import recipe.diso.repositories.LabelRepository;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LabelService {
    @Autowired
    private LabelRepository repository;
    @Autowired
    private JdbcTemplate template;

    /*public ResponseEntity<Object> getAll() {
        List<Label> labels = repository.findAll();
        labels.forEach(label -> label.setName(label.getName().toUpperCase()));
        if (labels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(labels, HttpStatus.OK);
    }*/

    public List<Label> allLabels() {
        return repository.findAll();
    }

    public Label labelByName(String name) {
       return repository.findByName(name).get();
    }

    public Label oneLabel(Long id) {
        return repository.findById(id).get();
    }

    public ResponseEntity<Object> save(Label label) {
        /*if (repository.findByName(label.getName()) != null)
            throw new IllegalArgumentException("Label with the same name already exists");*/
        Label savedLabel = repository.save(label);
        return new ResponseEntity<>(savedLabel, HttpStatus.OK);
    }

    public ResponseEntity<Object> update(Label label, Long id) {
        try{
            Label exlistLabel = repository.findById(id).get();
            repository.save(label);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<Object> delete(Long id) {
        try{
            Label label = repository.findById(id).get();
            repository.delete(label);
            return new ResponseEntity<>("Label successfully deleted", HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>("Label does not exists", HttpStatus.NOT_FOUND);
        }
    }

}
