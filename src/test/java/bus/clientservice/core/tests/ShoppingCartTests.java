package bus.clientservice.core.tests;

import bus.clientservice.core.model.ShoppingCart;
import helpers.ListInfo;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static bus.clientservice.core.utils.ShoppingCartController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTests {

    @Test
    @Description("Успешное создание корзины для пользователя из ИМ")
    void successCreateShoppingCartForClientFromCSM() {
        Boolean haveActiveShoppingCart = checkActiveShoppingCart(12996);
        if (haveActiveShoppingCart) {
            Integer shoppingCartId = getActiveClientShoppingCart(12996);
            deletedShoppingCart(shoppingCartId);
        }

        ShoppingCart shoppingCart = createShoppingCart("CMS", 12996);
        assertThat(shoppingCart.getId()).isPositive();
    }

    @Test
    @Description("Успешное создание корзины для пользователя из мобильного приложения")
    void successCreateShoppingCartForClientFromMobile() {
        Boolean haveActiveShoppingCart = checkActiveShoppingCart(12996);
        if (haveActiveShoppingCart) {
            Integer shoppingCartId = getActiveClientShoppingCart(12996);
            deletedShoppingCart(shoppingCartId);
        }

        ShoppingCart shoppingCart = createShoppingCart("MOBILE", 12996);
        assertThat(shoppingCart.getId()).isPositive();
    }

    @Test
    @Description("Успешное получение информации о корзинах пользователей")
    void successGetShoppingCartForClients() {
        ListInfo<ShoppingCart> shoppingCartListInfo = getShoppingCartList();
        assertThat(shoppingCartListInfo.getTotal()).isPositive();
        assertThat(shoppingCartListInfo.getData().get(0).getId()).isPositive();
        assertThat(shoppingCartListInfo.getData().size()).isEqualTo(10);
    }

    @Test
    @Description("Успешное получение информации о корзинах клиента")
    void successGetShoppingCartForClientByPersonProfile() {
        ListInfo<ShoppingCart> shoppingCartListInfo = getShoppingCartList("id", "desc", 12996);
        assertThat(shoppingCartListInfo.getTotal()).isPositive();
        assertThat(shoppingCartListInfo.getData().get(0).getId()).isPositive();
    }

    @Test
    @Description("Получение корзин клиента активных")
    void successGetActiveShoppingCartForClientByPersonProfile() {
//        ListInfo<ShoppingCart> shoppingCartListInfo = getShoppingCartList("id", "desc", 12996, false);
//
//        Integer shoppingCartId = getActiveClientShoppingCart(12996);
//
//        assertThat(shoppingCartListInfo.getTotal()).isEqualTo(1);
//        assertThat(shoppingCartListInfo.getData().get(0).getId()).isEqualTo(shoppingCartId);
    }
}
