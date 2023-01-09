package com.booking;

import com.constants.Token;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class UpdateBooking extends CreateBooking{
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
