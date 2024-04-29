package api.auth;

import api.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

@Getter
public class AuthBase extends BaseTest {

    public static String token;

    @BeforeMethod
    public void setupAuth() {
        setUpAuthEnvironment();
        createToken();
    }

    private void createToken() {
        ValidatableResponse loginResponse = given()
                .header(getCommonHeader())
                .body(getAuthBody(customConfig.getUsername(), customConfig.getPassword()))
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

    public Header getInvaldAuthHeader() {return new Header("Cookie", "token=invalid");}

    public Header getCommonHeader() {
        return new Header("Content-Type", "application/json");
    }

    public JSONObject getAuthBody(String username, String password) {
        JSONObject loginCredentials = new JSONObject();
        loginCredentials.put("username", username);
        loginCredentials.put("password", password);

        return loginCredentials;
    }
}