package com.springboot.BookManagementApp.service;

import com.springboot.BookManagementApp.dto.BookDto;
import com.springboot.BookManagementApp.exception.ResourceNotFoundException;
import com.springboot.BookManagementApp.model.Author;
import com.springboot.BookManagementApp.model.Book;
import com.springboot.BookManagementApp.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void getAllBooksTest(){
        Author author = new Author();
        author.setName("Leo");

        Book book1 = new Book();
        book1.setTitle("1");
        book1.setPublicationYear(2020);
        book1.setISBN("AT1234");
        book1.setAuthor(author);


        Book book2 = new Book();
        book2.setTitle("2");
        book2.setPublicationYear(2023);
        book2.setISBN("AT1254");
        book2.setAuthor(author);

        List<Book> books = List.of(book1,book2);

        Page<Book> bookPage = new PageImpl<>(books);

        int page = 0;
        int size = 2;

        Pageable pageable = PageRequest.of(page,size);

        when(bookRepository.findAll(pageable)).thenReturn(bookPage);

        Assertions.assertEquals(2,bookService.getAllBooks(0,2).data().size());


    }

    @Test
    public void getByISBNTestWhenExists(){

        Author author = new Author();
        author.setName("Leo");

        Book book1 = new Book();
        book1.setTitle("1");
        book1.setPublicationYear(2020);
        book1.setISBN("AT1234");
        book1.setAuthor(author);


        when(bookRepository.findByISBN("AT1234")).thenReturn(book1);

        //Prepare the DTO for above ticket object
        // So we know that DTO is getting prepared properly in our actual service class too.

        BookDto bookDto = new BookDto(
                book1.getTitle(),
                book1.getAuthor().getName(),
                book1.getISBN(),
                book1.getPublicationYear()
        );




        Assertions.assertEquals(bookDto,bookService.getBooksByISBN("AT1234"));






    }

}
