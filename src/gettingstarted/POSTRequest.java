package gettingstarted;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class POSTRequest {
	
	@Test
	public void postRequest() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String requestBody = "{" +
			    "\"location\":{" + 
		        "\"lat\" : -38.383494," + 
		        "\"lng\" : 33.427362" + 
		    "}," + 
		    "\"accuracy\":50," + 
		    "\"name\":\"Frontline house\"," + 
		    "\"phone_number\":\"(+91) 983 893 3937\"," +
		    "\"address\" : \"29, side layout, cohen 09\"," + 
		    "\"types\": [\"shoe park\",\"shop\"]," +
		    "\"website\" : \"http://google.com\", " +
		    "\"language\" : \"French-IN\"}";
		
		given().
		queryParam("key", "qaclick123").
		body(requestBody).
		
		when().
		post("/maps/api/place/add/json").
		
		then().
		assertThat().statusCode(200).and().
		contentType(ContentType.JSON).
		body("status", equalTo("OK"));
		
	}

}
