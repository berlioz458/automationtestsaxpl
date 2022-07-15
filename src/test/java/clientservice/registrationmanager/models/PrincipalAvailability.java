package clientservice.registrationmanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class PrincipalAvailability {
    @JsonProperty("available")
    private Boolean available;
    @JsonProperty("principalType")
    private String principalType;
    @JsonProperty("requiresVerification")
    private Boolean requiresVerification;
    @JsonProperty("verifyOnLogin")
    private Boolean verifyOnLogin;
    @JsonProperty("conflictPrincipal")
    private String conflictPrincipal;
    @JsonProperty("conflictedEntities")
    private List<String> conflictedEntities;
}
