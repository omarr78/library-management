package com.omar.library.library_management.service;

import com.omar.library.library_management.entity.Book;
import com.omar.library.library_management.exception.ResourceAlreadyExistException;
import com.omar.library.library_management.exception.ResourceNotFoundException;
import com.omar.library.library_management.jpa.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book checkBookExist(int id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book with id " + id + " Not found")
        );
    }

    public void checkUniqueBook(String title) {
        Book newBook = bookRepository.findByTitle(title);
        if (newBook != null) {
            throw new ResourceAlreadyExistException("title : " + title + " already exist");
        }
    }

    public ResponseEntity<Book> createBook(Book book) {
        checkUniqueBook(book.getTitle());

        Book newBook = bookRepository.save(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBook.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Book> updateBook(int id, Book updatedBook) {
        checkBookExist(id);
        checkUniqueBook(updatedBook.getTitle());

        updatedBook.setId(id);
        bookRepository.save(updatedBook);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
