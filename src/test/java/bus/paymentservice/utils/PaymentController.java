package bus.paymentservice.utils;

import bus.paymentservice.models.PaymentRequest;
import bus.paymentservice.models.Result;
import io.qameta.allure.Step;

import static bus.paymentservice.spec.PaymentServiceApiSpecs.*;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class PaymentController {
    @Step("Получить URL для оплаты")
    public static Result paymentFormUrl(
            Integer amount,
            String email,
            String failUrl,
            Integer firstPartyCounteragentId,
            String gateCode,
            Boolean mobileView,
            Integer osContractId,
            Integer osOrderId,
            String phone,
            String reason,
            String successUrl
    ) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(amount);
        paymentRequest.setEmail(email);
        paymentRequest.setFailUrl(failUrl);
        paymentRequest.setFirstPartyCounteragentId(firstPartyCounteragentId);
        paymentRequest.setGateCode(gateCode);
        paymentRequest.setMobileView(mobileView);
        paymentRequest.setOsContractId(osContractId);
        paymentRequest.setOsOrderId(osOrderId);
        paymentRequest.setPhone(phone);
        paymentRequest.setReason(reason);
        paymentRequest.setSuccessUrl(successUrl);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(paymentRequest)
                .post("/service/AUTO3N/paymentFormUrl")
                .then()
                .spec(responseSpec)
                .extract().as(Result.class);
    }
}
