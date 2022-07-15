package clientservice.registrationmanager.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ValidateClientRegistrationResult {
    private boolean good;
    private int gainedWeight;
    private int weightTreshold;
    private List<String> suspiciousFields;
}
