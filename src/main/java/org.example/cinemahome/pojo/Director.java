package org.example.cinemahome.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DIRECTOR")
@Data
@NoArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Director(String name) {
        this.name = name;
    }
}