package com.spring.reminder.repositories;

import com.spring.reminder.models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface ReminderRepository extends JpaRepository<Reminder, Integer> {

    @Query(value = "SELECT * FROM Reminder r, Event e where r.event_id = e.id and r.event_id = ?1", nativeQuery = true)
    Reminder getAllByEventId(int eventId, int reminderId);

    @Query(value =" SELECT * FROM Reminder  r WHERE  r.event_id = ?1 and r.id = ?2", nativeQuery = true)
    Reminder getByEventAndReminderId(Integer eventId, Integer reminderId);
}
