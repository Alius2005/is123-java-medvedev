package org.example.cinemahome.adapter.db;

import org.example.cinemahome.dto.SeriesDto;
import org.example.cinemahome.pojo.Series;
import org.example.cinemahome.pojo.Season;
import org.example.cinemahome.pojo.Episode;
import org.example.cinemahome.port.SeriesPort;
import org.example.cinemahome.repository.SeriesRepository;
import org.example.cinemahome.repository.SeasonRepository;
import org.example.cinemahome.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("dbSeriesPort")
public class DbSeriesAdapter implements SeriesPort {

    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private EpisodeRepository episodeRepository;

    @Override
    public List<SeriesDto> findAll() {
        return seriesRepository.findAll().stream()
                .map(s -> {
                    SeriesDto dto = new SeriesDto();
                    dto.setId(s.getId());
                    dto.setTitle(s.getTitle());
                    dto.setDescription(s.getDescription());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public SeriesDto findById(Long id) {
        return seriesRepository.findById(id)
                .map(s -> {
                    SeriesDto dto = new SeriesDto();
                    dto.setId(s.getId());
                    dto.setTitle(s.getTitle());
                    dto.setDescription(s.getDescription());
                    return dto;
                })
                .orElse(null);
    }

    @Override
    public void saveSeriesWithStructure(SeriesDto dto) {
        // 1. сериал
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
        Series savedSeries = seriesRepository.save(s);

        // 2. сезон
        Season season = new Season();
        season.setSeries(savedSeries);
        season.setSeasonNumber(seasonNumber);
        season.setEpisodesCount(episodesCount);
        season.setStatus("released");
        season.setIsWatched(false);
        season.setTitle(seasonNumber + " сезон");
        Season savedSeason = seasonRepository.save(season);

        // 3. эпизоды
        String folder = dto.getFolder();

        for (int epNum = 1; epNum <= episodesCount; epNum++) {
            Episode ep = new Episode();
            ep.setSeason(savedSeason);
            ep.setEpisodeNumber(epNum);
            ep.setTitle(epNum + " серия");
            ep.setIsWatched(false);
            ep.setFilePath(folder + "/" + seasonNumber + " сезон/" + epNum + " серия.mp4");
            episodeRepository.save(ep);
        }
    }
}