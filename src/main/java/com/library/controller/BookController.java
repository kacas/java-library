package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Katarina on 2/16/2017.
 */

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    @ModelAttribute("books")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ModelAttribute("book")
    public Book getBook(@PathVariable byte[] id) {
        return bookService.getBook(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void editBook(@RequestBody Book book, @PathVariable byte[] id) {
        bookService.updateBook(id, book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable byte[] id) {
        bookService.deleteBook(id);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ModelAttribute("book")
    public Book getBookCreationPage(){
        return new Book();
    }
}
