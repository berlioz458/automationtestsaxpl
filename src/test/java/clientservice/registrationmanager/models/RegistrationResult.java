package clientservice.registrationmanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class RegistrationResult {
    @JsonProperty("counteragentId")
    private Integer counteragentId;
    @JsonProperty("personProfileId")
    private Integer personProfileId;
    @JsonProperty("authProfileId")
    private Integer authProfileId;
    @JsonProperty("contractId")
    private Integer contractId;
}
