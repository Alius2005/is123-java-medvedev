package org.example.cinemahome.adapter.db;

import org.example.cinemahome.pojo.Season;
import org.example.cinemahome.port.SeasonPort;
import org.example.cinemahome.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dbSeasonPort")
public class DbSeasonAdapter implements SeasonPort {

    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    public List<Season> findBySeriesId(Long seriesId) {
        return seasonRepository.findAll().stream()
                .filter(s -> s.getSeries() != null && seriesId.equals(s.getSeries().getId()))
                .toList();
    }
}