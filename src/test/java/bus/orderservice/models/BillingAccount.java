package bus.orderservice.models;

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
public class BillingAccount extends Entity {
    private String accountantName;
    private String bankName;
    private String bic;
    private final Integer billingCurrency = 643;
    private String companyAddress;
    private String companyBankAccount;
    private String companyName;
    private Ref counteragent;
    private String correspondentBankAccount;
    private String directorName;
    private String directorNameParentalCase;
    private String formalCompanyName;
    private String formalCompanyNameParentalCase;
    private String vatPayer;
    private Integer vatPercent;
}
