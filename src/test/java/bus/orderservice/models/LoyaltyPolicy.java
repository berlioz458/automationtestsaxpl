package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Entity;
import helpers.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class LoyaltyPolicy extends Entity {
    private final Integer ownerAgentId = 10554;
    private final Boolean bonusSystemEnabled = false;
    private Integer contractPersonalDiscountLoyaltySystemThreshold;
    private Ref firstParty;
    private String name;
    private Integer selfAndPersonalDiscountLimit;
    private Integer selfOrderDiscountPercent;
}
