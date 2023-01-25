package bus.offerservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

@Getter
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class SearchParametersSet {
    private int id;
    private String scenario;
    private String name;
    private Integer position;
    private boolean applyAutomatically;
    private String useSources;
    private Boolean allowCrosses;
    @JsonInclude()
    private String crossesTypeRestriction;
    private String usedFilter;
    private Integer minRank;
    private boolean bestOffersOnly;
    private int bestOffersLimit;
    private boolean highestRankOffersOnly;
    private String promoFilter;



}
