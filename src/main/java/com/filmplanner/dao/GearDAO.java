package com.filmplanner.dao;

import com.filmplanner.models.Gear;
import com.filmplanner.models.Shooting;

import java.util.ArrayList;

public interface GearDAO {
    boolean createGear(Gear newGear);

    Gear findGearById(String id);

    ArrayList<Gear> findManyGearByShooting(Shooting shooting);

    ArrayList<Gear> findAllGear();

    boolean deleteGear(String id);

    boolean updateGear(String id, Gear gear);


}
