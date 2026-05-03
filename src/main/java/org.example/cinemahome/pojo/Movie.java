package org.example.cinemahome.pojo;

import java.util.List;

public class Movie {
    private String id;
    private String title;
    private String description;
    private String filePath;
    private String coverPath;
    private Integer releaseYear;
    private String status;
    private Integer duration;
    private Boolean isWatched;
    private Long directorId;
    private List<Long> genreIds;
    private List<Long> actorIds;

    // Конструкторы, геттеры и сеттеры
    public Movie() {}

    public Movie(String title, String description, String filePath) {
        this.title = title;
        this.description = description;
        this.filePath = filePath;
    }

    // Геттеры и сеттеры
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getCoverPath() { return coverPath; }
    public void setCoverPath(String coverPath) { this.coverPath = coverPath; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public Boolean getIsWatched() { return isWatched; }
    public void setIsWatched(Boolean isWatched) { this.isWatched = isWatched; }

    public Long getDirectorId() { return directorId; }
    public void setDirectorId(Long directorId) { this.directorId = directorId; }

    public List<Long> getGenreIds() { return genreIds; }
    public void setGenreIds(List<Long> genreIds) { this.genreIds = genreIds; }

    public List<Long> getActorIds() { return actorIds; }
    public void setActorIds(List<Long> actorIds) { this.actorIds = actorIds; }
}
