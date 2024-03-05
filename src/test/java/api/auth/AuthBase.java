package api.auth;

import api.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class AuthBase extends BaseTest {

    @Getter
    public static String token;
    public static CommonObjects authObjects;

    @BeforeMethod
    public void setupAuth() {
        authObjects = new CommonObjects();
        setUpAuthEnvironment();
        createToken();
    }

    private void createToken() {
        ValidatableResponse loginResponse = given()
                .header(getCommonHeader())
                .body(authObjects.getAuthBody(customConfig.getUsername(), customConfig.getPassword()))
                .post()
                .then();

        token = loginResponse.extract().jsonPath().getString("token");
    }

    private void setUpAuthEnvironment() {
        RestAssured.baseURI = customConfig.getApiBaseUri();
        RestAssured.basePath = customConfig.getApiAuthPath();
    }

    public Header getAuthHeader() {
        return new Header("Cookie", "token=" + token);
    }

    public Header getCommonHeader() {
        return new Header("Content-Type", "application/json");
    }
}