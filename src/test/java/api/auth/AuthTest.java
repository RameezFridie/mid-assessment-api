package api.auth;

import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Test
public class AuthTest extends AuthBase {

    @Description("As an API user, I want to successfully retrieve a token.")
    public void testRetrieveTokenSuccessfully() {
        given().
                header(getCommonHeader()).
                body(getAuthBody(customConfig.getUsername(), customConfig.getPassword())).
                when().
                post().
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                body("token", is(notNullValue()));
    }

    @Description("As an API user, I want to unsuccessfully retrieve a token using an invalid username and password.")
    public void testAttemptToRetrieveTokenWithInvalidCredentials() {
        given().
                header(getCommonHeader()).
                body(getAuthBody("invalidUserName", "invalidPassword")).
                when().
                post().
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                body("reason", is("Bad credentials"));
    }
}