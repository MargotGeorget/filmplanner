package com.filmplanner.models;

import java.util.HashSet;
import java.util.Set;

public class Project {

    private Long id;
    private String name;
    private String description;
    private Set<User> managers;
    //private Client client;
    //private Set<Paperwork> paperworks;
    //private Set<Shoot> shoots;


    public Project(String name, String description, User manager) {
        this.name = name;
        this.description = description;
        this.managers = new HashSet<>();
        this.managers.add(manager);
    }

    public Project(String name, String description, Set<User> managers) {
        this.name = name;
        this.description = description;
        this.managers = managers;
    }

    public Project(Long id, String name, String description, Set<User> managers) {
        this(name, description, managers);
        this.id = id;
    }

    public Project(Long id, Project project) {
        this(id, project.getName(), project.getDescription(), project.getManagers());
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

    public Set<User> getManagers() {
        return managers;
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

    public void setManagers(Set<User> managers) {
        this.managers = managers;
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
        this.managers.add(manager);
    }

    @Override
    public String toString() {
        String result = "id: " + this.id + "\nname: " + this.name + "\ndesc: " + this.description;
        for (User manager : this.managers) {
            result += "\n" + manager.toString();
        }
        return result;
    }
}
