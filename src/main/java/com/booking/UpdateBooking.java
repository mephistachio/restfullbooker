package com.booking;

import com.constants.Token;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

/**
 * The type Update booking.
 */
public class UpdateBooking extends CreateBooking{
    /**
     * Perform patch response.
     *
     * @param endPoint       the end point
     * @param requestPayload the request payload
     * @param token          the token
     * @return the response
     */
    public Response performPatch(String endPoint, Map<String,Object> requestPayload, Token token){
        try {
            return RestAssured.given().log().all()
                    .baseUri(endPoint)
                    .cookie("token", token.getToken())
                    .contentType(ContentType.JSON)
                    .body(requestPayload)
                    .patch()
                    .then().log().all().extract().response();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }


    }
}
