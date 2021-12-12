package com.filmplanner.dao;

import com.filmplanner.models.Gear;
import com.filmplanner.models.Shooting;

import java.util.ArrayList;

public interface GearDAO {
    void createGear(Gear newGear);

    Gear findGearById(int id);

    ArrayList<Gear> findManyGearByShooting(Shooting shooting);

    ArrayList<Gear> findAllGear();

    void deleteGear(int id);

    void updateGear(int id, Gear gear);


}
