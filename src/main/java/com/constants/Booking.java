package com.constants;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Booking {

    @CsvBindByName
    private String testScenario;
    @CsvBindByName
    private String firstName;
    @CsvBindByName
    private String lastName;
    @CsvBindByName
    private int totalPrice;
    @CsvBindByName
    private boolean paidStatus;
    @CsvBindByName
    private String checkin;
    @CsvBindByName
    private String checkout;
    @CsvBindByName
    private String additionalDetails;
    @CsvBindByName
    private String updateFirstName;

    @Override
    public String toString() {
        return "bookingdates{" +
                "checkin '" + checkin + '\'' +
                ", checkout '" + checkout + '\'' +
                '}';

    }
}
