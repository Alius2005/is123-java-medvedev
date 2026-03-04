package kinoteka.repository;

import kinoteka.model.Movie;
import kinoteka.util.JsonUtil;
import java.io.File;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JsonMovieRepository implements MovieRepositoryCustom {

    private final File file = new File("movies.json");
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private List<Movie> load() {
        if (!file.exists()) return new ArrayList<>();
        return JsonUtil.fromJson(file, List.class);
    }

    private void saveAll(List<Movie> movies) {
        JsonUtil.toJson(file, movies);
    }

    @Override
    public Movie save(Movie movie) {
        lock.writeLock().lock();
        try {
            List<Movie> movies = load();
            movies.removeIf(m -> m.getId().equals(movie.getId()));
            movies.add(movie);
            saveAll(movies);
            return movie;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<Movie> findById(String id) {
        lock.readLock().lock();
        try {
            return load().stream().filter(m -> m.getId().equals(id)).findFirst();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Movie> findAll() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(load());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void deleteById(String id) {
        lock.writeLock().lock();
        try {
            List<Movie> movies = load();
            movies.removeIf(m -> m.getId().equals(id));
            saveAll(movies);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
