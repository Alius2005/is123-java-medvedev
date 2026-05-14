package org.example.cinemahome.port;

import org.example.cinemahome.dto.EpisodeDto;

import java.util.List;

public interface EpisodePort {
    EpisodeDto findById(Long id);
    EpisodeDto findNext(EpisodeDto current);
    EpisodeDto findFirstOfSeries(Long seriesId);

    List<EpisodeDto> findBySeasonId(Long seasonId);
}
