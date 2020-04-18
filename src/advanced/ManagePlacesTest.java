package advanced;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ManagePlacesTest {
	
	Properties prop;
	String placeID;
	
	@BeforeTest
	public void setup() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\advanced\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void addPlace() {
		RestAssured.baseURI = prop.getProperty("BASE_URI");
		
		
		
		Response response = given().
		queryParam("key", prop.getProperty("KEY")).
		body(PayLoad.addPlace()).
		
		when().
		post(Resources.addPlace()).
		
		then().
		assertThat().statusCode(200).and().
		contentType(ContentType.JSON).and().
		body("status", equalTo("OK")).
		
		extract().response();
		
		String responseString = response.asString();
		JsonPath jsonBody = new JsonPath(responseString);
		placeID = jsonBody.get("place_id");
	}
	
	
	@Test
	public void deletePlace() {
		
		RestAssured.baseURI = prop.getProperty("BASE_URI");
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("place_id", placeID);
		
		given().
		queryParam("key", prop.getProperty("KEY")).
		body(requestBody).
		
		when().
		post("/maps/api/place/delete/json").
		
		then().
		assertThat().statusCode(200).and().
		contentType(ContentType.JSON).and().
		body("status", equalTo("OK"));
		
		
	}

}
