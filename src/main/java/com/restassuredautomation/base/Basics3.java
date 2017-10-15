package com.restassuredautomation.base;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import files.PayLoad;
import files.Resources;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Basics3 {
	
	Properties prop = new Properties();
	
	@BeforeMethod
	public void preCondition() throws IOException {
		
		String userDir = System.getProperty("user.dir");
		
		
		FileInputStream fis = new FileInputStream(userDir+"/src/main/resources/conf/env.properties");
		prop.load(fis);
	}
	
	@Test
	public void addAndDeletePlace() {
		
		//Task 1 - grab the response
		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given().
								queryParam("key", prop.getProperty("KEY")).
								body(PayLoad.getPostData()).
					   when().
					   			post(Resources.placePostData()).
					   then().
					   			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
					   			body("status",equalTo("OK")).
					   			extract().response();
		
		//Task 2 - Grab the place ID from response
		
		String responseString = res.asString();
		System.out.println(responseString);
		JsonPath js = new JsonPath(responseString);
		String placeId = js.get("place_id");
		System.out.println("Place ID = " +placeId);
		
		//Task 3 - Place this place ID in the Delete request
		
		given().
				queryParam("key", prop.getProperty("KEY")).
				body("{"+
				"\"place_id\": \""+placeId+"\""+
				"}").
		when().
			post("/maps/api/place/delete/json").
		then().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			body("status", equalTo("OK"));
		
		
		
		
	//Code is still incomplete

	}

}
