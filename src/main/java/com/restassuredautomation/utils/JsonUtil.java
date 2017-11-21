package com.restassuredautomation.utils;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JsonUtil {

	public static JSONObject convertToJsonObject(String requestJson) {
		JSONParser parser = new JSONParser();
		System.out.println(Constants.getProjectDirectory()+requestJson);
		JSONObject jObject;
		try {
			jObject = (JSONObject)parser.parse(new FileReader(Constants.getProjectDirectory()+requestJson));
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return jObject;
	}
	
}
