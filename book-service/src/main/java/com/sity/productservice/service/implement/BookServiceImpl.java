package com.sity.productservice.service.implement;

import com.sity.productservice.dto.BookRequest;
import com.sity.productservice.dto.BookResponse;
import com.sity.productservice.model.Book;
import com.sity.productservice.repository.BookRepository;
import com.sity.productservice.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::mapToResponse).orElse(null);
    }

    @Override
    public List<BookResponse> searchBooksTitle(String title){
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        return books.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        Book book = mapToEntity(bookRequest);
        bookRepository.save(book);
        return mapToResponse(book);
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setTitle(bookRequest.getTitle());
            book.setAuthor(bookRequest.getAuthor());
            book.setIsbn(bookRequest.getIsbn());
            book.setPublicationDate(bookRequest.getPublicationDate());

            book = bookRepository.save(book);
            return mapToResponse(book);
        }
        return null;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


    private BookResponse mapToResponse(Book book){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(book, BookResponse.class);
    }

    private Book mapToEntity(BookRequest bookRequest){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bookRequest, Book.class);
    }
}
