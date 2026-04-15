package org.example.cinemahome.service;

import org.example.cinemahome.repository.MovieRepository;
import org.example.cinemahome.dto.MovieDto;
import org.example.cinemahome.util.MoodAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CurationService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MoodAnalyzer moodAnalyzer;

    public MovieDto recommendForToday() {
        List<MovieDto> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            // Пустой каталог — возвращаем «заглушку» или бросаем своё исключение
            return new MovieDto("No movies yet", "Add something to the catalog", "Bored");
        }
        MovieDto movie = movies.get(new Random().nextInt(movies.size()));
        movie.setMoodTag(moodAnalyzer.analyzeMood());
        return movie;
    }

    public MovieDto recommendForUser() {
        return recommendForToday();
    }
}

