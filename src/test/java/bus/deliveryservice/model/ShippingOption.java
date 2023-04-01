package bus.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ShippingOption {
    private String name;
    private Integer shipmentAgentId;
    private Double totalPrice;
    private String estimatedTransportationDate;
    private String estimatedDeliveryDate;
    private Double totalWeight;
    private Boolean isApproximateResult;
    private Boolean isOk;
}
