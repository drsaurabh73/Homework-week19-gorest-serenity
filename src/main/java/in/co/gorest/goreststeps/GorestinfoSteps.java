package in.co.gorest.goreststeps;

import in.co.gorest.constant.EndPoints;
import in.co.gorest.model.GorestPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class GorestinfoSteps {
    @Step
    public ValidatableResponse createUsers(String name, String gender, String email, String status, HashMap<Object, Object> user) {

        GorestPojo gorestPojo = new GorestPojo();
       gorestPojo.setName(name);
       gorestPojo.setGender(gender);
       gorestPojo.setEmail(email);
       gorestPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .body(gorestPojo)
                .when()
                .post(EndPoints.CREATE_USERS_BY_ID)
                .then();

    }
    @Step("Getting the Users information with name: {0}")
    public HashMap<String, Object> getUserInfoByFirstname(int userID) {

        HashMap<String, Object> storeMap = SerenityRest.given().log().all()
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .when()
                .pathParam("userID", userID)
                .get(EndPoints.GET_SINGLE_USERS_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return storeMap;
    }
    @Step
    public ValidatableResponse updateUsers(int userID,String name, String gender, String email, String status, HashMap<Object, Object> user) {

        GorestPojo gorestPojo = new GorestPojo();
        gorestPojo.setName(name);
        gorestPojo.setGender(gender);
        gorestPojo.setEmail(email);
        gorestPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .pathParam("userID",userID)
                .body(gorestPojo)
                .when()
                .put(EndPoints.UPDATE_USERS_BY_ID)
                .then();

    }
    @Step("Deleting Users information with userId: {0}")
    public ValidatableResponse deleteUser(int userID){
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .pathParam("userID", userID)
                .when()
                .delete(EndPoints.DELETE_USERS_BY_ID)
                .then();
    }

    @Step("Getting Users information with userId: {0}")
    public ValidatableResponse getUsersById(int userID){
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_SINGLE_USERS_BY_ID)
                .then();
    }
    @Step("Getting all users")
    public ValidatableResponse getAllUsers() {
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then();
    }
}
