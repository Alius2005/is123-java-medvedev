package org.example.cinemahome.controller;

import org.example.cinemahome.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @GetMapping("/series")
    public String listSeries(Model model) {
        model.addAttribute("seriesList", seriesService.getAllSeries());
        return "series/list";
    }
}