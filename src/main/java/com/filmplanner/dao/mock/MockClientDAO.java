package com.filmplanner.dao.mock;

import com.filmplanner.dao.ClientDAO;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.Client;

import java.util.HashMap;
import java.util.List;

public class MockClientDAO implements ClientDAO {

    private final HashMap<Number, Client> clients;

    // The constructor must be package-private so only the PostgreDAOFactory can create a new instance.
    MockClientDAO() {
        this.clients = new HashMap<>();
        try {
            clients.put(1, new Client("Toto", "une super description", "toto", "toto@gmail.com", "06888888"));
            clients.put(2, new Client("Ruby","description", "margot", "margot-georget@gmail.com", "0671234567"));
        } catch (InvalidInputException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public long create(Client newClient) {
        return 0;
    }

    @Override
    public Client findById(long id) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public boolean update(long id, Client clientUpdated) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
