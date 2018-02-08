package com.restassuredautomation.googleapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.restassuredautomation.utils.Constants;
import com.restassuredautomation.utils.JsonUtil;
import com.restassuredautomation.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoogleApiTest {
	
	public static Logger log = LogManager.getLogger(GoogleApiTest.class.getName());
	
	@Test
	public static void getPlaceAPI() {
		
		RestAssured.baseURI = Utils.getProperty("GooglePlacesBaseURI");
		
		Response res =
			given()
						.param("location", "-33.8670522,151.1957362").param("radius", "500")
						.param("key", Utils.getProperty("GooglePlaceKey")).
			when()
						.get(Constants.GET_PLACE).
			then()
						.assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
						.body("results[0].name", equalTo("Sydney")).and()
						.body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and()
						.header("Server", "scaffolding on HTTPServer2").extract().response();
		
		log.info(res.asString());				
	}
	
	@Test
	public void addPlaceAPI() {
		
		RestAssured.baseURI = Utils.getProperty("GooglePlacesBaseURI");
		
		Response res =
		given().
				queryParam("key", Utils.getProperty("GooglePlaceKey")).
				body(JsonUtil.convertToJsonObject(Constants.ADD_PLACE_REQUEST)).
		when().
				post(Constants.ADD_PLACE).
		then().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().all().extract().response();
		
		log.info(res.asString());
	}

}
