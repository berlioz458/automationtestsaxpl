package bus.clientservice.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonNode;
import helpers.Entity;
import helpers.ItemContextInfo;
import helpers.Ref;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ShoppingCartItem extends Entity {
    private Ref shoppingCart;
    private Ref vehicleProfile;
    private String name;
    private String brand;
    private String oem;
    private Double summ;
    private Double price;
    private Double quantity;
    private Double discountSum;
    private String note;
    private boolean deleted = false;
    private Double minAllowedPrice;
    private Integer priceGroupId = 1020;
    private String offerId;
    private Integer priceListId;
    private String estimatedDeliveryDate;
    private Date actualityDate;
    private Integer contractId;
    private Integer orderItemId;
    private Integer currencyId = 643;
    private Integer agentId;
    private Integer detailId;
    private Integer brandId;
    private BigDecimal bonusSpentAmountAllowed;
    private BigDecimal plannedBonusAcquired;
    private BigDecimal contractDiscountPercent;
    private BigDecimal contractDiscountSum;
    private BigDecimal selfOrderDiscountPercent;
    private BigDecimal selfOrderDiscountSum;
    private String marketingActionName;
    private Integer marketingActionId;
    private BigDecimal marketingDiscountPercent;
    private BigDecimal marketingDiscountSum;
    private Boolean selfOrder = true;
    private ItemContextInfo itemContextInfo;
    private Boolean aviaDelivery = false;
    private boolean transportationDisabled;
    private boolean used = false;
    private JsonNode marketingData;
}
