package com.spring.reminder.controllers;

import com.spring.reminder.models.Event;
import com.spring.reminder.services.EventServices;
import jakarta.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {


    final
    EventServices eventServices;

    public EventController(EventServices eventServices) {
        this.eventServices = eventServices;
    }


    @GetMapping("/{eventID}")
    public Event getEvents(@PathVariable("eventID") int eventID){
        return  eventServices.getEvent(eventID);
    }

    @GetMapping()
    public List<Event> findAllEvents(@QueryParam("page") int page,
                                     @QueryParam("size") int size) {
        return eventServices.getAllEvents(page, size);
    }

    @PostMapping()
    public Event setEvent(@RequestBody Event event){
        return eventServices.addEvent(event);
    }

    @PutMapping("/{eventID}")
    public Event updateEvent(@RequestBody Event event, @PathVariable int eventID ){
        return eventServices.updateEvent(eventID, event);
    }

    @DeleteMapping("/{eventID}")
    public void deleteEvent(@PathVariable("eventId") int eventId) {
        eventServices.deleteEvent(eventId);
    }



}
