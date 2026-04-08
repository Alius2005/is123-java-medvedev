package org.example.cinemahome.repository;

import org.example.cinemahome.dto.UserDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Primary
public interface UserRepository {
    List<UserDto> findAll();
    void save(UserDto user);
}
