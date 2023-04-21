package bus.deliveryservice.tests;

import bus.deliveryservice.model.ShippingItem;
import bus.deliveryservice.model.ShippingPricing;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;

import static bus.deliveryservice.utils.DeliveryServiceApiController.rates;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("delivery")
public class RatesTests {

    static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    static Calendar calendar = new GregorianCalendar();
    static String shippingDate;
    static String deliveryDate;
    String selfCompanyName = "AUTO3N Самовывоз";
    String courierCompanyName = "Доставка";
    String courierNameMethod = "Курьером или транспортной компанией";

    @BeforeAll
    static void setUp() {
        shippingDate = formater.format(calendar.getTime());
        calendar.add(Calendar.HOUR, 24);
        deliveryDate = formater.format(calendar.getTime());
    }

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

    @SneakyThrows
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
        assertThat(formater.parse(rates.getShippingOptions().get(0).getShippingOption().get(0).getEstimatedDeliveryDate())).isEqualTo(shippingDate);
        assertThat(formater.parse(rates.getShippingOptions().get(0).getShippingOption().get(0).getEstimatedTransportationDate())).isEqualTo(shippingDate);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getTotalPrice()).isEqualTo(0);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getIsOk()).isEqualTo(true);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getIsApproximateResult()).isEqualTo(false);
    }

    @SneakyThrows
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
        assertThat(formater.parse(rates.getShippingOptions().get(0).getShippingOption().get(0).getEstimatedDeliveryDate())).isEqualTo(deliveryDate);
        assertThat(formater.parse(rates.getShippingOptions().get(0).getShippingOption().get(0).getEstimatedTransportationDate())).isEqualTo(shippingDate);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getTotalPrice()).isEqualTo(0);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getIsOk()).isEqualTo(true);
        assertThat(rates.getShippingOptions().get(0).getShippingOption().get(0).getIsApproximateResult()).isEqualTo(true);
    }
}
