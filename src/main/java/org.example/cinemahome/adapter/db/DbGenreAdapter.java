package org.example.cinemahome.adapter.db;

import org.example.cinemahome.dto.GenreDto;
import org.example.cinemahome.pojo.Genre;
import org.example.cinemahome.port.GenrePort;
import org.example.cinemahome.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("dbGenrePort")
public class DbGenreAdapter implements GenrePort {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<GenreDto> findAll() {
        return genreRepository.findAll().stream()
                .map(g -> new GenreDto(
                        g.getId(),
                        g.getName(),
                        g.getDescription()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void save(GenreDto dto) {
        Genre g = new Genre();
        if (dto.getId() != null) {
            g.setId(dto.getId());
        }
        g.setName(dto.getName());
        g.setDescription(dto.getDescription());
        genreRepository.save(g);
    }
}