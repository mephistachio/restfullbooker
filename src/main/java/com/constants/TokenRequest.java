package com.constants;



import io.restassured.http.ContentType;

import static com.constants.Endpoints.AUTH_ENDPOINT;
import static com.constants.Endpoints.BASE_URI;
import static io.restassured.RestAssured.given;


/**
 * The type Token request.
 */
public class TokenRequest {
    /**
     * Request token token.
     *
     * @param user the user
     * @return the token
     * This class used in test classes for API calls for get token with POST request.
     */
    public static Token requestToken(User user) {
        return given().contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URI + AUTH_ENDPOINT)
                .then()
                .log().all().extract().as(Token.class);
    }
}
