package com.omar.library.library_management.controller;

import com.omar.library.library_management.entity.User;
import com.omar.library.library_management.entity.UserDTO;
import com.omar.library.library_management.jpa.UserRepository;
import com.omar.library.library_management.service.UserBookService;
import com.omar.library.library_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;


    public UserController(UserRepository userRepository, UserService userService, UserBookService userBookService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> retrieveAllUsers() {
        return userRepository.findAll().stream().map(u -> new UserDTO(u)).toList();
    }

    @GetMapping("/{id}")
    public UserDTO retrieveUser(@PathVariable int id) {
        return new UserDTO (userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.checkUserExist(id);
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @Valid @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }
}
