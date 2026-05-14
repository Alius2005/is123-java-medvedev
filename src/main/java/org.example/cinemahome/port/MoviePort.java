package org.example.cinemahome.port;

import org.example.cinemahome.dto.MovieDto;

import java.util.List;

public interface MoviePort {
    List<MovieDto> findAll();
    MovieDto findById(Long id);
    void save(MovieDto movie);
}