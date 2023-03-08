package bus.offerservice.tests;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import lombok.var;
import org.junit.jupiter.api.Test;
import static bus.offerservice.utils.OfferServiceApiController.findOffersWithBrand;
import static bus.offerservice.utils.OfferServiceApiController.findOffersWithOutBrand;
import static org.assertj.core.api.Assertions.assertThat;

@Story("Получение предложений")
public class FindOffersTests {

    @Test
    @Story("Интернет Магазин")
    @Description("Получение списка брендов")
    void successGetBrandsList() {
        var searchResult = findOffersWithOutBrand(
                "OC47", 10522, 1020, 643, false, false, 0, "all", true, "refined", "simple"
        );

        var expectedDetailId = 107697003;
        var resultDetailId = searchResult.jsonPath().get("[0]['SearchResult']['originalDetail']['DetailInfo']['detailId']");

        assertThat(expectedDetailId).isEqualTo(resultDetailId);
    }

    @Test
    @Story("Интернет Магазин")
    @Description("Получение списка предложений по бренд+оем без кроссов")
    void successGetOffersListWithoutCross() {
        var searchResult = findOffersWithBrand(
                "C110", "DOLZ", 10522, 1020, 643, false, false, 0, "all", true, "refined", "simple"
        );
        var expectedDetailId = 69724573;
        var resultDetailId = searchResult.jsonPath().get("[0]['SearchResult']['originalDetail']['DetailInfo']['detailId']");
        var values = searchResult.jsonPath().get("[0]['SearchResult']['offers']");

        assertThat(expectedDetailId).isEqualTo(resultDetailId);
        assertThat(values).isNotNull();
    }

    @Test
    @Story("Интернет Магазин")
    @Description("Получение списка предложений по бренд+оем с кроссами")
    void successGetOffersListWithCross() {
        var searchResult = findOffersWithBrand(
                "C110",  "DOLZ", 10522, 1020, 643, true, false, 0, "all", true, "refined", "simple"
        );

        var expectedDetailId = 69724573;
        var resultDetailId = searchResult.jsonPath().get("[0]['SearchResult']['originalDetail']['DetailInfo']['detailId']");
        var values = searchResult.jsonPath().get("[0]['SearchResult']['offers']");
        var valuesCrosses = searchResult.jsonPath().get("[0]['SearchResult']['offers'][0]['key']['DetailInfo']['detailId']");

        assertThat(expectedDetailId).isEqualTo(resultDetailId);
        assertThat(160363916).isEqualTo(valuesCrosses);
        assertThat(values).isNotNull();
    }
}
