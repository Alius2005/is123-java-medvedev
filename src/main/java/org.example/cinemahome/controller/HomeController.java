package org.example.cinemahome.controller;

import org.example.cinemahome.service.CurationService;
import org.example.cinemahome.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private CurationService curationService;

    @GetMapping("/")
    public String home(Model model) {
        MovieDto movie = curationService.recommendForToday();
        model.addAttribute("movie", movie);
        return "index";
    }
}
