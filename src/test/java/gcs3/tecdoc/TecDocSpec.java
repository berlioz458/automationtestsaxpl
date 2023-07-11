package gcs3.tecdoc;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.lessThan;

public class TecDocSpec {
    public static RequestSpecification success_requestSpec = with()
            .baseUri("http://tecdoc.gcs3.bus.stage.auto3n.ru")
            .basePath("/api/v3/")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification success_responseSpec = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectContentType(ContentType.JSON)
            .expectHeader("Content-Encoding", "gzip")
            .expectResponseTime(lessThan(2500L), TimeUnit.MILLISECONDS)
            .build();
}
