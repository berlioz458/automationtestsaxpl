package bus.clientservice.core.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static bus.clientservice.core.utils.ShoppingCartController.getShoppingCartList;

public class ShoppingCartTests {

    @Test
    @Description("Успешное создание корзины для пользователя из ИМ")
    void successCreateShoppingCartForClientFromCSM() {
        getShoppingCartList();
    }

    @Test
    @Description("Успешное создание корзины для пользователя из мобильного приложения")
    void successCreateShoppingCartForClientFromMobile() {

    }
}

//http://api.client-service.bus.stage.auto3n.ru/v2/entity/AUTO3N/ShoppingCart?sort%255Bid%255D=desc
//http://api.client-service.bus.stage.auto3n.ru/v2/entity/AUTO3N/ShoppingCart?sort%5Bid%5D=desc