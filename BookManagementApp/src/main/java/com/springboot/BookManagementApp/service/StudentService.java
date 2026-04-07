package com.springboot.BookManagementApp.service;

import com.springboot.BookManagementApp.dto.AuthorSignUpDto;
import com.springboot.BookManagementApp.enums.Role;
import com.springboot.BookManagementApp.model.Student;
import com.springboot.BookManagementApp.model.User;
import com.springboot.BookManagementApp.repository.StudentRepository;
import com.springboot.BookManagementApp.repository.UserRepository;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    public void studentSignUp(AuthorSignUpDto authorSignUpDto) {
        User user = new User();

        user.setUserName(authorSignUpDto.username());
        user.setPassword(passwordEncoder.encode(authorSignUpDto.password()));
        user.setRole(Role.STUDENT);

        userRepository.save(user);

        Student student = new Student();
        student.setAge(authorSignUpDto.age());
        student.setName(authorSignUpDto.name());
        student.setUser(user);

        studentRepository.save(student);
    }

}
