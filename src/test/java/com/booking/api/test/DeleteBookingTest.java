package com.booking.api.test;

import com.booking.DeleteBooking;
import com.constants.Token;
import com.constants.TokenRequest;
import com.constants.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static com.constants.Endpoints.BASE_URI;
import static com.constants.Endpoints.BOOKING_ENDPOINT;
import static io.restassured.RestAssured.given;


/**
 * Delete booking test
 *
 * 1. Create new booking with testing data from testdata.csv
 * 2. Get bookingid for new Boooking
 * 3. Get the auth token
 * 4. Send Delete request by bookingid and check after if this bookingid is not exist
 */
public class DeleteBookingTest {
    private static Token token;
    DeleteBooking booking = new DeleteBooking();

    Map<String,Object> payload;
    Integer bookingId;

    @BeforeTest
    @Parameters({"scenarioName"})
    public void createBooking(@Optional("deleteBooking") String scenarioName) throws IOException {
        payload = booking.buildPayload(scenarioName);
        // Create new booking
        Response response = booking.performPost(BASE_URI +BOOKING_ENDPOINT,payload);
        // Check that status code is correct
        Assert.assertEquals(response.getStatusCode(),200,"Expected 200");
        JsonPath jsonPathEvaluator = response.jsonPath();
        bookingId = jsonPathEvaluator.get("bookingid");
        // Request token, that necessary for Delete request
        token = TokenRequest.requestToken(new User("admin", "password123"));
    }

    @Test
    public void deleteBooking(){
        // Delete Booking by ID
        Response response = booking.performDelete(BASE_URI + BOOKING_ENDPOINT + bookingId,payload, token);
        // Check status Code is correct
        Assert.assertEquals(response.getStatusCode(),201,"Expected 201");
        // Check that booking id is not available
        given().when().get(BASE_URI + BOOKING_ENDPOINT + bookingId).then().statusCode(404);

    }
}
