package org.example.cinemahome.port;

import org.example.cinemahome.dto.SeriesDto;

import java.util.List;

public interface SeriesPort {
    List<SeriesDto> findAll();
    SeriesDto findById(Long id);
    void saveSeriesWithStructure(SeriesDto dto); // сериал + сезон + эпизоды
}