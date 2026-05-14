package org.example.cinemahome.service;

import org.example.cinemahome.config.DataMode;
import org.example.cinemahome.config.DataModeService;
import org.example.cinemahome.dto.GenreDto;
import org.example.cinemahome.port.GenrePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired @Qualifier("dbGenrePort")
    private GenrePort dbGenrePort;

    @Autowired @Qualifier("jsonGenrePort")
    private GenrePort jsonGenrePort;

    @Autowired
    private DataModeService dataModeService;

    private GenrePort currentPort() {
        return (dataModeService.getMode() == DataMode.JSON) ? jsonGenrePort : dbGenrePort;
    }

    public List<GenreDto> getAllGenres() {
        return currentPort().findAll();
    }

    public void addGenre(GenreDto dto) {
        currentPort().save(dto);
    }
}