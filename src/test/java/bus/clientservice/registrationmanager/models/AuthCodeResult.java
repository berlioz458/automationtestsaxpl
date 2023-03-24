package bus.clientservice.registrationmanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class AuthCodeResult {
    @JsonProperty("sent")
    Boolean sent;
    @JsonProperty("channel")
    String channel;
    @JsonProperty("expiredAt")
    String expiredAt;
    @JsonProperty("limitExceeded")
    Boolean limitExceeded;
    @JsonProperty("dailyLimit")
    Integer dailyLimit;
}
