package integrationservice.tests;


import integrationservice.model.ExchangeRate;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static integrationservice.utils.IntegrationExchangeRateController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationExchangeRateApiTests {
    Integer idExchangeRateProfileForGet = 10004;
    Integer idExchangeRateProfile = 10012;
    Double value = 1.18;
    Integer currencyFrom=643;
    Integer currencyTo=840;


    @Description("List ExchangeRateProfile")
    @Test
    void successGetListExchangeRate() {
        Response exchangeRateList = getExchangeRateList(idExchangeRateProfileForGet);
        assertThat(exchangeRateList).isNotNull();
    }

    @Description("ExchangeRate by id")
    @Test
    void successGetExchangeRateById() {
        // получение последнего курса валюты по профайлу
        ExchangeRate exchangeRate = getExchangeRateAsExchangeRate(idExchangeRateProfileForGet, "sort", "{\"id\":\"DESC\"}");
        ExchangeRate exchangeRateById = getExchangeRateById(idExchangeRateProfileForGet, exchangeRate.getId());
        assertThat(exchangeRateById).isNotNull();
        //assertThat(exchangeRateById.getId()).isEqualTo(exchangeRate.getId());

    }

    @Description("ExchangeRate by date")
    @Test
    void successGetExchangeRateByDate() {
        // дописать получение сегодняшнего курса
        Response exchangeRate = getExchangeRateAsResponse(idExchangeRateProfileForGet, "q", "{\"$and\": [{\"date\":\"2023-06-28T00:00:00\"}]}");
        assertThat(exchangeRate).isNotNull();
    }

    @Description("Get one ExchangeRate")
    @Test
    void successGetExchangeRateProfile() {
        Response exchangeRate = getExchangeRateAsResponse(idExchangeRateProfile,"limit", "1");
        assertThat(exchangeRate).isNotNull();
    }
    @Description("Create ExchangeRate")
    @Test
    void successCreateExchangeRate() {
        // МБ надо в реф добавить сравнение
        ExchangeRate exchangeRate = createExchangeRate(idExchangeRateProfile, value);
        assertThat(exchangeRate.getValue()).isEqualTo(value);
    }

    @Description("Edit ExchangeRate value")
    @Test
    void successChangeExchangeRate() {
        ExchangeRate exchangeRate = createExchangeRate(idExchangeRateProfile, value);
        //edit by id
        value += 0.1;
        ExchangeRate exchangeRateEdit = changeExchangeRate(idExchangeRateProfile, exchangeRate.getId(), value);
        assertThat(exchangeRateEdit.getValue()).isEqualTo(value);
        assertThat(exchangeRateEdit.getChangedAt()).isNotNull();
        assertThat(exchangeRateEdit.getChangedByUser()).isNotNull();
    }

    @Description("Delete ExchangeRate")
    @Test
    void successDeleteExchangeRate() {
        ExchangeRate exchangeRate = createExchangeRate(idExchangeRateProfile, value);
        //delete by id
        ExchangeRate exchangeRateDelete = deleteExchangeRate(idExchangeRateProfile, exchangeRate.getId());
        assertThat(exchangeRateDelete).isNotNull();
        assertThat(exchangeRateDelete.getId()).isEqualTo(null);
    }
    @Description("get ExchangeRate by two currency")
    @Test
    void successGetExchangeRateByTwoCurrency() {
        ExchangeRate exchangeRate= getExchangeRateCurrencyToCurrency(currencyFrom,currencyTo);
        assertThat(exchangeRate).isNotNull();
        //assertThat(exchangeRate.getExchangeRateProfile()).isEqualTo(new Ref("ExchangeRateProfile",idExchangeRateProfile));


    }

}