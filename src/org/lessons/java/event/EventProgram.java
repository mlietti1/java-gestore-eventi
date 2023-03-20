package org.lessons.java.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventProgram {
    private String name;
    private List<Event> events;

    public EventProgram(String name) {
        this.name = name;
        this.events = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEvent(Event e){
        if(e == null){
            throw new IllegalArgumentException("Event must not be null.");
        }
        events.add(e);
    }

    public boolean removeEvent(Event e){
        return events.remove(e);
    }

    public List<Event> filterEventsByDate(LocalDate date){
        List<Event> filteredEvents = new ArrayList<>();
        for(Event e : events){
            if(e.getDate().equals(date)){
                filteredEvents.add(e);
            }
        }
        return filteredEvents;
    }

    public int getNumberOfEvents(){
        return events.size();
    }

    public void clearEvents(){
        events.clear();
    }

    @Override
    public String toString() {
        String str = getName() + "\n";
        List<Event> orderedEvents = new ArrayList<>();
        orderedEvents.addAll(events);
        Collections.sort(events);
        // appendo eventi ordinati per data
        for(Event e : orderedEvents){
            str += e.toString() + "\n";
        }
        return str;
    }
}
