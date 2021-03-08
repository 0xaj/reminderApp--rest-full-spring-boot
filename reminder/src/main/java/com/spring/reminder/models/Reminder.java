package com.spring.reminder.models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Reminder {

    @Id
    int id;
    private Date date;
    private LocalTime time;
    private String message;

    @ManyToOne(fetch = FetchType.EAGER,
              cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Event event;

    public Reminder(int id, Date date, LocalTime time, String message, Event event) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.message = message;
        this.event = event;
    }

    public Reminder() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}