package com.filmplanner.dao;

import com.filmplanner.models.Client;
import java.util.List;

public interface ClientDAO {

    /**
     * Create a client in the datasource
     * @param newClient
     * @return long : new client's id
     */
    long create(Client newClient);

    /**
     * Returns the client with the id entered in the datasource
     * @param id
     * @return Client : the client find in the datasource or null if no client has been found
     */
    Client findById(long id);

    /**
     * Returns all the clients saved in the datasource
     * @return List<Client>
     */
    List<Client> findAll();

    /**
     * Modifies a client in the datasource
     * @param id long : the id of the client to be modified
     * @param clientUpdated : the modified information of the client
     * @return boolean : true if the client was in the datasource and it has been updated
     *                        or false if the client could not be found or could not be updated
     */
    boolean update(long id, Client clientUpdated);

    /**
     * Delete a client in the datasource
     * @param id long : the id of the client to be deleted
     * @return boolean : true if the client was in the datasource and it has been deleted
     *                  or false if the client could not be found or could not be removed
     */
    boolean delete(long id);


}
