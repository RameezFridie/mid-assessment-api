package api.bookingApi;

import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.common.TestData.*;

@Test
public class UpdateABookingTest extends BookingsBase {

    String bookingId;

    @BeforeMethod
    public void createABooking() {
        bookingId = createABooking(getBookingDetailsBody
                (FIRST_NAME, LAST_NAME, DATE_FROM, DATE_TO, PRICE)).
                extract().
                path(BOOKING_ID_PATH).toString();
    }

    @Description("As an API user, I want to successfully update the first name and surname of a booking.")
    public void testUpdateABooking() {
        updateABookingId(getAuthHeader(), bookingId, getBookingDetailsBody
                (UPDATE_FIRST_NAME, UPDATE_LAST_NAME, DATE_FROM, DATE_TO, PRICE)).
                assertThat().
                spec(getBookingDetailsResponseBody(UPDATE_FIRST_NAME, UPDATE_LAST_NAME));
    }

    @Description("As an API user, I want to successfully partially update a booking with a new firstname")
    public void testPartiallyUpdateABooking() {
        partiallyUpdateABookingId(bookingId, getPartialBookingDetailsBody(UPDATE_FIRST_NAME, LAST_NAME)).
                assertThat().
                spec(getBookingDetailsResponseBody(UPDATE_FIRST_NAME, LAST_NAME));
    }

    @Description("As an API user, I should get an error message when making a booking without valid authorization.")
    public void testErrorHandlingWhenUsingInvalidAuthorization() {
        updateABookingId(getInvaldAuthHeader(), bookingId, getBookingDetailsBody
                (UPDATE_FIRST_NAME, UPDATE_LAST_NAME, DATE_FROM, DATE_TO, PRICE)).
                assertThat().
                spec(getForbiddenResponseSpec());
    }

    @AfterMethod
    public void deleteBooking() {
        deleteABooking(getAuthHeader(), bookingId);
    }
}