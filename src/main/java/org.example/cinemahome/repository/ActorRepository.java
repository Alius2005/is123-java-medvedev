package org.example.cinemahome.repository;

import org.example.cinemahome.dto.ActorDto;
import java.util.List;

public interface ActorRepository {
    List<ActorDto> findAll();
}
