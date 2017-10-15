package files;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public static XmlPath rawToXml(Response res) {
		
		String respon = res.asString();
		XmlPath x = new XmlPath(respon);
		return x;
	}
	
	public static JsonPath rawToJson(Response res) {
		
		String respon = res.asString();
		JsonPath x = new JsonPath(respon);
		return x;
	}
	
	public static String getSessionKey() {
		
		RestAssured.baseURI = "http://localhost:8080";
		
		Response res =
				given().
						header("Content-Type", "application/json").
						body("{ \"username\": \"Kiran Ramesh\", \"password\": \"Jira1234\" }").
				when().
						post("/rest/auth/1/session").
				then().
						statusCode(200).extract().response();
				
				String response = res.asString();
				JsonPath js = new JsonPath(response);
				String sessionId = js.get("session.value");
				return sessionId;
	}
}
