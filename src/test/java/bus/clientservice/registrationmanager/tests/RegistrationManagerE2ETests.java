package bus.clientservice.registrationmanager.tests;

import bus.clientservice.registrationmanager.models.*;
import bus.clientservice.registrationmanager.utils.RegistrationManagerController;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import unitls.EmailParser;
import unitls.SMSParser;

import java.util.Locale;

import static bus.clientservice.core.utils.clientController.getTokenByEmail;
import static bus.clientservice.core.utils.clientController.getTokenBySms;
import static bus.clientservice.registrationmanager.utils.RegistrationManagerController.*;
import static org.assertj.core.api.Assertions.assertThat;

//TODO: нужны тесты на авторизацию по коду
@Tag("smoke")
public class RegistrationManagerE2ETests {
    static Faker faker = new Faker(new Locale("ru-RU"));
    static DeviceToken deviceToken;
    static String email;
    static String firstName;
    static String lastName;
    static String nameWithMiddle;
    static String phone;

    //TODO: Требуется сделать кейсы на удаление профиля

    @BeforeEach
    void startUp() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
        email = "shulinina.e+" + faker.number().numberBetween(1, 100) + "@prodv.net" ;
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        nameWithMiddle = faker.name().nameWithMiddle();
        phone = "7" + faker.number().randomNumber(10, false);
    }

    @Test
    @Tag("smoke_prod")
    void clientRegistrationByEmail() {
        checkPrincipalAvailability(deviceToken.getDeviceToken(), email, "EMAIL", true);
        validateClientRegistration(deviceToken.getDeviceToken(), 10506, email, "EMAIL","123456", true, firstName, lastName, nameWithMiddle);
        registerClient(deviceToken.getDeviceToken(), 10506, email, phone, "EMAIL", "123456", firstName, lastName, nameWithMiddle);
        startVerification(deviceToken.getDeviceToken(),"EMAIL", email);
        String token = getTokenByEmail(email);
        VerificationResult verificationResult = endVerification(deviceToken.getDeviceToken(), token, null);
        AuthToken authToken = requestAuthToken(deviceToken.getDeviceToken(), "EMAIL", email, "123456");
        selfDeleteUser(deviceToken.getDeviceToken(), authToken.getAuthToken());
        assertThat(getPersonProfileById(verificationResult.getPersonProfileId()).getSelfDeleted()).isEqualTo(true);
    }

    @Test
    void clientRegistrationByPhone() {
        checkPrincipalAvailability(deviceToken.getDeviceToken(), phone, "MOBILE", true);
        validateClientRegistration(deviceToken.getDeviceToken(), 10506, email, "MOBILE","123456", true, firstName, lastName, nameWithMiddle);
        registerClient(deviceToken.getDeviceToken(), 10506, email, phone, "MOBILE", "123456", firstName, lastName, nameWithMiddle);
        String secret = startVerification(deviceToken.getDeviceToken(),"MOBILE", phone).getSecret();
        String token = getTokenBySms(phone);
        VerificationResult verificationResult = endVerification(deviceToken.getDeviceToken(), token, secret);
        assertThat(verificationResult.getAuthProfileId()).isPositive();
        assertThat(verificationResult.getPersonProfileId()).isPositive();
        assertThat(verificationResult.getAuthRecordId()).isPositive();
    }
}
