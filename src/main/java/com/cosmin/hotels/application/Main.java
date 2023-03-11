package com.cosmin.hotels.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        String myDate = "31/12/2023";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate myLocalDate = LocalDate.parse(myDate, dateTimeFormatter);

        System.out.println("MyLocalDate: "+myLocalDate);
    }

}
