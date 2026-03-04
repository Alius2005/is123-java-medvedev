package kinoteka.repository;

import kinoteka.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMovieRepository extends JpaRepository<Movie, String>, MovieRepositoryCustom {
}