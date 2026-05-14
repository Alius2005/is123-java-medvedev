package org.example.cinemahome.port;

import org.example.cinemahome.dto.ActorDto;

import java.util.List;

public interface ActorPort {
    List<ActorDto> findAll();
    void save(ActorDto actor);
}