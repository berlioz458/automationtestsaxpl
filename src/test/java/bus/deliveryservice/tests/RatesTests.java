package bus.deliveryservice.tests;

import bus.deliveryservice.model.ShippingItem;
import bus.deliveryservice.model.ShippingPricing;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import lombok.var;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static bus.deliveryservice.utils.DeliveryServiceApiController.rates;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("delivery")
public class RatesTests {

    String shippingDate = "2023-04-02T05:31:27.000+0000";
    String deliveryDate = "2023-04-03T05:31:27.000+0000";
    String selfCompanyName = "AUTO3N Самовывоз";
    String courierCompanyName = "Доставка";
    String courierNameMethod = "Курьером или транспортной компанией";
    @Test
    @Tag("prod_run")
    @Tag("smoke_predprod")
    @Story("Интернет Магазин")
    @Description("Получение доступных способов доставки для агента по широте и долготе")
    void successGetRatesAllByLongitudeAndLatitude() {
        ShippingItem shippingItem = new ShippingItem(
                69724573, "DOLZ", "C110", 2, 2513, (float) 0.606
        );
        List<ShippingItem> shippingItems = new ArrayList<>();
        shippingItems.add(shippingItem);
        ShippingPricing rates = rates(10521, shippingItems, "ALL", 54.957221f, 82.8312f, 1, shippingDate);

        assertThat(rates.getShippingOptions()).isNotNull();
    }

    @Test
    @Tag("smoke_predprod")
    @Tag("prod_run")
    @Story("Интернет Магазин")
    @Description("Получение доступных способов доставки для агента по широте и долготе - только самововоз")
    void successGetRatesSelfByLongitudeAndLatitude() {
        ShippingItem shippingItem = new ShippingItem(
                69724573, "DOLZ", "C110", 2, 2513, (float) 0.606
        );
        List<ShippingItem> shippingItems = new ArrayList<>();
        shippingItems.add(shippingItem);
        ShippingPricing rates = rates(10521, shippingItems, "SELF", 54.957221f, 82.8312f, 1, shippingDate);

        assertThat(rates.getShippingOptions().get(0).getTransportCompany().getName()).isEqualTo(selfCompanyName);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getName()).isEqualTo(selfCompanyName);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getEstimatedDeliveryDate()).isEqualTo(shippingDate);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getEstimatedTransportationDate()).isEqualTo(shippingDate);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getTotalPrice()).isEqualTo(0);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getIsOk()).isEqualTo(true);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getIsApproximateResult()).isEqualTo(false);
    }

    @Test
    @Tag("smoke_predprod")
    @Tag("prod_run")
    @Story("Интернет Магазин")
    @Description("Получение доступных способов доставки для агента по широте и долготе - только доставка курьером")
    void successGetRatesCourierByLongitudeAndLatitude() {
        ShippingItem shippingItem = new ShippingItem(
                69724573, "DOLZ", "C110", 2, 2513, (float) 0.606
        );
        List<ShippingItem> shippingItems = new ArrayList<>();
        shippingItems.add(shippingItem);
        ShippingPricing rates = rates(10521, shippingItems, "COURIER", 54.957221f, 82.8312f, 1, shippingDate);

        assertThat(rates.getShippingOptions().get(0).getTransportCompany().getName()).isEqualTo(courierCompanyName);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getName()).isEqualTo(courierNameMethod);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getEstimatedDeliveryDate()).isEqualTo(deliveryDate); //TODO: тут подумать можно не фиксмировать дату, а делать +1 день к той что отправляли, изучить работу с датами в java
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getEstimatedTransportationDate()).isEqualTo(shippingDate);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getTotalPrice()).isEqualTo(0);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getIsOk()).isEqualTo(true);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getIsApproximateResult()).isEqualTo(true);
    }
}
