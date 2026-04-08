package org.example.cinemahome.repository.impl;

import org.example.cinemahome.dto.UserDto;
import org.example.cinemahome.repository.UserRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

// 👇 Make sure you're implementing UserRepository!
public class JsonUserRepository implements UserRepository {

    private List<UserDto> users = new ArrayList<>(); // In-memory storage (for now)

    @Override
    public List<UserDto> findAll() {
        return users;
    }

    @Override
    public void save(UserDto user) {
        users.add(user); // 👈 Your JSON logic will go here later — for now, just store in memory
    }
}
