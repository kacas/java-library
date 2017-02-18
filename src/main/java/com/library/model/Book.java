package com.library.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by Katarina on 2/16/2017.
 */
@Entity
@Table(name = "books")
@Where(clause = "is_deleted='false'")
@SQLDelete(sql = "UPDATE books SET is_deleted = true WHERE book_id = ?")
public class Book {
    @Id
    @Column(name = "book_id", nullable = false, columnDefinition = "BINARY(16)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    private String description;

    private String author;

    private String isbn;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Book(){}

    public Book(UUID id, String title, String description, String author, String isbn, boolean isDeleted){
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.isDeleted = isDeleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public boolean getDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

}
