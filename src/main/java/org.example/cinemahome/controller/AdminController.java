package org.example.cinemahome.controller;

import org.example.cinemahome.service.MovieService;
import org.example.cinemahome.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/admin/movies")
    public String adminMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/form";
    }

    @PostMapping("/admin/movies")
    public String addMovie(MovieDto movie) {
        movieService.addMovie(movie);
        return "redirect:/admin/movies";
    }
}
