package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class MarketingAction extends Entity {
    private final Integer ownerAgentId = 10054;
    private final Integer agentId = 10056;
    private final Boolean ready = false;
    private final Boolean forAllBrands = true;
    private final Boolean enabled = false;

    private String activeFrom;
    private String activeTo;
    private String name;

    private Integer discountPercent;
    private Integer countOfOrdersToFreezeDiscount;
    private Integer extraChargePercent;
    private Integer periodToCheckOrdersDays;
    private Integer startDiscountPercent;

    private List<PriceListInfo> pricelists;
}
