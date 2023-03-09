package bus.paymentservice.spec;

import config.CredentialsConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.with;

public class PaymentServiceApiSpecs {
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    static String baseUri = config.uri();
    static String username = config.usr();
    static String password = config.pswrd();
    public static RequestSpecification request = with()
            .baseUri("http://api.payment-service." + baseUri)
            .auth().preemptive().basic(username, password)
            .basePath("/v2")
            .log().all().contentType(ContentType.JSON);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();
}
