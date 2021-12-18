package com.filmplanner.models;

import java.util.*;

public class Project {

    private Long id;
    private String name;
    private String description;
    private Map<Long, User> managers;
    //private Client client;
    //private Set<Paperwork> paperworks;
    //private Set<Shoot> shoots;


    // TODO refactor the constructors
    public Project(String name, String description, Map<Long, User> managers) {
        this.name = name;
        this.description = description;
        this.managers = managers;
    }

    public Project(Long id, String name, String description) {
        this(id, name, description, new HashMap<>());
    }

    public Project(Long id, String name, String description, Map<Long, User> managers) {
        this(name, description, managers);
        this.id = id;
    }

    public Project(Long id, Project project) {
        this(id, project.getName(), project.getDescription());
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

    public List<User> getManagers() {
        return new ArrayList<>(managers.values());
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

    @Override
    public String toString() {
        String result = "id: " + this.id + "\nname: " + this.name + "\ndesc: " + this.description;
        for (User manager : this.managers.values()) {
            result += "\n" + manager.toString();
        }
        return result;
    }
}
