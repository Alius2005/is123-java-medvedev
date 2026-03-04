package kinoteka.model;

import java.util.Set;

public class MovieDto {
    private String id;
    private String title;
    private Integer year;
    private String filePath;
    private String coverPath;
    private Set<String> genres;
    private Set<String> actors;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public String getCoverPath() { return coverPath; }
    public void setCoverPath(String coverPath) { this.coverPath = coverPath; }
    public Set<String> getGenres() { return genres; }
    public void setGenres(Set<String> genres) { this.genres = genres; }
    public Set<String> getActors() { return actors; }
    public void setActors(Set<String> actors) { this.actors = actors; }
}
