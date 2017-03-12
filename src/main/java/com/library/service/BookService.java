package com.library.service;

import com.library.model.Book;
import javassist.NotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * Created by Katarina on 2/16/2017.
 */

public interface BookService {

    public List<Book> getAllBooks();

    public List<Book> getBooksBySearchCriteria(String searchCriteria);

    public Book getBook(UUID id) throws NotFoundException;

    public void addBook(Book book);

    public void updateBook(UUID id, Book book);

    public void deleteBook(UUID id);
}
