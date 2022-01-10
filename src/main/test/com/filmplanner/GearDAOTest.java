package com.filmplanner;


import com.filmplanner.exceptions.InvalidInputException;

import com.filmplanner.models.Gear;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GearDAOTest {

    @Test
    void creationTest() {
        assertThrows(InvalidInputException.class, () -> {
            Gear gear = new Gear("","SonyX3","Camera",true);
        });
    }
}