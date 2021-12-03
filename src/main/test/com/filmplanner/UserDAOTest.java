package com.filmplanner;

import com.filmplanner.dao.UserDAO;
import com.filmplanner.dao.mock.MockDAOFactory;
import com.filmplanner.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserDAOTest {

    @Test
    void passwordValidationTest() {
        UserDAO userDAO = MockDAOFactory.getInstance().getUserDAO();
        User user = userDAO.find("margot-georget@gmail.com");
        assertEquals(user.getPassword(), userDAO.getPassword(user.getEmail()));
    }
}
