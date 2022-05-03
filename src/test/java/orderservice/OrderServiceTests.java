package orderservice;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;
import static orderservice.OrderServiceApiSpecs.request;
import static orderservice.OrderServiceApiSpecs.responseSpec;
import static org.hamcrest.Matchers.is;

@Tag("order")
public class OrderServiceTests {
    @TmsLink("C1")
    @Tag("smoke")
    @Test
    @Description("Get Version")
    void successGetVersion() {
        given()
            .filter(withCustomTemplate())
            .spec(request)
        .when()
            .get("/version")
        .then()
            .spec(responseSpec);
    }

    @TmsLink("C2")
    @Test
    @Description("Get 'Counteragent' entity")
    void successGetCounteragent() {
        given()
            .filter(withCustomTemplate())
            .spec(request)
        .when()
            .get("/entity/AUTO3N/Counteragent/35949")
        .then()
            .spec(responseSpec)
            .log().body()
            .body("Counteragent.id", is(35949));
    }

    @Test
    @Description("Get 'Order' entity")
    void successGetOrder() {
        given()
            .filter(withCustomTemplate())
            .spec(request)
        .when()
            .get("/entity/AUTO3N/Order/1781356")
        .then()
            .spec(responseSpec)
            .log().body()
            .body("Order.id", is(1781356));
    }

    @Test
    @Description("Get 'Contract' entity")
    void successGetContract() {
        given()
            .filter(withCustomTemplate())
            .spec(request)
        .when()
            .get("/entity/AUTO3N/Contract/390800")
        .then()
            .spec(responseSpec)
            .log().body()
            .body("Contract.id", is(390800));
    }

    @Test
    @Disabled
    @Description("Create Contract Template")
    void successCreateContractTemplate() {

    }
}
