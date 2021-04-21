package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	RequestSpecification request;
	Response response;

	@Given("GetEmployee Api {string}")
	public void getemployee_Api(String emp_id) throws IOException {
		request = given().spec(requestSpecification().pathParams("id", emp_id));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		ApiResources resourceapi = ApiResources.valueOf(resource);

		if (method.equalsIgnoreCase("Get")) {
			response = request.when().get(resourceapi.getResource());
		} else if (method.equalsIgnoreCase("Delete")) {
			response = request.when().delete(resourceapi.getResource());
		}
	}

	@Then("the API call is success with status code {int} {string}")
	public void the_API_call_is_success_with_status_code(int code, String method) {
		if (method.equals("GET")) {
			if (response.getStatusCode() == code) {
				String resp = response.asString();
				JsonPath js = new JsonPath(resp);
				System.out.println("Employee details:" + js.get("data").toString());
			} else {
				assertEquals("Emp record get has not been successfull.", code, response.getStatusCode());
				System.out.println("Employee record get API status code: " + response.getStatusCode());
			}
		} else {
			if (response.getStatusCode() == code) {
				String resp = response.asString();
				JsonPath js = new JsonPath(resp);
				assertEquals(js.get("message"), "Successfully! Record has been deleted");
				System.out.println("Successfully! deleted Records");
			} else {
				System.out.println("Emp record has not been deleted.status code is " + response.getStatusCode());
			}
		}
	}

	@Given("DeleteEmployee Api {string}")
	public void deleteemployee_Api(String emp_id) throws IOException {
		request = given().spec(requestSpecification().pathParams("id", emp_id));
	}
}
