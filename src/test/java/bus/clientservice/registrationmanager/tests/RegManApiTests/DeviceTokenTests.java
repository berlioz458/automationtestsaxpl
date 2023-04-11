package bus.clientservice.registrationmanager.tests.RegManApiTests;

import bus.clientservice.registrationmanager.models.DeviceToken;
import bus.clientservice.registrationmanager.utils.Error;
import bus.clientservice.registrationmanager.utils.RegistrationManagerController;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Registration User")
@Story("Registration device")
@Owner("shulinina.e")
@Tag("registration")
public class DeviceTokenTests {

    DeviceToken deviceToken;
    Error error_data;

    @Test
    @Tag("smoke_predprod")
    @Description("Получить deviceToken для ИМ")
    void success_get_device_token_for_web() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
        assertThat(deviceToken.getDeviceToken()).isNotNull();
        assertThat(deviceToken.getAnonymousInfoId()).isNotNull();
    }

    @Test
    @Tag("smoke_predprod")
    @Description("Получить deviceToken для МП Android")
    void success_get_device_token_for_mobile_android() {
        deviceToken = RegistrationManagerController.getDeviceToken("test1407-01", "1", null,"Android", "8.0.0", "ANDROID");
        assertThat(deviceToken.getDeviceToken()).isNotNull();
        assertThat(deviceToken.getAnonymousInfoId()).isNotNull();
    }

    @Test
    @Tag("smoke_predprod")
    @Description("Получить deviceToken для МП IOS")
    void success_get_device_token_for_mobile_ios() {
        deviceToken = RegistrationManagerController.getDeviceToken("test1407-02", "1", null, "iOS", "15.5", "IOS");
        assertThat(deviceToken.getDeviceToken()).isNotNull();
        assertThat(deviceToken.getAnonymousInfoId()).isNotNull();
    }

    @Test
    @Description("Получить 500 ошибку при получении deviceToken для не валидного типа устройства")
    void error_get_device_token_with_invalid_device_type() {
        error_data = RegistrationManagerController.getErrorDeviceToken("test1407-02", "1", null, "iOS", "15.5", "TEST");
        assertThat(error_data.getCode()).isEqualTo(500);
        assertThat(error_data.getMessage()).isNotNull();
    }

    @Test
    @Description("Получить 400 ошибку при получении deviceToken нет данных о OS и userAgent-а")
    void error_get_device_token_without_os_data() {
        error_data = RegistrationManagerController.getErrorDeviceToken("test1407-02", "1", "IOS");
        assertThat(error_data.getCode()).isEqualTo(400);
        assertThat(error_data.getMessage()).isEqualTo("User agent and os name/version is empty");
    }

    @Test
    @Description("Получить 400 ошибку при получении deviceToken нет deviceType")
    void error_get_device_token_without_deviceType() {
        error_data = RegistrationManagerController.getErrorDeviceToken("test1407-02", "1", null, "iOS", "15.5");
        assertThat(error_data.getCode()).isEqualTo(400);
        assertThat(error_data.getMessage()).isEqualTo("Device type is absent");
    }
}
