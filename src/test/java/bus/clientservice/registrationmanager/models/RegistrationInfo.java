package bus.clientservice.registrationmanager.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password"})
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class RegistrationInfo {
    private String deviceToken;
    private Integer regionAgentId;
    private String mobilePhone;
    private String email;
    private String loginType;
    private String password;
    private Boolean counteragentsBinding;
    private String firstName;
    private String lastName;
    private String middleName;
}
