package gcs3.tecdoc;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static gcs3.tecdoc.TecDocController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TecDocApiTests {

    @Test
    @Description("Получение параметров из конфигурационного файла")
    void getSuccessSetting() {
        Response response = getSettings();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat((Integer) response.getBody().jsonPath().get("maintenanceApplicabilityGroupId")).isEqualTo(100019);
    }

    @ParameterizedTest
    @ValueSource(ints = { 55616, 117728, 114384, 55617, 55618, 129317, 144423,146802,146804,101963,101962,101965,101964,103183,101967,103182,101966,103185,101969,101968,103184,101971,103186,101970,101973,101972,101975,101974,101977,101976,101979,101978,101981,101980,101983,101982,101985,101984,101987,101986,101989,101988,101990,18560,124739,23432,17928,22953,22954,30506,17931,22955,22956,32173,25488,31671,31672,31673,126495,140099,134736,135655,137818,136344,137816,134729,137817,134735,144429,124759,6396,148181,121315,137170,25458,8320,16003,16004,16005,8965,16006,8966,16007,8967,16008,8968,16009,16010,16011,15883,16012,16013,16014,16015,16016,15888,16017,26779,17692,8351})
    @Description("Получение обобщенных типов деталей по модификации и узлу")
    void getSuccessProductType(Integer modification) {
        Response response = getProductTypeByApplicabilityGroupAndModification(100019,modification);
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response).isNotNull();
    }

    @Test
    @Description("Получение ошибки при запросе продуктовых типов для несущесвующей модификации")
    void getErrorProductType(){
        Response response = getProductTypeByApplicabilityGroupAndModification(0, 0);
        assertThat(response.getStatusCode()).isEqualTo(404);
        assertThat(response.getBody().jsonPath().get("message").toString()).isEqualTo("Can't find carType with carId = 0");
    }

    @ParameterizedTest
    @ValueSource(ints = { 55616, 117728, 114384, 55617, 55618, 129317, 144423,146802,146804,101963,101962,101965,101964,103183,101967,103182,101966,103185,101969,101968,103184,101971,103186,101970,101973,101972,101975,101974,101977,101976,101979,101978,101981,101980,101983,101982,101985,101984,101987,101986,101989,101988,101990,18560,124739,23432,17928,22953,22954,30506,17931,22955,22956,32173,25488,31671,31672,31673,126495,140099,134736,135655,137818,136344,137816,134729,137817,134735,144429,124759,6396,148181,121315,137170,25458,8320,16003,16004,16005,8965,16006,8966,16007,8967,16008,8968,16009,16010,16011,15883,16012,16013,16014,16015,16016,15888,16017,26779,17692,8351})
    @Description("Получение списка карточек товара каталога ТО для нескольких модификаций авто")
    void successSearchProductsForModification(Integer modification) {
        Response response = searchProducts(modification, 100019, 0, 10);
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = { 55616, 117728, 114384, 55617, 55618, 129317, 144423,146802,146804,101963,101962,101965,101964,103183,101967,103182,101966,103185,101969,101968,103184,101971,103186,101970,101973,101972,101975,101974,101977,101976,101979,101978,101981,101980,101983,101982,101985,101984,101987,101986,101989,101988,101990,18560,124739,23432,17928,22953,22954,30506,17931,22955,22956,32173,25488,31671,31672,31673,126495,140099,134736,135655,137818,136344,137816,134729,137817,134735,144429,124759,6396,148181,121315,137170,25458,8320,16003,16004,16005,8965,16006,8966,16007,8967,16008,8968,16009,16010,16011,15883,16012,16013,16014,16015,16016,15888,16017,26779,17692,8351})
    @Description("Получение списка карточек товара каталога ТО для нескольких модификаций авто с фильтром по продуктовому типу")
    void successSearchProductsForModificationWithFilterByProductType(Integer modification) {
        Response response = searchProducts(modification, 100019, 7,0, 10);
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = { 598545117, 816941524, 1223247457, 25521856, 61693996, 1243622914, 135198942})
    @Description("Получение информации о карточке")
    void successGetProductInfo(Integer productId) {
        Response response = productsDescription(productId);
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = { 598545117, 816941524, 1223247457, 25521856, 61693996, 1243622914, 135198942})
    @Description("Получение списка применимым авто - модификации для детали")
    void successGetApplicability(Integer productId) {
        Response response = getApplicability(productId);
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response).isNotNull();
    }
}
