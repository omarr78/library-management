package com.omar.library.library_management.controller;


import com.omar.library.library_management.entity.Book;
import com.omar.library.library_management.service.UserBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserBookController {

    private final UserBookService userBookService;

    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @GetMapping("/{user_id}/books")
    public List<Book> getUserBooks(@PathVariable int user_id) {
        return userBookService.getUserBooks(user_id);
    }

    @GetMapping("/{user_id}/books/{book_id}")
    public Book getUserBook(@PathVariable int user_id, @PathVariable int book_id) {
       return userBookService.getUserBook(user_id, book_id);
    }

    @PatchMapping("/{user_id}/books/{book_id}")
    public ResponseEntity<String> addBookToUser(@PathVariable int user_id, @PathVariable int book_id) {
        return userBookService.addBookToUser(user_id, book_id);
    }
}
