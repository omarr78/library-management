package com.omar.library.library_management.service;

import com.omar.library.library_management.entity.Book;
import com.omar.library.library_management.entity.User;
import com.omar.library.library_management.exception.ResourceAlreadyExistException;
import com.omar.library.library_management.exception.ResourceNotFoundException;
import com.omar.library.library_management.jpa.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final BookService bookService;

    public UserBookService(UserRepository userRepository, UserService userService, BookService bookService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    public List<Book> getUserBooks(int user_id){
        User user = userService.checkUserExist(user_id);
        return user.getBooks();
    }

    public Book getUserBook(int user_id, int book_id){
        User user = userService.checkUserExist(user_id);
        Book book = bookService.checkBookExist(book_id);

        if(!user.getBooks().contains(book)) {
            throw new ResourceNotFoundException
                    ("Book with ID " + book_id + " does not belong to User with ID " + user_id);
        }
        return book;
    }

    public ResponseEntity<String> addBookToUser(int user_id, int book_id) {
        User user = userService.checkUserExist(user_id);
        Book book = bookService.checkBookExist(book_id);

        if(user.getBooks().contains(book)) {
            throw new ResourceAlreadyExistException
                    ("Book with ID " + book_id + " already belongs to User with ID " + user_id);
        }

        user.getBooks().add(book);
        userRepository.save(user);

        return ResponseEntity.ok("Book added to user successfully");
    }
}
