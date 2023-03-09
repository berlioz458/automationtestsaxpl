package bus.paymentservice.tests;

import bus.paymentservice.models.Result;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static bus.paymentservice.utils.PaymentController.paymentFormUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTests {
    @Test
    @Story("Оплата")
    @Description("Получение урл для оплаты заказа картой")
    void getPaymentFromURLSuccessForPayOrderWithCard() {
        Result paymentRequest = paymentFormUrl(
                2452,
                "volodga@bus.ru",
                "http://fail.stage.gcs.prodv.net",
                125567,
                "sbrf",
                false,
                165084,
                1810996,
                "89529470960",
                "test",
                "http://success.stage.gcs.prodv.net");

        assertThat(paymentRequest).isNotNull();
        assertThat(paymentRequest.getUri()).isNotNull();
        assertThat(paymentRequest.getHttpMethod()).isEqualTo("GET");
        assertThat(paymentRequest.getExternalOrderId()).isNotNull();
        assertThat(paymentRequest.getRequestId()).isNotNull();
    }

    @Test
    @Story("Оплата")
    @Description("Получение урл для оплаты заказа СБП")
    void getPaymentFromURLSuccessForPayOrderWithSBP() {

    }

    @Test
    @Story("Оплата")
    @Description("Получение урл для пополнения баланса картой")
    void getPaymentFromURLSuccessForBalanceWithCard() {
        Result paymentRequest = paymentFormUrl(
                100,
                "volodga@bus.ru",
                "http://fail.stage.gcs.prodv.net",
                125567,
                "sbrf",
                false,
                187934,
                null,
                "89529470960",
                "test",
                "http://success.stage.gcs.prodv.net");

        assertThat(paymentRequest).isNotNull();
        assertThat(paymentRequest.getUri()).isNotNull();
        assertThat(paymentRequest.getHttpMethod()).isEqualTo("GET");
        assertThat(paymentRequest.getExternalOrderId()).isNotNull();
        assertThat(paymentRequest.getRequestId()).isNotNull();
    }

    @Test
    @Story("Оплата")
    @Description("Получение урл для пополнения баланса СБП")
    void getPaymentFromURLSuccessForBalanceWithSBP() {
        Result paymentRequest = paymentFormUrl(
                100,
                "volodga@bus.ru",
                "http://fail.stage.gcs.prodv.net",
                125567,
                "sbrf-qr",
                false,
                187934,
                null,
                "89529470960",
                "test",
                "http://success.stage.gcs.prodv.net");

        assertThat(paymentRequest).isNotNull();
        assertThat(paymentRequest.getUri()).isNotNull();
        assertThat(paymentRequest.getHttpMethod()).isEqualTo("GET");
        assertThat(paymentRequest.getExternalOrderId()).isNotNull();
        assertThat(paymentRequest.getRequestId()).isNotNull();
    }
}
