package org.example.cinemahome.controller;

import org.example.cinemahome.pojo.Series;
import org.example.cinemahome.pojo.Season;
import org.example.cinemahome.pojo.Episode;
import org.example.cinemahome.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SeriesEpisodesController {

    @Autowired
    private SeriesRepository seriesRepository;

    @GetMapping("/series/{id}/episodes")
    public String listEpisodes(@PathVariable Long id, Model model) {
        Series series = seriesRepository.findById(id).orElse(null);
        if (series == null) {
            return "redirect:/series";
        }

        Season season = (series.getSeasons() != null && !series.getSeasons().isEmpty())
                ? series.getSeasons().get(0)
                : null;

        if (season == null || season.getEpisodes() == null || season.getEpisodes().isEmpty()) {
            model.addAttribute("series", series);
            model.addAttribute("episodes", java.util.List.of());
            return "series/episodes";
        }

        model.addAttribute("series", series);
        model.addAttribute("episodes", season.getEpisodes());
        return "series/episodes";
    }
}