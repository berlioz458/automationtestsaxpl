package integrationservice.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class IntegrationUserApiSpecs {

    public static RequestSpecification success_request = with()
            .baseUri("http://api.integration.bus.stage.auto3n.ru")
            .auth().preemptive().basic("shulinina.e", "shulinina.e")
            .basePath("/v2")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification success_responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();


    public static RequestSpecification error_request = with()
            .baseUri("http://api.integration.bus.stage.auto3n.ru")
            .auth().preemptive().basic("shulinina.e", "shulinina.e")
            .basePath("/v2")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification error_responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .expectContentType(ContentType.JSON)
            .build();
}
