package org.example.cinemahome.service;

import org.example.cinemahome.dto.EpisodeDto;
import org.example.cinemahome.pojo.Episode;
import org.example.cinemahome.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpisodeService {

    @Autowired
    private EpisodeRepository episodeRepository;

    public EpisodeDto getEpisodeById(Long id) {
        return episodeRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public EpisodeDto getNextEpisode(EpisodeDto current) {
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

    private EpisodeDto toDto(Episode e) {
        return new EpisodeDto(
                e.getId(),
                e.getTitle(),
                e.getFilePath(),
                e.getEpisodeNumber(),
                e.getSeason().getId()
        );
    }

    public EpisodeDto getFirstEpisodeOfSeries(Long seriesId) {
        return episodeRepository
                .findFirstBySeason_Series_IdOrderByEpisodeNumberAsc(seriesId)
                .map(this::toDto)
                .orElse(null);
    }
}