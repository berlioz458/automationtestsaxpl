package fourtochki;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplate;
import static fourtochki.ApiSpecs.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@Tag("4tochki")
public class ApiTests {
    FourtochkiController fourtochkiController;

    @Tag("smoke")
    @Description("Список брендов")
    @Test
    void successGetBrandsList() {
        ValidatableResponse validatableResponse;
        validatableResponse = fourtochkiController.getBrands();
        validatableResponse.assertThat().body(containsString("Acura"));
    }

    @Tag("smoke")
    @Description("Получение списка моделей по идентификатору бренда")
    @Test
    void successGetModelListByBrand() {
        given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .when()
                .get("vehicle/models/brand/" + 33)
                .then()
                .spec(success_responseSpec)
                .log().body();
    }

    @Tag("smoke")
    @Description("Получение списка модификаций по идентификатору модели")
    @Test
    void successGetModificationListByModel() {
        given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .when()
                .get("vehicle/modifications/model/" + 1350)
                .then()
                .spec(success_responseSpec)
                .log().body();
    }

    @Tag("smoke")
    @Description("Cкроллирующий по производителям возвращающий кусок дерева (марка - модель - модификация)")
    @Test
    void successGetTreeBrandsModelsModifications() {
        given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .when()
                .get("vehicle/tree?page=0&size=10")
                .then()
                .spec(success_responseSpec)
                .log().body();
    }

    @Tag("smoke")
    @Description("Получить бренд по идентификатору")
    @Test
    void successGetBrandById() {
        given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .when()
                .get("vehicle/brand/" + 31)
                .then()
                .spec(success_responseSpec)
                .log().body();
    }

    @Tag("smoke")
    @Description("Получение модели по идентификатору")
    @Test
    void successGetModelById() {
        given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .when()
                .get("vehicle/model/" + 1350)
                .then()
                .spec(success_responseSpec)
                .log().body();
    }

    @Tag("smoke")
    @Description("Получение модификации по идентификатору")
    @Test
    void successGetModificationById() {
        given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .when()
                .get("vehicle/modification/" + 2676)
                .then()
                .spec(success_responseSpec)
                .log().body();
    }

    @Tag("smoke")
    @Description("Получение списка поддерживаемых продуктовых типов")
    @Test
    void successGetProductType() {
        given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .when()
                .get("product-type/all")
                .then()
                .spec(success_responseSpec)
                .log().body();
    }

    @Tag("smoke")
    @Description("Получение модификации авто по идентификатору с аттрибутивной применимостью (Шины)")
    @Test
    void successGetAttributeApplicabilityForRim() {
        given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .when()
                .get("attribute-applicability/modification/" + 7401 + "/product-type/1")
                .then()
                .spec(success_responseSpec)
                .log().body();
    }

    @Tag("smoke")
    @Description("Получение модификации авто по идентификатору с аттрибутивной применимостью (Диски)")
    @Test
    void successGetAttributeApplicabilityForTyre() {
        given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .when()
                .get("attribute-applicability/modification/" + 7401 + "/product-type/2")
                .then()
                .spec(success_responseSpec)
                .log().body();
    }

}
