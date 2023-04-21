package bus.deliveryservice.tests;

import bus.deliveryservice.model.SettlementByLocationResult;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static bus.deliveryservice.utils.DeliveryServiceApiController.settlementByLocation;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("prod_run")
@Tag("delivery")
public class SettlementTests {
    @Test
    @Description("Получение поселения по координатам - Москва")
    void successGetMoscowByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(55.753220, 37.622513);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Москва");
    }

    @Test
    @Description("Получение поселения по координатам - Новосибирск")
    void successGetNskByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(54.8630098, 83.1208539);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Новосибирск");
    }

    @Test
    @Description("Получение поселения по координатам - Обь")
    void successGetObByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(54.995881, 82.698280);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Обь");
    }


    @Test
    @Description("Получение поселения по координатам - Аян(Хабаровск) - нет в дадате")
    void successGetAyanByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(55.753220, 137.622513);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Аян");
    }


    @Test
    @Description("Получение поселения по координатам - Спб")
    void successGetSpbByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(59.939099, 30.315877);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Санкт-Петербург");
    }

    @Test
    @Description("Получение поселения по координатам - Ростов")
    void successGetRostovByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(47.222078, 39.720358);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Ростов-на-Дону");
    }


    @Test
    @Description("Получение поселения по координатам - Аксай")
    void successGetAksayByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(47.269914, 39.862283);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Аксай");
    }


    @Test
    @Description("Получение поселения по координатам - Бугульма")
    void successGetBugulmaByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(54.541877, 52.798634);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Бугульма");
    }

    @Test
    @Description("Получение поселения по координатам - Великий Новгород")
    void successGetVelNovgorodByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(58.522857, 31.269816);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Великий Новгород");
    }

    @Test
    @Description("Получение поселения по координатам - Волжский")
    void successGetVolgskyByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(48.786127, 44.751229);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Волжский");
    }
    @Test
    @Description("Получение поселения по координатам - Фряново")
    void successGetFryanovoByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(56.122876, 38.428886);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Фряново");
    }
    @Test
    @Description("Получение поселения по координатам - Юность")
    void successGetYunostByCoordinate() {
        SettlementByLocationResult response = settlementByLocation(55.913613, 38.113486);
        assertThat(response.getNearestSettlement().getName()).isEqualTo("Юность");
    }
}
