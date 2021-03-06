package com.restassuredautomation.base;

import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Basics5 extends BaseTestNG{
	
	private static Logger log = LogManager.getLogger(Basics5.class.getName());
	
	@Test
	public void extractingNamesAPI() {
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		Response res=
		given().
				param("location","-33.8670522,151.1957362").
				param("radius", "500").
				param("key","AIzaSyDsN6-G__OfmwszpKhED_c7WMs3UZLZ7uc").log().all().
		
		when().
				get("/maps/api/place/nearbysearch/json").
				
		then().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("results[0].name",equalTo("Sydney")).and().
				body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
				header("Server","scaffolding on HTTPServer2").log().body().
				extract().response();
		JsonPath js = ReusableMethods.rawToJson(res);
		int size = js.get("results.size()");
		log.info(size);
		
		for(int i=0;i<size;i++) {
			String place = js.get("results["+i+"].name");
			log.info(place);
		}
		
	}
	
	

}
