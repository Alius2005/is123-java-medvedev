package org.example.cinemahome.repository.impl;

import org.example.cinemahome.repository.MovieRepository;
import org.example.cinemahome.dto.MovieDto;
import org.example.cinemahome.factory.RepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaMovieRepository implements MovieRepository {
    @Autowired
    private RepositoryFactory factory;

    @Override
    public List<MovieDto> findAll() {
        return factory.createJsonMovieRepository().findAll();
    }

    @Override
    public MovieDto findById(String id) {
        return factory.createJsonMovieRepository().findById(id);
    }

    @Override
    public void save(MovieDto movie) {
        factory.createJsonMovieRepository().save(movie);
    }
}
