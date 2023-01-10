package com.api.utils;

import com.constants.Booking;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The type Csv reader.
 *
 * This class is to read the csv template and read values based on the booking class object
 */
public class CsvReader {
    /**
     * Gets test data row.
     *
     * @param testScenario the test scenario
     * @return the test data row
     * @throws FileNotFoundException the file not found exception
     */

    public List<Booking> getTestDataRow(String testScenario) throws FileNotFoundException {
        FileReader file = new FileReader("src/main/resources/bookingTestData.csv");
        List<Booking> csvData = new CsvToBeanBuilder<Booking>(file)
                .withType(Booking.class)
                .withSeparator(';')
                .build().parse();

        // From the whole csv below lone filters the particular row based on the scenario
        Predicate<Booking> byScenario = filterData -> filterData.getTestScenario().equals(testScenario);

        List<Booking> rowData = csvData.stream().filter(byScenario).collect(Collectors.toList());
        return rowData;
    }
}
