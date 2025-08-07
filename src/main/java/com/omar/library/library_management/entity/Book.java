package com.omar.library.library_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name="books")
public class Book {
    @Id
    @GeneratedValue
    private int id;

    @Size(min = 2, max = 50)
    @Column(unique=true)
    private String title;

    @Size(min = 2, max = 50)
    private String author;

    @Size(min = 2, max = 50)
    private String topic;

    @Max(2025)
    @Min(1900)
    private int year;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private List<User> users;

    public Book(){}
    public Book(int id, String title, String author, String topic, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.topic = topic;
        this.year = year;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTopic() {
        return topic;
    }

    public int getYear() {
        return year;
    }

}
