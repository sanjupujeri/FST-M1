package Activities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Activity2 {

	private static final String BASE_URI = "https://petstore.swagger.io/v2/user";
	private static final String USERNAME = "justinc";
	private static final int USER_ID = 9901;
	private static final String INPUT_JSON = "src/test/resources/userInfo.json";
	private static final String OUTPUT_JSON = "src/test/java/activities/userGETResponse.json";

	@Test(priority = 1)
	public void createUserUsingJsonFile() throws IOException {

		try (FileInputStream jsonStream = new FileInputStream(INPUT_JSON)) {

			given().baseUri(BASE_URI).contentType(ContentType.JSON).body(jsonStream).when().post().then()
					.statusCode(200).body("code", equalTo(200)).body("message", equalTo(String.valueOf(USER_ID)));
		}
	}

	@Test(priority = 2)
	public void retrieveUserAndStoreResponse() {

		Response response = given().baseUri(BASE_URI).contentType(ContentType.JSON).pathParam("username", USERNAME)
				.when().get("/{username}").then().statusCode(200).extract().response();

		writeResponseToFile(response.asPrettyString());

		response.then().body("id", equalTo(USER_ID)).body("username", equalTo(USERNAME))
				.body("firstName", equalTo("Justin")).body("lastName", equalTo("Case"))
				.body("email", equalTo("justincase@mail.com")).body("password", equalTo("password123"))
				.body("phone", equalTo("9812763450"));
	}

	@Test(priority = 3)
	public void removeUser() {

		given().baseUri(BASE_URI).contentType(ContentType.JSON).pathParam("username", USERNAME).when()
				.delete("/{username}").then().statusCode(200).body("code", equalTo(200))
				.body("message", equalTo(USERNAME));
	}

	private void writeResponseToFile(String responseBody) {
		File file = new File(OUTPUT_JSON);

		try (FileWriter writer = new FileWriter(file)) {
			writer.write(responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
