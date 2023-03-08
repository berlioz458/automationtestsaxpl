package bus.offerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@Data
public class SearchResult {
    private DetailInfo originalDetail;
    private String availability;
    @JsonProperty(value = "offers")
    private String detailAndResultOffersList;
    private String suggestedSearchParameters;

    public SearchResult(String s) {

    }
}
