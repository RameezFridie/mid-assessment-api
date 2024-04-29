package api.bookingApi;

import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static api.common.TestData.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Test
public class CreateABookingTest extends BookingsBase {

    @Description("As an API user, I want to be able to successfully create a booking.")
    public void testCreateABooking() {
        String bookingId = createABooking(getBookingDetailsBody
                (FIRST_NAME, LAST_NAME, DATE_FROM, DATE_TO, PRICE)).
                assertThat().
                statusCode(HttpStatus.SC_OK).
                body(BOOKING_ID_PATH, is(notNullValue())).
                body("booking.firstname", is(FIRST_NAME)).
                body("booking.lastname", is(LAST_NAME)).
                extract().
                path(BOOKING_ID_PATH).toString();

        deleteABooking(getAuthHeader(), bookingId);
    }

    @Description("As an API user, I should be able to get an error when passing an empty object")
    public void testErrorHandlingWhenPassingEmptyObject() {
        JSONObject emptyJSONObject = new JSONObject();
        createABooking(emptyJSONObject).
                assertThat().
                statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}