package api.bookingApi;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static api.common.TestData.*;

@Test
public class UpdateABookingTest extends BookingsBase{

    @Description("As an API user, I want to successfully update the first name and surname of a booking.")
    public void testUpdateABooking() {
        String bookingId = createABooking(bookingsObject.getBookingDetailsBody
                (FIRST_NAME, LAST_NAME, DATE_FROM, DATE_TO, PRICE)).
                extract().
                path(BOOKING_ID_PATH).toString();

        updateABookingId(bookingId, bookingsObject.getBookingDetailsBody
                (UPDATE_FIRST_NAME, UPDATE_LAST_NAME, DATE_FROM, DATE_TO, PRICE)).
                assertThat().
                spec(bookingsObject.getBookingDetailsResponseBody(UPDATE_FIRST_NAME, UPDATE_LAST_NAME));

        deleteABooking(bookingId);
    }

    @Description("As an API user, I want to successfully partially update a booking with a new firstname")
    public void testPartiallyUpdateABooking() {
        String id = createABooking(bookingsObject.getBookingDetailsBody
                (FIRST_NAME, LAST_NAME, DATE_FROM, DATE_TO, PRICE)).
                extract().
                path(BOOKING_ID_PATH).toString();

        partiallyUpdateABookingId(id, bookingsObject.getPartialBookingDetailsBody(UPDATE_FIRST_NAME, LAST_NAME)).
                assertThat().
                spec(bookingsObject.getBookingDetailsResponseBody(UPDATE_FIRST_NAME, LAST_NAME));

        deleteABooking(id);
    }
}