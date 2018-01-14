package com.restassuredautomation.base;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Basics9 {

	
	static String ConsumerKey = "pFphsYuxHv8YMCwJiCd9RMiwn";
	static String ConsumerSecret = "JocWIKOk1PrfwEBZQoZq78PI11aMsTC5RayGJc2kHkaVTZa8aU";
	static String Token = "2259073033-vUy75f261TWaOswbtxTuXyqGykL1WnLKONBHqL0";
	static String TokenSecret = "m32jmrTZV8rtPejVFk1yOWHIPLodM5lAPJbRcfN762sYR";
	static String id;
	
	
	@Test
	public static void getLatestTweet() {
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = 
				given().
						auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
						.queryParam("count", "1")
				.when()
						.get("/home_timeline.json")
				.then()
						.assertThat().statusCode(200).log().body().extract().response();
		
		
		JsonPath js = new JsonPath(res.asString());
		System.out.println(js.get("id"));
//		JSONObject jObject = new JSONObject(res.asString());
//		System.out.println(jObject.getString("id"));
		
	}
	
	@Test
	public static void createTweet() {
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = 
				given().
						auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
						.queryParam("status", "I am creating this tweet with Automation3")
				.when()
						.post("/update.json")
				.then()
						.assertThat().statusCode(200).log().body().extract().response();
		
		JsonPath js = new JsonPath(res.asString());
		System.out.println(js.get("id"));
		id = js.get("id").toString();
	}
	
	@Test
	public static void deleteTweet() {
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = 
				given().
						auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
						.queryParam("status", "I am creating this tweet with Automation")
				.when()
						.post("/destroy/"+id+".json")
				.then()
						.assertThat().statusCode(200).log().body().extract().response();
		
	}
}
