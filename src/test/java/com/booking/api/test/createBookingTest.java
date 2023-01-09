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

public class createBookingTest {

    CreateBooking booking = new CreateBooking();

    Map<String,Object> payload;
    Integer bookingId;
    @Test
    @Parameters({"scenarioName"})
    public void createBooking(@Optional("createBooking") String scenarioName) throws IOException {
        payload = (Map<String, Object>) booking.buildPayload(scenarioName);
        Response response = booking.performPost(BASE_URI +"/booking",payload);
        Assert.assertEquals(response.getStatusCode(),200,"Expected 200");
        JsonPath jsonPathEvaluator = response.jsonPath();
        bookingId = jsonPathEvaluator.get("bookingid");
    }

    @AfterTest
    public void getBookingByID()
    {
        Response response =booking.performGet(BASE_URI +"/booking/"+bookingId.toString());
        Assert.assertEquals(response.getStatusCode(),200,"Expected 200");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.get("firstname"),payload.get("firstname"),"Firstname is same as expected");
    }
}
