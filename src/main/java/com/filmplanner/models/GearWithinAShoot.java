package com.filmplanner.models;

public class GearWithinAShoot {
    //Attributes:
    private long id;
    private long shootId;
    private long gearId;

    //Constructors:
    public GearWithinAShoot(long id, long shootId, long gearId) {
        this.id = id;
        this.shootId = shootId;
        this.gearId = gearId;
    }

    public GearWithinAShoot(long shootId, long gearId) {
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

    public long getGearId() {
        return gearId;
    }

    public void setGearId(long gearId) {
        this.gearId = gearId;
    }
}
