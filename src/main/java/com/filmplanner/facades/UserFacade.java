package com.filmplanner.facades;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.UserDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.User;

import java.util.List;

public class UserFacade {
    private static UserFacade instance;
    private final AbstractDAOFactory daoFactory;
    private final UserDAO userDAO;

    private UserFacade() {
        this.daoFactory =  PostgreDAOFactory.getInstance();
        this.userDAO = this.daoFactory.getUserDAO() ;
    }

    /**
     * Gets the single UserFacade instance.
     * @return the single UserFacade instance.
     */
    public static UserFacade getInstance() {
        if (instance == null) {
            instance = new UserFacade();
        }
        return instance;
    }

    /**
     * @param newUser
     * @return 1 if successful, 0 if not
     * creates user in the database
     */
    //Functions:
    public long create(User newUser){
        long idUser = userDAO.create(newUser);
        return idUser;
    }

    /**
     * @param id
     * @return user with the id provided
     */
    public User findById(Long id){
        User user = userDAO.findById(id);
        return user;
    }

    /**
     * @return all users
     */
    public List<User> findAll(){
        List<User> users = userDAO.findAll();
        return users;
    }

    /**
     * @param id of the user to update
     * @param userUpdated, id of the user to update
     * @return 1 if successfull, 0 if not
     */
    public long update(Long id, User userUpdated){
        try {
            userDAO.update(id, userUpdated);
            return 1;
        }catch(Exception e) {
            return 0;
        }

    }

    /**
     * @param id of the user to delete
     * @return 1 if successful, 0 if not
     */
    public long delete(Long id){
        return userDAO.deleteById(id);
    }
}