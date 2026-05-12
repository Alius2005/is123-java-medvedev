package org.example.cinemahome.repository;

import org.example.cinemahome.dto.UserDto;
import org.example.cinemahome.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    void save(UserDto user);

}
