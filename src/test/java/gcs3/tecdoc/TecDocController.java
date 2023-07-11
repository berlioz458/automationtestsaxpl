package gcs3.tecdoc;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static gcs3.tecdoc.TecDocSpec.success_requestSpec;
import static gcs3.tecdoc.TecDocSpec.success_responseSpec;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class TecDocController {
    @Step("Получение параметров (группа ТО - maintenanceApplicabilityGroupId)")
    public static Response getSettings() {
        return given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .get("product/settings")
                .then()
                .spec(success_responseSpec)
                .extract().response();
    }

    @Step("Получение продуктовых типов по группе применимости и модификации авто")
    public static Response getProductTypeByApplicabilityGroupAndModification(Integer applicabilityGroup, Integer modification) {
        return given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .get("product/generic-article/modification/" + modification.toString() + "/applicability-group/" + applicabilityGroup.toString())
                .then()
                .spec(success_responseSpec)
                .extract().response();
    }

    //TODO: дописать фильтры по производителю и продуктовому типу
    @Step("Получение карточек товара для авто с фильтрацией по продуктовому типу")
    public static Response searchProducts(Integer modification, Integer applicabilityGroup, Integer genericArticles, Integer skip, Integer limit) {
        return given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .get("product/search/modification/" + modification.toString() + "/applicability-group/" + applicabilityGroup.toString() + "?skip=" + skip + "&limit=" + limit + "&genericArticles[0]=" + genericArticles)
                .then()
                .spec(success_responseSpec)
                .extract().response();
    }

    @Step("Получение карточек товара для авто с фильтрацией")
    public static Response searchProducts(Integer modification, Integer applicabilityGroup, Integer skip, Integer limit) {
        return given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .get("product/search/modification/" + modification.toString() + "/applicability-group/" + applicabilityGroup.toString() + "?skip=" + skip + "&limit=" + limit)
                .then()
                .spec(success_responseSpec)
                .extract().response();
    }

    @Step("Получение информации о продукте")
    public static Response productsDescription(Integer productId) {
        return given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .get("product/productsDescription?productId=" + productId)
                .then()
                .spec(success_responseSpec)
                .extract().response();
    }

    @Step("Применимость")
    public static Response getApplicability(Integer productId) {
        return given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .get("product/applicability/product/" + productId)
                .then()
                .spec(success_responseSpec)
                .extract().response();
    }
}
