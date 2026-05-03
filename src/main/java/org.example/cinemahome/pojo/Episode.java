package org.example.cinemahome.pojo;

public class Episode {
    private int id;
    private String seasonId;
    private String title;
    private Integer duration;
    private String filePath;
    private Boolean isWatched;
    private Integer episodeNumber;

    public Episode() {}

    public Episode(String seasonId, Integer episodeNumber) {
        this.seasonId = seasonId;
        this.episodeNumber = episodeNumber;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSeasonId() { return seasonId; }
    public void setSeasonId(String seasonId) { this.seasonId = seasonId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public Boolean getIsWatched() { return isWatched; }
    public void setIsWatched(Boolean isWatched) { this.isWatched = isWatched; }

    public Integer getEpisodeNumber() { return episodeNumber; }
    public void setEpisodeNumber(Integer episodeNumber) { this.episodeNumber = episodeNumber; }
}