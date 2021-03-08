package com.spring.reminder.controllers;

import com.spring.reminder.models.Reminder;
import com.spring.reminder.services.ReminderService;
import jakarta.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{eventId}/reminders")
public class ReminderController {
    @Autowired
    private ReminderService reminderService;

    @PostMapping("/{eventId}/reminders")
    public Reminder createReminder(@PathVariable("eventId") int eventId,
                                   @RequestBody Reminder reminder) {
        return reminderService.addReminder(eventId, reminder);
    }

    @GetMapping("/{eventId}/reminders")
    public List<Reminder> findAllReminders(@PathVariable("eventId") int eventId,
                                           @QueryParam("page") int page,
                                           @QueryParam("size") int size) {
        return reminderService.getAllReminders(eventId, page, size);
    }

    @GetMapping("/{eventId}/reminders/{reminderId}")
    public Reminder findReminder(@PathVariable("eventId") int eventId,
                                 @PathVariable("reminderId") int reminderId) {
        return  reminderService.getReminder(eventId, reminderId);
    }

    @PutMapping("/{eventId}/reminders/{reminderId}")
    public Reminder modifyReminder(@PathVariable("eventId") int eventId,
                                   @PathVariable("reminderId") int reminderId,
                                   @RequestBody Reminder reminder) {
        return reminderService.updateReminder(eventId, reminderId, reminder);
    }

    @DeleteMapping("/{eventId}/reminders/{reminderId}")
    public ResponseEntity<?> deleteReminder(@PathVariable("eventId") int eventId,
                                            @PathVariable("reminderId") int reminderId) {
        return reminderService.deleteReminder(eventId, reminderId);
    }
}



