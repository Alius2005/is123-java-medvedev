package org.example.cinemahome.controller;

import org.example.cinemahome.dto.SeriesDto;
import org.example.cinemahome.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminSeriesController {

    @Autowired
    private SeriesService seriesService;

    @GetMapping("/admin/series")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String adminSeries(Model model) {
        model.addAttribute("seriesList", seriesService.getAllSeries());
        return "admin/series-form";
    }

    @PostMapping("/admin/series")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String addSeries(SeriesDto dto) {
        seriesService.addSeries(dto);
        return "redirect:/admin/series";
    }
}