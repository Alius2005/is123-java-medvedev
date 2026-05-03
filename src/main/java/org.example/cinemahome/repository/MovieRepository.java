package org.example.cinemahome.repository;

import org.example.cinemahome.dto.MovieDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface MovieRepository {
    List<MovieDto> findAll();
    MovieDto findById(String id);
    void save(MovieDto movie);
    void update(MovieDto movie);
    void delete(String id);
}