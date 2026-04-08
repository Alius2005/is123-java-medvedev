package org.example.cinemahome.controller;

import org.example.cinemahome.service.GenreService;
import org.example.cinemahome.dto.GenreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    public String listGenres(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "genre/list";
    }
}
