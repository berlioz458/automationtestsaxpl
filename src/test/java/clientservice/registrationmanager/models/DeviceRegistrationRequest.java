package clientservice.registrationmanager.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)

public class DeviceRegistrationRequest {
    private String deviceManufacturerId;
    private String deviceUser;
    private String userAgent;
    private String osName;
    private String osVersion;
    private String deviceType;
}
