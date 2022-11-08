package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ShipmentInfo {
    private Integer transportationCompanyId;
    private String transportationCompanyName;
    private String address;
    private String additionalInfo;
    private Integer additionalDeliveryCost;
}
