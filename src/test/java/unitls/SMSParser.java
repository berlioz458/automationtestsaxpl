package unitls;

import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;

public class SMSParser {

    public static String getCodeBySms() throws InterruptedException {
        sleep(5000);
        String code = given()
        .when()
        .get("http://bus.stage.gcs.prodv.net:2080/messages?size=1&sort=receivedAt,desc")
        .then()
        .extract()
                .path("_embedded.messages[0].text");
        code = code.substring(0, 6);
        return code;
    }
}
