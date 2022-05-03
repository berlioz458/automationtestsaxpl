package orderservice;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

@Tag("order")
public class OrderServiceTests {

    @TmsLink("C1")
    @Tag("smoke")
    @Test
    @Description("Get Version")
    void successGetVersion() {
        given()
            .filter(withCustomTemplate())
        .when()
            .get("http://api.order-service.bus2.auto3n.ru/v2/version")
        .then()
            .statusCode(200);
    }

    @TmsLink("C2")
    @Test
    @Description("Get 'Counteragent' entity")
    void successGetCounteragent() {
        given()
            .filter(withCustomTemplate())
                .auth().preemptive().basic("SHOP", "shop2016auto3n")
        .when()
            .get("http://api.order-service.bus2.auto3n.ru/v2/entity/AUTO3N/Counteragent/35949")
        .then()
            .statusCode(200);
    }
}
