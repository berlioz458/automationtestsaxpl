package bus.offerservice.utils;

import bus.offerservice.model.SearchResult;
import io.qameta.allure.Step;

import java.util.List;

import static bus.offerservice.spec.OfferServiceApiSpecs.request;
import static bus.offerservice.spec.OfferServiceApiSpecs.responseSpec;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class OfferServiceApiController {

    @Step("Поиск предложений по параметрам без бренда")
    public static List<SearchResult> findOffersWithBrand(
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
                .extract().as(List<SearchResult.class>);

    }
}
