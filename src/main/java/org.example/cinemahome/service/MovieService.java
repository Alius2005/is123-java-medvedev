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
                .map(movie -> new MovieDto(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getDescription(),
                        null,
                        movie.getGenres().stream().map(g -> g.getId()).collect(Collectors.toList()),
                        movie.getActors().stream().map(a -> a.getId()).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public MovieDto getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(movie -> new MovieDto(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getDescription(),
                        null,
                        movie.getGenres().stream().map(g -> g.getId()).collect(Collectors.toList()),
                        movie.getActors().stream().map(a -> a.getId()).collect(Collectors.toList())
                ))
                .orElse(null);
    }

    public void addMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());

        if (movieDto.getGenreIds() != null) {
            movie.getGenres().addAll(
                    genreRepository.findAllById(movieDto.getGenreIds())
            );
        }

        if (movieDto.getActorIds() != null) {
            movie.getActors().addAll(
                    actorRepository.findAllById(movieDto.getActorIds())
            );
        }

        movieRepository.save(movie);
    }
}
