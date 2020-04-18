package advanced;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resusables.ConvertResponse;

public class ParsingJsonResponse {

	@Test
	public void getBooks() {

		RestAssured.baseURI = "http://216.10.245.166";

		Response response = given().param("AuthorName", "Mehamgn").

				when().get(Resources.getBooks()).

				then().assertThat().statusCode(200).contentType(ContentType.JSON).

				extract().response();

		JsonPath responseJson = ConvertResponse.rawToJSONPath(response);

		List list = responseJson.getList("$");
		int length = list.size();

		for (int i = 0; i < length; i++) {
			System.out.println(list.get(i));
		}

	}

}
