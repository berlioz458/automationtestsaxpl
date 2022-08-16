package orderservice.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Contract {
    private final String name = "С покупателем";
    private Boolean active;
    private Integer agentId;
    private Integer personalDiscount;
    private Integer ownerAgentId;
    private Integer currencyId;

}
