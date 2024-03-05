package api.bookingApi;

import api.auth.AuthBase;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class BookingsBase extends AuthBase {

    public static CommonObjects bookingsObject;

    @BeforeMethod
    public void setupBookingsBase() {
        setUpEnvironment();
        bookingsObject = new CommonObjects();
    }

    public ValidatableResponse createABooking(JSONObject createBookingBody) {
        return given().
                header(getCommonHeader()).
                body(createBookingBody).
                when().
                post().
                then();
    }

    public ValidatableResponse getABookingById(String id) {
        return given().
                when().
                get(id).
                then();
    }

    public ValidatableResponse updateABookingId(String id, JSONObject updateBookingBody) {
        return given().
                header(getAuthHeader()).
                header(getCommonHeader()).
                body(updateBookingBody).
                when().
                put(id).
                then();
    }

    public ValidatableResponse partiallyUpdateABookingId(String id, JSONObject partiallyUpdateBookingBody) {
        return given().
                header(getAuthHeader()).
                header(getCommonHeader()).
                body(partiallyUpdateBookingBody).
                when().
                patch(id).
                then();
    }

    public ValidatableResponse deleteABooking(String bookingId) {
        return given().
                header(getAuthHeader()).
                when().
                delete(bookingId).
                then();
    }
}
