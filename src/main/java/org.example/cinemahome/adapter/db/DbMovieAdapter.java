package org.example.cinemahome.adapter.db;

import org.example.cinemahome.dto.MovieDto;
import org.example.cinemahome.pojo.Movie;
import org.example.cinemahome.port.MoviePort;
import org.example.cinemahome.repository.ActorRepository;
import org.example.cinemahome.repository.GenreRepository;
import org.example.cinemahome.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("dbMoviePort")
public class DbMovieAdapter implements MoviePort {

    @Autowired private MovieRepository movieRepository;
    @Autowired private GenreRepository genreRepository;
    @Autowired private ActorRepository actorRepository;

    @Override
    public List<MovieDto> findAll() {
        return movieRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto findById(Long id) {
        return movieRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public void save(MovieDto movieDto) {
        Movie movie = new Movie();
        if (movieDto.getId() != null) {
            movie.setId(movieDto.getId());
        }
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

    private MovieDto toDto(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setFilePath(movie.getFilePath());
        dto.setGenreIds(
                movie.getGenres().stream().map(g -> g.getId()).collect(Collectors.toList())
        );
        dto.setActorIds(
                movie.getActors().stream().map(a -> a.getId()).collect(Collectors.toList())
        );
        return dto;
    }
}