package basics;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimpleGETRequest {

	@Test(enabled = true)
	public void getPlaceDetails() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.basePath = "/maps/api/place/get/json";

		RequestSpecification requestSpecification = RestAssured.given();

		requestSpecification.param("key", "qaclick123");
		requestSpecification.param("place_id", "2419afd3a6f68658b440a44474c5e8ae");

		Response response = requestSpecification.request(Method.GET);
		System.out.println(response.body().asString());

		// Capture Status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Expected Status code not returned");
	}

	@Test(enabled = false)
	public void getPlaceDetails2() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.basePath = "/maps/api/place/get/json";

		RequestSpecification requestSpecification = RestAssured.given();
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("key", "qaclick123");
		parameters.put("place_id", "2419afd3a6f68658b440a44474c5e8ae");
		
		requestSpecification.params(parameters);

		Response response = requestSpecification.get();

		// Capture Status Code
		String statusLine = response.getStatusLine();
		Assert.assertTrue(statusLine.contains("200"), "Expected Status code not returned");

	}

}
