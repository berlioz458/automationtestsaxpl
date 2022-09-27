package unitls;

import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;

public class SMSParser {
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    static String baseUri = config.uri();
    public static String getCodeBySms() throws InterruptedException {
        sleep(5000);
        String code = given()
        .when()
        .get("http://" + baseUri + ":2080/messages?size=1&sort=receivedAt,desc")
        .then()
        .extract()
                .path("_embedded.messages[0].text");
        code = code.substring(0, 6);
        return code;
    }
}
