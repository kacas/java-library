package com.library.service;

import com.library.model.Book;
import java.util.List;
import java.util.UUID;

/**
 * Created by Katarina on 2/16/2017.
 */

public interface BookService {

    public List<Book> getAllBooks();

    public Book getBook(byte[] id);

    public void addBook(Book book);

    public void updateBook(byte[] id, Book book);

    public void deleteBook(byte[] id);
}
