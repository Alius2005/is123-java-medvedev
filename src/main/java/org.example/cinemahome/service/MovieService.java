package org.example.cinemahome.service;

import org.example.cinemahome.config.DataMode;
import org.example.cinemahome.config.DataModeService;
import org.example.cinemahome.dto.MovieDto;
import org.example.cinemahome.port.MoviePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Service
public class MovieService {

    @Autowired @Qualifier("dbMoviePort")
    private MoviePort dbMovieAdapter;
    @Autowired @Qualifier("jsonMoviePort")
    private MoviePort jsonMovieAdapter;
    @Autowired
    private DataModeService dataModeService;

    private MoviePort currentPort() {
        DataMode mode = dataModeService.getMode();
        if (mode == DataMode.JSON) {
            return jsonMovieAdapter;
        } else {
            return dbMovieAdapter;
        }
    }

    public List<MovieDto> getAllMovies() {
        return currentPort().findAll();
    }

    public MovieDto getMovieById(Long id) {
        return currentPort().findById(id);
    }

    public void addMovie(MovieDto movieDto) {
        currentPort().save(movieDto);
    }
}