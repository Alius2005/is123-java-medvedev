package org.example.cinemahome.controller;

import org.example.cinemahome.dto.MovieDto;
import org.example.cinemahome.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlayerController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/player/movie/{id}")
    public String playMovie(@PathVariable Long id, Model model) {
        MovieDto movie = movieService.getMovieById(id);
        if (movie == null || movie.getFilePath() == null || movie.getFilePath().isBlank()) {
            return "error";
        }
        model.addAttribute("movie", movie);
        return "player/movie-player";
    }
}