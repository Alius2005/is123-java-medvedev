package org.example.cinemahome.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "SERIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "BLOB")
    private String description;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "cover_path")
    private String coverPath;

    @Column(name = "status")
    private String status;

    @Column(name = "seasons_count")
    private Integer seasonsCount;

    @Column(name = "episodes_count")
    private Integer episodesCount;

    @Column(name = "is_watched")
    private Boolean isWatched;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Season> seasons;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}
