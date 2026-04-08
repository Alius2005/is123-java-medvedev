package org.example.cinemahome.repository.impl;

import org.example.cinemahome.dto.UserDto;
import org.example.cinemahome.factory.RepositoryFactory;
import org.example.cinemahome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository {

    @Autowired
    private RepositoryFactory factory;

    @Override
    public void save(UserDto user) {
        factory.createJsonUserRepository().save(user);
    }

    @Override
    public List<UserDto> findAll() {
        return factory.createJsonUserRepository().findAll();
    }
}
