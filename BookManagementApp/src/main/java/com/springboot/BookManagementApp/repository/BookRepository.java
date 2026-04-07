package com.springboot.BookManagementApp.repository;

import com.springboot.BookManagementApp.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book,Long> {

    @Query(
            "Select b from Book b where b.ISBN = ?1"
    )
    Book findByISBN(String isbn);
}
