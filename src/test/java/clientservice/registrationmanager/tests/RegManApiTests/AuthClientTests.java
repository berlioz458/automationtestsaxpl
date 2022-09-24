package clientservice.registrationmanager.tests.RegManApiTests;

import clientservice.registrationmanager.models.*;
import clientservice.registrationmanager.utils.RegistrationManagerController;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("registration")
@Owner("shulinina.e")
@Feature("Authorization User")
public class AuthClientTests {
    DeviceToken deviceToken;
    AuthToken authToken;


    @BeforeEach
    void startUp() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "IOS");
    }


    @Test
    @Story("Auth Client by Email")
    @Description("Авторизация клиента с принципалом, тип EMAIL")
    void success_auth_by_email() {
        authToken = RegistrationManagerController.requestAuthToken(deviceToken.getDeviceToken(), "EMAIL", "berlioz458@gmail.com", "123456");
        assertThat(authToken.getAuthToken()).isNotEmpty();
    }

    @Test
    @Story("Auth Client by Email")
    @Description("Авторизация клиента с принципалом, тип MOBILE")
    void success_auth_by_phone() {
        authToken = RegistrationManagerController.requestAuthToken(deviceToken.getDeviceToken(), "MOBILE", "89529470960", "123456");
        assertThat(authToken.getAuthToken()).isNotEmpty();
    }
}
