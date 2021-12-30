package com.filmplanner.models;

public class GearWithinAShoot {
    //Attributes:
    private long id;
    private long shootId;
    private String gearId;

    //Constructors:
    public GearWithinAShoot(long id, long shootId, String gearId) {
        this.id = id;
        this.shootId = shootId;
        this.gearId = gearId;
    }

    public GearWithinAShoot(long shootId, String gearId) {
        this(-1, shootId, gearId);
    }

    //Getters and Setters:
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShootId() {
        return shootId;
    }

    public void setShootId(long shootId) {
        this.shootId = shootId;
    }

    public String getGearId() {
        return gearId;
    }

    public void setGearId(String gearId) {
        this.gearId = gearId;
    }
}
