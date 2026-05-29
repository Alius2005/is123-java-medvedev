package org.example.cinemahome.adapter.db;

import org.example.cinemahome.dto.EpisodeDto;
import org.example.cinemahome.pojo.Episode;
import org.example.cinemahome.port.EpisodePort;
import org.example.cinemahome.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dbEpisodePort")
public class DbEpisodeAdapter implements EpisodePort {

    @Autowired
    private EpisodeRepository episodeRepository;

    private EpisodeDto toDto(Episode e) {
        return new EpisodeDto(
                e.getId(),
                e.getTitle(),
                e.getFilePath(),
                e.getEpisodeNumber(),
                e.getSeason().getId()
        );
    }

    @Override
    public EpisodeDto findById(Long id) {
        return episodeRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public EpisodeDto findNext(EpisodeDto current) {
        if (current == null || current.getSeasonId() == null || current.getEpisodeNumber() == null) {
            return null;
        }
        return episodeRepository
                .findBySeason_IdAndEpisodeNumber(
                        current.getSeasonId(),
                        current.getEpisodeNumber() + 1
                )
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public EpisodeDto findFirstOfSeries(Long seriesId) {
        return episodeRepository
                .findFirstBySeason_Series_IdOrderByEpisodeNumberAsc(seriesId)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public List<EpisodeDto> findBySeasonId(Long seasonId) {
        return episodeRepository.findAll().stream()
                .filter(e -> e.getSeason() != null && seasonId.equals(e.getSeason().getId()))
                .map(this::toDto)
                .toList();
    }

    @Override
    public EpisodeDto findPrevious(EpisodeDto current) {
        if (current == null || current.getSeasonId() == null || current.getEpisodeNumber() == null || current.getEpisodeNumber() <= 1) {
            return null;
        }
        return episodeRepository
                .findBySeason_IdAndEpisodeNumber(
                        current.getSeasonId(),
                        current.getEpisodeNumber() - 1
                )
                .map(this::toDto)
                .orElse(null);
    }
}