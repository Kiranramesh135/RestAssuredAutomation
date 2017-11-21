package com.restassuredautomation.base;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.restassuredautomation.utils.Constants;
import com.restassuredautomation.utils.JsonUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Basics2 {
	
	@Test
	public void addPlaceAPI() {
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().
				queryParam("key", "AIzaSyDsN6-G__OfmwszpKhED_c7WMs3UZLZ7uc").
				body(JsonUtil.convertToJsonObject(Constants.ADD_PLACE_REQUEST)).
		when().
				post("/maps/api/place/add/json").
		then().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().all().
				body("status", equalTo("OK"));
	}

}
