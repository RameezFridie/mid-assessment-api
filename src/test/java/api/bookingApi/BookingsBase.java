package api.bookingApi;

import api.auth.AuthBase;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BookingsBase extends AuthBase {

    @BeforeMethod
    public void setupBookingsBase() {
        setUpEnvironment();
    }

    public ValidatableResponse createABooking(JSONObject createBookingBody) {
        return given().
                header(getCommonHeader()).
                body(createBookingBody).
                when().
                post().
                then().log().all();
    }

    public ValidatableResponse getABookingById(String id) {
        return given().
                when().
                get(id).
                then();
    }

    public ValidatableResponse updateABookingId(Header token, String id, JSONObject updateBookingBody) {
        return given().
                header(token).
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

    public ValidatableResponse deleteABooking(Header token, String bookingId) {
        return given().
                header(token).
                when().
                delete(bookingId).
                then();
    }

    public JSONObject getBookingDetailsBody(String firstName, String lastName, String dateFrom,
                                            String dateTo, int price) {
        JSONObject body = new JSONObject();
        JSONObject bookingDatesObject = new JSONObject();

        bookingDatesObject.put("checkin", dateFrom);
        bookingDatesObject.put("checkout", dateTo);

        body.put("firstname", firstName);
        body.put("lastname", lastName);
        body.put("totalprice", price);
        body.put("depositpaid", true);
        body.put("bookingdates", bookingDatesObject);
        body.put("additionalneeds", "Breakfast");

        return body;
    }

    public JSONObject getPartialBookingDetailsBody(String firstName, String lastName) {
        JSONObject body = new JSONObject();
        body.put("firstname", firstName);
        body.put("lastname", lastName);

        return body;
    }

    public ResponseSpecification getBookingDetailsResponseBody(String firstName, String lastName) {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectBody("firstname", equalTo(firstName))
                .expectBody("lastname", equalTo(lastName))
                .build();
    }

    public ResponseSpecification getForbiddenResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_FORBIDDEN)
                .expectBody(is("Forbidden"))
                .build();
    }

    public ResponseSpecification getNotFoundResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .expectBody(is("Not Found"))
                .build();
    }
}