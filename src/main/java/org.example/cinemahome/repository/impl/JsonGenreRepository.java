package org.example.cinemahome.repository.impl;

import org.example.cinemahome.repository.GenreRepository;
import org.example.cinemahome.dto.GenreDto;
import org.example.cinemahome.config.ApplicationProperties;
import org.example.cinemahome.util.JsonDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JsonGenreRepository implements GenreRepository {
    @Autowired
    private ApplicationProperties properties;

    @Override
    public List<GenreDto> findAll() {
        return JsonDataLoader.loadGenres(properties.getDataPath());
    }
}
