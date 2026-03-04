package kinoteka.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @Column(length = 50)
    private String id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private String filePath;
    private String coverPath;
    private LocalDateTime createdAt;

    @ElementCollection
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private Set<String> genres;

    @ElementCollection
    @CollectionTable(name = "movie_actors", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "actor")
    private Set<String> actors;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public String getCoverPath() { return coverPath; }
    public void setCoverPath(String coverPath) { this.coverPath = coverPath; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Set<String> getGenres() { return genres; }
    public void setGenres(Set<String> genres) { this.genres = genres; }
    public Set<String> getActors() { return actors; }
    public void setActors(Set<String> actors) { this.actors = actors; }
}
