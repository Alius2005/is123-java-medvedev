package org.example.cinemahome.port;

import org.example.cinemahome.dto.UserDto;

import java.util.List;

public interface UserPort {
    List<UserDto> findAll();
    UserDto findByUsername(String username);
    void save(UserDto user);
}