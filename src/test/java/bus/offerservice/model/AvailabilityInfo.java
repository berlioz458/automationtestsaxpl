package bus.offerservice.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class AvailabilityInfo {
    boolean detailOffersExist;
    boolean detailCrossesOffersExist;
}
