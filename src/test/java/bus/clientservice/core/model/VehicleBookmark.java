package bus.clientservice.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Entity;
import helpers.ItemContextInfo;
import helpers.Ref;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class VehicleBookmark extends Entity {
    private Boolean deleted;
    private ItemContextInfo itemContextInfo;
    private List<String> mediaIds;
    private String name;
    private String note;
    private Ref personProfile;
    private Ref type;
}
