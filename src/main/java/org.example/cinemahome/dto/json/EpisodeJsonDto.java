package org.example.cinemahome.dto.json;

public class EpisodeJsonDto {
    private Long id;
    private Long seasonId;
    private Long seriesId;
    private Integer episodeNumber;
    private String title;
    private String filePath;

    public EpisodeJsonDto() {}

    public EpisodeJsonDto(Long id, Long seasonId, Long seriesId,
                          Integer episodeNumber, String title, String filePath) {
        this.id = id;
        this.seasonId = seasonId;
        this.seriesId = seriesId;
        this.episodeNumber = episodeNumber;
        this.title = title;
        this.filePath = filePath;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSeasonId() { return seasonId; }
    public void setSeasonId(Long seasonId) { this.seasonId = seasonId; }

    public Long getSeriesId() { return seriesId; }
    public void setSeriesId(Long seriesId) { this.seriesId = seriesId; }

    public Integer getEpisodeNumber() { return episodeNumber; }
    public void setEpisodeNumber(Integer episodeNumber) { this.episodeNumber = episodeNumber; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}