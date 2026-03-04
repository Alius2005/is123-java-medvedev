package kinoteka.repository;

import kinoteka.model.User;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryUserRepository implements UserRepositoryCustom {

    private final Map<Long, User> store = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(System.currentTimeMillis());
        }
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return store.values().stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
