package bus.orderservice.tests;

import bus.orderservice.models.MarketingAction;
import bus.orderservice.models.PriceListInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;

import static bus.orderservice.utils.OrderServiceApiController.*;
import static org.assertj.core.api.Assertions.assertThat;
@Tag("order")
public class MarketingActionTests {
    @Test
    void successCreateMarketingAction() {
        List<PriceListInfo> pricelists = new ArrayList<>();
        PriceListInfo priceListInfo = new PriceListInfo();
        priceListInfo.setPricelistId(11857);
        priceListInfo.setPricelistName("ПрофитЛига тест");
        pricelists.add(priceListInfo);
        MarketingAction marketingAction = createMarketingAction(
                "2022-10-31T21:00:00.000+0000",
                "2022-11-29T21:00:00.000+0000",
                "AutoTest create #1",
                10,
                5,
                15,
                3,
                10,
                pricelists
                );
        assertThat(marketingAction.getId()).isPositive();
    }

    @Test
    void successGetMarketingAction() {
        Integer id = 146;
        MarketingAction marketingAction = getMarketingAction(id);
        assertThat(marketingAction.getId()).isEqualTo(id);
    }
}
