package org.example.cinemahome.repository.impl;

import org.example.cinemahome.repository.GenreRepository;
import org.example.cinemahome.dto.GenreDto;
import org.example.cinemahome.factory.RepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaGenreRepository implements GenreRepository {
    @Autowired
    private RepositoryFactory factory;

    @Override
    public List<GenreDto> findAll() {
        return factory.createJsonGenreRepository().findAll();
    }
}
