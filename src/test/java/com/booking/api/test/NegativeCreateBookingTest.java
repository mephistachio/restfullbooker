package com.booking.api.test;

import com.booking.CreateBooking;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static com.constants.Endpoints.BASE_URI;

public class NegativeCreateBookingTest {

    CreateBooking booking = new CreateBooking();
    Map<String,Object> payload;

    /**
     * Create booking negative test.
     *
     * @param scenarioName the scenario name
     * @throws IOException the io exception
     *
     * The attempt to create new booking sending POST request to incorrect endpoint
     */
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
}
