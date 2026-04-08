package org.example.cinemahome.repository.impl;

import org.example.cinemahome.repository.ActorRepository;
import org.example.cinemahome.dto.ActorDto;
import org.example.cinemahome.factory.RepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaActorRepository implements ActorRepository {
    @Autowired
    private RepositoryFactory factory;

    @Override
    public List<ActorDto> findAll() {
        return factory.createJsonActorRepository().findAll();
    }
}
