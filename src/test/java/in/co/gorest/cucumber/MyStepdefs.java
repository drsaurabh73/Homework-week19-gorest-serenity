package in.co.gorest.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.co.gorest.goreststeps.GorestinfoSteps;
import in.co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyStepdefs {

    static String name = "Tenali Ramakrishna" ;
    static String gender = "female";
    static String email = "tenali.ramakrishna" + TestUtils.getRandomValue() + "@email.com"; ;
    static String status = "active" ;
    static int userID;
    static ValidatableResponse response;
    @Steps
    GorestinfoSteps gorestinfoSteps;

    @Given("^I am on Homepage of application GoRest$")
    public void iAmOnHomepageOfApplicationGoRest() {
    }
    
    @When("^User send a GET Request to list endpoint products$")
    public void userSendAGETRequestToListEndpointProducts() {       
        response = gorestinfoSteps.getAllUsers();
    }

    @Then("^User can get back a valid status code (\\d+) of product$")
    public void userCanGetBackAValidStatusCodeOfProduct(int code) {
        response.statusCode(code);
        response.assertThat().statusCode(code);
    }

    @When("^User can create new product using POST method in products application$")
    public void userCanCreateNewProductUsingPOSTMethodInProductsApplication() {
        HashMap<Object,Object> userdata = new HashMap<>();
        ValidatableResponse response = gorestinfoSteps.createUsers(name,gender,email,status,userdata);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }

    @When("^User can create new product using PUT method in products application$")
    public void userCanCreateNewProductUsingPUTMethodInProductsApplication() {
        HashMap<Object,Object> userdata = new HashMap<>();
        name = name  + "_updated";
        ValidatableResponse response = gorestinfoSteps.updateUsers(userID,name,gender,email,status,userdata);
        HashMap<String, Object> studentMap = gorestinfoSteps.getUserInfoByFirstname(userID);
        Assert.assertThat(studentMap, hasValue(name));
    }

    @And("^User verify that the product is deleted successfully from product$")
    public void userVerifyThatTheProductIsDeletedSuccessfullyFromProduct() {
        gorestinfoSteps.deleteUser(userID).statusCode(204);
        response.assertThat().statusCode(200);

    }

    @When("^User can Delete new product using DELETE method in products application$")
    public void userCanDeleteNewProductUsingDELETEMethodInProductsApplication() {
        gorestinfoSteps.getUsersById(userID).statusCode(404);
        response.assertThat().statusCode(200);
    }
}
