package kinoteka.repository;

import kinoteka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByUsername(String username);
}
