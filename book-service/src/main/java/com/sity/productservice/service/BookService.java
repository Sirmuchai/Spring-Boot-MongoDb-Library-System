package com.sity.productservice.service;

import com.sity.productservice.dto.BookRequest;
import com.sity.productservice.dto.BookResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    List<BookResponse> searchBooksTitle(String title);

    BookResponse addBook(BookRequest bookRequest);

    BookResponse updateBook(Long id, BookRequest bookRequest);

    void deleteBook(Long id);
}
