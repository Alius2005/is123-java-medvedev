package org.example.cinemahome.service;

import org.example.cinemahome.config.DataMode;
import org.example.cinemahome.config.DataModeService;
import org.example.cinemahome.dto.SeriesDto;
import org.example.cinemahome.port.SeriesPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {

    @Autowired @Qualifier("dbSeriesPort")
    private SeriesPort dbSeriesPort;

    @Autowired @Qualifier("jsonSeriesPort")
    private SeriesPort jsonSeriesPort;

    @Autowired
    private DataModeService dataModeService;

    private SeriesPort currentPort() {
        return (dataModeService.getMode() == DataMode.JSON) ? jsonSeriesPort : dbSeriesPort;
    }

    public List<SeriesDto> getAllSeries() {
        return currentPort().findAll();
    }

    public SeriesDto getSeriesById(Long id) {
        return currentPort().findById(id);
    }

    public void addSeries(SeriesDto dto) {
        currentPort().saveSeriesWithStructure(dto);
    }
}