package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ShipmentItem {
    private String brand;
    private Integer discountSum;
    private String name;
    private String oem;
    private Ref orderItem;
    private Integer position;
    private Integer price;
    private Ref shipment;
    private Integer sum;
    private Integer taxPercent;
    private double taxSum;
    private Integer totalSum;
    private String unitName;
    private Integer units;
}
