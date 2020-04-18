package basics;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


@SuppressWarnings("unchecked")
public class SimplePOSTRequest {
	
	public void addPlace() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.basePath = "/maps/api/place/add/json";
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.param("key", "qaclick123");
		
		JSONObject jsonBody = new JSONObject();
		
		Map<String, Object> requestBody = new HashMap<String, Object>();
		
		Map<String, Object> locationValue = new HashMap<String, Object>();
		locationValue.put("lat", -38.383494);
		locationValue.put("lng", 33.427362);
		
		JSONArray typeValue = new JSONArray();
		typeValue.add("shoe park");
		typeValue.add("shop");
		
		requestBody.put("location", locationValue);
		requestBody.put("accuracy", 50);
		requestBody.put("name", "Frontline house");
		requestBody.put("phone_number", "(+91) 983 893 3937");
		requestBody.put("address", "29, side layout, cohen 09");
		requestBody.put("types", typeValue);
		requestBody.put("website", "http://google.com");
		requestBody.put("language", "French-IN");
		
		jsonBody.putAll(requestBody);
		
		requestSpecification.body(jsonBody.toJSONString());
		
		Response response = requestSpecification.post();
		
		System.out.println(response.asString());

	}

}
