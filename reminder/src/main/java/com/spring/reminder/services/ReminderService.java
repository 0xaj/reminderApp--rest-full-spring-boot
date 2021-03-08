package com.spring.reminder.services;

import com.spring.reminder.models.Event;
import com.spring.reminder.models.Reminder;
import com.spring.reminder.repositories.EventRepository;
import com.spring.reminder.repositories.ReminderRepository;
import com.spring.reminder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Service
public class ReminderService {

    final ReminderRepository reminderRepository;
    final EventRepository eventRepository;

    @Autowired
    public ReminderService(ReminderRepository reminderRepository, EventRepository eventRepository) {
        this.reminderRepository = reminderRepository;
        this.eventRepository = eventRepository;
    }
    public Reminder addReminder(int eventId, Reminder reminder){

        return eventRepository.findById(eventId).map(Event -> {
            reminder.setEvent(Event);
            return reminderRepository.save(reminder);
        }).orElseThrow(() -> new ResourceNotFoundException("Event [eventId=" + eventId + "] can't be found"));
    }


    public List<Reminder> getAllReminders(int eventId, int page , int size){

        List<Reminder> reminderList = reminderRepository.findAllById(Collections.singleton(eventId));
        if(size <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + size);
        }
        int fromIndex = (page - 1) * size;
        if(reminderList == null || reminderList.size() <= fromIndex){
            return Collections.emptyList();
        }
        return reminderList.subList(fromIndex, Math.min(fromIndex + size, reminderList.size()));
    }

    public Reminder getReminder(int eventId, int reminderId) {
        return reminderRepository.getByEventAndReminderId(eventId, reminderId);
    }

    public Reminder updateReminder(int eventId, int reminderId, Reminder reminder){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event [eventId="+eventId+"] can't be found"));
        return reminderRepository.findById(reminderId).map(Reminder ->{
            reminder.setEvent(event);
            reminderRepository.save(reminder);
            return reminder;
        }).orElseThrow(() -> new ResourceNotFoundException("Reminder [reminderId="+reminderId+"] can't be found"));
    }

    public ResponseEntity<?> deleteReminder(int eventId, int reminderId){
        if (!eventRepository.existsById(eventId)) {
            throw new ResourceNotFoundException("event [eventId=" + eventId + "] can't be found");
        }
        return reminderRepository.findById(reminderId).map(Reminder -> {
            reminderRepository.delete(Reminder);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Reminder [reminderId=" + reminderId + "] can't be found"));
    }
}

