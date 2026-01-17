package Activities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Activity3 {

	private RequestSpecification reqSpec;
	private ResponseSpecification resSpec;

	@BeforeClass
	public void initializeSpecs() {

		reqSpec = new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2/pet")
				.addHeader("Content-Type", "application/json").build();

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json")
				.expectBody("status", equalTo("alive")).build();
	}

	@DataProvider(name = "petDataSet")
	public Object[][] providePetData() {
		return new Object[][] { { 77232, "Riley", "alive" }, { 77233, "Hansel", "alive" } };
	}

	@Test(priority = 1, dataProvider = "petDataSet")
	public void createPet(int id, String name, String status) {

		Map<String, Object> payload = new HashMap<>();
		payload.put("id", id);
		payload.put("name", name);
		payload.put("status", status);

		given().spec(reqSpec).body(payload).when().post().then().spec(resSpec).body("name", equalTo(name));
	}

	@Test(priority = 2, dataProvider = "petDataSet")
	public void fetchPetDetails(int id, String name, String status) {

		given().spec(reqSpec).pathParam("petId", id).when().get("/{petId}").then().spec(resSpec).body("name",
				equalTo(name));
	}

	@Test(priority = 3, dataProvider = "petDataSet")
	public void removePet(int id, String name, String status) {

		given().spec(reqSpec).pathParam("petId", id).when().delete("/{petId}").then().body("code", equalTo(200))
				.body("message", equalTo(String.valueOf(id)));
	}
}
