package org.example.cinemahome.service;

import org.example.cinemahome.config.DataMode;
import org.example.cinemahome.config.DataModeService;
import org.example.cinemahome.dto.ActorDto;
import org.example.cinemahome.port.ActorPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired @Qualifier("dbActorPort")
    private ActorPort dbActorPort;

    @Autowired @Qualifier("jsonActorPort")
    private ActorPort jsonActorPort;

    @Autowired
    private DataModeService dataModeService;

    private ActorPort currentPort() {
        return (dataModeService.getMode() == DataMode.JSON) ? jsonActorPort : dbActorPort;
    }

    public List<ActorDto> getAllActors() {
        return currentPort().findAll();
    }

    public void addActor(ActorDto dto) {
        currentPort().save(dto);
    }
}