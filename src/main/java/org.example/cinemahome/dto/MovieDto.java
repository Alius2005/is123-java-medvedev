package org.example.cinemahome.dto;

import java.util.List;

public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private String moodTag;
    private List<Long> genreIds;
    private List<Long> actorIds;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMoodTag() { return moodTag; }
    public void setMoodTag(String moodTag) { this.moodTag = moodTag; }

    public List<Long> getGenreIds() { return genreIds; }
    public void setGenreIds(List<Long> genreIds) { this.genreIds = genreIds; }

    public List<Long> getActorIds() { return actorIds; }
    public void setActorIds(List<Long> actorIds) { this.actorIds = actorIds; }

    public MovieDto() {}

    public MovieDto(String title, String description, String moodTag) {
        this.title = title;
        this.description = description;
        this.moodTag = moodTag;
    }
}
