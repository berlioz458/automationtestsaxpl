package bus.paymentservice.tests;

import bus.paymentservice.models.Result;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static bus.paymentservice.utils.PaymentController.paymentFormUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTests {
    @Test
    @Story("Оплата")
    @Tag("smoke_predprod")
    @Description("Получение урл для оплаты заказа картой")
    void getPaymentFromURLSuccessForPayOrderWithCard() {
        Result paymentRequest = paymentFormUrl(
                3456,
                "volodga@bus.ru",
                "http://fail.stage.gcs.prodv.net",
                125567,
                "sbrf",
                false,
                373898,
                1749484,
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
    @Tag("smoke_predprod")
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
    @Tag("smoke_predprod")
    @Description("Получение урл для пополнения баланса СБП")
    void getPaymentFromURLSuccessForBalanceWithSBP() {
        Result paymentRequest = paymentFormUrl(
                100,
                "volodga@bus.ru",
                "http://fail.stage.gcs.prodv.net",
                519587,
                "sbrf-qr",
                false,
                606580,
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
