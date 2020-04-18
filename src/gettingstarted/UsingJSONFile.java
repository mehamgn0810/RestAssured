package gettingstarted;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import advanced.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UsingJSONFile {

	@Test
	public void addBooks() throws IOException {

		RestAssured.baseURI = "http://216.10.245.166";
		String requestBody = GenerateStringFromResource(System.getProperty("user.dir") + "\\resources\\BooksData.json");

		Response response = given().
								header("Content-Type", "application/json").
								body(requestBody).

				when().
					post(Resources.addBook()).

				then().
					assertThat().statusCode(200).and().contentType(ContentType.JSON).and().

				extract().
					response();

		String responseString = response.asString();
		JsonPath jsonResponse = new JsonPath(responseString);
		String ID = jsonResponse.get("ID");

		System.out.println("ID: " + ID);
	}

	public static String GenerateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
