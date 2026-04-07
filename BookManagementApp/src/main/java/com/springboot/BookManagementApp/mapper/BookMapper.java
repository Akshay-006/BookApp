package com.springboot.BookManagementApp.mapper;

import com.springboot.BookManagementApp.dto.BookDto;
import com.springboot.BookManagementApp.dto.BookPostDto;
import com.springboot.BookManagementApp.model.Book;

public class BookMapper {


    public static BookDto mapToDto(Book book){
        return new BookDto(
                book.getTitle(),
                book.getAuthor().getName(),
                book.getISBN(),
                book.getPublicationYear()
        );
    }


    public static Book mapToEntity(BookPostDto bookPostDto){
        Book book = new Book();

        book.setISBN(bookPostDto.ISBN());
        book.setTitle(bookPostDto.title());
        book.setPublicationYear(bookPostDto.publicationYear());

        return book;

    }

}
