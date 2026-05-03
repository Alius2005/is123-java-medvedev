package org.example.cinemahome.pojo;

public class Season {
    private int id;
    private String seriesId;
    private Integer seasonNumber;
    private Integer episodesCount;
    private String status;
    private String filePath;
    private Integer duration;
    private Integer releaseYear;
    private String title;
    private Boolean isWatched;
    private Long directorId;

    public Season() {}

    public Season(String seriesId, Integer seasonNumber) {
        this.seriesId = seriesId;
        this.seasonNumber = seasonNumber;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSeriesId() { return seriesId; }
    public void setSeriesId(String seriesId) { this.seriesId = seriesId; }

    public Integer getSeasonNumber() { return seasonNumber; }
    public void setSeasonNumber(Integer seasonNumber) { this.seasonNumber = seasonNumber; }

    public Integer getEpisodesCount() { return episodesCount; }
    public void setEpisodesCount(Integer episodesCount) { this.episodesCount = episodesCount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Boolean getIsWatched() { return isWatched; }
    public void setIsWatched(Boolean isWatched) { this.isWatched = isWatched; }

    public Long getDirectorId() { return directorId; }
    public void setDirectorId(Long directorId) { this.directorId = directorId; }
}
