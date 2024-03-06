package api.common;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestData {

    private static final Faker faker = new Faker();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static final String DATA_PATH = "data.properties";
    public static final String FIRST_NAME = faker.name().firstName();
    public static final String LAST_NAME = faker.name().lastName();
    public static final String UPDATE_FIRST_NAME = faker.name().firstName();
    public static final String UPDATE_LAST_NAME = faker.name().lastName();
    public static final String DATE_FROM = generateRandomFromDate();
    public static final String DATE_TO = generateRandomToDate();
    public static final int PRICE = 100;
    public static final String BOOKING_ID_PATH = "bookingid";

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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}