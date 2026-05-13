package org.example.cinemahome.dto;

public class EpisodeDto {
    private Long id;
    private String title;
    private String filePath;
    private Integer episodeNumber;
    private Long seasonId;   // ВАЖНО

    public EpisodeDto() {}

    public EpisodeDto(Long id, String title, String filePath,
                      Integer episodeNumber, Long seasonId) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.episodeNumber = episodeNumber;
        this.seasonId = seasonId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public Integer getEpisodeNumber() { return episodeNumber; }
    public void setEpisodeNumber(Integer episodeNumber) { this.episodeNumber = episodeNumber; }

    public Long getSeasonId() { return seasonId; }
    public void setSeasonId(Long seasonId) { this.seasonId = seasonId; }
}