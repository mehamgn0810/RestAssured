package oauth2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class SpecBuilderTest {
	
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
		
		
		RequestSpecification reqspec =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123").build();
		
		ResponseSpecification respspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification request = given().spec(reqspec)
		.body(place);
		
		Response response = request.when().post("/maps/api/place/add/json").
		then().spec(respspec).extract().response();
		
		System.out.println(response.asString());
		
		
	}

}
