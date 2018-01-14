package com.restassuredautomation.base;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Basics {

	@Test
	public void getPlaceAPI() {

		RestAssured.baseURI = "https://maps.googleapis.com";

		Response res = given().param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", "AIzaSyDsN6-G__OfmwszpKhED_c7WMs3UZLZ7uc").

				when().get("/maps/api/place/nearbysearch/json").

				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("results[0].name", equalTo("Sydney")).and()
				.body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().header("Server", "scaffolding on HTTPServer2")
				.extract().response();

		// JsonPath js = new JsonPath(res.asString());
		//
		// System.out.println("result json array = "+js.get("results"));
		// = js.get("results");
		// System.out.println("JsonArray size = "+results.size());

		// Practicing JSONObject and JSONArray
		JSONParser parser = new JSONParser();
		try {
			JSONObject jObject = (JSONObject) parser.parse(res.asString());
			JSONArray results = (JSONArray) jObject.get("results");

			System.out.println("JsonArray size = " + results.size());
			// System.out.println(results.get(19));
			// results.get
			for (int i = 0; i < results.size(); i++) {
				// System.out.println(results.get(i));
				JSONObject a = (JSONObject) results.get(i);
				System.out.println("name = " + a.get("name"));

			}

		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
