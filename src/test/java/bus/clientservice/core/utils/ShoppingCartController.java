package bus.clientservice.core.utils;

import bus.clientservice.core.model.ShoppingCart;
import helpers.ListInfo;
import helpers.Ref;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;

import java.util.ArrayList;
import java.util.List;

import static bus.clientservice.core.spec.ClientServiceApiSpec.*;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class ShoppingCartController {

    @Step("Создание корзины")
    public static ShoppingCart createShoppingCart(String from, Integer personProfile) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setPersonProfile(new Ref("PersonProfile", personProfile, null));
        switch (from) {
            case ("CMS"):
                shoppingCart.setName("Основная корзина");
                break;

            case ("MOBILE"):
                shoppingCart.setName("Mobile shop cart");
                break;
        }

        return given()
                .filter(withCustomTemplate())
                .spec(entity_request)
                .body(shoppingCart)
                .post("/entity/AUTO3N/ShoppingCart")
                .then()
                .spec(response)
                .extract().as(ShoppingCart.class);
    }

    @Step("Проверка на наличие активных корзин у клиента")
    public static Boolean checkActiveShoppingCart(Integer personProfile) {
        ListInfo<ShoppingCart> shoppingCartListInfo = getShoppingCartList("id", "desc", personProfile);

        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        shoppingCartList = shoppingCartListInfo.getData();

        for (ShoppingCart s: shoppingCartList
             ) {
            if (!s.getDeleted()) {
                return true;
            }
        }
        return false;
    }

    @Step("Получение активной корзины пользователя")
    public static Integer getActiveClientShoppingCart(Integer personProfile) {
        ListInfo<ShoppingCart> shoppingCartListInfo = getShoppingCartList("id", "desc", personProfile);

        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        shoppingCartList = shoppingCartListInfo.getData();
        for (ShoppingCart s: shoppingCartList
        ) {
            try {
                if (!s.getDeleted()) {
                    return s.getId();
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return 0;
    }

    @Step("Получение списка корзин")
    public static ListInfo<ShoppingCart> getShoppingCartList() {
        return given()
                .filter(withCustomTemplate())
                .spec(entity_request)
                .get("/ShoppingCart")
                .then()
                .spec(response)
                .log().body(true)
                .extract()
                .body()
                .as(new TypeRef<ListInfo<ShoppingCart>>() {
                });
    }

    @Step("Получение списка корзин (вкл. сортировка)")
    public static ListInfo<ShoppingCart> getShoppingCartList(String sort, String sortOrder) {
        return given()
                .filter(withCustomTemplate())
                .spec(entity_request)
                .get("/ShoppingCart?sort["+ sort +"]=" + sortOrder)
                .then()
                .spec(response)
                .log().body(true)
                .extract()
                .body()
                .as(new TypeRef<ListInfo<ShoppingCart>>() {
                });
    }

    @Step("Получение списка корзин для пользователя")
    public static ListInfo<ShoppingCart> getShoppingCartList(String sort, String sortOrder, Integer personProfile) {
        return given()
                .filter(withCustomTemplate())
                .spec(entity_request)
                .get("/ShoppingCart?sort["+ sort +"]=" + sortOrder + "{\"$and\": [{\"personProfile\": {\"$eq\": " + personProfile + "}}]}")
                .then()
                .spec(response)
                .log().body(true)
                .extract()
                .body()
                .as(new TypeRef<ListInfo<ShoppingCart>>() {
                });
    }

    @Step("Получение списка корзин для пользователя по состоянию корзины")
    public static ListInfo<ShoppingCart> getShoppingCartList(String sort, String sortOrder, Integer personProfile, Boolean deleted) {
        return given()
                .filter(withCustomTemplate())
                .spec(entity_request)
                .get("/ShoppingCart?sort["+ sort +"]=" + sortOrder + "{\"$and\": [{\"personProfile\": {\"$eq\": " + personProfile + "}},{\"deleted\": {\"$eq\": " + deleted + "}}]}")
                .then()
                .spec(response)
                .log().body(true)
                .extract()
                .body()
                .as(new TypeRef<ListInfo<ShoppingCart>>() {
                });
    }
}
