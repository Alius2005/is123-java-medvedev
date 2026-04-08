package org.example.cinemahome.service;

import org.example.cinemahome.repository.MovieRepository;
import org.example.cinemahome.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll();
    }

    public MovieDto getMovieById(String id) {
        return movieRepository.findById(id);
    }

    public void addMovie(MovieDto movie) {
        movieRepository.save(movie);
    }
}
