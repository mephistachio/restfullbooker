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

public class partialUpdateBookingTest {

    private static Token token;
    UpdateBooking booking = new UpdateBooking();

    Map<String, Object> payload;
    Integer bookingId;


    @BeforeTest
    @Parameters({"scenarioName"})
    public void createBooking(@Optional("partialUpdateBooking") String scenarioName) throws IOException {
        payload = (Map<String, Object>) booking.buildPayload(scenarioName);
        Response response = booking.performPost(BASE_URI + "/booking", payload);
        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200");
        JsonPath jsonPathEvaluator = response.jsonPath();
        bookingId = jsonPathEvaluator.get("bookingid");
        token = TokenRequest.requestToken(new User("admin", "password123"));
    }

    @Test
    public void partialUpdateBooking() {
        // Update
        Response response = booking.performPatch(BASE_URI + "/booking/" + bookingId, Map.of("firstname", "NewFirstName" ), token);
        //
        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200");
        Assert.assertEquals(response.jsonPath().get("firstname"), "NewFirstName");



    }

}
