package com.library.controller;

import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import javassist.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertTrue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Katarina on 3/6/2017.
 */

public class BookControllerTest extends ControllerTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testGetBooks() throws Exception {
        mockMvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"));
    }

    @Test
    public void testGetBooksBySearchCriteria() throws Exception {
        String searchCriteria = "java";

        createBook("Java Cookbook", "Ian F. Darwin", "", "978-0-59600-701-0");
        createBook("Mockito for Spring", "Sujoy Acharya", "", "978-1-78398-378-0");

        List<Book> books = (List<Book>) mockMvc.perform(get("/books/search")
                .param("searchCriteria", searchCriteria))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andReturn().getModelAndView().getModel().get("books");

        for (Book book : books) {
            assertTrue(book.getTitle().toLowerCase().contains(searchCriteria) ||
                book.getAuthor().toLowerCase().contains(searchCriteria) ||
                book.getIsbn().toLowerCase().contains(searchCriteria));
        }
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = createBook("Java Cookbook", "Ian F. Darwin", "", "978-0-59600-701-0");

        Book fetchedBook = (Book) mockMvc.perform(get("/books/{id}", book.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("book"))
                .andReturn().getModelAndView().getModel().get("book");

        assertEquals(book.getId(), fetchedBook.getId());
    }

    @Test
    public void testGetBookNonExisting() throws Exception {
        mockMvc.perform(get("/books/{id}", UUID.randomUUID()))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    public void testGetBookCreationPage() throws Exception {
        mockMvc.perform(get("/books/new/"))
                .andExpect(status().isOk())
                .andExpect(view().name("book"))
                .andExpect(model().attribute("book", hasProperty("id", nullValue())))
                .andExpect(model().attribute("book", hasProperty("title", isEmptyString())))
                .andExpect(model().attribute("book", hasProperty("description", isEmptyString())))
                .andExpect(model().attribute("book", hasProperty("author", isEmptyString())))
                .andExpect(model().attribute("book", hasProperty("isbn", isEmptyString())));
    }

    @Test
    public void testCreateValidBook() throws Exception {
        Book savedBook = createBook("Java Cookbook", "Ian F. Darwin", "", "978-0-59600-701-0");

        assertTrue(bookRepository.exists(savedBook.getId()));

        Book createdBook = (Book) mockMvc.perform(get("/books/{id}", savedBook.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("book"))
                .andReturn().getModelAndView().getModel().get("book");

        assertThat(createdBook.getTitle(), is(notNullValue()));
        assertThat(createdBook.getAuthor(), is(notNullValue()));
        assertThat(createdBook.getIsbn(), is(notNullValue()));
        assertThat(createdBook.getDeleted(), is(false));
    }

    @Test
    public void testCreateInvalidBook() throws Exception {
        mockMvc.perform(post("/books/")
                .param("title", "")
                .param("description", "")
                .param("author", "")
                .param("isbn", ""))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("book"))
                .andReturn().getModelAndView().getModel().get("book");
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteBook() throws Exception {
        Book book = createBook("Java Cookbook", "Ian F. Darwin", "", "978-0-59600-701-0");
        assertTrue(bookRepository.exists(book.getId()));

        mockMvc.perform(delete("/books/{id}", book.getId()))
                .andExpect(status().isNoContent());

        bookService.getBook(book.getId());
    }

    private Book createBook(String title, String author, String description, String isbn) throws Exception {
        return (Book) mockMvc.perform(post("/books/")
                .param("title", title)
                .param("description", description)
                .param("author", author)
                .param("isbn", isbn))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books"))
                .andReturn().getModelAndView().getModel().get("book");
    }
}
