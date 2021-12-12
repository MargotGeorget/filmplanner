package com.filmplanner.dao.postgre;

import com.filmplanner.dao.ClientDAO;
import com.filmplanner.models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreClientDAO implements ClientDAO {

    private Connection connection;

    PostgreClientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long create(Client client) {

        String sql = "INSERT INTO client(company_name,description,referee_name, referee_email,referee_tel) " +
                "VALUES(?,?,?,?,?)";
        long id = 0;

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, client.getCompanyName());
            stmt.setString(2, client.getDescription());
            stmt.setString(3, client.getRefereeName());
            stmt.setString(4, client.getRefereeEmail());
            stmt.setString(5, client.getRefereeTel());

            ResultSet rs = stmt.executeQuery();
            //check the affected rows
            if(rs != null ){
                //get the ID back
                if(rs.next()){
                    id = rs.getLong(1);
                }
            }else {
                id = -1;
            }
            System.out.println("Operation done successfully");

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Client findById(String id) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        //Création de la liste de client
        List<Client> clients = new ArrayList<>();
        try {
            //création du statement qui permet d'effectuer les requêtes mySQL
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM client;");

            //exécution de la requête
            ResultSet rs = stmt.executeQuery();

            //boucle pour récupérer tout les clients
            while(rs.next()){
                long idClient = rs.getLong("client_id");
                String companyName = rs.getString("company_name");
                String refereeName = rs.getString("referee_name");
                String refereeEmail = rs.getString("referee_email");
                String refereeTel = rs.getString("referee_tel");
                String description = rs.getString("description");

                Client client = new Client(idClient, companyName, description, refereeName, refereeEmail, refereeTel);
                clients.add(client);
            }
            rs.close();
            //TODO: est ce qu'il faut fermer statement aussi?
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Operation done successfully");
        return clients;
        /*
        //TODO: connection data base
        List<Client> clients = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            clients.add( new Client("Company " + i, "desc", "Bernard", "bernard.dumont@gmail.com", "0203785412" ));
        }
        return clients;
        */
    }

    @Override
    public Client update(String id, Client clientUpdated) {
        return null;
    }
}
