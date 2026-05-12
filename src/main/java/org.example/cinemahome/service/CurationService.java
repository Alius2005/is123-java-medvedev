package org.example.cinemahome.service;

import org.example.cinemahome.repository.MovieRepository;
import org.example.cinemahome.dto.MovieDto;
import org.example.cinemahome.util.MoodAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CurationService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MoodAnalyzer moodAnalyzer;

    public MovieDto recommendForToday() {
        List<MovieDto> movies = movieRepository.findAll().stream()
                .map(movie -> new MovieDto(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getDescription(),
                        moodAnalyzer.analyzeMood(),
                        movie.getGenres().stream().map(g -> g.getId()).collect(Collectors.toList()),
                        movie.getActors().stream().map(a -> a.getId()).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        if (movies.isEmpty()) {
            return new MovieDto(null, "No movies yet", "Add something to the catalog", "Bored", List.of(), List.of());
        }

        return movies.get(new Random().nextInt(movies.size()));
    }

    public MovieDto recommendForUser() {
        return recommendForToday();
    }
}
