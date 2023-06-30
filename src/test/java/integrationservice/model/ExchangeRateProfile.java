package integrationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Entity;
import helpers.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ExchangeRateProfile extends Entity {
    private String source;
    private String changeValue;
    private Integer nominal;
    private Ref company;
    private Ref currencyFrom;
    private Ref currencyTo;
}
