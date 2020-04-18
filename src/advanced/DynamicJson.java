package advanced;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DynamicJson {
	
	@Test(dataProvider="BooksData", enabled = true)
	public void addBooks(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response response = given().
								header("Content-Type", "application/json").
		body(PayLoad.addBook(isbn, aisle)).
		
		when().
		post(Resources.addBook()).
		
		then().
		assertThat().statusCode(200).and().
		contentType(ContentType.JSON).and().
		
		extract().response();
		
		String responseString = response.asString();
		JsonPath jsonResponse = new JsonPath(responseString);
		String ID = jsonResponse.get("ID");
		
		System.out.println("ID: " + ID);
	}
	
	
	//Delete Books
	@Test(dataProvider="BooksData", enabled = true)
	public void deletePlace(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("ID", isbn + aisle);
		
		given().
		body(requestBody).
		
		when().
		post(Resources.deleteBook()).
		
		then().
		assertThat().statusCode(200).and().
		contentType(ContentType.JSON);
		
		
	}
	
	
	@DataProvider(name="BooksData")
	public Object[][] booksData() {
		
		//Array => Collection of elements
		//Multidimensional Array => Collection of Arrays
		
		return new Object[][] {{"meh", "01"}, //Array 1
								{"ash", "01"}, // Array 2
								{"mgn", "01"}	// Array 3
			
		};
		
	}

}
