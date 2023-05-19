package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class OrderItemContextInfo {
    private Integer id;
    private String clientData;
    private ItemContextInfo itemContextInfo;
}
