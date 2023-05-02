package bus.offerservice.tests;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static bus.offerservice.utils.OfferServiceApiController.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("offers")
@Story("Получение предложений")
public class FindOffersTests {

    @Test
    @Story("Интернет Магазин")
    @Tag("smoke_predprod")
    @Description("Получение списка брендов")
    void successGetBrandsList() {
        Response searchResult = findOffersWithOutBrand(
                "OC47", 10522, 1020, 643, false, false, 0, "all", true, "refined", "simple"
        );

        int expectedDetailId = 107697003;
        int resultDetailId = searchResult.jsonPath().get("[0]['SearchResult']['originalDetail']['DetailInfo']['detailId']");

        assertThat(expectedDetailId).isEqualTo(resultDetailId);
    }

    @Test
    @Tag("smoke_predprod")
    @Story("Интернет Магазин")
    @Description("Получение списка предложений по бренд+оем без кроссов - Блок 'Запрошенный артикул'")
    void successGetOffersListWithoutCross() {
        Response searchResult = findOffersWithBrand(
                "RINGAH009", "FEBEST", 10101, 1020, 643, false, false, 0, "all", true, "refined", "simple"
        );
        int expectedDetailId = 125423437;
        int resultDetailId = searchResult.jsonPath().get("[0]['SearchResult']['originalDetail']['DetailInfo']['detailId']");
        //var values = searchResult.jsonPath().get("[0]['SearchResult']['offers']");

        assertThat(expectedDetailId).isEqualTo(resultDetailId);
        //assertThat(values).isNotNull();
    }

    @Test
    @Tag("smoke_predprod")
    @Story("Интернет Магазин")
    @Description("Получение списка предложений по бренд+оем с кроссами - Блок 'Остальные аналоги'")
    void successGetOffersListWithCrossExternal() {
        Response searchResult = findOffersWithBrand(
                "RINGAH009",  "FEBEST", 10101, 1020, 643, true, false, 0, "internal", true, "refined", "simple"
        );

        int expectedDetailId = 125423437;
        int resultDetailId = searchResult.jsonPath().get("[0]['SearchResult']['originalDetail']['DetailInfo']['detailId']");
        //var values = searchResult.jsonPath().get("[0]['SearchResult']['offers']");

        assertThat(expectedDetailId).isEqualTo(resultDetailId);
        //assertThat(values).isNotNull();
    }

    @Test
    @Tag("smoke_predprod")
    @Story("Интернет Магазин")
    @Description("Получение списка предложений по бренд+оем с кроссами - Блок 'Рекомендуемые аналоги'")
    void successGetOffersListWithCrossInternal() {
        Response searchResult = findOffersWithBrand(
                "RINGAH009",  "FEBEST", 10101, 1020, 643, true, false, 0, "internal", true, "refined", "simple"
        );

        int expectedDetailId = 125423437;
        int resultDetailId = searchResult.jsonPath().get("[0]['SearchResult']['originalDetail']['DetailInfo']['detailId']");
        //var values = searchResult.jsonPath().get("[0]['SearchResult']['offers']");
        int valuesCrosses = searchResult.jsonPath().get("[0]['SearchResult']['offers'][0]['key']['DetailInfo']['detailId']");

        assertThat(expectedDetailId).isEqualTo(resultDetailId);
        assertThat(152241939).isEqualTo(valuesCrosses);
        //assertThat(values).isNotNull();
    }

    @Test
    @Disabled
    @Tag("smoke_predprod")
    @Story("Интернет Магазин")
    @Description("Перепроценка корзины клиента")
    void successGetPriceForShoppingCart() {
        Response offersForOrder = findPriceListOffers(10468, 1020, 643, "all");

        assertThat(offersForOrder).isNotNull();
        //TODO: Переделать
        assertThat(72852613).isEqualTo(offersForOrder.jsonPath().get("[0]['key']['DetailInfo']['detailId']"));
        //assertThat(offersForOrder.jsonPath().get("[0]['value']")).isNotNull();

        assertThat(125423437).isEqualTo(offersForOrder.jsonPath().get("[1]['key']['DetailInfo']['detailId']"));
        //assertThat(offersForOrder.jsonPath().get("[1]['value']")).isNotNull();

        assertThat(77096157).isEqualTo(offersForOrder.jsonPath().get("[2]['key']['DetailInfo']['detailId']"));
        //assertThat(offersForOrder.jsonPath().get("[3]['value']")).isNotNull();

        assertThat(-533659449).isEqualTo(offersForOrder.jsonPath().get("[3]['key']['DetailInfo']['detailId']"));
        //assertThat(offersForOrder.jsonPath().get("[4]['value']")).isNotNull();
    }
}
