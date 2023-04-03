package com.dh.course.controller;

import com.dh.course.model.Course;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

class CourseControllerTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }



    @Test
    void create() {
       var responseId= given()
                .contentType(ContentType.JSON)
                .when()
                .body(new Course(null,"Esp. Back",null))
                .post("/api/v1/courses")
                .as(Map.class)              ;


        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("id",responseId.get("id"))
                .get("/api/v1/courses/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("Esp. Back"));

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("id",responseId.get("id"))
                .delete("/api/v1/courses/{id}");
    }


}