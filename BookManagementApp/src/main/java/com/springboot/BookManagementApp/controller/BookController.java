package com.springboot.BookManagementApp.controller;

import com.springboot.BookManagementApp.dto.BookDto;
import com.springboot.BookManagementApp.dto.BookPageDto;
import com.springboot.BookManagementApp.dto.BookPostDto;
import com.springboot.BookManagementApp.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/book")
public class BookController {


    private final BookService bookService;


    @GetMapping("/get-all")
    public BookPageDto getAllBooks(
            @RequestParam(value = "page",required = false,defaultValue = "0") int page,
            @RequestParam(value = "size",required = false,defaultValue = "5") int size
            ){
        return bookService.getAllBooks(page, size);
    }

    @GetMapping("/getByISBN/{ISBN}")
    public BookDto getBooksByISBN(@PathVariable String ISBN){

        return bookService.getBooksByISBN(ISBN);

    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody BookPostDto bookPostDto){

        bookService.addBook(bookPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<?> updateBook(
            @PathVariable long bookId,
            @RequestParam (value = "title", required = false, defaultValue = "") String title,
            @RequestParam (value = "year", required = false, defaultValue = "-1") int year,
            @RequestParam (value = "authorName", required = false, defaultValue = "") String authorName,
            Principal principal

    ){

        bookService.updateBook(bookId,title,year,authorName);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }


}
