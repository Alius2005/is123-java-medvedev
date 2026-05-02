package org.example.cinemahome.repository.impl;

import org.example.cinemahome.dto.UserDto;
import org.example.cinemahome.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.util.Optional;


import java.util.ArrayList;
import java.util.List;
@Repository
@Primary
public class JsonUserRepository implements UserRepository {

    private List<UserDto> users = new ArrayList<>();

    @Override
    public List<UserDto> findAll() {
        return users;
    }

    @Override
    public void save(UserDto user) {
        users.add(user);
    }

    public Optional<UserDto> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }




}
