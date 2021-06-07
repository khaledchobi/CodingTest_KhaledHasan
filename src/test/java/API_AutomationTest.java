import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class API_AutomationTest {


    @Test
    public void getAllUsers(){
        RestAssured.baseURI = "http://dummy.restapiexample.com";

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/v1/employees");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();
        int statusCode = response.getStatusCode();

        // Get the Response
        String bodyAsString = body.prettyPrint();
        System.out.println(bodyAsString);

        // Assertion for status code
        Assert.assertEquals(statusCode, 200, "Correct status code should display");


    }



    @Test
    public void deleteRequest(){
        RestAssured.baseURI = "http://dummy.restapiexample.com";

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.delete("/api/v1/delete/2");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();
        int statusCode = response.getStatusCode();

        // Get the Response
        String bodyAsString = body.prettyPrint();
        System.out.println(bodyAsString);

        // Assertion for status code
        Assert.assertEquals(statusCode, 200, "Correct status code should display");


    }

    @Test
    public void getUserById(){
        RestAssured.baseURI = "http://dummy.restapiexample.com";

        String response = given().log().all().
                header("Content-Type", "application/json; charset=UTF-8").
                when().get("/api/v1/employee/7").
                then().assertThat().
                statusCode(200).
                body("data \n" + "'id' \n" + "7", equalTo(7))
                .header("server", "cloudflare").extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response); //for parsing Json
        String username = js.getString("data \n" + "'employee_name' \n" + "'Herrod Chandler'");
        Assert.assertEquals(username, "Herrod Chandler", "Correct address received in the Response");

        String addressPath = ("data \n" + "'employee_salary' \n" + "137500");
        String address = js.getString(addressPath);
        Assert.assertEquals(address, "137500", "Correct address received in the Response");

        System.out.println(username);
        System.out.println(address);

    }


    @Test
    public void ValidateMessageBody()
    {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/v1/employee/7");
        // Retrieve the body of the Response
        ResponseBody body = response.getBody();
        // To check for sub string presence get the Response body as a String.
        // Do a String.contains
        String bodyAsString = body.asString();
        System.out.println(bodyAsString);
        Assert.assertEquals(bodyAsString.contains("Herrod Chandler") /*Expected value*/, true /*Actual Value*/, "Response body contains Herrod Chandler");
    }

    @Test
    public void DisplayAllNodesInUsersAPI() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/v1/employees");

        // Get response from query and print
        String abcd = given().log().all().queryParam("employee_name").header("Content-Type","application/json; charset=utf-8")
                .when().get("/api/v1/employee/7")
                .then().assertThat().statusCode(200).body("data \n" + "'employee_name' \n" + "'Herrod Chandler'", equalTo("Herrod Chandler"))
                .header("server", "cloudflare").extract().response().asString();
        System.out.println(abcd);


        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();
        // Let us print the Name variable to see what we got
        System.out.println("Id received from Response: " + jsonPathEvaluator.get("data \n" + "'id' \n" + "'7'"));
        String name = jsonPathEvaluator.get("data \n" + "'id' \n" + "'7'");
        // Validate the response
        Assert.assertEquals(name, "7", "Correct name received in the Response");


    }


}
