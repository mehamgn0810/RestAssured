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

public class ManageBooksTest {
	
	Properties prop;
	String ID;
	
	@BeforeTest
	public void setup() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\advanced\\env.properties");
		prop.load(fis);
	}
	
	@Test(enabled = false)
	public void addPlace() {
		RestAssured.baseURI = prop.getProperty("BASE_URI");	
		
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("name", "Learn RestAssured API with Java");
		jsonBody.put("isbn", "meh");
		jsonBody.put("aisle", "004");
		jsonBody.put("author", "Mehamgn");
		
		Response response = given().
		body(jsonBody).
		
		when().
		post(Resources.addBook()).
		
		then().
		assertThat().statusCode(200).and().
		contentType(ContentType.JSON).and().
		
		extract().response();
		
		String responseString = response.asString();
		JsonPath jsonResponse = new JsonPath(responseString);
		ID = jsonResponse.get("ID");
		
		System.out.println("ID: " + ID);
	}
	
	
	@Test(enabled = true)
	public void deletePlace() {
		
		RestAssured.baseURI = prop.getProperty("BASE_URI");
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("ID", "meh227");
		
		given().
		body(requestBody).
		
		when().
		post(Resources.deleteBook()).
		
		then().
		assertThat().statusCode(200).and().
		contentType(ContentType.JSON);
		
		
	}

}
