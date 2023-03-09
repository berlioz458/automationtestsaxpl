package bus.deliveryservice.utils;

import bus.deliveryservice.model.ShippingItem;
import bus.deliveryservice.model.ShippingRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static bus.deliveryservice.spec.DeliveryServiceApiSpecs.request;
import static bus.deliveryservice.spec.DeliveryServiceApiSpecs.responseSpec;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class DeliveryServiceApiController {
    @Step
    public static Response rates(
            Integer agentId,
            List<ShippingItem> shippingItems,
            String shippingMethod,
            float toLatitude,
            float toLongitude,
            Integer pointsPerCompanyLimit,
            String shippingDate
    ){
        ShippingRequest body = new ShippingRequest(agentId, shippingItems, shippingMethod, toLatitude, toLongitude, pointsPerCompanyLimit, shippingDate);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/service/AUTO3N/delivery/rates")
                .then()
                .spec(responseSpec)
                .extract().response();
    }
}
