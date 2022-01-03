package com.filmplanner.controllers;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.page.WeekPage;

import com.filmplanner.facades.CalendarFacade;
import com.filmplanner.facades.LoginFacade;
import com.filmplanner.models.Shoot;
import com.filmplanner.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class CalendarController  implements Initializable {
    private CalendarFacade calendarFacade;
    @FXML
    private WeekPage Calendrier;

    public CalendarController() {this.calendarFacade = CalendarFacade.getInstance();}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User currentUser = LoginFacade.getInstance().getCurrentUser();
        HashMap<String, List<Entry<String>>> shootEntries = this.calendarFacade.getShootsEntriesFromManagerId(currentUser);
        List<Calendar> calendars = new ArrayList<>();
        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        //Filling entries in my calendar
        int i = 0;
        for (var entry : shootEntries.entrySet()) {
            Calendar cal = new Calendar(entry.getKey());
            cal.setStyle(Calendar.Style.getStyle(i));
            i++;
            for(Entry<String> e : entry.getValue()) {
                cal.addEntry(e);
            }
            myCalendarSource.getCalendars().add(cal);
        }


        //Disabling unwanted features
        Calendrier.setContextMenuCallback(null);
        Calendrier.setEntryFactory(param ->null);
        Calendrier.setEntryDetailsPopOverContentCallback(param ->{return null;});
        Calendrier.setDraggedEntry(null);
        Calendrier.setEntryEditPolicy((param) -> {return false;});
        Calendrier.setEntryContextMenuCallback((param) -> {return null;});
        Calendrier.setEntryDetailsCallback((param) -> {return false;});
        Calendrier.setZoneId(ZoneId.of("Europe/London"));


        //SETTING DATA from the shoots to the view
        Calendrier.getCalendarSources().setAll(myCalendarSource);


    }
}
