package api;

import lombok.Getter;

import java.util.Properties;

@Getter
public class CustomConfig {

    private final String apiBaseUri;
    private final String apiBookingPath;
    private final String apiAuthPath;
    private final String username;
    private final String password;


    CustomConfig(Properties properties) {
        apiBaseUri = properties.getProperty("base.url");
        apiBookingPath = properties.getProperty("booking.path");
        apiAuthPath = properties.getProperty("auth.path");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }
}