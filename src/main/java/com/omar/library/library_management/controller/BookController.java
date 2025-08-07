package com.omar.library.library_management.controller;

import com.omar.library.library_management.entity.Book;
import com.omar.library.library_management.jpa.BookRepository;
import com.omar.library.library_management.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repository;
    private final BookService bookService;

    public BookController(BookRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> retrieveAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Book retrieveBook(@PathVariable int id) {
        return bookService.checkBookExist(id);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.checkBookExist(id);
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @Valid @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }
}
