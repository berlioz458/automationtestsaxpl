package bus.orderservice.tests;

import bus.orderservice.models.Order;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static bus.orderservice.utils.OrderServiceApiController.createOrderForClient;
import static bus.orderservice.utils.OrderServiceApiController.getOrderContract;
import static org.assertj.core.api.Assertions.assertThat;
@Tag("order")
public class OrderTests {
    @Test
    @Tag("smoke_predprod")
    @Description("Создание заказа из ИМ - клиентом")
    void successCreateOrderFromImByUser() {
        Order order = createOrderForClient(10563, "test-2@prodv.net", 606580, false, false, "SELF");
        assertThat(order.getId()).isPositive();
    }

    @Test
    @Tag("smoke_predprod")
    @Description("Создание заказа из ИМ - делегированный")
    void successCreateOrderFromImByManager() {
        Order order = createOrderForClient(10563, "test-2@prodv.net", 606580, true, true, "SELF");
        assertThat(order.getId()).isPositive();
    }

    @Test
    @Tag("smoke_predprod")
    @Description("Создание заказа из ИМ - делегированный")
    void successCreateOrderFromImByManagerWithDelivery() {
        Order order = createOrderForClient(10563, "test-2@prodv.net", 606580, true, true, "COURIER");
        assertThat(order.getId()).isPositive();
    }

    @Test
    @Tag("smoke_predprod")
    @Description("Получении информации о заказе и его позициях по идентификатору")
    void successGetInfoAboutOrderById() {
        Integer id = 1820211;
        Order order = getOrderContract(id);
        assertThat(order.getId()).isEqualTo(id);
    }
}
