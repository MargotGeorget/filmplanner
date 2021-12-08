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
    public Client create(Client newClient){
        return null;
    }

    public Client findById(String id){
        return null;
    }

    public List<Client> findAll(String id){
        return null;
    }

    public Client update(String id, Client clientUpdated){
        return null;
    }
}
