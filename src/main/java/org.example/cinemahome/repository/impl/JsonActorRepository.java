package org.example.cinemahome.repository.impl;

import org.example.cinemahome.repository.ActorRepository;
import org.example.cinemahome.dto.ActorDto;
import org.example.cinemahome.config.ApplicationProperties;
import org.example.cinemahome.util.JsonDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Primary
public class JsonActorRepository implements ActorRepository {

    @Autowired
    private ApplicationProperties properties;

    @Override
    public List<ActorDto> findAll() {
        return JsonDataLoader.loadActors(properties.getDataPath());
    }
}
