package org.example.cinemahome.controller;

import org.example.cinemahome.dto.EpisodeDto;
import org.example.cinemahome.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SeriesPlayerController {

    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/player/series/{episodeId}")
    public String playEpisode(@PathVariable Long episodeId, Model model) {
        EpisodeDto episode = episodeService.getEpisodeById(episodeId);
        if (episode == null || episode.getFilePath() == null || episode.getFilePath().isBlank()) {
            return "error";
        }

        EpisodeDto next = episodeService.getNextEpisode(episode);

        model.addAttribute("episode", episode);
        model.addAttribute("nextEpisode", next);

        return "player/series-player";
    }
}