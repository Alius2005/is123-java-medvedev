package org.example.cinemahome.adapter.db;

import org.example.cinemahome.dto.ActorDto;
import org.example.cinemahome.pojo.Actor;
import org.example.cinemahome.port.ActorPort;
import org.example.cinemahome.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("dbActorPort")
public class DbActorAdapter implements ActorPort {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<ActorDto> findAll() {
        return actorRepository.findAll().stream()
                .map(a -> new ActorDto(
                        a.getId(),
                        a.getFirstName(),
                        a.getLastName(),
                        a.getBio()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void save(ActorDto dto) {
        Actor a = new Actor();
        if (dto.getId() != null) {
            a.setId(dto.getId());
        }
        a.setFirstName(dto.getFirstName());
        a.setLastName(dto.getLastName());
        a.setBio(dto.getBio());
        actorRepository.save(a);
    }
}