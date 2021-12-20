package com.filmplanner.models;

import java.util.*;

public class Shoot {
    //Attributes:
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Location location;
    private Map<User, String> members; //TODO: Changer string par role
    private List<Gear> gears;
    private Project project;

    //Constructors:
    public Shoot(String name, String description, Date startDate, Date endDate, Location location, Map<User, String> members, ArrayList<Gear> gears, Project project) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.members = members;
        this.gears = gears;
        this.project = project;
    }

    public Shoot(String name, String description, Date startDate, Date endDate, Location location, Project project) {
        this(name,description,startDate, endDate, location, new HashMap<>(), new ArrayList<>(), project);
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.project = project;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
}
