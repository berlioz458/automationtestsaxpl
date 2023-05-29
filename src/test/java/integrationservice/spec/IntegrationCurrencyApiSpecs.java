package integrationservice.spec;

import config.CredentialsConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.with;

public class IntegrationCurrencyApiSpecs {
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    static String baseUri = config.uri();
    static String username = config.usr();
    static String password = config.pswrd();
    public static RequestSpecification success_request = with()
            .baseUri("http://api.integration."+baseUri)
            .auth().preemptive().basic(username, password)
            .basePath("/v2")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification success_responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();


    public static RequestSpecification error_request = with()
            .baseUri("http://api.integration."+baseUri)
            .auth().preemptive().basic(username, password)
            .basePath("/v2")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification error_responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .expectContentType(ContentType.JSON)
            .build();
}
