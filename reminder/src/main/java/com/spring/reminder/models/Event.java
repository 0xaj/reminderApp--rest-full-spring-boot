package com.spring.reminder.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Event {

    @Id
    int id;
    String name;
    private String type;
    Date date;

    public Event(int id, String name, String type, Date date) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.date = date;
    }

    public Event() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
