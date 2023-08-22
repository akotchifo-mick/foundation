package recipe.diso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.diso.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
