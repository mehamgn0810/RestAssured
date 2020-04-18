package jira;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class GetIssuesAndParsingResponse {

	@Test
	public void getIssues() {

		RestAssured.baseURI = "http://localhost:8080";

		// Session Filter
		SessionFilter session = new SessionFilter();

		// Create Session
		given().relaxedHTTPSValidation(). // bypassing http certification
				header("Content-Type", "application/json")
				.body("{ \"username\": \"Mehavarnan\", \"password\": \"Jira@123\" }").log().all().filter(session).

				when().post("/rest/auth/1/session");

		// Add Comment
		String commentText = "This is a comment form Get Isse Test";
		String addCommentResponse = given().pathParam("issueID", "10100").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"" + commentText + "\"\r\n" + "}").log().all().filter(session).

				when().post("/rest/api/2/issue/{issueID}/comment").

				then().assertThat().statusCode(201).extract().response().asString();
		JsonPath jsComment = new JsonPath(addCommentResponse);
		int addCommentId = jsComment.getInt("id");

		// GetIssue
		String response = given().header("Content-Type", "application/json")
				.queryParam("fields", "comment") //filtering response using query parameter
				.pathParam("issueId", "10100").filter(session).log().all().

				when().get("/rest/api/2/issue/{issueId}").

				then().log().all().extract().response().asString();

		JsonPath js = new JsonPath(response);
		int commentsSize = js.getInt("fields.comment.comments.size()");

		// iterate through comments
		for (int i = 0; i < commentsSize; i++) {
			int CommentId = js.getInt("fields.comment.comments[" + i + "].id");
			if (CommentId == addCommentId) {
				String actualCommentMessage = js.getString("fields.comment.comments[" + i + "].body");
				System.out.println("Comment Id: " + CommentId);
				System.out.println("Comment Text: " + actualCommentMessage);
				Assert.assertEquals(actualCommentMessage, commentText); // Assertion
			}
		}
	}

}
