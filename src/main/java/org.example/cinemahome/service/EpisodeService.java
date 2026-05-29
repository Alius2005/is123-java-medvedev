package org.example.cinemahome.service;

import org.example.cinemahome.config.DataMode;
import org.example.cinemahome.config.DataModeService;
import org.example.cinemahome.dto.EpisodeDto;
import org.example.cinemahome.port.EpisodePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {

    @Autowired @Qualifier("dbEpisodePort")
    private EpisodePort dbEpisodePort;

    @Autowired @Qualifier("jsonEpisodePort")
    private EpisodePort jsonEpisodePort;

    @Autowired
    private DataModeService dataModeService;

    private EpisodePort currentPort() {
        return (dataModeService.getMode() == DataMode.JSON) ? jsonEpisodePort : dbEpisodePort;
    }

    public EpisodeDto getEpisodeById(Long id) {
        return currentPort().findById(id);
    }

    public EpisodeDto getNextEpisode(EpisodeDto current) {
        return currentPort().findNext(current);
    }

    public EpisodeDto getFirstEpisodeOfSeries(Long seriesId) {
        return currentPort().findFirstOfSeries(seriesId);
    }

    public EpisodeDto getPreviousEpisode(EpisodeDto current) {
        return currentPort().findPrevious(current);
    }

    public List<EpisodeDto> getEpisodesBySeasonId(Long seasonId) {
        return currentPort().findBySeasonId(seasonId);
    }
}
