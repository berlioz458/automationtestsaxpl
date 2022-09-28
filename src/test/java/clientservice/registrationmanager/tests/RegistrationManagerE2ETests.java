package clientservice.registrationmanager.tests;

import clientservice.registrationmanager.models.*;
import clientservice.registrationmanager.utils.RegistrationManagerController;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import unitls.EmailParser;
import unitls.SMSParser;

import static clientservice.registrationmanager.utils.RegistrationManagerController.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("smoke")
public class RegistrationManagerE2ETests {
    static Faker faker = new Faker();
    static DeviceToken deviceToken;
    static String email;
    static String firstName;
    static String lastName;
    static String nameWithMiddle;
    static String phone;
    //Пользовательский сценарий: Регистрация клиента по почте
    // 1. Получение девайс токена
    // 2. Проверка введенных данных на ботность
    // 3. Проверка введенных данных на возможность регистрации на принципал
    // 4. Регистрация клиента
    // 5. Отправка письма для подтверждения логина клиенту
    // 6. Переход клиента по ссылке из письма

    @BeforeAll
    static void run() {
        Configuration.headless = false;
        Configuration.remote = "http://localhost:4444/wd/hub/";
        Configuration.browserSize = "1920x1080";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void startUp() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
        email = faker.internet().emailAddress();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        nameWithMiddle = faker.name().nameWithMiddle();
        phone = "+7" + faker.number().randomNumber(10, false);
    }

    @Test
    void clientRegistrationByEmail() {
        checkPrincipalAvailability(deviceToken.getDeviceToken(), email, "EMAIL", true);
        validateClientRegistration(deviceToken.getDeviceToken(), 10506, email, "EMAIL","123456", true, firstName, lastName, nameWithMiddle);
        registerClient(deviceToken.getDeviceToken(), 10506, email, phone, "EMAIL", "123456", firstName, lastName, nameWithMiddle);
        startVerification(deviceToken.getDeviceToken(),"EMAIL", email);
        String token = EmailParser.getTokenFromEmail();
        VerificationResult verificationResult = endVerification(deviceToken.getDeviceToken(), token, null);
        assertThat(verificationResult.getAuthProfileId()).isPositive();
        assertThat(verificationResult.getPersonProfileId()).isPositive();
        assertThat(verificationResult.getAuthRecordId()).isPositive();
    }

    @Test
    void clientRegistrationByPhone() throws InterruptedException {
        checkPrincipalAvailability(deviceToken.getDeviceToken(), phone, "MOBILE", true);
        validateClientRegistration(deviceToken.getDeviceToken(), 10506, email, "MOBILE","123456", true, firstName, lastName, nameWithMiddle);
        registerClient(deviceToken.getDeviceToken(), 10506, email, phone, "MOBILE", "123456", firstName, lastName, nameWithMiddle);
        String secret = startVerification(deviceToken.getDeviceToken(),"MOBILE", phone).getSecret();
        String token = SMSParser.getCodeBySms();
        VerificationResult verificationResult = endVerification(deviceToken.getDeviceToken(), token, secret);
        assertThat(verificationResult.getAuthProfileId()).isPositive();
        assertThat(verificationResult.getPersonProfileId()).isPositive();
        assertThat(verificationResult.getAuthRecordId()).isPositive();
    }
}
