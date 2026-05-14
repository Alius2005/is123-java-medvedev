package org.example.cinemahome.port;

import org.example.cinemahome.pojo.Season;

import java.util.List;

public interface SeasonPort {
    List<Season> findBySeriesId(Long seriesId);
}