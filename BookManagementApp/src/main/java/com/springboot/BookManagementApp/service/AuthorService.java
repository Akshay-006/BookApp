package com.springboot.BookManagementApp.service;

import com.springboot.BookManagementApp.dto.AuthorSignUpDto;
import com.springboot.BookManagementApp.enums.Role;
import com.springboot.BookManagementApp.model.Author;
import com.springboot.BookManagementApp.model.User;
import com.springboot.BookManagementApp.repository.AuthorRepository;
import com.springboot.BookManagementApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
@AllArgsConstructor

public class AuthorService {

    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Author getAuthorById(long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResolutionException("Author ID Invalid !"));
        return author;
    }

    public void authorSignUp(AuthorSignUpDto authorSignUpDto) {
        User user = new User();

        user.setUserName(authorSignUpDto.username());
        user.setPassword(passwordEncoder.encode(authorSignUpDto.password()));
        user.setRole(Role.AUTHOR);

        userRepository.save(user);

        Author author = new Author();
        author.setAge(authorSignUpDto.age());
        author.setName(authorSignUpDto.name());
        author.setUser(user);

        authorRepository.save(author);
    }
}
