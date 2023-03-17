package org.lessons.java.event;

public class NoBookingsException  extends RuntimeException{
    public NoBookingsException(String message){
        super(message);
    }
}
