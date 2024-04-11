package dev.netanelbcn.kinderkit.Models;

public class KidEvent {
    private String eventTitle;
    private MyDate eventDate;
    private String eId;

    public KidEvent() {
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public KidEvent setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
        this.eId = this.eventTitle + this.getEDate().getDay() + this.getEDate().getMonth() + this.getEDate().getYear();
        return this;
    }

    public MyDate getEDate() {
        return eventDate;
    }

    public KidEvent setEDate(int day, int month, int year) {
        this.eventDate = new MyDate(day, month, year);
        this.eId = this.eventTitle + this.getEDate().getDay() + this.getEDate().getMonth() + this.getEDate().getYear();
        return this;
    }

    public KidEvent setEDate(MyDate date) {
        this.eventDate = date;
        this.eId = this.eventTitle + this.getEDate().getDay() + this.getEDate().getMonth() + this.getEDate().getYear();
        return this;

    }

    @Override
    public String toString() {
        return "KidEvent{" +
                "eventTitle='" + eventTitle + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }

    public String geteId() {
        return this.eId;
    }

    public KidEvent seteId(String eId) {
        this.eId = eId;
        return this;
    }
}
