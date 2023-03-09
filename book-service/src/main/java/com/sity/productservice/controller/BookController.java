package com.sity.productservice.controller;

import com.sity.productservice.dto.BookRequest;
import com.sity.productservice.dto.BookResponse;
import com.sity.productservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse addBook(@RequestBody BookRequest bookRequest){
        return bookService.addBook(bookRequest);
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse getBookById(@PathVariable("bookId") Long id){
        return bookService.getBookById(id);
    }


}
