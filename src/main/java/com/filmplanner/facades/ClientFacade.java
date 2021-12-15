package com.filmplanner.facades;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.ClientDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Client;

import java.util.List;

public class ClientFacade {
    private static ClientFacade instance;
    private final AbstractDAOFactory daoFactory;
    private ClientDAO clientDAO;

    private ClientFacade() {
        this.daoFactory =  PostgreDAOFactory.getInstance();
        this.clientDAO = this.daoFactory.getClientDAO() ;
    }

    /**
     * Gets the single ClientFacade instance.
     * @return the single ClientFacade instance.
     */
    public static ClientFacade getInstance() {
        if (instance == null) {
            instance = new ClientFacade();
        }
        return instance;
    }

    //Functions:
    /**
     * Create a client in the datasource
     * @param newClient
     * @return long : new client's id
     */
    public long create(Client newClient){
        long idClient = clientDAO.create(newClient);
        return idClient;
    }

    /**
     * Returns the client with the id entered in the datasource
     * @param id
     * @return Client : the client find in the data base or null if no client has been found
     */
    public Client findById(long id){
        Client client = clientDAO.findById(id);
        return client;
    }

    /**
     * Returns all the clients saved in the datasource
     * @return List<Client>
     */
    public List<Client> findAll(){
        List<Client> clients = clientDAO.findAll();
        return clients;
    }

    /**
     * Modifies a client in the datasource
     * @param id long : the id of the client to be modified
     * @param clientUpdated : the modified information of the client
     * @return boolean : the client with his new information
     */
    public boolean update(long id, Client clientUpdated){
        boolean client = clientDAO.update(id, clientUpdated);
        return client;
    }

    /**
     * Delete a client in the datasource
     * @param id long : the id of the client to be deleted
     * @return boolean : true if the client was in the datasource and it has been deleted
     *                  or false if the client could not be found or could not be removed
     */
    public boolean delete(long id) {
        boolean res = clientDAO.delete(id);
        return res;
    }
}
