package org.example.cinemahome.repository;

import org.example.cinemahome.dto.MovieDto;
import java.util.List;

public interface MovieRepository {
    List<MovieDto> findAll();
    MovieDto findById(String id);
    void save(MovieDto movie);
}