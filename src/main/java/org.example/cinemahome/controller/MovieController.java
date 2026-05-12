package org.example.cinemahome.controller;

import org.example.cinemahome.service.MovieService;
import org.example.cinemahome.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MovieController {

    @Autowired private MovieService movieService;

    @GetMapping("/movies")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movie/list";
    }

    @GetMapping("/movies/{id}")
    public String getMovie(@PathVariable String id, Model model) {
        try {
            Long movieId = Long.parseLong(id);
            MovieDto movie = movieService.getMovieById(movieId);
            if (movie == null) {
                return "error/404";
            }
            model.addAttribute("movie", movie);
            return "movie/detail";
        } catch (NumberFormatException e) {
            return "error/404";
        }
    }
}
