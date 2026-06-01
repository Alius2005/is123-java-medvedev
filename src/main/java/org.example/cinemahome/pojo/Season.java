package org.example.cinemahome.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "SEASONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "season_number", nullable = false)
    private Integer seasonNumber;

    @Column(name = "episodes_count")
    private Integer episodesCount;

    @Column(name = "status")
    private String status;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "title")
    private String title;

    @Column(name = "is_watched")
    private Boolean isWatched;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    private Series series;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episode> episodes;

}
