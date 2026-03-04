package kinoteka.service;

import kinoteka.model.User;
import kinoteka.model.UserDto;
import kinoteka.repository.UserRepositoryCustom;
import kinoteka.pattern.RepositoryFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepositoryCustom repo;
    private final PasswordEncoder encoder;

    public UserService(RepositoryFactory factory, PasswordEncoder encoder) {
        this.repo = factory.getUserRepository();
        this.encoder = encoder;
    }

    public User register(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRoles(dto.getRoles());
        return repo.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public List<User> findAll() {
        return repo.findAll();
    }
}
