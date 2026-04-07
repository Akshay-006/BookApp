package com.springboot.BookManagementApp.service;

import com.springboot.BookManagementApp.dto.BookDto;
import com.springboot.BookManagementApp.dto.BookPageDto;
import com.springboot.BookManagementApp.dto.BookPostDto;
import com.springboot.BookManagementApp.exception.NoAccessException;
import com.springboot.BookManagementApp.exception.ResourceNotFoundException;
import com.springboot.BookManagementApp.mapper.BookMapper;
import com.springboot.BookManagementApp.model.Author;
import com.springboot.BookManagementApp.model.Book;
import com.springboot.BookManagementApp.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springboot.BookManagementApp.mapper.BookMapper.mapToDto;
import static com.springboot.BookManagementApp.mapper.BookMapper.mapToEntity;

@Service
@AllArgsConstructor

public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public Book getBookById(long id){
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book ID Invalid !!")
        );

        return book;

    }

    public BookPageDto getAllBooks(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Book> bookPage = bookRepository.findAll(pageable);

        long totalElements = bookPage.getTotalElements();
        int totalPages = bookPage.getTotalPages();

        List<BookDto> bookDtoList =
                bookPage.stream().toList()
                        .stream()
                        .map(BookMapper :: mapToDto)
                        .toList();

        return new BookPageDto(
                bookDtoList,
                totalElements,
                totalPages
        );

    }

    public BookDto getBooksByISBN(String ISBN) {

        Book book = bookRepository.findByISBN(ISBN);
        BookDto bookDto = mapToDto(book);

        return  bookDto;



    }

    public void addBook(BookPostDto bookPostDto) {
        Book book = mapToEntity(bookPostDto);
        long authorId = bookPostDto.authorId();
        Author author = authorService.getAuthorById(authorId);
        book.setAuthor(author);

        bookRepository.save(book);

    }


    public void updateBook(long bookId, String title, int year, String authorName) {
        Book book = getBookById(bookId);

        /*if (!book.getAuthor().getName().equalsIgnoreCase(existingAuthor)){
            throw new NoAccessException("U dont have Access to update");
        }*/

        if (!title.isEmpty()) book.setTitle(title);
        if (year!=-1) book.setPublicationYear(year);
        if (!authorName.isEmpty()) book.getAuthor().setName(authorName);

        bookRepository.save(book);

    }

    public void deleteBook(long bookId) {
        Book book = getBookById(bookId);
        bookRepository.delete(book);
    }
}
