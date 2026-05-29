package org.example.cinemahome.controller;

import org.example.cinemahome.service.*;
import org.example.cinemahome.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired private MovieService movieService;
    @Autowired private GenreService genreService;
    @Autowired private ActorService actorService;
    @Autowired private DirectorService directorService;

    @GetMapping("/admin/movies")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String adminMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/form";
    }

    @PostMapping("/admin/movies")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String addMovie(MovieDto movie) {
        movieService.addMovie(movie);
        return "redirect:/admin/movies";
    }

    @GetMapping("/admin/genres")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String adminGenres(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "admin/genre-form";
    }

    @PostMapping("/admin/genres")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String addGenre(GenreDto genre) {
        genreService.addGenre(genre);
        return "redirect:/admin/genres";
    }

    @GetMapping("/admin/actors")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String adminActors(Model model) {
        model.addAttribute("actors", actorService.getAllActors());
        return "admin/actor-form";
    }

    @PostMapping("/admin/actors")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String addActor(ActorDto actor) {
        actorService.addActor(actor);
        return "redirect:/admin/actors";
    }

    @GetMapping("/admin/directors")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String adminDirectors(Model model) {
        model.addAttribute("directors", directorService.getAllDirectors());
        return "admin/director-form";
    }

    @PostMapping("/admin/directors")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String addDirector(DirectorDto director) {
        directorService.addDirector(director);
        return "redirect:/admin/directors";
    }
}