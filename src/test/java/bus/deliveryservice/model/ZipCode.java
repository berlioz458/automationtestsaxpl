package bus.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Ref;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ZipCode {
    private Integer id;
    private String zipCode;
    private String oldZipCode;
    private String dateChange;
    private String postName;
    private Double latitude;
    private Double longitude;
    private String address;
    private String workTime;
    private String contactInfo;
    private String metro;
    private String comment;
    private Long detailActualityDate;
    private Long d1;
    private Long d2;
    private String street;
    private String house;
}
