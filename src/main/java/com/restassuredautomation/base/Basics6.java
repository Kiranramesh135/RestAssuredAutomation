package com.restassuredautomation.base;


import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Basics6 {
	
	Properties prop = new Properties();
	
	@BeforeMethod
	public void preCondition() throws IOException {
		
		String userDir = System.getProperty("user.dir");
		
		
		FileInputStream fis = new FileInputStream(userDir+"/src/main/resources/conf/env.properties");
		prop.load(fis);
	}
	
	@Test(enabled=false)
	public void JiraAPICreateIssue() {
		
		//creating Issue/Defect  
		
		RestAssured.baseURI = prop.getProperty("JIRA_HOST");
		
		Response res =
				given().
								header("Content-Type","application/json").
								header("Cookie","JSESSIONID="+ReusableMethods.getSessionKey()).
								body("{"+
										"\"fields\": {"+
											"\"project\": {"+
												"\"key\": \"RES\""+
										"},"+
										"\"summary\": \"Direct debit defect\","+
										"\"description\": \"Creating my first bug\", "+
										"\"issuetype\": {"+
											"\"name\": \"Bug\""+
												"}"+
									"}}"). 
				when().
								post("rest/api/2/issue").
				then().
								statusCode(201).extract().response();
		
		String response = res.asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.get("id");
		System.out.println(id);
								
		
		
		
	}

}
