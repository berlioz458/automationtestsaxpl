package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Entity;
import helpers.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Order extends Entity {
    private Integer ownerAgentId;
    private Integer shipmentAgentId;
    private Integer prepaidPercentRequired;
    private final String note = "Тестовый заказ";
    private Integer deliveryCostOverride;
     private final String createdByUser = "Загоруйко В.";
    private Ref contract;
    private Ref status;
    private PersonInfo createdPerson;
    private ShipmentInfo shipmentInfo;
    private List<OrderItem> orderItems;
}
