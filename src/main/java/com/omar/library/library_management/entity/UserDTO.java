package com.omar.library.library_management.entity;

public class UserDTO {
    private int id;
    private String username;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.id = user.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
