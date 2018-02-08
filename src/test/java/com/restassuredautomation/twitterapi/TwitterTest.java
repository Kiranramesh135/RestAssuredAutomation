package com.restassuredautomation.twitterapi;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.restassuredautomation.utils.Constants;
import com.restassuredautomation.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TwitterTest {
	
	public static Logger log = LogManager.getLogger(TwitterTest.class.getName());
	
	static String id;
	
	@Test(priority=0)
	public static void createTweet() {

		RestAssured.baseURI = Utils.getProperty("TwitterBaseURI");
		
		Response res =
		given()
				.auth().oauth(Utils.getProperty("ConsumerKey"), Utils.getProperty("ConsumerSecret"),
						Utils.getProperty("Token"), Utils.getProperty("TokenSecret"))
				.queryParam("status", "I am creating this tweet with Automation2").
		when()
				.post(Constants.UPDATE_TWITTER_STATUS).
		then()
				.assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();
		
		log.info("UpdateTweet Response = "+ res.asString());
		
		//converting the response to JSON Object
		JSONObject jObj =  Utils.convertToJsonObject(res.asString());
		id = jObj.get("id").toString();	
		log.info("Tweet ID = "+ id);
		
	}
	
	@Test(dependsOnMethods = {"createTweet"}, enabled = false, priority=2)
	public static void deleteTweet() {

		RestAssured.baseURI = Utils.getProperty("TwitterBaseURI");
		
		Response res =
		given()
					.auth().oauth(Utils.getProperty("ConsumerKey"), Utils.getProperty("ConsumerSecret"),
							Utils.getProperty("Token"), Utils.getProperty("TokenSecret")).
		when()
					.post("/destroy/"+id+".json").
		then()
					.assertThat().statusCode(200).extract().response();
		log.info("DeleteTweet response = "+res.asString());
	
	}
	
	@Test()
	public static void getLatestTweet() {
	
		
		RestAssured.baseURI = Utils.getProperty("TwitterBaseURI");
		
		Response res =
		given()
					.auth().oauth(Utils.getProperty("ConsumerKey"), Utils.getProperty("ConsumerSecret"),
							Utils.getProperty("Token"), Utils.getProperty("TokenSecret")).
		when()
					.get(Constants.GET_LATEST_TWEET).
		then()
					.assertThat().statusCode(200).extract().response();
		
		log.info("GetLatestTweet Response = "+res.asString());
	}
	
	@Test(priority=1)
	public static void reTweet() {
		
		RestAssured.baseURI = Utils.getProperty("TwitterBaseURI");
		
		Response res =
		given()
					.auth().oauth(Utils.getProperty("ConsumerKey"), Utils.getProperty("ConsumerSecret"),
							Utils.getProperty("Token"), Utils.getProperty("TokenSecret")).
		when()
					.post("/statuses/retweet/"+id+".json").
		then()
					.assertThat().statusCode(200).extract().response();
		
		log.info(res.asString());	
	}
	
	@Test(enabled=false)
	public static void updateProfileImage() {
		
		RestAssured.baseURI = Utils.getProperty("TwitterBaseURI");
			
//	  	https://api.twitter.com/1.1/account/update_profile_image.json
		String path = System.getProperty("user.dir")+"/img/Kiran.jpg";
		System.out.println(path);
		
		File f =  new File(path);
	    String encodstring = Utils.encodeFileToBase64Binary(f);
//	    System.out.println(encodstring);
		
		Response res =
		given()
					.auth().oauth(Utils.getProperty("ConsumerKey"), Utils.getProperty("ConsumerSecret"),
							Utils.getProperty("Token"), Utils.getProperty("TokenSecret")).
					queryParam("image", encodstring).
				
		when()
					.post("/update_profile_image.json").
					
					
					
		then()
					.assertThat().extract().response();
		System.out.println();
		System.out.println(res.asString());
	}
	
	@Test(dependsOnMethods={"createTweet"} )
	public static void likeTweet() {
		
		RestAssured.baseURI = Utils.getProperty("TwitterBaseURI");
	
		Response res =
		given()
					.auth().oauth(Utils.getProperty("ConsumerKey"), Utils.getProperty("ConsumerSecret"),
							Utils.getProperty("Token"), Utils.getProperty("TokenSecret")).
					queryParam("id", id).
		when()
					.post(Constants.FAVOURITE_TWEET).
		then()
					.assertThat().statusCode(200).extract().response();
		
		log.info(res.asString());
		
					
	}
}
