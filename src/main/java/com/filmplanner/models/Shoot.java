package com.filmplanner.models;


import com.filmplanner.exceptions.InvalidInputException;
import utils.ValidationUtils;

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
    private Long projectId;

    //Constructors:
    public Shoot(long id, String name, String description, String date, Location location, Map<User, String> members, List<Gear> gears, Long projectId) throws InvalidInputException {
        this.setIdShoot(id);
        this.setName(name);
        this.setDescription(description);
        this.setDate(date);
        this.setLocation(location);
        this.setMembers(members);
        this.setGears(gears);
        this.setProjectId(projectId);
    }

    public Shoot(long id, String name, String description, String date, Location location, List<Gear> gears, Long projectId) throws InvalidInputException {
        this(id, name,description,date, location, new HashMap<>(), gears, projectId);
    }

    public Shoot(long id, String name, String description, String date, Location location, Long projectId) throws InvalidInputException {
        this(id, name,description,date, location, new HashMap<>(), new ArrayList<>(), projectId);
    }

    public Shoot(String name, String description, String date, Location location, Long projectId) throws InvalidInputException {
        this(-1, name,description,date, location, new HashMap<>(), new ArrayList<>(), projectId);
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

    public void setDate(String date) throws InvalidInputException {
        if(!ValidationUtils.isDate(date)){
            throw new InvalidInputException("The date must be in xx/xx/xxx format");
        }
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

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public long getIdShoot() {
        return idShoot;
    }

    public void setIdShoot(long idShoot) {
        this.idShoot = idShoot;
    }
}
