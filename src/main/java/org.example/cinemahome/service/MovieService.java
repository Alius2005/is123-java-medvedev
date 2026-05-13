package org.example.cinemahome.service;

import org.example.cinemahome.repository.ActorRepository;
import org.example.cinemahome.repository.GenreRepository;
import org.example.cinemahome.repository.MovieRepository;
import org.example.cinemahome.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.cinemahome.pojo.Movie;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired private MovieRepository movieRepository;
    @Autowired private GenreRepository genreRepository;
    @Autowired private ActorRepository actorRepository;

    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(movie -> {
                    MovieDto dto = new MovieDto();
                    dto.setId(movie.getId());
                    dto.setTitle(movie.getTitle());
                    dto.setDescription(movie.getDescription());
                    dto.setFilePath(movie.getFilePath()); // важно
                    // moodTag не знаем – оставляем null
                    dto.setGenreIds(
                            movie.getGenres().stream().map(g -> g.getId()).collect(Collectors.toList())
                    );
                    dto.setActorIds(
                            movie.getActors().stream().map(a -> a.getId()).collect(Collectors.toList())
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public MovieDto getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(movie -> {
                    MovieDto dto = new MovieDto();
                    dto.setId(movie.getId());
                    dto.setTitle(movie.getTitle());
                    dto.setDescription(movie.getDescription());
                    dto.setFilePath(movie.getFilePath()); // важно
                    dto.setGenreIds(
                            movie.getGenres().stream().map(g -> g.getId()).collect(Collectors.toList())
                    );
                    dto.setActorIds(
                            movie.getActors().stream().map(a -> a.getId()).collect(Collectors.toList())
                    );
                    return dto;
                })
                .orElse(null);
    }

    public void addMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setFilePath(movieDto.getFilePath());

        if (movieDto.getGenreIds() != null) {
            movie.setGenres(
                    genreRepository.findAllById(movieDto.getGenreIds())
            );
        }

        if (movieDto.getActorIds() != null) {
            movie.setActors(
                    actorRepository.findAllById(movieDto.getActorIds())
            );
        }

        movieRepository.save(movie);
    }
}
