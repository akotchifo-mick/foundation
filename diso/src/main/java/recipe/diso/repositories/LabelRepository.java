package recipe.diso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.diso.models.Label;

import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label, Long> {
    Optional<Label> findByName(String name);
}

