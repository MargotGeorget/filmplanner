package com.filmplanner.models;


import com.filmplanner.exceptions.InvalidInputException;
import utils.ValidationUtils;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    private Map<User, Role> members;
    private List<Gear> gears;
    private Long projectId;

    //Constructors:
    public Shoot(long id, String name, String description, String date, Location location, Map<User, Role> members, List<Gear> gears, Long projectId) throws InvalidInputException {
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

    public LocalDate getLocalDate(){
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date newdate =new Date();
        try {
            newdate = simpleDateFormat.parse(this.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Instant.ofEpochMilli(newdate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
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

    public Map<User, Role> getMembers() {
        return members;
    }

    public void setMembers(Map<User, Role> members) {
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

    @Override
    public String toString() {
        return this.name + "\n->" + this.description;
    }
}
