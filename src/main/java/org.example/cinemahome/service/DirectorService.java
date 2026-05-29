package org.example.cinemahome.service;

import org.example.cinemahome.config.DataMode;
import org.example.cinemahome.config.DataModeService;
import org.example.cinemahome.dto.DirectorDto;
import org.example.cinemahome.port.DirectorPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {
    @Autowired @Qualifier("dbDirectorPort") private DirectorPort dbDirectorPort;
    @Autowired @Qualifier("jsonDirectorPort") private DirectorPort jsonDirectorPort;
    @Autowired private DataModeService dataModeService;

    private DirectorPort currentPort() {
        return (dataModeService.getMode() == DataMode.JSON) ? jsonDirectorPort : dbDirectorPort;
    }

    public List<DirectorDto> getAllDirectors() { return currentPort().findAll(); }
    public void addDirector(DirectorDto dto) { currentPort().save(dto); }
}