package com.filmplanner;

import com.filmplanner.exceptions.InvalidInputException;

import com.filmplanner.models.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RoleDAOTest {

    @Test
    void creationTest() {

        assertThrows(InvalidInputException.class, () -> {
            Role role = new Role("",true);
        });
    }
}