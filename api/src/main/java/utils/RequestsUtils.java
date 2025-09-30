package utils;

import static io.restassured.RestAssured.given;
import dto.GetJobStatusResponse;
import dto.ScheduleJobRequest;
import dto.ScheduleJobResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RequestsUtils {

    public static ScheduleJobResponse scheduleJob(ScheduleJobRequest request, String endpoint) {
        return given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .as(ScheduleJobResponse.class);
    }

    public static GetJobStatusResponse getJobStatus(String endpoint, int expectedStatusCode) {
        return given()
                .when()
                .get(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .as(GetJobStatusResponse.class);
    }

    public static Response getScheduleJobRaw(ScheduleJobRequest request, String endpoint) {
        return given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(endpoint);
    }

    public static Response getJobStatusRaw(String endpoint, int expectedStatusCode) {
        return given()
                .when()
                .get(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }
}