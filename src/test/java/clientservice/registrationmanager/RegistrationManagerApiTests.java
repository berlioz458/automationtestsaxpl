package clientservice.registrationmanager;

import clientservice.registrationmanager.models.DeviceToken;
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

    @Test
    @Feature("Registration User")
    @Story("Registration device")
    @Tag("smoke")
    @Description("Получить deviceToken для ИМ")
    @Owner("shulinina.e")
    void get_device_token_for_web() {
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
    void get_device_token_for_mobile_android() {
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
    void get_device_token_for_mobile_ios() {
        data = RegistrationManagerController.getDeviceToken("test1407-02", "1", null, "iOS", "15.5", "IOS");
        assertThat(data.getDeviceToken()).isNotNull();
        assertThat(data.getAnonymousInfoId()).isNotNull();
    }
}
