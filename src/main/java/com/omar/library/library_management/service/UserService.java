package com.omar.library.library_management.service;

import com.omar.library.library_management.entity.User;
import com.omar.library.library_management.entity.UserDTO;
import com.omar.library.library_management.exception.ResourceNotFoundException;
import com.omar.library.library_management.jpa.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkUserExist(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " Not found")
        );
    }

    public void checkUniqueUser(String username) {
        User newUser = userRepository.findByUsername(username);
        if (newUser != null) {
            throw new ResourceNotFoundException("username : " + username + " already exist");
        }
    }

    public User getUser(int id){
        return checkUserExist(id);
    }

    public ResponseEntity<UserDTO> createUser(User user) {
        checkUniqueUser(user.getUsername());

        User newUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<UserDTO> updateUser(int id, User updatedUser) {
        checkUserExist(id);
        checkUniqueUser(updatedUser.getUsername());

        updatedUser.setId(id);
        userRepository.save(updatedUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
