package com.booking;

import com.constants.Token;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

/**
 * The type Delete booking.
 */
public class DeleteBooking extends CreateBooking{

    /**
     * Perform delete response.
     *
     * @param endPoint       the end point
     * @param requestPayload the request payload
     * @param token          the token
     * @return the response
     */
    public Response performDelete(String endPoint,  Map<String,Object> requestPayload, Token token){
        try {
            return RestAssured.given().log().all()
                    .baseUri(endPoint)
                    .contentType(ContentType.JSON)
                    .cookie("token", token.getToken())
                    .body(requestPayload)
                    .delete()
                    .then().log().all().extract().response();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }


    }
}
