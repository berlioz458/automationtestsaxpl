package clientservice.registrationmanager.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class RegistrationManagerApiSpecs {
    public static RequestSpecification request = with()
            .baseUri("http://api.client-service.bus.stage.auto3n.ru")
            .auth().preemptive().basic("shulinina.e", "shulinina.e")
            .basePath("/v2/service/AUTO3N/registration")
            .log().all().contentType(ContentType.JSON);

    public static ResponseSpecification response = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
