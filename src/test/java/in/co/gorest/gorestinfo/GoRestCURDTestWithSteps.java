package in.co.gorest.gorestinfo;

import in.co.gorest.goreststeps.GorestinfoSteps;
import in.co.gorest.testbase.TestBase;
import in.co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class GoRestCURDTestWithSteps extends TestBase {

    static String name = "Tenali Ramakrishna" ;
    static String gender = "female";
    static String email = "tenali.ramakrishna" + TestUtils.getRandomValue() + "@email.com"; ;
    static String status = "active" ;
    static int userID;

    @Steps
    GorestinfoSteps gorestinfoSteps;
    @Title("This will create new user")
    @Test
    public void test001() {
        HashMap<Object,Object> userdata = new HashMap<>();
        ValidatableResponse response = gorestinfoSteps.createUsers(name,gender,email,status,userdata);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }
    @Title("Verify if the user was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = gorestinfoSteps.getUserInfoByFirstname(userID);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(userID);
    }
    @Title("Update the Users information and verify the updated information")
    @Test
    public void test003(){
        HashMap<Object,Object> userdata = new HashMap<>();
        name = name  + "_updated";
        ValidatableResponse response = gorestinfoSteps.updateUsers(userID,name,gender,email,status,userdata);
        HashMap<String, Object> studentMap = gorestinfoSteps.getUserInfoByFirstname(userID);
        Assert.assertThat(studentMap, hasValue(name));
    }
    @Title("Delete the User and verify if the User is deleted!")
    @Test
    public void test004() {
        gorestinfoSteps.deleteUser(userID).statusCode(204);
        gorestinfoSteps.getUsersById(userID).statusCode(404);
    }
    @Title("Getting all users")
    @Test
    public void test005(){
        gorestinfoSteps.getAllUsers();
    }

}
