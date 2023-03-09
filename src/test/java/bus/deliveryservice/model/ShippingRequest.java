package bus.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ShippingRequest {
    private Integer agentId;
    private final Integer currencyId = 643;
    private final String deliveryPriceGroupCode = "default";
    private List<ShippingItem> shippingItems;
    private String shippingMethod;
    private float toLatitude;
    private float toLongitude;
    private Integer pointsPerCompanyLimit;
    private String shippingDate;
}
