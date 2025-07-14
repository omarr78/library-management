package com.omar.library.library_management.book;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class BookResource {

    private BookDaoService service;

    public BookResource(BookDaoService service) {
        this.service = service;
    }

    @GetMapping("books")
    public List<Book> retrieveAllBooks(){
        return service.finaAll();
    }

    @GetMapping("books/{id}")
    public Book retrieveBook(@PathVariable int id){
        Book book = service.findById(id);
        if(book == null){
            throw new BookNotFoundException("id:" + id);
        }
        return book;
    }

    @PostMapping("books")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book newBook = service.add(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBook.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("books/{id}")
    public void deleteBook(@PathVariable int id) {
        service.deleteById(id);
    }
}
