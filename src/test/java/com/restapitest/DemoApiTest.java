package com.restapitest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DemoApiTest {

    @Test
    public void apiTest1() {

        given().
                param("username", "chakra").param("password", "chakra123").
                headers("header", "allheader").
                when().
                get("https://api.github.com/users/petrgazarov").
                then()
                .statusCode(200).contentType(ContentType.JSON)
                .log().all();
    }

    @Test
    public void apiTest2() {

        Response response = given().get("https://api.github.com/users/petrgazarov").then().extract().response();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().prettyPrint());

    }

    @Test
    public void apiTest3() {
        Response response = given().get("https://api.github.com/users/petrgazarov").then().extract().response();
        String s = response.getBody().asString();
        JsonPath jp = new JsonPath(s);
        String login = jp.getString("login");
        System.out.println(login);

    }

    @Test
    public void apiTest4() {
        Response response = given().header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com").
                header("X-RapidAPI-Key", "8bcb167f50msh8c2f0f0c3daa81ep162e14jsn12daa5cf7d03").
                get("https://restcountries-v1.p.rapidapi.com/all").then().extract().response();
        String jsonBody = response.getBody().prettyPrint();
        System.out.println(jsonBody);
        JsonPath jp = new JsonPath(jsonBody);
        List<String> countryNames = jp.get("name");
        System.out.println(countryNames);
        for (String s : countryNames) {
            System.out.println(s);
        }

    }

    @Test
    public void apiTest5() {
        Response response = given().header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com").
                header("X-RapidAPI-Key", "8bcb167f50msh8c2f0f0c3daa81ep162e14jsn12daa5cf7d03").
                get("https://restcountries-v1.p.rapidapi.com/all").then().extract().response();

        String jsonBody = response.getBody().asString();
        String all = response.getBody().prettyPrint();
        JsonPath jp = new JsonPath(jsonBody);
        ArrayList<Map<String, Object>> borders = jp.get("borders");
        System.out.println(borders);

    }

    @Test
    public void apiTest6() {
        Response response = given().header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com").
                header("X-RapidAPI-Key", "8bcb167f50msh8c2f0f0c3daa81ep162e14jsn12daa5cf7d03").
                get("https://restcountries-v1.p.rapidapi.com/alpha/ru").then().extract().response();

        String jsonBody = response.getBody().prettyPrint();
        JsonPath jp = new JsonPath(jsonBody);
        List<String> countryBorder = jp.get("borders");
        System.out.println(countryBorder);
    }


    @Test
    public void apiTest7() {

        ValidatableResponse vr = given().header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com").
                header("X-RapidAPI-Key", "8bcb167f50msh8c2f0f0c3daa81ep162e14jsn12daa5cf7d03").
                get("https://restcountries-v1.p.rapidapi.com/alpha/ru").then();

        vr.log().all();
        vr.statusCode(200);
        vr.contentType(ContentType.JSON);
        vr.extract().response().getBody().prettyPrint();


    }

    @Test
    public void apiTest8() {
        Response response = given().header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com").
                header("X-RapidAPI-Key", "8bcb167f50msh8c2f0f0c3daa81ep162e14jsn12daa5cf7d03").
                get("https://restcountries-v1.p.rapidapi.com/alpha/ru").then().extract().response();

        String s = response.getBody().asString();
        JsonPath jp = new JsonPath(s);
        Map<String, String> l = jp.getMap("translations");
        System.out.println(l);

    }
}
