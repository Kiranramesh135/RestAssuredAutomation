package com.restassuredautomation.base;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Basics9 extends BaseTestNG{
	
	private static Logger log = LogManager.getLogger(Basics9.class.getName());
	
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
		log.info(js.get("id"));
		
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
		log.info(js.get("id"));
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
