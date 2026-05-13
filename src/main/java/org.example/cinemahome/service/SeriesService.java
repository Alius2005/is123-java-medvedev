package org.example.cinemahome.service;

import org.example.cinemahome.dto.SeriesDto;
import org.example.cinemahome.pojo.Series;
import org.example.cinemahome.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    public List<SeriesDto> getAllSeries() {
        return seriesRepository.findAll().stream()
                .map(s -> new SeriesDto(
                        s.getId(),
                        s.getTitle(),
                        s.getDescription(),
                        s.getFilePath()
                ))
                .collect(Collectors.toList());
    }

    public void addSeries(SeriesDto dto) {
        Series s = new Series();
        s.setTitle(dto.getTitle());
        s.setDescription(dto.getDescription());
        s.setFilePath(dto.getFilePath());
        seriesRepository.save(s);
    }
}