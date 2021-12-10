package com.filmplanner.models;

import java.util.Set;

public class Project {

    private String name;
    private String description;
    private Set<User> managers;
    //private Client client;
    //private Set<Paperwork> paperworks;
    //private Set<Shoot> shoots;


    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Project(String name, String description, Set<User> managers) {
        this(name, description);
        this.managers = managers;
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
}
