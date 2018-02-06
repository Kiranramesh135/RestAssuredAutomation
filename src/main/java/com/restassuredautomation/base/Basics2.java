package com.restassuredautomation.base;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.restassuredautomation.utils.Constants;
import com.restassuredautomation.utils.JsonUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Basics2 extends BaseTestNG{
	
	private static Logger log = LogManager.getLogger(Basics2.class.getName());
	
	@Test
	public void addPlaceAPI() {
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		Response res =
		given().
				queryParam("key", "AIzaSyDsN6-G__OfmwszpKhED_c7WMs3UZLZ7uc").
				body(JsonUtil.convertToJsonObject(Constants.ADD_PLACE_REQUEST)).
		when().
				post("/maps/api/place/add/json").
		then().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().all().extract().response();
		
		log.info(res.asString());
	}

}
