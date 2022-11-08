package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Payment {
    private final Integer ownerAgentId = 10404;
    private final Integer currencyId = 643;
    private String externalId;
    private String merchantID;
    private String paymentTime;
    private String personContactInfo;
    private Integer sum;
    private String transactionNumber;
    private Ref contract;
    private Ref paymentType;
}
