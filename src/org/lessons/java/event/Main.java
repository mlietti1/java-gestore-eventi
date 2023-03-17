package org.lessons.java.event;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to your event manager!");
        System.out.print("Add the event title: ");
        String title = scan.nextLine();
        boolean correct = false;
        String strDate = "";
        while (!correct){
            System.out.println("Add the event date: ");
            try{
                System.out.print("day : ");
                int day = Integer.parseInt(scan.nextLine());
                while(day < 1 || day > 31) {
                    System.out.println("Invalid day. Please enter a valid day between 1 and 31.");
                    System.out.print("day : ");
                    day = Integer.parseInt(scan.nextLine());
                }
                System.out.println("month: ");
                int month = Integer.parseInt(scan.nextLine());
                while(month < 1 || month > 12) {
                    System.out.println("Invalid month. Please enter a valid month between 1 and 12.");
                    System.out.println("month: ");
                    month = Integer.parseInt(scan.nextLine());
                }
                System.out.println("year: ");
                int year = Integer.parseInt(scan.nextLine());
                while(year < 1900 || year > 2100) {
                    System.out.println("Invalid year. Please enter a valid year between 1900 and 2100.");
                    System.out.println("year: ");
                    year = Integer.parseInt(scan.nextLine());
                }
                if (month == 2 && day > 28){
                    System.out.println("Invalid date. Please enter a valid date.");
                }
                if ((month == 9 || month == 11 || month == 4 || month == 6) && day > 30){
                    System.out.println("Invalid date. Please enter a valid date.");
                }

                if(month < 10 && day < 10){
                    strDate = year + "-" + "0" + month + "-" + "0" + day;
                }else if(day < 10){
                    strDate = year + "-" + month + "-" + "0" + day;
                }else if(month < 10){
                    strDate = year + "-" + "0" + month + "-" + day;
                }else{
                    strDate = year + "-" + month + "-" + day;
                }
                correct = true;
            }catch (NumberFormatException e) {
                System.out.println("Invalid date format.");
            }
        }

        boolean invalidCapacity = true;
        String capacityStr = "";
        while(invalidCapacity){
            try {
                System.out.print("Add the venue capacity: ");
                capacityStr = scan.nextLine();
                while (Integer.parseInt(capacityStr) < 1){
                    System.out.println("The capacity must be greater than 0.");
                    System.out.print("Add the venue capacity: ");
                    capacityStr = scan.nextLine();
                }
                invalidCapacity = false;
            }catch (RuntimeException e){
                System.out.println("Invalid capacity number.");
            }
        }

        LocalDate date = LocalDate.parse(strDate);
        int capacity = Integer.parseInt(capacityStr);

        Event event = new Event(title, date, capacity);

        System.out.println("Your event was created successfully.");

        System.out.println("Title: " + event.getTitle());
        System.out.println("Date: " + event.getFormattedDate());
        System.out.println("Capacity: " + event.getCapacity());

        System.out.println("Press \"1\" to add a new booking or \"2\" to cancel a booking or \"3\" to exit.");

        String option;
        do{
            option = scan.nextLine();
            while (!option.equals("1") && !option.equals("2") && !option.equals("3")) {
                System.out.println("Command not found");
                System.out.println("Press \"1\" to add a new booking or \"2\" to cancel a booking or \"3\" to exit.");
                option = scan.nextLine();
            }
            switch (option) {
                case "1" -> {
                    String quantityStr = "";
                    boolean invalidInput = true;
                    while(invalidInput){
                        try {
                            System.out.print("How many tickets do you want to book? ");
                            quantityStr = scan.nextLine();
                            while (Integer.parseInt(quantityStr) < 1){
                                System.out.println("The amount must be greater than 0.");
                                System.out.print("How many tickets do you want to book? ");
                                quantityStr = scan.nextLine();
                            }
                            while (Integer.parseInt(quantityStr) > event.getAvailable()){
                                System.out.println("Sorry, not enough tickets available");
                                System.out.print("How many tickets do you want to book? ");
                                quantityStr = scan.nextLine();
                            }

                            invalidInput = false;
                        }catch (RuntimeException e){
                            System.out.println("Invalid amount number.");
                        }
                    }

                    int quantity = Integer.parseInt(quantityStr);

                    try {
                        for(int i = 0; i < quantity; i++ ){
                            event.book();
                        }

                        System.out.println("Your booking for the " + title + " event on " + event.getFormattedDate() + " was successful.");
                        System.out.println("There are " + event.getAvailable() + " tickets available." );
                        System.out.println("Sold tickets: " + event.getBooked());
                        System.out.println("Press \"1\" to add a new booking or \"2\" to cancel a booking or \"3\" to exit.");

                    }catch (SoldOutException | PastEventException e){
                        System.out.println(e.getMessage());
                        System.out.println("Press \"1\" to add a new booking or \"2\" to cancel a booking or \"3\" to exit.");
                    }
                }
                case "2" -> {
                    String quantityStr = "";
                    boolean invalidInput = true;
                    while(invalidInput){
                        try {
                            System.out.print("How many tickets do you want refunded? ");
                            quantityStr = scan.nextLine();
                            while (Integer.parseInt(quantityStr) < 1){
                                System.out.println("The amount must be greater than 0.");
                                System.out.print("How many tickets do you want refunded? ");
                                quantityStr = scan.nextLine();
                            }
                            while (Integer.parseInt(quantityStr) > capacity){
                                System.out.println("I'm sorry, there are not enough tickets booked.");
                                System.out.print("How many tickets do you want refunded? ");
                                quantityStr = scan.nextLine();
                            }
                            invalidInput = false;
                        }catch (RuntimeException e){
                            System.out.println("Invalid amount number.");
                        }
                    }

                    int quantity = Integer.parseInt(quantityStr);

                    try {
                        for(int i = 0; i < quantity; i++ ){
                            event.cancel();
                        }

                        System.out.println("Your refund for the " + title + " event on " + event.getFormattedDate() + " was successful.");
                        System.out.println("There are " + event.getAvailable() + " tickets available." );
                        System.out.println("Sold tickets: " + event.getBooked());
                        System.out.println("Press \"1\" to add a new booking or \"2\" to cancel a booking or \"3\" to exit.");

                    }catch (NoBookingsException | PastEventException e){
                        System.out.println(e.getMessage());
                        System.out.println("Press \"1\" to add a new booking or \"2\" to cancel a booking or \"3\" to exit.");
                    }
                }
                default -> System.out.println("Goodbye!");
            }
        }
        while (!option.equals("3"));

    }
}
