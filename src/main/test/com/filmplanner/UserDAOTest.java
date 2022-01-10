package com.filmplanner;

import com.filmplanner.dao.UserDAO;
import com.filmplanner.dao.mock.MockDAOFactory;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserDAOTest {

    @Test
    void loginTest() {
        UserDAO userDAO = MockDAOFactory.getInstance().getUserDAO();
        User user = userDAO.findByEmail("margot-georget@gmail.com");
        assertEquals(user.getPassword(), userDAO.findByEmail(user.getEmail()).getPassword());
    }
    @Test
    void creationTest() {

        assertThrows(Exception.class, () -> {
            User user = new User("person", "bademailcom", "06001", true);
        });
    }
}
