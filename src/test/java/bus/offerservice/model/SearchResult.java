package bus.offerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class SearchResult {
    private DetailInfo originalDetail;
    private AvailabilityInfo availability;
    @JsonProperty(value = "offers")
    private List<DetailAndResultOffers> detailAndResultOffersList;
    private List<SearchParametersSet> suggestedSearchParameters;

}
