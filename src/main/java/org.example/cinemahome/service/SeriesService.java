package org.example.cinemahome.service;

import org.example.cinemahome.dto.SeriesDto;
import org.example.cinemahome.pojo.Series;
import org.example.cinemahome.pojo.Season;
import org.example.cinemahome.pojo.Episode;
import org.example.cinemahome.repository.SeriesRepository;
import org.example.cinemahome.repository.SeasonRepository;
import org.example.cinemahome.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    public List<SeriesDto> getAllSeries() {
        return seriesRepository.findAll().stream()
                .map(s -> {
                    SeriesDto dto = new SeriesDto();
                    dto.setId(s.getId());
                    dto.setTitle(s.getTitle());
                    dto.setDescription(s.getDescription());
                    // folder/seasonNumber/episodesCount здесь не нужны
                    return dto;
                })
                .collect(Collectors.toList());
    }
    public void addSeries(SeriesDto dto) {
        // 1. Сериал
        Series s = new Series();
        s.setTitle(dto.getTitle());
        s.setDescription(dto.getDescription());
        s.setStatus("released");
        s.setIsWatched(false);

        Integer seasonNumber = (dto.getSeasonNumber() != null) ? dto.getSeasonNumber() : 1;
        Integer episodesCount = (dto.getEpisodesCount() != null && dto.getEpisodesCount() > 0)
                ? dto.getEpisodesCount()
                : 1;

        s.setSeasonsCount(1);
        s.setEpisodesCount(episodesCount);
        seriesRepository.save(s);

        // 2. Сезон
        Season season = new Season();
        season.setSeries(s);
        season.setSeasonNumber(seasonNumber);
        season.setEpisodesCount(episodesCount);
        season.setStatus("released");
        season.setIsWatched(false);
        season.setTitle(seasonNumber + " сезон");
        seasonRepository.save(season);

        // 3. ЭПИЗОДЫ ПО ЦИКЛУ
        String folder = dto.getFolder(); // "жуки", например

        for (int epNum = 1; epNum <= episodesCount; epNum++) {
            Episode ep = new Episode();
            ep.setSeason(season);
            ep.setEpisodeNumber(epNum);
            ep.setTitle(epNum + " серия");
            ep.setIsWatched(false);

            // путь: жуки/4 сезон/1 серия.mp4, жуки/4 сезон/2 серия.mp4, ...
            ep.setFilePath(folder + "/" + seasonNumber + " сезон/" + epNum + " серия.mp4");

            episodeRepository.save(ep);
        }
    }
}