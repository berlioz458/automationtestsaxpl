package bus.paymentservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class PaymentRequest {
    private float amount;
    private final Integer currencyIsoNumber = 643;
    private String email;
    private String failUrl;
    private Integer firstPartyCounteragentId;
    private String gateCode;
    private Boolean mobileView;
    private Integer osContractId;
    private Integer osOrderId;
    private String phone;
    private String reason;
    private String successUrl;
}
