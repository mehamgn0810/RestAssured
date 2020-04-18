package oauth2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.Location;

public class SerializeTest {
	
	@Test
	public void addPlace() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace place = new AddPlace();
		
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		place.setLocation(location);
		place.setAccuracy(50);
		place.setName("Frontline house");
		place.setPhone_number("(+91) 983 893 3937");
		place.setAddress("29, side layout, cohen 09");
		
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		
		place.setTypes(types);
		place.setWebsite("http://google.com");
		place.setLanguage("French-IN");
		
		String responseString = given().
		queryParam("key", "qaclick123").
		body(place).log().all().
		
		when().
		post("/maps/api/place/add/json").
		
		then().assertThat().statusCode(200).
		
		extract().response().asString();
		
		System.out.println(responseString);
		
	}

}
