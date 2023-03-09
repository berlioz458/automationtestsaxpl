package bus.paymentservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Result {
    @JsonProperty("uri")
    String uri;
    @JsonProperty("httpMethod")
    String httpMethod;
    @JsonProperty("externalOrderId")
    String externalOrderId;
    @JsonProperty("requestId")
    Integer requestId;
}
