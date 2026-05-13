package org.example.cinemahome.dto;

import java.util.List;

public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private String moodTag;
    private String filePath;   // ← добавили
    private List<Long> genreIds;
    private List<Long> actorIds;

    public MovieDto() {}

    public MovieDto(Long id, String title, String description, String moodTag,
                    String filePath,
                    List<Long> genreIds, List<Long> actorIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.moodTag = moodTag;
        this.filePath = filePath;
        this.genreIds = genreIds;
        this.actorIds = actorIds;
    }

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

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

}
