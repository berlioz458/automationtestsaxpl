package bus.orderservice.tests;

import bus.orderservice.models.Payment;
import org.junit.jupiter.api.Test;

import static bus.orderservice.utils.OrderServiceApiController.createPaymentForOrder;
import static bus.orderservice.utils.OrderServiceApiController.getPaymentFromClient;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTests {
    @Test
    void successCreatePaymentFromClient1C() {
        Payment payment = createPaymentForOrder();
        assertThat(payment.getId()).isPositive();
    }

    @Test
    void successGetInfoAboutPaymentFromClient() {
        Integer id = 1333540;
        Payment payment = getPaymentFromClient(1333540);
        assertThat(payment.getId()).isEqualTo(id);
    }
}
