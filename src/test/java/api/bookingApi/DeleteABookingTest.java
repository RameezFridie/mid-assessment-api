package api.bookingApi;

import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static api.common.TestData.*;
import static api.common.TestData.BOOKING_ID_PATH;

@Test
public class DeleteABookingTest extends BookingsBase{

    @Description("As an API user, I want to successfully delete a booking using its ID.")
    public void testDeleteABooking() {
        String bookingId = createABooking(bookingsObject.getBookingDetailsBody
                (FIRST_NAME, LAST_NAME, DATE_FROM, DATE_TO, PRICE)).
                extract().
                path(BOOKING_ID_PATH).toString();

        deleteABooking(bookingId).
                assertThat().
                statusCode(HttpStatus.SC_CREATED);
    }
}