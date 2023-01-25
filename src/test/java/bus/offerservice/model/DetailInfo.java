package bus.offerservice.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

import java.util.List;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@Getter
public class DetailInfo {
    private final int detailId;
    private final String oem;
    private final String brand;
    private final List<String> brandAlias;
    private final int brandId;
    private final String name;
    private final Integer groupId;
    private final Float weight;
    private final Float volume;
    private final String requestedBrand;
    private final String crossType;

    DetailInfo(int detailId, String oem, String brand, List<String> brandAlias, int brandId, String name, Integer groupId,
               Float weight, Float volume, String requestedBrand, String crossType) {
        this.detailId = detailId;
        this.oem = oem;
        this.brand = brand;
        this.brandAlias = brandAlias;
        this.brandId = brandId;
        this.name = name;
        this.groupId = groupId;
        this.weight = weight;
        this.volume = volume;
        this.requestedBrand = requestedBrand;
        this.crossType = crossType;

    }
}
