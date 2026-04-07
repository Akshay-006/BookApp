package com.springboot.BookManagementApp.repository;

import com.springboot.BookManagementApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
