package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {

    String url = "http://3.89.103.0:8000";

      /*
        Given Accept type is application/json
        When user send GET request to  /api/spartans end point
        Then status code must be 200
        And response content type must be application/json
     */

    @Test
    public void test1(){


        Response response =
                RestAssured.
                        given().accept(ContentType.JSON).
                        when().
                        get(url+"/api/spartans");


        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());


    }



}
