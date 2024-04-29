package api;

import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static api.common.TestData.DATA_PATH;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class BaseTest {

    private static final Logger LOG = getLogger(lookup().lookupClass());
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
        } catch (FileNotFoundException fileNotFound) {
            LOG.info(String.format("Properties file not found %s", fileNotFound));
        }
        return properties;
    }

    public void setUpEnvironment() {
        RestAssured.baseURI = customConfig.getApiBaseUri();
        RestAssured.basePath = customConfig.getApiBookingPath();
    }
}