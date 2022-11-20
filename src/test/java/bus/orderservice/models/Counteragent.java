package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Counteragent extends Entity {
    private Integer ownerAgentId;
    private Boolean active;
    private String address;
    private Integer agentId;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean legalEntity;
    private String legalName;
    private String name;
    private String phone;
    private String companyRegistrationNumber;
    private final String companyRegistrationNumberName = "КПП";
    private String taxRegistrationNumber;
    private final String taxRegistrationNumberName = "ИНН";
}
