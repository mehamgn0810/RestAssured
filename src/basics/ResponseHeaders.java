package basics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ResponseHeaders {
	
	@Test
	public void getResponseHeaders() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.basePath = "/maps/api/place/get/json";
		
		RequestSpecification requestSpecification = RestAssured.given();

		requestSpecification.param("key", "qaclick123");
		requestSpecification.param("place_id", "2419afd3a6f68658b440a44474c5e8ae");
		
		Response response = requestSpecification.request(Method.GET);
		
		Headers headers = response.getHeaders();
//		System.out.println(headers.toString());
		
		for(Header header: headers) {
			System.out.println(header.toString());
		}
	}

}
