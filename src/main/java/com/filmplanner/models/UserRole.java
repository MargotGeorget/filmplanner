package com.filmplanner.models;


public class UserRole {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final Long id;
    private final String role;
    private final User user;

    public UserRole(User user, Role role) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.id = user.getId();
        this.phoneNumber = user.getPhoneNumber();
        this.role = role.getName();
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getId() {
        return id;
    }
}
