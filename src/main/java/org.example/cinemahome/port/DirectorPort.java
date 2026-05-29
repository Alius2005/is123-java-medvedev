package org.example.cinemahome.port;

import org.example.cinemahome.dto.DirectorDto;
import java.util.List;

public interface DirectorPort {
    List<DirectorDto> findAll();
    void save(DirectorDto director);
}