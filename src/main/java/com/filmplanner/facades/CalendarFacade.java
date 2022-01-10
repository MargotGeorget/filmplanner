package com.filmplanner.facades;

import com.calendarfx.model.Entry;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Project;
import com.filmplanner.models.Shoot;
import com.filmplanner.models.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CalendarFacade {
    private static CalendarFacade instance;
    private ProjectFacade projectFacade;
    private ShootFacade shootFacade;

    /**
     * Gets the single CalendarFacade instance.
     */
    private CalendarFacade() {
        this.projectFacade = ProjectFacade.getInstance();
        this.shootFacade = ShootFacade.getInstance();
    }

    /**
     * @param manager
     * @return the list of shoots from a manager, in an hashmap containing the name of the project as a key
     */
    private HashMap<String,List<Shoot>>  getShootsByManagerID(User manager) {
        HashMap<String,List<Shoot>>  shoots = new HashMap<String, List<Shoot>>();
        Project[] projects = projectFacade.findManyByManager(manager);
        for (Project p : projects) {
            shoots.put(p.getName(), shootFacade.findAllShootInProject(p.getId()));
        }
        return shoots;
    }

    /**
     *
     * @param shoots
     * @return An hashmap of shoots, with the key being the calendar it belongs to, and the value being a list of entries for this specific calendar
     */
    private HashMap<String,List<Entry<String>>> getShootsEntriesFromShootsList(HashMap<String,List<Shoot>>  shoots) {
        HashMap<String,List<Entry<String>>> shootEntries = new HashMap<String, List<Entry<String>>>();

        for (var entry : shoots.entrySet()) {
            shootEntries.put(entry.getKey(), new ArrayList<Entry<String>>());
            for(Shoot s : entry.getValue()) {
                Entry<String> newEntry = new Entry<>("Projet: " + entry.getKey()+ "\n\n\nShoot:" + s.getName() + "\n\n\n" +"Description:\n" +s.getDescription());
                newEntry.setInterval(s.getLocalDate());
                newEntry.changeStartTime(LocalTime.of(8,30));
                newEntry.changeEndTime(LocalTime.of(20,30));
                newEntry.setLocation(s.getLocation().getCity());
                shootEntries.get(entry.getKey()).add(newEntry);
            }
        }
        return shootEntries;
    }

    /**
     *
     * @param manager
     * @return An hashmap of shoots, with the key being the calendar it belongs to, and the value being a list of entries for this specific calendar, belonging to this manager
     */
    public HashMap<String,List<Entry<String>>> getShootsEntriesFromManagerId(User manager) {
        return getShootsEntriesFromShootsList(getShootsByManagerID(manager));
    }

    /**
     * Singleton instance
     * @return
     */
    public static CalendarFacade getInstance() {
        if (instance == null) {
            instance = new CalendarFacade();
        }
        return instance;
    }
}
