package com.booking;

import com.api.utils.CsvReader;
import com.constants.Booking;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.*;


/**
 * The type Create booking.
 */
public class CreateBooking extends CsvReader {

    /**
     * Build payload map.
     *
     * @param testScenario the test scenario
     * @return the map
     * @throws IOException the io exception
     */
    public Map<String,Object> buildPayload(String testScenario) throws IOException {

        List<Booking> inputData = getTestDataRow(testScenario);
        ObjectMapper objectMapper = new ObjectMapper();
        // Get data from payload file
        Map<String,Object> payload = objectMapper.readValue(new File("src/main/resources/createBookingPayload.json"),
                new TypeReference<>() {
                });
        // Re-write data
        payload.put("firstname",inputData.get(0).getFirstName());
        payload.put("lastname",inputData.get(0).getLastName());
        payload.put("totalprice",inputData.get(0).getTotalPrice());
        payload.put("depositpaid",inputData.get(0).isPaidStatus());
        List<String> bookingDates = Arrays.asList(inputData.get(0).getCheckin(),inputData.get(0).getCheckout());
        payload.put("bookingDates",bookingDates);
        payload.put("additionalneeds",inputData.get(0).getAdditionalDetails());

        return payload;
    }

    /**
     * Perform post response.
     *
     * @param endPoint       the end point
     * @param requestPayload the request payload
     * @return the response
     */
    public Response performPost(String endPoint, Map<String,Object> requestPayload) {
        try {
            return RestAssured.given().log().all()
                    .baseUri(endPoint)
                    .contentType(ContentType.JSON)
                    .body(requestPayload)
                    .post()
                    .then().log().all().extract().response();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Perform get response.
     *
     * @param endPoint the end point
     * @return the response
     */
    public Response performGet(String endPoint){
        try {
            return RestAssured.given().log().all()
                    .baseUri(endPoint)
                    .contentType(JSON)
                    .get()
                    .then().log().all().extract().response();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}

