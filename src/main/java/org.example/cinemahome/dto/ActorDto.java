package org.example.cinemahome.dto;

public class ActorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public ActorDto() {}

    public ActorDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
