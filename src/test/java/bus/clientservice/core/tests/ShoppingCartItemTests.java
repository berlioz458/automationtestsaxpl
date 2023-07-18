package bus.clientservice.core.tests;

import bus.clientservice.core.model.ShoppingCartItem;
import helpers.ListInfo;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static bus.clientservice.core.utils.ShoppingCartItemController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartItemTests {

    private final Integer SHOPPING_CART_ID = 1213569;

    @Test
    @Description("Получение списка айтемов корзины клиента")
    void successGetShoppingCartItemList() {
        ListInfo<ShoppingCartItem> cartItemListInfo = getShoppingCartItemList(SHOPPING_CART_ID);

        assertThat(cartItemListInfo.getTotal()).isPositive();
        assertThat(cartItemListInfo.getData()).isNotNull();
    }

    @Test
    @Description("Получение айтема корзины клиента по идентификатору")
    void successGetShoppingCartItemById() {
        ListInfo<ShoppingCartItem> cartItemListInfo = getShoppingCartItemList(SHOPPING_CART_ID);

        ShoppingCartItem shoppingCartItem = getShoppingCartItemById(cartItemListInfo.getData().get(0).getId());
        assertThat(shoppingCartItem.getId()).isPositive();
        assertThat(shoppingCartItem.getId()).isEqualTo(cartItemListInfo.getData().get(0).getId());
    }

    @Test
    @Description("Добавить позицию в корзину")
    void successAddNewItem() {
        ShoppingCartItem shoppingCartItem = addShoppingCartItem(SHOPPING_CART_ID, "OEM");
        assertThat(shoppingCartItem.getId()).isPositive();

        deleteShoppingCartItem(shoppingCartItem.getId());
    }

    @Test
    @Description("Изменение количества у позиции в корзине")
    void successChangeQuantity() {
        ListInfo<ShoppingCartItem> cartItemListInfo = getShoppingCartItemList(SHOPPING_CART_ID);
        ShoppingCartItem shoppingCartItem = getShoppingCartItemById(cartItemListInfo.getData().get(0).getId());

        shoppingCartItem = changeQuantity(shoppingCartItem.getId(), 3);

        assertThat(shoppingCartItem.getId()).isPositive();
        assertThat(shoppingCartItem.getQuantity()).isEqualTo(3);
    }
}
