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
import static io.restassured.RestAssured.given;
public class deleteBookingTest {
    private static Token token;
    DeleteBooking booking = new DeleteBooking();

    Map<String,Object> payload;
    Integer bookingId;



    @BeforeTest
    @Parameters({"scenarioName"})
    public void createBooking(@Optional("deleteBooking") String scenarioName) throws IOException {
        payload = (Map<String, Object>) booking.buildPayload(scenarioName);
        Response response = booking.performPost(BASE_URI +"/booking",payload);
        Assert.assertEquals(response.getStatusCode(),200,"Expected 200");
        JsonPath jsonPathEvaluator = response.jsonPath();
        bookingId = jsonPathEvaluator.get("bookingid");
        token = TokenRequest.requestToken(new User("admin", "password123"));
    }

    @Test
    public void deleteBooking(){
        // Delete
        Response response = booking.performDelete(BASE_URI +"/booking/"+bookingId,payload, token);
        //
        Assert.assertEquals(response.getStatusCode(),201,"Expected 201");
        //
        given().when().get(BASE_URI +"/booking/"+bookingId).then().statusCode(404);

    }
}
