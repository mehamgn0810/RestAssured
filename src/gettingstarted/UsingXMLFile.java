package gettingstarted;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import resusables.ConvertResponse;

public class UsingXMLFile {
	
	@Test
	public void postRequestUsingXML() throws IOException {

		RestAssured.baseURI = "http://216.10.245.166";
		String requestBody = GenerateStringFromResource(System.getProperty("user.dir") + "\\resources\\postdata.xml");
		
		Response response = given().
		queryParam("key", "qaclick123").
		body(requestBody).
		
		when().
		post("/maps/api/place/add/xml").  //sending XML request
		
		then().
		assertThat().statusCode(200).and().
		contentType(ContentType.XML).
		extract().response();
		
		System.out.println(response.asString());
		
		XmlPath xmlResponse = ConvertResponse.rawToXMLPath(response);
		String placeID = xmlResponse.get("response.place_id").toString();
		
		System.out.println(placeID);
	}
	
	
	public static String GenerateStringFromResource(String path) throws IOException {
		
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
