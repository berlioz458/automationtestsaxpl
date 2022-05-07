package orderservice;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class OrderServiceApiSpecs {
    public static RequestSpecification request = with()
            .baseUri("http://api.order-service.bus.stage.auto3n.ru")
            .auth().preemptive().basic("SHOP", "123456")
            .basePath("/v2")
            .log().all();

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();
}
