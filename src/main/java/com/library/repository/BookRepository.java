package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Katarina on 2/16/2017.
 */

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("SELECT b FROM Book b WHERE (b.title LIKE %:searchCriteria% OR b.isbn LIKE %:searchCriteria% " +
            "OR b.author LIKE %:searchCriteria%) AND b.isDeleted = FALSE")
    List<Book> findBooksBySearchCriteria(@Param("searchCriteria") String searchCriteria);
}