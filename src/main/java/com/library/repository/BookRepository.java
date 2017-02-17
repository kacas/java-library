package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by Katarina on 2/16/2017.
 */

@Repository
public interface BookRepository extends CrudRepository<Book, byte[]> {

}
