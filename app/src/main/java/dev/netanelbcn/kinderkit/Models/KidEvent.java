package dev.netanelbcn.kinderkit.Models;

import java.util.Date;

public class KidEvent {
    private String eventTitle;
    private Date date;

    public KidEvent() {
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public KidEvent setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public KidEvent setDate(Date date) {
        this.date = date;
        return this;
    }


}
