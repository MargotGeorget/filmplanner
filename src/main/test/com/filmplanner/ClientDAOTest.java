package com.filmplanner;

import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientDAOTest {

    @Test()
    void creationTest() {

        assertThrows(InvalidInputException.class, () -> {
            Client client = new Client("Toto", "une super description", "toto", "toto@gmail.com", "06888");
        });

    }
}
