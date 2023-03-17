package org.lessons.java.event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private LocalDate date;
    private final int capacity;
    private int booked;

    public Event(String title, LocalDate date, int capacity) {
        this.title = title;
        this.date = date;
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBooked() {
        return booked;
    }

    public int getAvailable(){
        return capacity - booked;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date=" + getFormattedDate() +
                '}';
    }

    public void book() throws PastEventException, SoldOutException{
        if(LocalDate.now().isAfter(date)){
            throw new PastEventException("This event is no longer available.");
        }
        if(booked == capacity){
            throw new SoldOutException("Booking unsuccessful, this event is sold out.");
        }

        booked++;
    }

    public void cancel() throws PastEventException{
        if(LocalDate.now().isAfter(date)){
            throw new PastEventException("This event is no longer available.");
        }
        if (booked == 0){
            throw new NoBookingsException("Refund unsuccessful, there aren't enough bookings for this event");
        }
        booked--;

    }
}
