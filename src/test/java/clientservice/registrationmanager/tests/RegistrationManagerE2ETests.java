package clientservice.registrationmanager.tests;

import clientservice.registrationmanager.models.*;
import clientservice.registrationmanager.utils.RegistrationManagerController;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import unitls.EmailParser;

import static clientservice.registrationmanager.utils.RegistrationManagerController.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("smoke")
@Tag("e2e")
public class RegistrationManagerE2ETests {
    Faker faker = new Faker();
    DeviceToken deviceToken;
    String email, firstName, lastName, nameWithMiddle;
    //Пользовательский сценарий: Регистрация клиента по почте
    // 1. Получение девайс токена
    // 2. Проверка введенных данных на ботность
    // 3. Проверка введенных данных на возможность регистрации на принципал
    // 4. Регистрация клиента
    // 5. Отправка письма для подтверждения логина клиенту
    // 6. Переход клиента по ссылке из письма

    @BeforeEach
    void startUp() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
        email = faker.internet().emailAddress();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        nameWithMiddle = faker.name().nameWithMiddle();
    }

    @Test
    void clientRegistrationByEmail() {
        checkPrincipalAvailability(deviceToken.getDeviceToken(), email, "EMAIL", true);
        validateClientRegistration(deviceToken.getDeviceToken(), 10506, email, "EMAIL","123456", true, firstName, lastName, nameWithMiddle);
        registerClient(deviceToken.getDeviceToken(), 10506, email, "+7" + faker.number().randomNumber(10, false), "EMAIL", "123456", firstName, lastName, nameWithMiddle);
        startVerification(deviceToken.getDeviceToken(),"EMAIL", email);
        String token = EmailParser.getTokenFromEmail();
        VerificationResult verificationResult = endVerification(deviceToken.getDeviceToken(), token);
        assertThat(verificationResult.getAuthProfileId()).isPositive();
        assertThat(verificationResult.getPersonProfileId()).isPositive();
        assertThat(verificationResult.getAuthRecordId()).isPositive();
    }
}
