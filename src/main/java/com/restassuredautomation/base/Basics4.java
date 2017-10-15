package com.restassuredautomation.base;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Basics4 {
	
	@Test
	public void addPlaceXML() throws IOException {
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		String postData = generateStringFromResource(System.getProperty("user.dir")+"/src/main/resources/data/postdata.xml");
		System.out.println(postData);
		
//		/RestAssuredAutomation/src/main/resources/data/postdata.xml
		
		Response res =
		given().
				queryParam("key", "AIzaSyDsN6-G__OfmwszpKhED_c7WMs3UZLZ7uc").
				body(postData).
		when().
				post("/maps/api/place/add/xml").
		then().
				assertThat().statusCode(200).and().contentType(ContentType.XML).
				extract().response();
		
		XmlPath x = ReusableMethods.rawToXml(res);
		System.out.println(x.get("PlaceAddResponse.place_id"));
	}
	
	public static String generateStringFromResource(String path) throws IOException {
		
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
