package gcs3.tochki;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.lessThan;

public class ApiSpecs {
    public static RequestSpecification success_requestSpec = with()
            .baseUri("http://4tochki.gcs.stage.gcs.prodv.net")
            .basePath("/api/v3/")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification success_responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .expectHeader("Content-Encoding", "gzip")
            .expectResponseTime(lessThan(2500L), TimeUnit.MILLISECONDS)
            .build();
}
