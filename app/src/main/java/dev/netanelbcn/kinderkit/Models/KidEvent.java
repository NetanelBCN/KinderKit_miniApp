package dev.netanelbcn.kinderkit.Models;

public class KidEvent {
    private String eventTitle;
    private MyDate eventDate;

    public KidEvent() {
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public KidEvent setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
        return this;
    }

    public MyDate getEDate() {
        return eventDate;
    }

    public KidEvent setEDate(int day, int month, int year) {
        this.eventDate = new MyDate(day, month, year);
        return this;
    }


}
