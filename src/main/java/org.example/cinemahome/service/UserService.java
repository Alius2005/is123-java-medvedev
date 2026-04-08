package org.example.cinemahome.service;

import org.example.cinemahome.repository.UserRepository;
import org.example.cinemahome.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserDto user) {
        userRepository.save(user);
    }
}
