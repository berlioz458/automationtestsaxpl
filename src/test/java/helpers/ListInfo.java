package helpers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NONE;

@JsonTypeInfo(use = NONE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListInfo<T> implements Serializable {
    private Integer total;
    private Integer skip;
    private Integer limit;
    @JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
    private List<T> data;
}
