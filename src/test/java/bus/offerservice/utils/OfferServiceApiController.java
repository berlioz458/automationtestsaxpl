package bus.offerservice.utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static bus.offerservice.spec.OfferServiceApiSpecs.request;
import static bus.offerservice.spec.OfferServiceApiSpecs.responseSpec;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class OfferServiceApiController {

    @Step("Поиск предложений по параметрам c брендом")
            public static Response findOffersWithBrand(
                    String oem,
            String brand,
            Integer agentId,
            Integer priceGroupId,
            Integer currencyId,
            Boolean allowCrosses,
            Boolean bestOffersOnly,
            Integer bestOffersLimit,
            String useSources,
            Boolean selfOrder,
            String mode,
            String format
    ) {

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/offers/AUTO3N/findOffers?" +
                        "oem=" + oem +
                        "&brand=" + brand +
                        "&agentId=" + agentId.toString() +
                        "&priceGroupId=" + priceGroupId.toString() +
                        "&currencyId=" + currencyId.toString() +
                        "&allowCrosses=" + allowCrosses.toString() +
                        "&bestOffersOnly=" + bestOffersOnly.toString() +
                        "&bestOffersLimit=" + bestOffersLimit.toString() +
                        "&useSources=" + useSources +
                        "&selfOrder=" + selfOrder.toString() +
                        "&mode=" + mode +
                        "&format=" + format)
                .then()
                .spec(responseSpec)
                .extract().response();
    }

    @Step("Поиск брендов")
    public static Response findOffersWithOutBrand(
            String oem,
            Integer agentId,
            Integer priceGroupId,
            Integer currencyId,
            Boolean allowCrosses,
            Boolean bestOffersOnly,
            Integer bestOffersLimit,
            String useSources,
            Boolean selfOrder,
            String mode,
            String format
    ) {

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/offers/AUTO3N/findOffers?" +
                        "oem=" + oem +
                        "&agentId=" + agentId.toString() +
                        "&priceGroupId=" + priceGroupId.toString() +
                        "&currencyId=" + currencyId.toString() +
                        "&allowCrosses=" + allowCrosses.toString() +
                        "&bestOffersOnly=" + bestOffersOnly.toString() +
                        "&bestOffersLimit=" + bestOffersLimit.toString() +
                        "&useSources=" + useSources +
                        "&selfOrder=" + selfOrder.toString() +
                        "&mode=" + mode +
                        "&format=" + format)
                .then()
                .spec(responseSpec)
                .extract().response();
    }


}
