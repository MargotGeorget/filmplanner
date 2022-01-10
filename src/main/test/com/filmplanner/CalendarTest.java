package com.filmplanner;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarTest {

    @Test
    void addingEntryTest(){
        Calendar calendar = new Calendar("appointments");
        Entry<String> dentistAppointment = new Entry<>("Dentist");
        calendar.addEntry(dentistAppointment);
        assertEquals(dentistAppointment.getTitle(),calendar.findEntries("Dentist").get(0).getTitle());
    }
}
