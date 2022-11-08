package bus.clientservice.registrationmanager.tests.RegManApiTests;

import bus.clientservice.registrationmanager.models.DeviceToken;
import bus.clientservice.registrationmanager.models.RegistrationResult;
import bus.clientservice.registrationmanager.utils.RegistrationManagerController;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Registration User")
@Story("Registration Client")
@Tag("registration")
@Owner("shulinina.e")
public class RegisterClientTests {

    DeviceToken deviceToken;
    RegistrationResult registrationResult;
    Faker faker = new Faker();
    String email, phone, firstName, lastName, middleName;

    @BeforeEach
    void startUp() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
        email = faker.internet().emailAddress();
        phone = "+7" + faker.number().randomNumber(10, false);
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        middleName = faker.funnyName().name();
    }

    @Test
    @Description("Регистрация клиента по почте")
    void success_registration_user_by_email() {
        registrationResult = RegistrationManagerController.registerClient(
                deviceToken.getDeviceToken(),
                10506,
                email,
                phone,
                "EMAIL",
                "123456",
                firstName,
                lastName,
                middleName);
        assertThat(registrationResult.getAuthProfileId()).isPositive();
        assertThat(registrationResult.getCounteragentId()).isPositive();
        assertThat(registrationResult.getContractId()).isPositive();
        assertThat(registrationResult.getPersonProfileId()).isPositive();
    }

    @Test
    @Description("Регистрация клиента по номеру телефона")
    void success_registration_user_by_phone() {
        registrationResult = RegistrationManagerController.registerClient(
                deviceToken.getDeviceToken(),
                10506,
                email,
                phone,
                "MOBILE",
                "123456",
                firstName,
                lastName,
                middleName);
        assertThat(registrationResult.getAuthProfileId()).isPositive();
        assertThat(registrationResult.getCounteragentId()).isPositive();
        assertThat(registrationResult.getContractId()).isPositive();
        assertThat(registrationResult.getPersonProfileId()).isPositive();
    }
}
