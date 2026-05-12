package org.example.cinemahome.service;

import org.example.cinemahome.repository.GenreRepository;
import org.example.cinemahome.dto.GenreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired private GenreRepository genreRepository;

    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(genre -> new GenreDto(
                        genre.getId(),
                        genre.getName(),
                        genre.getDescription()
                ))
                .collect(Collectors.toList());
    }
}
