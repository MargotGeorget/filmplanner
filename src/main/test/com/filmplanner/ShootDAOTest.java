package com.filmplanner;

import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.Client;
import com.filmplanner.models.Location;
import com.filmplanner.models.Shoot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShootDAOTest {

    @Test
    void creationTest() {

        assertThrows(InvalidInputException.class, () -> {
            Location location = new Location(34, "rue", "ville", "code");
            Shoot shoot = new Shoot("Toto", "une super description", "02/12/22", location , 1L);
        });
    }
}
