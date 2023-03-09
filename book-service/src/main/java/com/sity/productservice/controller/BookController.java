package com.sity.productservice.controller;

import com.sity.productservice.dto.BookRequest;
import com.sity.productservice.dto.BookResponse;
import com.sity.productservice.repository.BookRepository;
import com.sity.productservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookService bookService,
                          BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }


    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse getBookById(@PathVariable("bookId") Long id){
        return bookService.getBookById(id);
    }

    @GetMapping("/search/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> searchBookByTitle(@PathVariable("title") String title){
        return bookService.searchBooksTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse addBook(@RequestBody BookRequest bookRequest){
        return bookService.addBook(bookRequest);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse updateBook(@PathVariable("bookId") Long id, @RequestBody BookRequest bookRequest){
        return bookService.updateBook(id, bookRequest);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("bookId") Long id){
        bookService.deleteBook(id);
    }


}
