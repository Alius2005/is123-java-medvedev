package org.example.cinemahome.dto.json;

public class SeasonJsonDto {
    private Long id;
    private Long seriesId;
    private Integer seasonNumber;
    private Integer episodesCount;
    private String title;

    public SeasonJsonDto() {}

    public SeasonJsonDto(Long id, Long seriesId, Integer seasonNumber,
                         Integer episodesCount, String title) {
        this.id = id;
        this.seriesId = seriesId;
        this.seasonNumber = seasonNumber;
        this.episodesCount = episodesCount;
        this.title = title;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSeriesId() { return seriesId; }
    public void setSeriesId(Long seriesId) { this.seriesId = seriesId; }

    public Integer getSeasonNumber() { return seasonNumber; }
    public void setSeasonNumber(Integer seasonNumber) { this.seasonNumber = seasonNumber; }

    public Integer getEpisodesCount() { return episodesCount; }
    public void setEpisodesCount(Integer episodesCount) { this.episodesCount = episodesCount; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}