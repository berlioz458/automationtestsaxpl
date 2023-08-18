package integrationservice.tests;

import helpers.ListInfo;
import helpers.Ref;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import integrationservice.model.ExchangeRateProfile;

import static integrationservice.utils.IntegrationExchangeRateProfileController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationExchangeRateProfileApiTests {
    Integer id =10004;
    Ref currencyTo = new Ref("Currency",1019);
    Ref currencyFrom= new Ref("Currency",840);
    Integer nominal;
    String source;
    @Description("List ExchangeRateProfile")
    @Test
    void successGetListExchangeRateProfile() {
        ListInfo<ExchangeRateProfile> exchangeRateProfileList= getExchangeRateProfileList();
        assertThat(exchangeRateProfileList).isNotNull();
    }
    @Description("ExchangeRateProfile by id")
    @Test
    void successGetExchangeRateProfileById() {
        ExchangeRateProfile exchangeRateProfile= getExchangeRateProfileById(id);
        assertThat(exchangeRateProfile).isNotNull();
        assertThat(exchangeRateProfile.getId()).isEqualTo(id);

    }
    @Description("ExchangeRateProfile by CurrencyFrom")
    @Test
    void successGetExchangeRateProfileByCurrencyFrom() {
        ListInfo<ExchangeRateProfile>  exchangeRateProfile= getExchangeRateProfile("q","{\"$and\": [{\"currencyFrom\":\"840\"}]}");
        assertThat(exchangeRateProfile).isNotNull();
    }
    @Description("Get one ExchangeRateProfile")
    @Test
    void successGetExchangeRateProfile() {
        ListInfo<ExchangeRateProfile>  exchangeRateProfile= getExchangeRateProfile("limit","1");
        assertThat(exchangeRateProfile).isNotNull();
    }
    @Description("Create ExchangeRateProfile")
    @Test
    void successCreateExchangeRateProfile() {
        // МБ надо в реф добавить сравнение
        ExchangeRateProfile exchangeRateProfile= createExchangeRateProfile(currencyFrom,currencyTo);
        //assertThat(exchangeRateProfile.currencyFrom()).isEqualTo(currencyFrom);
       // assertThat(exchangeRateProfile.currencyTo().id).isEqualTo(currencyTo);
    }
    @Description("Edit ExchangeRateProfile source and nominal")
    @Test
    void successChangeExchangeRateProfileSourceAndNominal() {
        ExchangeRateProfile exchangeRateProfile= createExchangeRateProfile(currencyFrom,currencyTo);
        //edit by id
        ExchangeRateProfile exchangeRateProfileEdit= changeExchangeRateProfile(exchangeRateProfile.getId(),source, nominal);
        assertThat(exchangeRateProfileEdit.getSource()).isEqualTo(source);
        assertThat(exchangeRateProfileEdit.getNominal()).isEqualTo(nominal);
        assertThat(exchangeRateProfileEdit.getChangedAt()).isNotNull();
        assertThat(exchangeRateProfileEdit.getChangedByUser()).isNotNull();
    }
    @Description("Delete ExchangeRateProfile")
    @Test
    void successDeleteExchangeRateProfile() {
        ExchangeRateProfile exchangeRateProfile= createExchangeRateProfile(currencyFrom,currencyTo);
        //delete by id
        ExchangeRateProfile exchangeRateProfileDelete= deleteExchangeRateProfile(exchangeRateProfile.getId());
        assertThat(exchangeRateProfileDelete).isNotNull();
        assertThat(exchangeRateProfileDelete.getId()).isEqualTo(null);
    }
}
