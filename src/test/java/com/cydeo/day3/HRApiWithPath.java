package com.cydeo.day3;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApiWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        //response.prettyPrint();
        //print limit result
        System.out.println(response.path("limit").toString());
        //print hasMore
        System.out.println(response.path("hasMore").toString());
        //print second country id
        System.out.println(response.path("items[1].country_id").toString());
        //print 4th element country name
        System.out.println(response.path("items[3].country_name").toString());

        //get me all country names
        List<String> countryNames= response.path("items.country_name");
        System.out.println(countryNames);

        //assert that in the response all region_ids are 2
        //save all the regions ids inside the list
        List<Integer>  allRegionsIDs = response.path("items.region_id");

        //assert one by one that they are equal to 2
        for (Integer regionsID : allRegionsIDs) {
            assertEquals(2,regionsID);
        }
    }

    /*
        send a GET request o employees endpoint, filter only Job_id IT_PROG
        then assert that all job_ids are IT_PROG
     */

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test2() {
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees");

       // response.prettyPrint();
         boolean flag = true;

       List<String> itemsJobId = response.path("items.job_id");
        System.out.println("itemsJobId = " + itemsJobId);

        for (String s : itemsJobId) {
            assertEquals("IT_PROG",s);

        }



       String path = response.path("items[3].job_id");
        System.out.println("path = " + path);


    }
    }
