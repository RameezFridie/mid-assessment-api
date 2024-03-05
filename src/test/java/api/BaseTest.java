package api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static api.common.TestData.DATA_PATH;

public abstract class BaseTest {

    public static Properties properties;
    public static CustomConfig customConfig;

    @BeforeMethod
    public void setup() throws IOException {
        customConfig = new CustomConfig(getProperties());
        setUpEnvironment();
    }

    private Properties getProperties() throws IOException {
        properties = new Properties();
        try (FileInputStream file = new FileInputStream(DATA_PATH)) {
            properties.load(file);
        }
        return properties;
    }

    public void setUpEnvironment() {
        RestAssured.baseURI = customConfig.getApiBaseUri();
        RestAssured.basePath = customConfig.getApiBookingPath();
    }
}