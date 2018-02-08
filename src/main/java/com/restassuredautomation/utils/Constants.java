package com.restassuredautomation.utils;

public class Constants {
	
	public static final String ADD_PLACE_REQUEST = "/src/main/resources/jsonRequest/AddPlace.json";
	
	//Twitter endpoints
	public static final String UPDATE_TWITTER_STATUS = "/statuses/update.json";
	public static final String GET_LATEST_TWEET = "/statuses/home_timeline.json";
	public static final String FAVOURITE_TWEET = "/favorites/create.json";
	
	//GooglePlace endpoints
	public static final String GET_PLACE = "/maps/api/place/nearbysearch/json";
	public static final String ADD_PLACE = "/maps/api/place/nearbysearch/json";
	
	
	public static String getProjectDirectory(){
		
		return System.getProperty("user.dir");
	}
	

}
