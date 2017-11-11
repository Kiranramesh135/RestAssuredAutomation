package com.restassuredautomation.base;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import files.PayLoad;
import files.Resources;
import files.ReusableMethods;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Basics8 {
	
	Properties prop = new Properties();
	
	@BeforeMethod
	public void preCondition() throws IOException {
		
		String userDir = System.getProperty("user.dir");
		
		
		FileInputStream fis = new FileInputStream(userDir+"/src/main/resources/conf/env.properties");
		prop.load(fis);
	}
	
	@Test
	public void JiraAPIUpdateComment() {
		
		//Updating comment
		
		RestAssured.baseURI = prop.getProperty("JIRA_HOST");
		
		Response res =
				given().
								header("Content-Type","application/json").
								header("Cookie","JSESSIONID="+ReusableMethods.getSessionKey()).
								pathParams("commentid", "10200").
								body("{"+
										"\"body\": \"Updating comment through rest assured automation\","+
										"\"visibility\": {"+
										"\"type\": \"role\","+
										"\"value\": \"Administrators\""+
									 "}}"). 
				when().
								put("/rest/api/2/issue/10025/comment/{commentid}").
				then().
								statusCode(200).extract().response();
		
		String response = res.asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.get("id");
		System.out.println(id);
								
		
		
		
	}

}