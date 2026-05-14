package org.example.cinemahome.port;

import org.example.cinemahome.dto.GenreDto;

import java.util.List;

public interface GenrePort {
    List<GenreDto> findAll();
    void save(GenreDto genre);
}