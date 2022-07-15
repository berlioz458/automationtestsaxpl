package clientservice.registrationmanager.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class RegistrationResult {
    private Integer counteragentId;
    private Integer personProfileId;
    private Integer authProfileId;
    private Integer contractId;
}
