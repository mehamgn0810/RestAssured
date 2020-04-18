package jira;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class AddComment {

	@Test
	public void addCommentToIssue() {
		
		RestAssured.baseURI = "http://localhost:8080";
		
		
		//Session Filter
		SessionFilter session = new SessionFilter();
		
		//Create Session
		given().
				header("Content-Type", "application/json").
				body("{ \"username\": \"Mehavarnan\", \"password\": \"Jira@123\" }").
				log().all().
				filter(session).
				
		when().
			post("/rest/auth/1/session").
			
		then().log().all().
		extract().response().asString();
		
		
		//Add Comment
		given().
		pathParam("issueID", "10100").
		header("Content-Type", "application/json").
		body("{\r\n" + 
				"    \"body\": \"Add Comment from Rest Assured\"\r\n" + 
				"}").
		log().all().
		filter(session).
		
		when().
		post("/rest/api/2/issue/{issueID}/comment").
		
		then().assertThat().statusCode(201);
	}
	
}
