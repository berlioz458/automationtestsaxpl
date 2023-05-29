package integrationservice.tests;

import integrationservice.model.Currency;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;

import static integrationservice.utils.IntegrationCurrencyController.*;
import static io.restassured.RestAssured.with;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationCurrencyApiTests {
    String name="AMD";
    String isoAlfa="AMD";
    String isoNumber="51";
    String nameChange="AMD1";
    @Description("List currency")
    @Test
    void successGetListCurrency() {
        Response currencyList= getCurrency();
        assertThat(currencyList).isNotNull();
    }
    @Description("Currency by name")
    @Test
    void successGetCurrencyByName() {
        Response currency= getCurrency("q","{\"$and\": [{\"isoAlfa\":\"EUR\"}]}");
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
        // условность, создается для компании с id=1
        Currency currency= createCurrency(name,isoAlfa,isoNumber);
        assertThat(currency.getName()).isEqualTo(name);
        assertThat(currency.getIsoAlfa()).isEqualTo(isoAlfa);
        assertThat(currency.getIsoNumber()).isEqualTo(isoNumber);
    }
    @Description("Edit currency")
    @Test
    void successEditCurrency() {
        Response currency= getCurrency("q","{\"$and\": [{\"isoAlfa\":\"AMD\"}]}");
        //get id
        JsonPath jsonCurrency=currency.getBody().jsonPath();
        int id=jsonCurrency.get("data.Currency[0].id");
        //edit by id
        Currency currencyEdit= editCurrency(id,nameChange);
        assertThat(currencyEdit.getName()).isEqualTo(nameChange);
    }
    @Description("Delete currency")
    @Test
    void successDeleteCurrency() {
        Response currency= getCurrency("q","{\"$and\": [{\"isoAlfa\":\"AMD\"}]}");
        //get id
        JsonPath jsonCurrency=currency.getBody().jsonPath();
        int id=jsonCurrency.get("data.Currency[0].id");
        //delete by id
        Response currencyDelete= deleteCurrency(id);
        assertThat(currencyDelete).isNotNull();
    }
}
