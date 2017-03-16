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

    @Query("SELECT b FROM Book b WHERE (LOWER(b.title) LIKE LOWER(CONCAT('%',:searchCriteria, '%')) OR " +
            "LOWER(b.isbn) LIKE LOWER(CONCAT('%',:searchCriteria, '%')) OR " +
            "LOWER(b.author) LIKE LOWER(CONCAT('%',:searchCriteria, '%'))) AND b.isDeleted = FALSE")
    List<Book> findBooksBySearchCriteria(@Param("searchCriteria") String searchCriteria);
}