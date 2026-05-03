package org.example.cinemahome.pojo;

public class Series {
    private int id;
    private String title;
    private String description;
    private String filePath;
    private String coverPath;
    private String status;
    private Integer seasonsCount;
    private Integer episodesCount;
    private Boolean isWatched;

    public Series() {}

    public Series(String title, String description, String filePath) {
        this.title = title;
        this.description = description;
        this.filePath = filePath;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getCoverPath() { return coverPath; }
    public void setCoverPath(String coverPath) { this.coverPath = coverPath; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getSeasonsCount() { return seasonsCount; }
    public void setSeasonsCount(Integer seasonsCount) { this.seasonsCount = seasonsCount; }

    public Integer getEpisodesCount() { return episodesCount; }
    public void setEpisodesCount(Integer episodesCount) { this.episodesCount = episodesCount; }

    public Boolean getIsWatched() { return isWatched; }
    public void setIsWatched(Boolean isWatched) { this.isWatched = isWatched; }
}
