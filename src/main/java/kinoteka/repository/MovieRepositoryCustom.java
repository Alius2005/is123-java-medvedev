package kinoteka.repository;

import kinoteka.model.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieRepositoryCustom {
    Movie save(Movie movie);
    Optional<Movie> findById(String id);
    List<Movie> findAll();
    void deleteById(String id);
}
