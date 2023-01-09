package com.booking.api.test;

import com.booking.UpdateBooking;
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

public class partialUpdateBookingTest {

    private static Token token;
    UpdateBooking booking = new UpdateBooking();

    Map<String, Object> payload;
    Integer bookingId;

    @BeforeTest
    @Parameters({"scenarioName"})
    public void createBooking(@Optional("partialUpdateBooking") String scenarioName) throws IOException {
        payload = booking.buildPayload(scenarioName);
        // Create new booking
        Response response = booking.performPost(BASE_URI + BOOKING_ENDPOINT, payload);
        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200");
        JsonPath jsonPathEvaluator = response.jsonPath();
        bookingId = jsonPathEvaluator.get("bookingid");
        // Request token to use in PartialUpdate
        token = TokenRequest.requestToken(new User("admin", "password123"));
    }

    @Test
    public void partialUpdateBooking() {
        // Update firstname with New item
        Response response = booking.performPatch(BASE_URI + BOOKING_ENDPOINT + bookingId, Map.of("firstname", "NewFirstName" ), token);
        // Check Status code is correct
        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200");
        // Check that new firstname is set
        Assert.assertEquals(response.jsonPath().get("firstname"), "NewFirstName");

    }

}
