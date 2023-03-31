package bus.deliveryservice.tests;

import bus.deliveryservice.model.ShippingItem;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import lombok.var;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static bus.deliveryservice.utils.DeliveryServiceApiController.rates;
import static org.assertj.core.api.Assertions.assertThat;

public class RatesTests {

    @Test
    @Story("Интернет Магазин")
    @Description("Получение доступных способов доставки для агента по широте и долготе")
    void successGetRatesByLongitudeAndLatitude() {
        ShippingItem shippingItem = new ShippingItem(
                69724573, "DOLZ", "C110", 2, 2513, (float) 0.606
        );
        List<ShippingItem> shippingItems = new ArrayList<>();
        shippingItems.add(shippingItem);
        var rates = rates(10521, shippingItems, "ALL", 54.957221f, 82.8312f, 1, "2023-02-14T08:31:27+03:00");
        var shippingOptions = rates.jsonPath().get("ShippingPricing.shippingOptions");

        assertThat(shippingOptions).isNotNull();
        //TODO: ответ, смотреть время (1)
    }
}
