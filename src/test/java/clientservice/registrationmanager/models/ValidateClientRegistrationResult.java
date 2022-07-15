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
public class ValidateClientRegistrationResult {
    @JsonProperty("good")
    private boolean good;
    @JsonProperty("gainedWeight")
    private int gainedWeight;
    @JsonProperty("weightTreshold")
    private int weightTreshold;
    @JsonProperty("suspiciousFields")
    private List<String> suspiciousFields;
}
