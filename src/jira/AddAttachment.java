package jira;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class AddAttachment {

	@Test(enabled = true)
	public void addAttachment() {

		RestAssured.baseURI = "http://localhost:8080";

		// Session Filter
		SessionFilter session = new SessionFilter();

		// Create Session
		given().header("Content-Type", "application/json")
				.body("{ \"username\": \"Mehavarnan\", \"password\": \"Jira@123\" }").log().all().filter(session).

				when().post("/rest/auth/1/session");
		
		//Add attachment
		given()
		.header("X-Atlassian-Token", "no-check")
		.pathParam("issueId", "10100")
		.multiPart(new File(".\\resources\\jira.txt"))
		.filter(session).log().all().
		
		when().post("/rest/api/2/issue/{issueId}/attachments").
		
		then().log().all().assertThat().statusCode(200);

	}

}
