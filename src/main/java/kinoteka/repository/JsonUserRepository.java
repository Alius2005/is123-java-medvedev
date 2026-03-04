package kinoteka.repository;

import kinoteka.model.User;
import kinoteka.util.JsonUtil;
import java.io.File;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JsonUserRepository implements UserRepositoryCustom {

    private final File file = new File("users.json");
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private List<User> load() {
        if (!file.exists()) return new ArrayList<>();
        return JsonUtil.fromJson(file, List.class);
    }

    private void saveAll(List<User> users) {
        JsonUtil.toJson(file, users);
    }

    @Override
    public User save(User user) {
        lock.writeLock().lock();
        try {
            List<User> users = load();
            if (user.getId() == null) {
                user.setId(System.currentTimeMillis());
            } else {
                users.removeIf(u -> u.getId().equals(user.getId()));
            }
            users.add(user);
            saveAll(users);
            return user;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        lock.readLock().lock();
        try {
            return load().stream().filter(u -> u.getId().equals(id)).findFirst();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        lock.readLock().lock();
        try {
            return load().stream().filter(u -> u.getUsername().equals(username)).findFirst();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<User> findAll() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(load());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void deleteById(Long id) {
        lock.writeLock().lock();
        try {
            List<User> users = load();
            users.removeIf(u -> u.getId().equals(id));
            saveAll(users);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
