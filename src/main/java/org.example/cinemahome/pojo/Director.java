package org.example.cinemahome.pojo;

public class Director {
    private int id;
    private String name;

    public Director() {}

    public Director(String name) {
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
