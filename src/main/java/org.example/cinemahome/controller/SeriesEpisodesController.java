package org.example.cinemahome.controller;

import org.example.cinemahome.config.DataMode;
import org.example.cinemahome.config.DataModeService;
import org.example.cinemahome.dto.EpisodeDto;
import org.example.cinemahome.dto.SeriesDto;
import org.example.cinemahome.pojo.Season;
import org.example.cinemahome.port.SeasonPort;
import org.example.cinemahome.service.EpisodeService;
import org.example.cinemahome.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;

@Controller
public class SeriesEpisodesController {

    @Autowired @Qualifier("dbSeasonPort")
    private SeasonPort dbSeasonPort;

    @Autowired @Qualifier("jsonSeasonPort")
    private SeasonPort jsonSeasonPort;

    @Autowired
    private DataModeService dataModeService;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private EpisodeService episodeService;

    private SeasonPort currentSeasonPort() {
        return (dataModeService.getMode() == DataMode.JSON) ? jsonSeasonPort : dbSeasonPort;
    }

    @GetMapping("/series/{id}/episodes")
    public String listEpisodes(@PathVariable Long id, Model model) {
        SeriesDto series = seriesService.getSeriesById(id);
        if (series == null) {
            return "redirect:/series";
        }

        List<Season> seasons = currentSeasonPort().findBySeriesId(id);
        if (seasons == null || seasons.isEmpty()) {
            model.addAttribute("series", series);
            model.addAttribute("episodes", List.of());
            return "series/episodes";
        }

        seasons.sort(Comparator.comparingInt(Season::getSeasonNumber));
        Season season = seasons.get(0);

        List<EpisodeDto> episodes = episodeService.getEpisodesBySeasonId(season.getId());

        model.addAttribute("series", series);
        model.addAttribute("episodes", episodes);
        return "series/episodes";
    }
}