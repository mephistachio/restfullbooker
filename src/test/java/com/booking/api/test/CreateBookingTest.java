package com.booking.api.test;
import com.booking.CreateBooking;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static com.constants.Endpoints.BASE_URI;
import static com.constants.Endpoints.BOOKING_ENDPOINT;

public class CreateBookingTest {

    CreateBooking booking = new CreateBooking();

    Map<String,Object> payload;
    Integer bookingId;

    @Test
    @Parameters({"scenarioName"})
    public void createBooking(@Optional("createBooking") String scenarioName) throws IOException {
        payload = booking.buildPayload(scenarioName);
        // Make a POST request to Booking endpoint to place new booking
        Response response = booking.performPost(BASE_URI +BOOKING_ENDPOINT,payload);
        // Check that status code is correct
        Assert.assertEquals(response.getStatusCode(),200,"Expected 200");
        // Get id of new booking
        JsonPath jsonPathEvaluator = response.jsonPath();
        bookingId = jsonPathEvaluator.get("bookingid");
    }


    @Test
    @Parameters({"scenarioName"})
    public void createBookingNegativeTest(@Optional("createBookingNegative") String scenarioName) throws IOException
    {
        payload = booking.buildPayload(scenarioName);
        // Make a POST request for new Booking to incorrect endpoint
        Response response = booking.performPost(BASE_URI ,payload);
        // Check that status code is correct
        Assert.assertEquals(response.getStatusCode(),404,"Expected 404");

    }
    @AfterTest
    public void getBookingByID()
    {
        // Get booking by ID
        Response response = booking.performGet(BASE_URI + BOOKING_ENDPOINT+bookingId.toString());
        // Check status code is correct
        Assert.assertEquals(response.getStatusCode(),200,"Expected 200");
        // To check that firstname field is as expected
        Assert.assertEquals(response.jsonPath().get("firstname"),payload.get("firstname"),"Firstname is same as expected");
    }
}
