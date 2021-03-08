package com.spring.reminder.services;

import com.spring.reminder.models.Event;
import com.spring.reminder.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServices {

    final EventRepository eventRepository;

    @Autowired
    public EventServices(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

      public List<Event> getAllEvents(int page, int size) {
        /*Pageable paging = PageRequest.of(page,size);
        Page<Event> pagedResult = eventRepository.findAll(paging);
        return pagedResult.toList();*/
        return eventRepository.findAll();
    }


    public List<Event> getAllEvents() {
        /*Pageable paging = PageRequest.of(page,size);
        Page<Event> pagedResult = eventRepository.findAll(paging);
        return pagedResult.toList();*/
        return eventRepository.findAll();
    }

    public Event getEvent(int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("event Id not found"));

    }

    public Event updateEvent(int eventId, Event event) {
        return eventRepository.findById(eventId).map(Event -> {
            Event.setName(event.getName());
            Event.setType(event.getType());
            Event.setDate(event.getDate());
            eventRepository.save(event);
            return event;
        }).orElseThrow(() -> new ResourceNotFoundException("Event [eventId="+eventId+"] can't be found"));
    }

    public void deleteEvent(int eventId) {
        eventRepository.deleteById(eventId);
    }
}
