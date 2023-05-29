package integrationservice.tests;

import integrationservice.model.Currency;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static integrationservice.utils.IntegrationCurrencyController.getCurrency;
import static integrationservice.utils.IntegrationCurrencyController.createCurrency;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationCurrencyApiTests {
    String name="AMD";
    String isoAlfa="AMD";
    String isoNumber="051";
    @Description("List currency")
    @Test
    void successGetListCurrency() {
        Response currencyList= getCurrency();
        assertThat(currencyList).isNotNull();
    }
    @Description("Currency by name")
    @Test
    void successGetCurrencyByName() {
        Response currency= getCurrency("q","{\"$and\": [{\"name\":\"EUR\"}]}");
        assertThat(currency).isNotNull();
    }
    @Description("Get one currency")
    @Test
    void successGetCurrency() {
        Response currency= getCurrency("limit","1");
        assertThat(currency).isNotNull();
    }
    @Description("Create currency")
    @Test
    void successCreateCurrency() {
        Currency currency= createCurrency(name,isoAlfa,isoNumber);
        assertThat(currency.getName()).isEqualTo(name);
        assertThat(currency.getIsoAlfa()).isEqualTo(isoAlfa);
        assertThat(currency.getIsoNumber()).isEqualTo(isoNumber);
    }
}
