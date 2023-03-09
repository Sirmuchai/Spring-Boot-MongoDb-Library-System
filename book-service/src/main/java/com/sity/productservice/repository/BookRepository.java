package com.sity.productservice.repository;

import com.sity.productservice.dto.BookResponse;
import com.sity.productservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<BookResponse> findByTitleContainingIgnoreCase(String title);
    List<BookResponse> findByAuthorContainingIgnoreCase(String author);
    Optional<BookResponse> findByIsbn(String isbn);
}
