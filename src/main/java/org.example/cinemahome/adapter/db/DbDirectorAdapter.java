package org.example.cinemahome.adapter.db;

import org.example.cinemahome.dto.DirectorDto;
import org.example.cinemahome.pojo.Director;
import org.example.cinemahome.port.DirectorPort;
import org.example.cinemahome.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("dbDirectorPort")
public class DbDirectorAdapter implements DirectorPort {
    @Autowired private DirectorRepository directorRepository;

    @Override
    public List<DirectorDto> findAll() {
        return directorRepository.findAll().stream()
                .map(d -> new DirectorDto(d.getId(), d.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(DirectorDto dto) {
        Director d = new Director();
        if (dto.getId() != null) d.setId(dto.getId());
        d.setName(dto.getName());
        directorRepository.save(d);
    }
}