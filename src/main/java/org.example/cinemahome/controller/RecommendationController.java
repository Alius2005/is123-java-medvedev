package org.example.cinemahome.controller;

import org.example.cinemahome.service.CurationService;
import org.example.cinemahome.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController {
    @Autowired
    private CurationService curationService;

    @GetMapping("/api/recommend")
    public MovieDto recommend() {
        return curationService.recommendForUser();
    }
}
