package helpers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NONE;

@JsonTypeInfo(use = NONE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListInfo<T> {
    private Integer total;
    private Integer skip;
    private Integer limit;
    private List<T> data;
}
