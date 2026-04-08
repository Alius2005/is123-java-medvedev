package org.example.cinemahome.repository;

import org.example.cinemahome.dto.GenreDto;
import java.util.List;

public interface GenreRepository {
    List<GenreDto> findAll();
}
