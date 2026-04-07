package com.springboot.BookManagementApp.dto;

import java.util.List;

public record BookPageDto(
        List<BookDto> data,
        long totalElements,
        int totalPages

) {
}
