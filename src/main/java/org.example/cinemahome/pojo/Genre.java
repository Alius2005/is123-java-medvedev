package org.example.cinemahome.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "GENRE")
@Data
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "BLOB")
    private String description;

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }
}