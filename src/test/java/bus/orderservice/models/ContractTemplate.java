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
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ContractTemplate extends Entity {
    private final Integer currencyId = 643;
    private final Integer priceGroupId = 1020;
    private final Integer forRegionAgentId = 10156;
    private final Integer defaultAgentId = 10156;
    private final Boolean deleted = false;
    private String name;
    private Ref firstParty;
    private Ref contractType;

}
