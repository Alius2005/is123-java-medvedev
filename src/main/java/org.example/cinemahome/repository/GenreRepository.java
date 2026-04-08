package org.example.cinemahome.repository;

import org.example.cinemahome.dto.GenreDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface GenreRepository {
    List<GenreDto> findAll();
}
