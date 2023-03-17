package org.lessons.java.event;

public class SoldOutException  extends RuntimeException{
    public SoldOutException(String message){
        super(message);
    }
}
