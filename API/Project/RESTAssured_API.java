package RESTAssured_Project;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RESTAssured_API {

	
	RequestSpecification requestSpec;

	String sshKey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQC90QkBq8XoZ5kOxVFOGpooFWAd5pvaI9XaBdGn1l0l/iODhiV7S6YQytenuSCLKzZ1MJAZST7icFG6VoPA0ZkN1ynXPHIO8uNyCJmMNZZ6JLR/s86r19T2XPeDPGhifuaE+6NNyNjyinKkIsQ8aNC9HMCgnLmVE4gT9hvQGVvFsthT4HzcdnjPjxMBM3vk5CRwaLttrBBpjZBAADcnADOhMx2vosBO7B0SdY94U+cxOPoPQc2Uj1mcdQvfk9pDm1tVF9hecVFB6zMFbBMppDbwNkDe4f2494BMA9X6L3tKVWN0w6yboRGmfLfR57NSSrV4p40OIMcnPqlqxkEvZoumHXJkiCVZsyJyzhx1YwmMtDA1ycqm8fZ9hNZoLOSEGu58cTE/N4lAzNtQJuZJD3r5TXYbxj+CSbe7YxnkLO96SpDmaSXCuxKq/4mX7qnPPyNEoYSSaHGHqcoaDoQc+Y04Mw/km7VSv3aubFGOxGjOKa8MtMXgZo6gJa+xDOo1ZEgp6pO3Bu6JoT9Jk+ilRI3B5Djzz2ing+aWgNnMrslqKVs85x2VoUeNGdOLmIpR+Bo1sk/96jZSkOwmeph3H0aohCxiCwd0AsHzosDD/pcbqUrIK3q1kUtGwY5RRl4ZwjOeki7KIZ6wZD0oUL/ECXCViSs2zWYREHW0MsqMEAAaZw==";

	
	int keyId;

	@BeforeClass
	public void setup() {

		requestSpec = new RequestSpecBuilder().setBaseUri("https://api.github.com").setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer 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")
				.addHeader("Accept", "application/vnd.github+json").build();

		RestAssured.requestSpecification = requestSpec;
	}

//POST Request	
	@Test(priority = 1)
	public void addSSHKey() {

		String requestBody = "{\n" + "  \"title\": \"TestAPIKey\",\n" + "  \"key\": \"" + sshKey + "\"\n" + "}";

		Response response = given().body(requestBody).when().post("/user/keys").then().extract().response();

		
		keyId = response.jsonPath().getInt("id");

		Reporter.log("POST Response: " + response.asString(), true);
		Reporter.log("Generated Key ID: " + keyId, true);

		
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertTrue(keyId > 0);
	}

	//GET Request 
	@Test(priority = 2)
	public void getSSHKey() {

		Response response = given().pathParam("keyId", keyId).when().get("/user/keys/{keyId}").then().extract()
				.response();

		Reporter.log("GET Response: " + response.asString(), true);

		// Assertions
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("id"), keyId);
	}

	//DELETE Request
	@Test(priority = 3)
	public void deleteSSHKey() {

		Response response = given().pathParam("keyId", keyId).when().delete("/user/keys/{keyId}").then().extract()
				.response();

		Reporter.log("DELETE Status Code: " + response.getStatusCode(), true);

	
		Assert.assertEquals(response.getStatusCode(), 204);
	}
}
