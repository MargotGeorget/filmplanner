package com.filmplanner.dao.postgre;

import com.filmplanner.dao.ClientDAO;
import com.filmplanner.models.Client;

import java.sql.Connection;
import java.util.ArrayList;
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
    public List<Client> findAll() {
        //TODO: connection data base
        List<Client> clients = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            clients.add( new Client("Company " + i, "desc", "Bernard", "Dumont", "bernard.dumont@gmail.com", "0203785412" ));
        }
        return clients;
    }

    @Override
    public Client update(String id, Client clientUpdated) {
        return null;
    }
}
