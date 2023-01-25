package bus.offerservice.tests;

import bus.offerservice.model.SearchResult;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static bus.offerservice.utils.OfferServiceApiController.findOffersWithOutBrand;

@Story("Получение предложений")
public class FindOffersTests {

    @Test
    @Story("Интернет Магазин")
    @Description("Получение списка брендов")
    void successGetBrandsList() {
        String searchResult = findOffersWithOutBrand(
                "OC47", 10522, 1020, 643, false, false, 0, "all", true, "refined", "simple"
        );
    }

    @Test
    @Story("Интернет Магазин")
    @Description("Получение списка предложений по бренд+оем без кроссов")
    void successGetOffersListWithoutCross() {
        String searchResult = findOffersWithBrand(
                "C110", "DOLZ", 10522, 1020, 643, false, false, 0, "all", true, "refined", "simple"
        );
    }

    @Test
    @Story("Интернет Магазин")
    @Description("Получение списка предложений по бренд+оем с кроссами")
    void successGetOffersListWithCross() {
        String searchResult = findOffersWithOutBrand(
                "C110",  10522, 1020, 643, true, false, 0, "all", true, "refined", "simple"
        );
    }
}
