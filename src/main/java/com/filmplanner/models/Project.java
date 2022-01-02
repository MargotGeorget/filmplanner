package com.filmplanner.models;

import java.util.*;

public class Project {

    private Long id;
    private String name;
    private String description;
    private Client client;
    private Map<Long, User> managers;
    private Set<Shoot> shoots;

    public Project(Long id, String name, String description, Client client, Map<Long, User> managers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.client = client;
        this.managers = managers;
        this.shoots = new HashSet<>();
    }

    public Project(String name, String description, Client client, Map<Long, User> managers) {
        this(null, name, description, client, managers);
    }

    public Project(Long id, String name, String description, Client client) {
        this(id, name, description, client, new HashMap<>());
    }

    public Project(Long id, Project project) {
        this(id, project.getName(), project.getDescription(), project.getClient());
    }


    /*
    Getters
     */

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Client getClient() {
        return client;
    }

    public List<User> getManagers() {
        return new ArrayList<>(managers.values());
    }

    public List<Shoot> getShoots() {
        return new ArrayList<>(this.shoots);
    }

    public Long getId() {
        return id;
    }

    /*
    Setters
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /*
    Methods
     */

    /**
     * Adds a manager to the project.
     *
     * @param manager an instance of User to add as a manager
     */
    public void addManager(User manager) {
        this.managers.put(manager.getId(), manager);
    }

    /**
     * Removes a manager from the project.
     *
     * @param manager an instance of User to remove as a manager
     */
    public void removeManager(User manager) {
        this.managers.remove(manager.getId());
    }

    public void addShoot(Shoot shoot) {
        this.shoots.add(shoot);
    }

    public void removeShoot(Shoot shoot) {
        this.shoots.remove(shoot);
    }


    @Override
    public String toString() {
        String result = "id: " + this.id + "\nname: " + this.name + "\ndesc: " + this.description;
        for (User manager : this.managers.values()) {
            result += "\n" + manager.toString();
        }
        return result;
    }
}
