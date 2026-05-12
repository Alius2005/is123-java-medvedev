package org.example.cinemahome.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "EPISODE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "episode_number", nullable = false)
    private Integer episodeNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "is_watched")
    private Boolean isWatched;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;
}
