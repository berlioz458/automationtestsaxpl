package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
//@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ProvisionPlan {
    private String actualityDate;
    private Boolean aviaDelivery = false;
    private String brandOriginal = "TRW";
    private Integer deliveryCost = 0;
    private String deliveryDateMax , deliveryDateMin = "2023-04-30T08:23:03.820+0000";
    private Integer deliveryIntervalMax, deliveryIntervalMin = 88616;
    private String descrOriginal = "GDB3582_колодки дисковые п.! \\\\ KIA Optima 10>";
    private Integer destinationRoutingPointId = 10521;
    private String destinationRoutingPointName =  "Новосибирск ( Новосибирск Агентство Авто 3Н) СП";
    private Integer detail = 113687910;
    private Integer loadingId = 6323303;
    private String maxReturnDate = "2023-05-30T08:23:03.820+0000";
    private double minPrice = 1947.38;
    private Integer multiplicityPartyOriginal = 1;
    private String oemOriginal = "GDB3582";
    private String offerId = "PIN:GDB3582:BRAND:TRW:VKORG:4000:KUNNR_RG:43275565:KEYZAK:MOV0000019:";
    private Integer offerLineId = 117727;
    private String placementDateTime = "2023-04-01T08:30:00.000+0000";
    private Integer pricelistAgentId = 10401;
    private Integer requestAgentId = 10515;
    private Integer requestCurrencyId = 643;
    private Integer requestPriceGroupId = 10001;
    private Boolean returnsAllowed = true;
    private Integer sellerCurrencyId = 643;
    private Integer sellerPrice = 1887;
    private Integer sourcePriceListId = 13167;
    private Integer supplierAgentId = 1242;
    private String supplierAvailabilityDateTime = "2023-04-01T10:30:00.000+0000";
    private String supplierKeyOriginal = "GDB3582_TRW";
}
