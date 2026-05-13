package org.example.cinemahome.dto;

public class SeriesDto {
    private Long id;
    private String title;
    private String description;
    private String folder;
    private Integer seasonNumber;
    private Integer episodesCount;

    public SeriesDto() {}

    public SeriesDto(Long id, String title, String description,
                     String folder, Integer seasonNumber, Integer episodesCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.folder = folder;
        this.seasonNumber = seasonNumber;
        this.episodesCount = episodesCount;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFolder() { return folder; }
    public void setFolder(String folder) { this.folder = folder; }

    public Integer getSeasonNumber() { return seasonNumber; }
    public void setSeasonNumber(Integer seasonNumber) { this.seasonNumber = seasonNumber; }

    public Integer getEpisodesCount() { return episodesCount; }
    public void setEpisodesCount(Integer episodesCount) { this.episodesCount = episodesCount; }
}