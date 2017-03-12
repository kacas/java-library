package com.library.service.internal;

import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Katarina on 2/17/2017.
 */

@Service
public class JPABookService implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksBySearchCriteria(String searchCriteria) {
        return bookRepository.findBooksBySearchCriteria(searchCriteria);
    }

    @Override
    public Book getBook(UUID id) throws NotFoundException {
        Book book = bookRepository.findOne(id);
        if(book == null){
            throw new NotFoundException(String.format("Book with id %s does not exist." , id));
        }
        return book;
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(UUID id, Book book){
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(UUID id) {
        bookRepository.delete(id);
    }
}
