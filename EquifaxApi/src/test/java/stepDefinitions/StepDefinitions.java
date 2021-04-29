package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import resources.ApiResources;
import resources.Utils;

public class StepDefinitions extends Utils {
	RequestSpecification request;
	Response response;

	@Given("GetEmployee API {string}")
	public void getEmployee_API(String emp_id) throws IOException {
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

	@Then("validate response code {int} {string}")
	public void validate_response_code(int code, String method) {
		if (method.equals("GET")) {
			if (response.getStatusCode() == code) {
				String resp = response.asString();
				JsonPath js = new JsonPath(resp);
			} else {
				assertEquals("Emp record get has not been successfull.", code, response.getStatusCode());
			}
		} else {
			if (response.getStatusCode() == code) {
			} else {
				assertEquals("Emp record delete has not been successfull.", code, response.getStatusCode());
			}
		}
	}

	@Then("validate the response data for get API {string} {int} {int}")
	public void validate_the_response_data_for_get_API(String emp_name, int emp_sal, int age) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		HashMap data = js.get("data");
		assertEquals(emp_name, data.get("employee_name"));
		assertEquals(emp_sal, data.get("employee_salary"));
		assertEquals(age, data.get("employee_age"));
	}

	@Given("DeleteEmployee API {string}")	
	public void deleteEmployee_API(String emp_id) throws IOException {
		request = given().spec(requestSpecification().pathParams("id", emp_id));
	}

	@Then("validate the reponse data for delete API {string} {string}")
	public void validate_the_reponse_data_for_delete_API(String status_code, String message) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(status_code, js.get("status"));
		assertEquals(message, js.get("message"));		
	}
}
