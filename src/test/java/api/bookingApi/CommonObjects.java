package api.bookingApi;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

import static org.hamcrest.Matchers.equalTo;

public class CommonObjects {

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
}