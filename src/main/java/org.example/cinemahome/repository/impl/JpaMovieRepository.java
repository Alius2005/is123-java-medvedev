package org.example.cinemahome.repository.impl;

import org.example.cinemahome.dto.MovieDto;
import org.example.cinemahome.pojo.Movie;
import org.example.cinemahome.repository.MovieRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaMovieRepository implements MovieRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MovieDto> findAll() {
        return entityManager.createQuery("SELECT m FROM Movie m", MovieDto.class).getResultList();
    }

    @Override
    public MovieDto findById(String id) {
        return entityManager.find(MovieDto.class, id);
    }

    @Override
    public void save(MovieDto movie) {
        entityManager.persist(movie);
    }

    @Override
    public void update(MovieDto movie) {
        entityManager.merge(movie);
    }

    @Override
    public void delete(String id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null) {
            entityManager.remove(movie);
        }
    }
}