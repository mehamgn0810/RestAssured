package gettingstarted;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class GETRequest {
	
	@Test
	public void getRequest() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given().
		param("key", "qaclick123").
		param("place_id", "2419afd3a6f68658b440a44474c5e8ae").
		
		when().
		get("/maps/api/place/get/json").
		
		then().assertThat().statusCode(200);
		
	}

}
