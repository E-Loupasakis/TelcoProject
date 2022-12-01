package gr.codehub.telco.telcoproject;


import gr.codehub.telco.telcoproject.model.Customer;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Date;

public class TestModels {

    public static void main(String[] args) {


       /* Date currentDate = new Date();

        System.out.println("Current Date: "+currentDate);

        String dateToStr = DateFormat.getInstance().format(currentDate);

        System.out.println("Date Format using getInstance(): "+dateToStr);*/

        Timestamp timestamp = new Timestamp(5000);

        System.out.println(timestamp.getTime());

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));




    }
}
