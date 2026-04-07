package com.springboot.BookManagementApp.dto;

public record BookPostDto(
        String title,
        String ISBN,
        int publicationYear,
        long authorId
) {
}
