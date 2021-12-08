package com.filmplanner.dao.postgre;

import com.filmplanner.dao.ClientDAO;
import com.filmplanner.models.Client;

import java.sql.Connection;
import java.util.List;

public class PostgreClientDAO implements ClientDAO {

    private Connection connection;

    PostgreClientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Client create(Client newClient) {
        return null;
    }

    @Override
    public Client findById(String id) {
        return null;
    }

    @Override
    public List<Client> findAll(String id) {
        return null;
    }

    @Override
    public Client update(String id, Client clientUpdated) {
        return null;
    }
}
