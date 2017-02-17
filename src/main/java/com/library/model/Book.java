package com.library.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by Katarina on 2/16/2017.
 */
@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "BookID", nullable = false, columnDefinition = "BINARY(16)")
    private byte[] id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Author")
    private String author;

    @Column(name = "ISBN")
    private String isbn;

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
