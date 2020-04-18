package oauth2;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) {

		//authenticated url containg code
		String url = "https://rahulshettyacademy.com/getCourse.php?"
				+ "code=4%2FygFk6xXkSKStvM2hZTUrPauirc90kAoCY1pH8KLAeELOrlLYD3_Y_rwEupQc96yEBwG68IVOHlNpQDTI0a0Mw48&"
				+ "scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&")[0];
		
		System.out.println("Code: " + code);

		// get access token
		String accessTokenResponse = given()
				.urlEncodingEnabled(false)   //will not encode special characters in url eg: %
				.queryParams("code", code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("grant_type", "authorization_code")
				.queryParams("state", "verifyfjdss")
				.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
//				.queryParam("scope", "https://www.googleapis.com/auth/userinfo.email")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").log().all()

				.when().post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");

		// get Courses
		String response = given().queryParam("access_token", accessToken).log().all()

				.when().get("https://rahulshettyacademy.com/getCourse.php").asString();

		System.out.println(response);
	}

}
