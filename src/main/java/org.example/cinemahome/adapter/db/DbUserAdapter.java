package org.example.cinemahome.adapter.db;

import org.example.cinemahome.dto.UserDto;
import org.example.cinemahome.pojo.User;
import org.example.cinemahome.port.UserPort;
import org.example.cinemahome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("dbUserPort")
public class DbUserAdapter implements UserPort {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(u -> {
                    UserDto dto = new UserDto();
                    dto.setId(u.getId());
                    dto.setUsername(u.getUsername());
                    // пароль в DTO можно не класть
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(u -> {
                    UserDto dto = new UserDto();
                    dto.setId(u.getId());
                    dto.setUsername(u.getUsername());
                    return dto;
                })
                .orElse(null);
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) {
            user.setId(userDto.getId());
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoleName("user");

        userRepository.save(user);
    }
}