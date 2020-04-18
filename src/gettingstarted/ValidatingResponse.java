package gettingstarted;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;;

public class ValidatingResponse {

	@Test
	public void validateResponse() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		given().
			param("key", "qaclick123").
			param("place_id", "2419afd3a6f68658b440a44474c5e8ae").

		when().
			get("/maps/api/place/get/json").
			
		then().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			header("Server", "Apache/2.4.18 (Ubuntu)").and().
			body("name", equalTo("Frontline house")).and().
			body("types", equalTo("shoe park,shop"));

	}

}
