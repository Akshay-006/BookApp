package com.springboot.BookManagementApp.dto;

public record AuthorSignUpDto(
    String name,
    int age,
    String username,
    String password
) {
}
