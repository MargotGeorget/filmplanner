package com.filmplanner.dao;


import com.filmplanner.models.User;
import java.util.List;

public interface UserDAO {
    /**
     * Returns the user with the email entered in the datasource
     * @param email string
     * @return User : the user find in the datasource or null if no user has been found
     */
    User findByEmail(String email);

    /**
     * Modifies a user in the datasource
     * @param id long : the id of the user to be modified
     * @param updatedUser : the modified information of the user
     * @return int : >0 if the user was in the datasource and it has been updated
     *            or 0 if the user could not be found or could not be updated
     */
    int update(Long id, User updatedUser);

    /**
     * Returns all the users saved in the datasource
     * @return List<User>
     */
    List<User> findAll();

    /**
     * Returns the user with the id entered in the datasource
     * @param id long
     * @return User : the user find in the datasource or null if no user has been found
     */
    User findById(Long id);

    /**
     * @param newUser
     * @return 1 if successful, 0 if not
     * creates user in the database
     */
    Long create(User newUser);

    /**
     * Delete a user in the datasource
     * @param id long : the id of the user to be deleted
     * @return int : 0 if the user could not be found or could not be removed; otherwise >0
     */
    int deleteById(Long id);

    /**
     * Return all user registered in the datasource and who are not assigned to a shoot for a given date
     * @param date
     * @return List<User>
     */
    List<User> allUserAvailableForDate(String date);


}
