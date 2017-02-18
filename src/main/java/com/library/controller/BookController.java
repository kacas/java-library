package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView getBooks() {
        List<Book> books = bookService.getAllBooks();
        ModelAndView model = new ModelAndView("books", "books", books);
        return model;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView getBooksBySearchCriteria(@RequestParam String searchCriteria){
        List<Book> books = bookService.getBooksBySearchCriteria(searchCriteria);
        ModelAndView model = new ModelAndView("books", "books", books);
        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getBook(@PathVariable UUID id) {
        ModelAndView model = new ModelAndView("book", "book", bookService.getBook(id));
        return model;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getBookCreationPage(){
        ModelAndView model = new ModelAndView("book", "book", new Book(null, "", "", "", "", false));
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return new ModelAndView("redirect:/books");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void editBook(@RequestBody Book book, @PathVariable UUID id) {
        bookService.updateBook(id, book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
    }
}
