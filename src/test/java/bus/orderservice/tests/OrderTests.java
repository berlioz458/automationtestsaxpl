package bus.orderservice.tests;

import bus.orderservice.models.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static bus.orderservice.utils.OrderServiceApiController.createOrderForClient;
import static bus.orderservice.utils.OrderServiceApiController.getOrderContract;
import static org.assertj.core.api.Assertions.assertThat;
@Tag("order")
public class OrderTests {
    @Test
    void successCreateOrderFromIM() {
        Order order = createOrderForClient();
        assertThat(order.getId()).isPositive();
    }

    @Test
    void successGetInfoAboutOrder() {
        Integer id = 1820211;
        Order order = getOrderContract(id);
        assertThat(order.getId()).isEqualTo(id);
    }
}
