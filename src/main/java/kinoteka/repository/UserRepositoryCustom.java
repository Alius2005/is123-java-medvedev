package kinoteka.repository;

import kinoteka.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    void deleteById(Long id);
}
