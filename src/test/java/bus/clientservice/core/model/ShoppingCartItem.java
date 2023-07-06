package bus.clientservice.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Integer priceGroupId;
    private String offerId;
    private Integer priceListId;
    private Date estimatedDeliveryDate;
    private Date actualityDate;
    private Integer contractId;
    private Integer orderItemId;
    private Integer currencyId;
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
    private Boolean selfOrder;
    private ItemContextInfo itemContextInfo;
    private Boolean aviaDelivery = false;
    private boolean transportationDisabled;
    private boolean used;
    private JsonNode marketingData;
}
