package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ItemContextInfo {
    private String catalog; // Имя каталога OEM (только OEM)
    private String catalogVehicleId; // Идентификатор машины в OEM (только OEM)
    private String ssd; // SSD OEM (только OEM)
    private String vin; // VIN номер (для всех каталогов, в т.ч. Usearch, OEM, TecDocOnline и поиска)
    private String frame; // Номер кузова (для OEM, Usearch)

    private String catalogCategoryId; // Идентификатор категории из OEM (только OEM)
    private String catalogGroupId; // Идентификатор группы из OEM (только OEM)
    private String catalogUnitId; // Идентификатор узла из OEM (только OEM)
    private String catalogCodeOnImage; // Код на картинке (только OEM)

    private String detailCode; // OEM номер (PIN)
    private String brand; // Бренд (каноническое имя)

    private String searchQueryId; // Идентификатор поискового запроса Usearch
    private String searchQuery; // Тело поискового запроса Usearch
    private String searchPositionSelected; // Номер выбранной позиции Usearch
    private Integer vehicleProfileId; // Идентификатор профиля автомобиля Usearch (только Usearch)

    private String modelId; // Идентификатор машины в GCS
    private String productId; // Идентификатор продукта GCS
    private Long gcsCategoryId; // Идентификатор категории GCS

    private String externalCatalog; // Имя внешнего каталога ('4Tochki', 'TecDocOnline', 'OEM')
    private String externalCatalogModelManufacturerName; // Имя производителя авто из внешнего каталога, все кроме GCS, в т.ч. OEM (поле brand)
    private String externalCatalogModelId; // Идентификатор модели внешнего каталога, если есть (VehicleModelJson.externalId из 4 точки, TecDocOnline)
    private String externalCatalogModelName; // Имя модели внешнего каталога, все кроме GCS, в т.ч. OEM (VehicleModelJson.name)
    private String externalCatalogModificationId; // Идентификатор модификации из внешнего каталога (VehicleModificationJson.externalId)
    private String externalCatalogModificationName; // Название модификации из внешнего каталога, все кроме GCS, в т.ч. OEM (VehicleModificationJson.name)
    private String externalCatalogCategoryId; // Идентификатор категории внешнего каталога
    private String externalCatalogCategoryName; // Название категории внешнего каталога (4Tochki - 'Шины' (тип 1 ) либо 'Диски' (тип 2))
    private String externalCatalogNodeId; // Идентификатор узла внешнего каталога (ApplicabilityGroupJson.externalId)
    private String externalCatalogNodeName; // Названия узла внешнего каталога (ApplicabilityGroupJson.name)
    private String externalCatalogItemId; // Идентификатор товара (детали) во внешнем каталоге (ProductJson.externalId)
}
