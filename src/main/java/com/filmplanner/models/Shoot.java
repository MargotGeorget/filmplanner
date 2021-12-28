package com.filmplanner.models;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shoot {
    //Attributes:
    private long idShoot;
    private String name;
    private String description;
    private String date;
    private Location location;
    private Map<User, String> members; //TODO: Changer string par role
    private List<Gear> gears;
    private Project project;

    //Constructors:
    public Shoot(long id, String name, String description, String date, Location location, Map<User, String> members, List<Gear> gears, Project project) {
        this.setIdShoot(id);
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.members = members;
        this.gears = gears;
        this.project = project;
    }

    public Shoot(long id, String name, String description, String date, Location location, List<Gear> gears, Project project) {
        this(id, name,description,date, location, new HashMap<>(), gears, project);
    }

    public Shoot(long id, String name, String description, String date, Location location, Project project) {
        this(id, name,description,date, location, new HashMap<>(), new ArrayList<>(), project);
    }

    public Shoot(String name, String description, String date, Location location, Project project) {
        this(-1, name,description,date, location, new HashMap<>(), new ArrayList<>(), project);
    }

    //Getters and setters:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Map<User, String> getMembers() {
        return members;
    }

    public void setMembers(Map<User, String> members) {
        this.members = members;
    }

    public List<Gear> getGears() {
        return gears;
    }

    public void setGears(List<Gear> gears) {
        this.gears = gears;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getIdShoot() {
        return idShoot;
    }

    public void setIdShoot(long idShoot) {
        this.idShoot = idShoot;
    }
}
