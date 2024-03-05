package api.bookingApi;

import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static api.common.TestData.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Test
public class GetBookingsTest extends BookingsBase {

    @Description("As an API user, I want to successfully retrieve a list of bookings")
    public void testRetrieveListOfBookings() {
        given().
                when().
                get().
                then().assertThat().
                statusCode(HttpStatus.SC_OK).
                body(is(notNullValue()));
    }

    @Description("As an API user, I want to successfully retrieve a booking by ID.")
    public void testRetrieveBookingById() {
        String bookingId = createABooking(bookingsObject.getBookingDetailsBody
                (FIRST_NAME, LAST_NAME, DATE_FROM, DATE_TO, PRICE)).
                extract().
                path(BOOKING_ID_PATH).toString();

        getABookingById(bookingId).
                assertThat().
                spec(bookingsObject.getBookingDetailsResponseBody(FIRST_NAME, LAST_NAME));

        deleteABooking(bookingId);

    }

    @Description("As an API user, I want to unsuccessfully retrieve a booking using an invalidID.")
    public void testAttemptToRetrieveBookingWithInvalidId() {
        getABookingById("invalidId").
                assertThat().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }
}