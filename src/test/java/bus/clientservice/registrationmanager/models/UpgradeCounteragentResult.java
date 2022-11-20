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
public class UpgradeCounteragentResult {
    @JsonProperty("authProfileId")
    Integer authProfileId;
    @JsonProperty("personProfileId")
    Integer personProfileId;
    @JsonProperty("newAuth")
    Boolean newAuth;
    @JsonProperty("password")
    String password;
}
