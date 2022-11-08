package bus.clientservice.registrationmanager.tests.RegManApiTests;

import bus.clientservice.registrationmanager.models.*;
import bus.clientservice.registrationmanager.utils.RegistrationManagerController;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static bus.clientservice.registrationmanager.utils.RegistrationManagerController.startVerification;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Registration User")
@Owner("shulinina.e")
@Tag("registration")
public class StartVerificationTests {

    DeviceToken deviceToken;
    RegistrationResult registrationResult;
    StartVerificationResult startVerificationResult;
    Faker faker = new Faker();
    String email, phone, firstName, lastName, middleName;

    @BeforeEach
    void startUp() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "IOS");
        email = faker.internet().emailAddress();
        phone = "+7" + faker.number().randomNumber(10, false);
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        middleName = faker.funnyName().name();
        registrationResult = RegistrationManagerController.registerClient(
                deviceToken.getDeviceToken(),
                10506,
                email,
                phone,
                "BOTH",
                "123456",
                firstName,
                lastName,
                middleName);
    }

    @Test
    @Story("Start Verification Client - Email")
    @Description("Начать процесс верификации AuthRecord-а после регистрации, для входа в ЛК по почте")
    void success_start_verification_process_for_user_by_email() {
        startVerificationResult = startVerification(deviceToken.getDeviceToken(),"EMAIL", email);
        assertThat(startVerificationResult.getNow()).isNotNull();
        assertThat(startVerificationResult.getNow()).isNotNull();
    }

    @Test
    @Story("Start Verification Client - Mobile Phone")
    @Description("Начать процесс верификации AuthRecord-а после регистрации, для входа в ЛК по номеру телефона")
    void success_start_verification_process_for_user_by_phone() {
        startVerificationResult = startVerification(deviceToken.getDeviceToken(),"MOBILE", phone);

        assertThat(startVerificationResult.getSecret()).isNotNull();
        assertThat(startVerificationResult.getNow()).isNotNull();
        assertThat(startVerificationResult.getExpiredAt()).isNotNull();
    }
}
