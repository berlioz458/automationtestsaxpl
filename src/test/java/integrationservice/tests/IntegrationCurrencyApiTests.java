package integrationservice.tests;

import helpers.ListInfo;
import integrationservice.model.Currency;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;


import static integrationservice.utils.IntegrationCurrencyController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationCurrencyApiTests {
    String name="AMD";
    String isoAlfa="AMD";
    String isoNumber="51";
    String nameChange="AMD1" + java.time.LocalDateTime.now();
    Integer id=643;
    @Description("List currency")
    @Test
    void successGetListCurrency() {
        ListInfo<Currency> currencyList= getCurrencyList();
        assertThat(currencyList).isNotNull();

    }
    @Description("Currency by id")
    @Test
    void successGetCurrencyById() {
        Currency currency= getCurrencyById(id);
        assertThat(currency).isNotNull();
        assertThat(currency.getId()).isEqualTo(id);
    }
    @Description("Currency by name")

    @Test
    void successGetCurrencyByName() {
        ListInfo<Currency> currency= getCurrency("q","{\"$and\": [{\"isoAlfa\":\"EUR\"}]}");
        assertThat(currency).isNotNull();
    }
    @Description("Get one currency")
    @Test
    void successGetCurrency() {
        ListInfo<Currency> currency= getCurrency("limit","1");
        assertThat(currency).isNotNull();
    }
    @Description("Create currency")
    @Test
    void successCreateCurrency() {
        // условность, создается для компании с id=1
        Currency currency= createCurrency(name,isoAlfa,isoNumber);
        assertThat(currency.getName()).isEqualTo(name);
        assertThat(currency.getIsoAlfa()).isEqualTo(isoAlfa);
        assertThat(currency.getIsoNumber()).isEqualTo(isoNumber);
    }
    @Description("Edit currency by name")
    @Test
    void successChangeCurrencyName() {
        // create
        name=name + " TEST " + java.time.LocalDateTime.now();
        Currency currency= createCurrency(name,isoAlfa,isoNumber);
        //edit by id
        Currency currencyEdit= changeCurrencyName(currency.getId(),nameChange);
        assertThat(currencyEdit.getName()).isEqualTo(nameChange);
        assertThat(currencyEdit.getChangedAt()).isNotNull();
        assertThat(currencyEdit.getChangedByUser()).isNotNull();
    }
    @Description("Delete currency")
    @Test
    void successDeleteCurrency() {
        // create
        name=name + " TEST " + java.time.LocalDateTime.now();
        Currency currency= createCurrency(name,isoAlfa,isoNumber);
        //delete by id
        Currency currencyDelete= deleteCurrency(currency.getId());
        assertThat(currencyDelete).isNotNull();
        assertThat(currencyDelete.getId()).isEqualTo(null);
    }
}
