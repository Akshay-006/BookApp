package com.springboot.BookManagementApp.dto;

public record BookDto(
        String title,
        String AuthorName,
        String ISBN,
        int publicationYear
) {
}
