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


}
