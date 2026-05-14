package org.example.cinemahome.service;

import org.example.cinemahome.config.DataMode;
import org.example.cinemahome.config.DataModeService;
import org.example.cinemahome.dto.UserDto;
import org.example.cinemahome.port.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired @Qualifier("dbUserPort")
    private UserPort dbUserPort;

    @Autowired @Qualifier("jsonUserPort")
    private UserPort jsonUserPort;

    @Autowired
    private DataModeService dataModeService;

    private UserPort currentPort() {
        return (dataModeService.getMode() == DataMode.JSON) ? jsonUserPort : dbUserPort;
    }

    public void registerUser(UserDto userDto) {
        currentPort().save(userDto);
    }
}