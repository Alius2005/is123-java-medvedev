package org.example.cinemahome.dto;

public class GenreDto {
    private Long id;
    private String name;
    private String description;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public GenreDto() {}

    public GenreDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
