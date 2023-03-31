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


    @Step("Перепроценка - проверка доступности предложения")
    public static Response findPriceListOffers(Integer agentId, Integer priceGroupId, Integer currencyId, String useSources){
        String body = "[\n" +
                "    {\n" +
                "        \"brand\": \"CHERY\",\n" +
                "        \"oem\": \"A113707130405060EA\",\n" +
                "        \"pricelistIds\": [\n" +
                "            13971, 13179, 14219\n" +
                "        ],\n" +
                "        \"offerIds\": [\n" +
                "            \"AUTOPITER:7685_1684667645_3:1138\",\n" +
                "            \"PIN:A11-3707130405060EA:BRAND:CHERY:VKORG:4000:KUNNR_RG:43275565:KEYZAK:0000141966:\"\n" +
                "        ],\n" +
                "        \"selfOrder\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"brand\": \"FEBEST\",\n" +
                "        \"oem\": \"RINGAH009\",\n" +
                "        \"pricelistIds\": [\n" +
                "            14219,13180\n" +
                "        ],\n" +
                "        \"offerIds\": [\n" +
                "            \"PARTKOM:RINGAH009:346351:5541:252.0\",\n" +
                "            \"PIN:RINGAH-009:BRAND:FEBEST:VKORG:4000:KUNNR_RG:43275565:KEYZAK:MOV0007276:\"\n" +
                "        ],\n" +
                "        \"selfOrder\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"brand\": \"AIRLINE\",\n" +
                "        \"oem\": \"ARWAV02\",\n" +
                "        \"pricelistIds\": [\n" +
                "            13271\n" +
                "        ],\n" +
                "        \"offerIds\": [\n" +
                "            \"00-00000929\"\n" +
                "        ],\n" +
                "        \"selfOrder\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"brand\": \"PRC\",\n" +
                "        \"oem\": \"8200068583\",\n" +
                "        \"pricelistIds\": [\n" +
                "            13179\n" +
                "        ],\n" +
                "        \"offerIds\": [\n" +
                "            \"PIN:8200068583:BRAND:PRC:VKORG:4000:KUNNR_RG:43275565:KEYZAK:0000107481:\"\n" +
                "        ],\n" +
                "        \"selfOrder\": true\n" +
                "    }\n" +
                "]";
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/offers/AUTO3N/findPricelistOffers?" +
                        "agentId=" + agentId.toString() +
                        "&priceGroupId=" + priceGroupId.toString() +
                        "&currencyId=" + currencyId.toString() +
                        "&useSources=" + useSources)
                .then()
                .spec(responseSpec)
                .extract().response();
    }
}
