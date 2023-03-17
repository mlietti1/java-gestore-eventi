package org.lessons.java.event;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concert extends Event{

    private final LocalTime time;
    private final BigDecimal price;

    public Concert(String title, int date, int capacity, String time, double price) {
        super(title, LocalDate.ofEpochDay(date), capacity);
        this.time = LocalTime.parse(time);
        this.price = BigDecimal.valueOf(price);
    }

    public LocalTime getTime() {
        return time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getFormattedDateTime(){
        LocalDateTime dateTime = LocalDateTime.of(getDate(), getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        return dateTime.format(formatter);
    }

    public String getFormattedPrice(){
        DecimalFormat format = new DecimalFormat("##.##€");
        return format.format(price);
    }

    @Override
    public String toString() {
        return "Concert " + getTitle() + " full date: " + getFormattedDateTime() + "\n" + "Price: " + getFormattedPrice();
    }
}
