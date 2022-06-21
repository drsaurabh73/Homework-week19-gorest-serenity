package in.co.gorest.gorestinfo;

import in.co.gorest.constant.EndPoints;
import in.co.gorest.testbase.TestBase;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
public class FirstTest extends TestBase {

    @Test
    public void getAllUsers(){
            SerenityRest.given()
                    .when()
                    .get(EndPoints.GET_ALL_USERS)
                    .then()
                    .log().all()
                    .statusCode(200);
        }
}
