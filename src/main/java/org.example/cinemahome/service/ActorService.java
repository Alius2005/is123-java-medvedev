package org.example.cinemahome.service;

import org.example.cinemahome.repository.ActorRepository;
import org.example.cinemahome.dto.ActorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public List<ActorDto> getAllActors() {
        return actorRepository.findAll();
    }
}
