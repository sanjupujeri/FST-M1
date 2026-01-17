package Activities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Activity1 {

	private static final String BASE_URI = "https://petstore.swagger.io/v2/pet";
	private static final int PET_ID = 77232;

	private Map<String, Object> createPetPayload() {
		Map<String, Object> payload = new HashMap<>();
		payload.put("id", PET_ID);
		payload.put("name", "Riley");
		payload.put("status", "alive");
		return payload;
	}

	@Test(priority = 1)
	public void createPet() {

		Response response = given().baseUri(BASE_URI).contentType(ContentType.JSON).body(createPetPayload()).when()
				.post().then().statusCode(200).body("id", equalTo(PET_ID)).body("name", equalTo("Riley"))
				.body("status", equalTo("alive")).extract().response();

		System.out.println("Pet created successfully: " + response.asString());
	}

	@Test(priority = 2)
	public void fetchPetDetails() {

		given().baseUri(BASE_URI).contentType(ContentType.JSON).pathParam("id", PET_ID).when().get("/{id}").then()
				.statusCode(200).body("id", equalTo(PET_ID)).body("name", equalTo("Riley"))
				.body("status", equalTo("alive"));
	}

	@Test(priority = 3)
	public void removePet() {

		given().baseUri(BASE_URI).contentType(ContentType.JSON).pathParam("id", PET_ID).when().delete("/{id}").then()
				.statusCode(200).body("code", equalTo(200)).body("message", equalTo(String.valueOf(PET_ID)));
	}
}
