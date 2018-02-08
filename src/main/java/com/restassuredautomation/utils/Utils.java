package com.restassuredautomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utils {
	
	public static String getProperty(String key) {
		File config = new File("config/config.properties");
		String value = null;
		try {
			FileReader reader = new FileReader(config);
			Properties props = new Properties();
			props.load(reader);
			value = props.getProperty(key);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return value;
	}
	
	public static JSONObject convertToJsonObject(String responseJson) {
		JSONParser parser = new JSONParser();
		JSONObject jObject;
		try {
			jObject = (JSONObject)parser.parse(responseJson);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return jObject;
	}
	
	public static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.getEncoder().encodeToString(bytes);
           
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }

}
