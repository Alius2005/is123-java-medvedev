package org.example.cinemahome.pojo;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (roleName == null || roleName.trim().isEmpty()) {
            roleName = "user";
        }
    }
}