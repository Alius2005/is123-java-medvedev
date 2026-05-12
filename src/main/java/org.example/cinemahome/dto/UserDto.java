package org.example.cinemahome.dto;

import java.time.LocalDateTime;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private LocalDateTime createdAt;

    public UserDto() {}

    public UserDto(Long id, String username, String password) {
        this.username = username;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
