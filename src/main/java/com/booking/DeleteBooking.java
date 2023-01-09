package com.booking;

import java.util.Map;

import com.constants.Token;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class DeleteBooking extends CreateBooking{

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
