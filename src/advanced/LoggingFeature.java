package advanced;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoggingFeature {
	
	@Test
	public void logging() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		Response response = given().
			param("key", "qaclick123").
			param("place_id", "2419afd3a6f68658b440a44474c5e8ae").log().all(). //logging all 

		when().
			get("/maps/api/place/get/json").

		then().
			assertThat().statusCode(200).contentType(ContentType.JSON).log().headers(). //logging only headers
					
		extract().
			response();
		
		System.out.println("***************************************");
		System.out.println(response.asString());
	}

}
