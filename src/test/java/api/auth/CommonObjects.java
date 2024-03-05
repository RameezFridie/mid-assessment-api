package api.auth;

import org.json.simple.JSONObject;

public class CommonObjects {

    public JSONObject getAuthBody(String username, String password) {
        JSONObject loginCredentials = new JSONObject();
        loginCredentials.put("username", username);
        loginCredentials.put("password", password);

        return loginCredentials;
    }
}