package com.filmplanner.dao;

import com.filmplanner.models.Client;

import java.util.List;

public interface ClientDAO {

    abstract long create(Client newClient);

    abstract Client findById(String id);

    abstract List<Client> findAll();

    abstract Client update(String id, Client clientUpdated);


}
