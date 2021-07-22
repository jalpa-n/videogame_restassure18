package com.videogame.model.videoGame;

import com.videogame.model.VideoGamePojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class VideoGameTest {

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/app/videogames";
    }

    Response response;

    //Get Request
    @Test
    public void getAllVideoGameData() {

        response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();

    }

    //Get single video game data with id
    @Test
    public void getSingleVideoGameData() {

        response = given()
                .pathParam("id", 3)
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    //Post Request with json
    @Test
    public void createNewVideoGameRecord() {

        VideoGamePojo videoGamePojo=new VideoGamePojo();
        videoGamePojo.setId(13);
        videoGamePojo.setName("Super Smash Bros");
        videoGamePojo.setReleaseDate("2000-10-01");
        videoGamePojo.setReviewScore(850);
        videoGamePojo.setCategory("Shooter");
        videoGamePojo.setRating("Universal");

        response=given()
                .header("Content-Type","application/json")
                .body(videoGamePojo)
                .when()
                .post();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //Post Request with xml
    @Test
    public void createVideoGameRecordWithXml(){

        String body="    <videoGame category=\"Shooter\" rating=\"Universal\">\n" +
                "        <id>11</id>\n" +
                "        <name>Resident Evil 5</name>\n" +
                "        <releaseDate>2006-10-01T00:00:00+01:00</releaseDate>\n" +
                "        <reviewScore>95</reviewScore>\n" +
                "    </videoGame>";
        response=given()
                .header("Content-Type","application/xml")
                .body(body)
                .when()
                .post();
        response.then().statusCode(200);
        response.prettyPrint();
    }



    //Put Request
    @Test
    public void updateVideoGameRecord() {

        VideoGamePojo videoGamePojo=new VideoGamePojo();
        videoGamePojo.setId(13);
        videoGamePojo.setName("Super Smash Bros");
        videoGamePojo.setReleaseDate("2004-10-01");
        videoGamePojo.setReviewScore(850);
        videoGamePojo.setCategory("Shooter");
        videoGamePojo.setRating("Universal");

        response=given()
                .pathParam("id",13)
                .header("Content-Type","application/json")
                .body(videoGamePojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //Delete Request
    @Test
    public void deleteVideoGameRecord(){

        response=given()
                .pathParam("id",2)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
