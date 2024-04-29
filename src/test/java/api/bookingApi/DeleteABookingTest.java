package api.bookingApi;

import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.common.TestData.*;

@Test
public class DeleteABookingTest extends BookingsBase {

    String bookingId;

    @BeforeMethod
    public void createABooking() {
        bookingId = createABooking(getBookingDetailsBody
                (FIRST_NAME, LAST_NAME, DATE_FROM, DATE_TO, PRICE)).
                extract().
                path(BOOKING_ID_PATH).toString();
    }

    @Description("As an API user, I want to successfully delete a booking using its ID.")
    public void testDeleteABooking() {
        deleteABooking(getAuthHeader(), bookingId).
                assertThat().
                statusCode(HttpStatus.SC_CREATED);
    }

    @Description("As an API user, I should get an error message when deleting a booking without valid authorization.")
    public void testErrorHandlingWhenDeletingWithInvalidAuthorization() {
        deleteABooking(getInvaldAuthHeader(), bookingId).
                assertThat().
                spec(getForbiddenResponseSpec());
    }

    @Description("As an API user, I should get an error message when deleting a booking with no bookingId.")
    public void testErrorHandlingWhenDeletingWithNoBookingId() {
        deleteABooking(getInvaldAuthHeader(), "").
                assertThat().
                spec(getNotFoundResponseSpec());
    }
}