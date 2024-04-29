package api.common;

import com.github.javafaker.Faker;
import org.slf4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class TestData {

    public static final String DATA_PATH = "data.properties";
    public static final int PRICE = 100;
    public static final String BOOKING_ID_PATH = "bookingid";
    private static final Logger LOG = getLogger(lookup().lookupClass());
    private static final Faker faker = new Faker();
    public static final String FIRST_NAME = faker.name().firstName();
    public static final String LAST_NAME = faker.name().lastName();
    public static final String UPDATE_FIRST_NAME = faker.name().firstName();
    public static final String UPDATE_LAST_NAME = faker.name().lastName();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final String DATE_FROM = generateRandomFromDate();
    public static final String DATE_TO = generateRandomToDate();

    private static String generateRandomFromDate() {
        long millis = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(faker.number().numberBetween(1, 365));
        Date randomDate = new Date(millis);
        return dateFormat.format(randomDate);
    }

    private static String generateRandomToDate() {
        try {
            Date fromDateObj = dateFormat.parse(TestData.DATE_FROM);
            long millis = fromDateObj.getTime() + TimeUnit.DAYS.toMillis(faker.number().numberBetween(1, 30));
            return dateFormat.format(new Date(millis));
        } catch (ParseException parseException) {
            LOG.info(String.format("Error parsing date %s", parseException));
            return null;
        }
    }
}