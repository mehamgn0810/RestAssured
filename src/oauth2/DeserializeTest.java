package oauth2;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCourses;
import pojo.WebAutomation;

public class DeserializeTest {

	public static void main(String[] args) {

		// authenticated url containg code
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2FygFKyP1StKJVuYSTrhLoXv4VktTTHXua5DaIl1jvqHLhsWu3GvG9cIB_RMvK_2iOHv79KMkQo8FyLGdNuyZgsnc&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";

		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&")[0];

		System.out.println("Code: " + code);

		// get access token
		String accessTokenResponse = given().urlEncodingEnabled(false) // will not encode special characters in url eg:
																		// %
				.queryParams("code", code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("grant_type", "authorization_code").queryParams("state", "verifyfjdss")
				.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
				// .queryParam("scope", "https://www.googleapis.com/auth/userinfo.email")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").log().all()

				.when().post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");

		// get Courses
		GetCourses getCourses = given().queryParam("access_token", accessToken)
				.expect().defaultParser(Parser.JSON) // expecting JSON response (default type is text/html; charset=UTF-8)
				.log().all()

				.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
		
		
		System.out.println(getCourses.getInstructor());
		System.out.println(getCourses.getLinkedIn());
		
		
		//get price if a course in WebAutomation
		List<WebAutomation> webAutomationList = getCourses.getCourses().getWebAutomation();
		for(WebAutomation w: webAutomationList) {
			if(w.getCourseTitle().equalsIgnoreCase("Selenium Webdriver Java")){
				System.out.println("Price of Selenium course: " + w.getPrice());
			}
		}

		//Verify Courses in WebAutomation
		String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};
		
		ArrayList<String> actualTitles = new ArrayList<String>();
		for(int i=0; i < webAutomationList.size(); i++) {
			actualTitles.add(webAutomationList.get(i).getCourseTitle());
		}
		
		List<String> expectedTitles = Arrays.asList(courseTitles);
		
		Assert.assertTrue(actualTitles.equals(expectedTitles));
		
	}

}
