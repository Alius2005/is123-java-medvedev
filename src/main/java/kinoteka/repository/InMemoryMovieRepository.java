package kinoteka.repository;

import kinoteka.model.Movie;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryMovieRepository implements MovieRepositoryCustom {

    private final Map<String, Movie> store = new ConcurrentHashMap<>();

    @Override
    public Movie save(Movie movie) {
        store.put(movie.getId(), movie);
        return movie;
    }

    @Override
    public Optional<Movie> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Movie> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }
}
