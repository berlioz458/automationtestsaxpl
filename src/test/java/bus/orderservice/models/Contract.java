package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Entity;
import helpers.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Contract extends Entity {
    private final String name = "С покупателем";
    private Integer priceGroupId;
    private Boolean active;
    private String deliveryGroupCode;
    private String fromDate;
    private Boolean manualDiscountManagement;
    private Integer agentId;
    private Integer personalDiscount;
    private Integer ownerAgentId;
    private Integer currencyId;
    private Ref contractType;
    private Ref secondParty;
    private Ref firstParty;
}
