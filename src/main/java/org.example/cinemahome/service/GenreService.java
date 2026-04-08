package org.example.cinemahome.service;

import org.example.cinemahome.repository.GenreRepository;
import org.example.cinemahome.dto.GenreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll();
    }
}
