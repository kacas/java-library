package com.library.service.internal;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Cakana on 22.2.2017..
 */

@RunWith(MockitoJUnitRunner.class)
public class JPABookServiceTest {

    private List<Book> allBooks;
    private List<Book> javaBooks;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private JPABookService bookService;

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    @Before
    public void setUp() {
        Book book1 = new Book(UUID.randomUUID(), "Java Cookbook", "", "Ian F. Darwin", "978-0-59600-701-0", false);
        Book book2 = new Book(UUID.randomUUID(), "Java EE 6 Pocket Guide", "", "Arun Gupta", "978-1-4493-3668-4", false);
        Book book3 = new Book(UUID.randomUUID(), "Java 7 Pocket Guide", "", "Robert Liguori, Patricia Liguori", "978-1-44934-356-9", false);

        Book book4 = new Book(UUID.randomUUID(), "Just Spring", "", "Madhusudhan Konda", "978-1-4493-0640-3", false);
        Book book5 = new Book(UUID.randomUUID(), "Mockito for Spring", "", "Sujoy Acharya", "978-1-78398-378-0", false);

        javaBooks = new ArrayList<>();
        javaBooks.add(book1);
        javaBooks.add(book2);
        javaBooks.add(book3);

        allBooks = new ArrayList<>();
        allBooks.add(book1);
        allBooks.add(book2);
        allBooks.add(book3);
        allBooks.add(book4);
        allBooks.add(book5);
    }

    @Test
    public void testGetAllBooks(){
        when(bookRepository.findAll()).thenReturn(allBooks);

        List<Book> resultBooks = bookService.getAllBooks();

        assertEquals(5, resultBooks.size());
    }

    @Test
    public void testGetBooksBySearchCriteria(){
        String searchCriteria = "java";

        when(bookRepository.findBooksBySearchCriteria(searchCriteria)).thenReturn(javaBooks);

        List<Book> filteredBooks = bookService.getBooksBySearchCriteria(searchCriteria);

        assertEquals(3, filteredBooks.size());
    }

    @Test
    public void testGetBook(){
        UUID bookID = UUID.randomUUID();
        Book book = new Book(bookID, "Mockito for Spring", "", "Sujoy Acharya", "978-1-78398-378-0", false);

        given(bookRepository.findOne(bookID)).willReturn(book);

        Book entity = bookService.getBook(bookID);

        assertThat(entity.getId(), is(book.getId()));
        assertThat(entity.getTitle(), is(book.getTitle()));
        assertThat(entity.getDescription(), is(book.getDescription()));
        assertThat(entity.getAuthor(), is(book.getAuthor()));
        assertThat(entity.getIsbn(), is(book.getIsbn()));
        assertThat(entity.getDeleted(), is(book.getDeleted()));
    }

    @Test
    public void testAddBook() {
        Book book = new Book(UUID.randomUUID(), "Java Cookbook", "", "Ian F. Darwin", "978-0-59600-701-0", false);
        bookService.addBook(book);

        verify(bookRepository).save(bookArgumentCaptor.capture());

        Book entity = bookArgumentCaptor.getValue();

        assertThat(entity.getId(), is(book.getId()));
        assertThat(entity.getTitle(), is(book.getTitle()));
        assertThat(entity.getDescription(), is(book.getDescription()));
        assertThat(entity.getAuthor(), is(book.getAuthor()));
        assertThat(entity.getIsbn(), is(book.getIsbn()));
        assertThat(entity.getDeleted(), is(book.getDeleted()));
    }

    @Test
    public void testDeleteBook(){
        UUID bookToDeleteId = UUID.randomUUID();

        bookService.deleteBook(bookToDeleteId);

        verify(bookRepository).delete(bookToDeleteId);
        verify(bookRepository, Mockito.never()).delete(UUID.randomUUID());
        verify(bookRepository, Mockito.times(1)).delete(bookToDeleteId);
    }
}
