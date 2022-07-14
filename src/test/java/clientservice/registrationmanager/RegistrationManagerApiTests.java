package clientservice.registrationmanager;

import clientservice.registrationmanager.models.DeviceToken;
import clientservice.registrationmanager.utils.Error;
import clientservice.registrationmanager.utils.RegistrationManagerController;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Tag("registration")
public class RegistrationManagerApiTests {
    DeviceToken data;
    Error error_data;

    @Test
    @Feature("Registration User")
    @Story("Registration device")
    @Tag("smoke")
    @Description("Получить deviceToken для ИМ")
    @Owner("shulinina.e")
    void success_get_device_token_for_web() {
        data = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
        assertThat(data.getDeviceToken()).isNotNull();
        assertThat(data.getAnonymousInfoId()).isNotNull();
    }

    @Test
    @Feature("Registration User")
    @Story("Registration device")
    @Tag("smoke")
    @Description("Получить deviceToken для МП Android")
    @Owner("shulinina.e")
    void success_get_device_token_for_mobile_android() {
        data = RegistrationManagerController.getDeviceToken("test1407-01", "1", null,"Android", "8.0.0", "ANDROID");
        assertThat(data.getDeviceToken()).isNotNull();
        assertThat(data.getAnonymousInfoId()).isNotNull();
    }

    @Test
    @Feature("Registration User")
    @Story("Registration device")
    @Tag("smoke")
    @Description("Получить deviceToken для МП IOS")
    @Owner("shulinina.e")
    void success_get_device_token_for_mobile_ios() {
        data = RegistrationManagerController.getDeviceToken("test1407-02", "1", null, "iOS", "15.5", "IOS");
        assertThat(data.getDeviceToken()).isNotNull();
        assertThat(data.getAnonymousInfoId()).isNotNull();
    }

    @Test
    @Feature("Registration User")
    @Story("Registration device")
    @Description("Получить 500 ошибку при получении deviceToken для не валидного типа устройства")
    @Owner("shulinina.e")
    void error_get_device_token_with_invalid_device_type() {
        error_data = RegistrationManagerController.getErrorDeviceToken("test1407-02", "1", null, "iOS", "15.5", "TEST");
        assertThat(error_data.getCode()).isEqualTo(500);
        assertThat(error_data.getMessage()).isNotNull();
    }

    @Test
    @Feature("Registration User")
    @Story("Registration device")
    @Description("Получить 400 ошибку при получении deviceToken нет данных о OS и userAgent-а")
    @Owner("shulinina.e")
    void error_get_device_token_without_osdata() {
        error_data = RegistrationManagerController.getErrorDeviceToken("test1407-02", "1", "IOS");
        assertThat(error_data.getCode()).isEqualTo(400);
        assertThat(error_data.getMessage()).isEqualTo("User agent and os name/version is empty");
    }

    @Test
    @Feature("Registration User")
    @Story("Registration device")
    @Description("Получить 400 ошибку при получении deviceToken нет deviceType")
    @Owner("shulinina.e")
    void error_get_device_token_without_deviceType() {
        error_data = RegistrationManagerController.getErrorDeviceToken("test1407-02", "1", null, "iOS", "15.5");
        assertThat(error_data.getCode()).isEqualTo(400);
        assertThat(error_data.getMessage()).isEqualTo("Device type is absent");
    }


}
