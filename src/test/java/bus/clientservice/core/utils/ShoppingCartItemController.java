package bus.clientservice.core.utils;

import bus.clientservice.core.model.ShoppingCart;
import bus.clientservice.core.model.ShoppingCartItem;
import helpers.ItemContextInfo;
import helpers.ListInfo;
import helpers.Ref;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static bus.clientservice.core.spec.ClientServiceApiSpec.entity_request;
import static bus.clientservice.core.spec.ClientServiceApiSpec.response;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class ShoppingCartItemController {

    static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    static Calendar calendar = new GregorianCalendar();
    @Step("Получение списка элементов в корзине")
    public static ListInfo<ShoppingCartItem> getShoppingCartItemList(Integer shoppingCartId) {
        return given()
                .filter(withCustomTemplate())
                .spec(entity_request)
                .param("q", "{\"$and\": [{\"shoppingCart\": {\"$eq\": " + shoppingCartId + "}}]}")
                .param("sort[id]", "desc")
                .get("/ShoppingCartItem")
                .then()
                .spec(response)
                .log().body(true)
                .extract()
                .body()
                .as(new TypeRef<ListInfo<ShoppingCartItem>>() {
                });
    }

    @Step("Получение айтема корзины по идентификатору")
    public static ShoppingCartItem getShoppingCartItemById(Integer shoppingCartItemId) {
        return given()
                .filter(withCustomTemplate())
                .spec(entity_request)
                .get("/ShoppingCartItem/" + shoppingCartItemId.toString())
                .then()
                .spec(response)
                .log().body(true)
                .extract()
                .body()
                .as(ShoppingCartItem.class);
    }

    @Step("Добавление айтема в корзину клиента")
    public static ShoppingCartItem addShoppingCartItem(Integer shoppingCart, String from) {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setAgentId(10508);
        shoppingCartItem.setBonusSpentAmountAllowed(BigDecimal.valueOf(8));
        shoppingCartItem.setBrand("HYUNDAI / KIA");
        shoppingCartItem.setEstimatedDeliveryDate(formater.format(calendar.getTime()));
        shoppingCartItem.setName("КАБЕЛЬ В СБОРЕ-СТОЯНОЧНЫЙ ТОРМОЗ");
        shoppingCartItem.setOem("597701R300");
        shoppingCartItem.setOfferId("PIN:597701R300:BRAND:MOBIS:VKORG:4000:KUNNR_RG:43275565:KEYZAK:0000142666:");
        shoppingCartItem.setPrice(1996.0);
        shoppingCartItem.setPriceListId(13159);
        shoppingCartItem.setQuantity(1.0);
        shoppingCartItem.setSelfOrderDiscountPercent(BigDecimal.valueOf(15));
        shoppingCartItem.setSelfOrderDiscountSum(BigDecimal.valueOf(352));
        shoppingCartItem.setSumm(1996.0);
        shoppingCartItem.setShoppingCart(new Ref("ShoppingCart",shoppingCart, "Основная корзина"));


        ItemContextInfo itemContextInfo = new ItemContextInfo();
        switch (from) {
            case ("OFFER"):
                itemContextInfo.setDetailCode("597701R300");
                itemContextInfo.setBrand("HYUNDAI / KIA");

                shoppingCartItem.setItemContextInfo(itemContextInfo);
                break;
            case ("OEM"):
                itemContextInfo.setDetailCode("597701R300");
                itemContextInfo.setBrand("HYUNDAI / KIA");
                itemContextInfo.setCatalog("AU1489");
                itemContextInfo.setCatalogCodeOnImage("21");
                itemContextInfo.setCatalogUnitId("5672556");
                itemContextInfo.setExternalCatalog("OEM");
                itemContextInfo.setSsd("$*KwHl0cDlgpq1oKe_4JG40b2piY6Q5O_o5bqxpeblr4-npaWT2MXUnpWdlZ6SlJ3a08iFn5-XqYmhoPjAy5aDk5ji4pPiv86t5ufh5eOopLrqwOf1ovPq9Yzz_6zpvu3j5-fl4-Tl8_7qsr6Rq_W08-mswcn1-vOjuvTr8dvT3fX687P17OPlv6y-uvTr8pbg8_-s8__17PPkleTkhcWisPWzsvLt9JCH36Kw9aKy8u30lInUorD1oLi-9ezzhM_V3p_j5ZLm55_iv7Gs4uLz_PWi8-ms1tWZiYKEhZ-Sh6z9AAAAAAi12fc=$");
                itemContextInfo.setVin("WAUBH54B11N111054");

                shoppingCartItem.setItemContextInfo(itemContextInfo);
                break;
            case ("TECDOC"):
                itemContextInfo.setDetailCode("597701R300");
                itemContextInfo.setBrand("HYUNDAI / KIA");
                itemContextInfo.setExternalCatalog("TecDocOnline");
                itemContextInfo.setExternalCatalogItemId("47260671");
                itemContextInfo.setExternalCatalogModelId("3395");
                itemContextInfo.setExternalCatalogModelManufacturerName("AUDI");
                itemContextInfo.setExternalCatalogModelName("A6 C5 Avant (4B5)");
                itemContextInfo.setExternalCatalogModificationId("57275");
                itemContextInfo.setExternalCatalogModificationName("3.0");
                itemContextInfo.setExternalCatalogNodeId("103779");
                itemContextInfo.setExternalCatalogNodeName("Масляный фильтр");

                shoppingCartItem.setItemContextInfo(itemContextInfo);
                break;
            case ("4TOCHKI"):
                itemContextInfo.setDetailCode("597701R300");
                itemContextInfo.setBrand("HYUNDAI / KIA");
                itemContextInfo.setExternalCatalog("4Tochki");
                itemContextInfo.setExternalCatalogModelId("2307");
                itemContextInfo.setExternalCatalogModelManufacturerName("Subaru");
                itemContextInfo.setExternalCatalogModelName("Forester");
                itemContextInfo.setExternalCatalogModificationId("11965");
                itemContextInfo.setExternalCatalogModificationName("2.0i S-Turbo");

                shoppingCartItem.setItemContextInfo(itemContextInfo);
                break;
            case ("USEARCH"):
                itemContextInfo.setDetailCode("597701R300");
                itemContextInfo.setBrand("HYUNDAI / KIA");
                itemContextInfo.setSearchQuery("антифриз зеленый 1л");
                itemContextInfo.setSearchQueryId("166884");

                shoppingCartItem.setItemContextInfo(itemContextInfo);
                break;
            case ("GCS"):
                itemContextInfo.setDetailCode("597701R300");
                itemContextInfo.setBrand("HYUNDAI / KIA");
                itemContextInfo.setGcsCategoryId(28L);
        }

        return given()
                .filter(withCustomTemplate())
                .spec(entity_request)
                .body(shoppingCartItem)
                .post("/ShoppingCartItem")
                .then()
                .spec(response)
                .log().body(true)
                .extract()
                .body()
                .as(ShoppingCartItem.class);
    }

    @Step("Удалить позицию из корзины")
    public static ShoppingCartItem deleteShoppingCartItem(Integer shoppingCartItemId) {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setDeleted(true);

        return given()
                .filter(withCustomTemplate())
                .spec(entity_request)
                .body(shoppingCartItem)
                .put("/ShoppingCartItem/" + shoppingCartItemId.toString())
                .then()
                .spec(response)
                .log().body(true)
                .extract()
                .body()
                .as(ShoppingCartItem.class);
    }

    @Step("Изменить количество у позиции корзины")
    public static ShoppingCartItem changeQuantity(Integer shoppingCartItemId, Integer quantity) {
        ShoppingCartItem shoppingCartItem = getShoppingCartItemById(shoppingCartItemId);

        shoppingCartItem.setQuantity(Double.valueOf(quantity));
        shoppingCartItem.setSumm(shoppingCartItem.getPrice() * quantity);

        return given()
                .filter(withCustomTemplate())
                .spec(entity_request)
                .body(shoppingCartItem)
                .put("/ShoppingCartItem/" + shoppingCartItemId.toString())
                .then()
                .spec(response)
                .log().body(true)
                .extract()
                .body()
                .as(ShoppingCartItem.class);
    }
}
