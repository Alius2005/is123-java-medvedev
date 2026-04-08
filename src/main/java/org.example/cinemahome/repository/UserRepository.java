package org.example.cinemahome.repository;

import org.example.cinemahome.dto.UserDto;
import java.util.List;

public interface UserRepository {
    List<UserDto> findAll();
    void save(UserDto user);
}
