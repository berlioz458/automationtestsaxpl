package bus.offerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class DetailAndResultOffers {
    @JsonProperty(value = "key")
    private DetailInfo detail;

    @JsonProperty(value = "value")
    private List<ResultOffer> resultOffers;

    public void setResultOffers(List<ResultOffer> resultOffers) {
        this.resultOffers = resultOffers;
    }

    @JsonIgnore
    public int getOffersCount() {
        return resultOffers.size();
    }

}
