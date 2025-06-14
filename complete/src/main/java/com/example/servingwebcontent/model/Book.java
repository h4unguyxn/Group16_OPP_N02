package com.example.servingwebcontent.model;

import com.example.servingwebcontent.common.Identifiable;
import java.io.Serializable;

public class Book implements Serializable, Identifiable {
    private String id;
    private String title;
    private String author;
    private String genre;

    public Book() {}

    public Book(String id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
}
