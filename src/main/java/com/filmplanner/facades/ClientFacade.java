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
    public long create(Client newClient){
        long idClient = clientDAO.create(newClient);
        return idClient;
    }

    public Client findById(long id){
        Client client = clientDAO.findById(id);
        return client;
    }

    public List<Client> findAll(){
        List<Client> clients = clientDAO.findAll();
        return clients;
    }

    public Client update(long id, Client clientUpdated){
        Client client = clientDAO.update(id, clientUpdated);
        return client;
    }
}
