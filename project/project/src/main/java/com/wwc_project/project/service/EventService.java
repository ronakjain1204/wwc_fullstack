package com.wwc_project.project.service;

import com.wwc_project.project.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventService {

    private List<Event> events = new ArrayList<>();

    public List<Event> getAllEvents() {
        return events;
    }

    public Event getEventById(int id){
        return events.stream()
                .filter(event -> event.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Event addEvent(Event event){
        events.add(event);
        return event;
    }

    public boolean deleteEvent(int id){
        return events.removeIf(event -> event.getId() == id);
    }

    

    
}
