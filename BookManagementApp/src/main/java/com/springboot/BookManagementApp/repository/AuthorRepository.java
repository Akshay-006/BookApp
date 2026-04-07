package com.springboot.BookManagementApp.repository;

import com.springboot.BookManagementApp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
