package org.example.cinemahome.config;

import org.example.cinemahome.pojo.User;
import org.example.cinemahome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Optional<User> existingAdmin = userRepository.findByUsername("admin");

        if (!userRepository.findByUsername("admin").isPresent()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoleName("ADMIN");
            admin.setCreatedAt(java.time.LocalDateTime.now());
            userRepository.save(admin);
            System.out.println("Администратор создан: admin/admin");
        }

        // Создаём модератора, если его нет
        if (!userRepository.findByUsername("moderator").isPresent()) {
            User moderator = new User();
            moderator.setUsername("moderator");
            moderator.setPassword(passwordEncoder.encode("moderator"));
            moderator.setRoleName("moderator");
            moderator.setCreatedAt(java.time.LocalDateTime.now());
            userRepository.save(moderator);
            System.out.println("Модератор создан: moderator/moderator");
        }

//        // Проверим, что пользователи есть
//        userRepository.findAll().forEach(user ->
//                System.out.println("👤 Пользователь: " + user.getUsername() + " | Роль: " + user.getRoleName())
//        );

    }
}
