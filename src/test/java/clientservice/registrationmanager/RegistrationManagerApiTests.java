package clientservice.registrationmanager;

import clientservice.registrationmanager.models.DeviceToken;
import clientservice.registrationmanager.models.PrincipalAvailability;
import clientservice.registrationmanager.models.RegistrationResult;
import clientservice.registrationmanager.models.ValidateClientRegistrationResult;
import clientservice.registrationmanager.utils.Error;
import clientservice.registrationmanager.utils.RegistrationManagerController;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Tag("registration")
public class RegistrationManagerApiTests {
    DeviceToken deviceToken;
    PrincipalAvailability principalAvailability;
    ValidateClientRegistrationResult validateClientRegistrationResult;
    RegistrationResult registrationResult;
    Error error_data;
    Faker faker = new Faker();

    @Test
    @Feature("Registration User")
    @Story("Registration device")
    @Tag("smoke")
    @Description("Получить deviceToken для ИМ")
    @Owner("shulinina.e")
    void success_get_device_token_for_web() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
        assertThat(deviceToken.getDeviceToken()).isNotNull();
        assertThat(deviceToken.getAnonymousInfoId()).isNotNull();
    }

    @Test
    @Feature("Registration User")
    @Story("Registration device")
    @Tag("smoke")
    @Description("Получить deviceToken для МП Android")
    @Owner("shulinina.e")
    void success_get_device_token_for_mobile_android() {
        deviceToken = RegistrationManagerController.getDeviceToken("test1407-01", "1", null,"Android", "8.0.0", "ANDROID");
        assertThat(deviceToken.getDeviceToken()).isNotNull();
        assertThat(deviceToken.getAnonymousInfoId()).isNotNull();
    }

    @Test
    @Feature("Registration User")
    @Story("Registration device")
    @Tag("smoke")
    @Description("Получить deviceToken для МП IOS")
    @Owner("shulinina.e")
    void success_get_device_token_for_mobile_ios() {
        deviceToken = RegistrationManagerController.getDeviceToken("test1407-02", "1", null, "iOS", "15.5", "IOS");
        assertThat(deviceToken.getDeviceToken()).isNotNull();
        assertThat(deviceToken.getAnonymousInfoId()).isNotNull();
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

    @Test
    @Feature("Registration User")
    @Story("Check principal free or not")
    @Description("Проверка доступность принципала с типом EMAIL, свободного")
    @Tag("smoke")
    @Owner("shulinina.e")
    void success_check_email_type_principal_free() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
        principalAvailability = RegistrationManagerController.checkPrincipalAvailability(deviceToken.getDeviceToken(), faker.internet().emailAddress(), "EMAIL", true);
        assertThat(principalAvailability.getAvailable()).isTrue();
        assertThat(principalAvailability.getPrincipalType()).isEqualTo("EMAIL");
    }

    @Test
    @Feature("Registration User")
    @Story("Check principal free or not")
    @Description("Проверка доступность принципала с типом MOBILE, свободного")
    @Tag("smoke")
    @Owner("shulinina.e")
    void success_check_mobile_type_principal_free() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "1", "1","Android", "10.0.0", "ANDROID");
        principalAvailability = RegistrationManagerController.checkPrincipalAvailability(deviceToken.getDeviceToken(), faker.phoneNumber().toString(), "MOBILE", true);
        assertThat(principalAvailability.getAvailable()).isTrue();
        assertThat(principalAvailability.getPrincipalType()).isEqualTo("MOBILE");
    }

    @Test
    @Feature("Registration User")
    @Story("Check principal free or not")
    @Description("Проверка доступность принципала с типом EMAIL, не свободного")
    @Owner("shulinina.e")
    void success_check_email_type_principal_unfree() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "1", "1","Android", "10.0.0", "ANDROID");
        principalAvailability = RegistrationManagerController.checkPrincipalAvailability(deviceToken.getDeviceToken(), "berlioz458@gmail.com", "EMAIL", true);
        assertThat(principalAvailability.getAvailable()).isFalse();
        assertThat(principalAvailability.getPrincipalType()).isEqualTo("EMAIL");
        assertThat(principalAvailability.getConflictedEntities()).isNotNull();
    }

    @Test
    @Feature("Registration User")
    @Story("Validate Cleint Data")
    @Description("Проверка данных клиента на ботность, валидные данные")
    @Tag("smoke")
    @Owner("shulinina.e")
    void success_validate_user_data() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
        validateClientRegistrationResult = RegistrationManagerController.validateClientRegistration(deviceToken.getDeviceToken(), 10506, faker.internet().emailAddress(), "EMAIL","123456", true, faker.name().firstName(), faker.name().lastName(), faker.name().nameWithMiddle());
        assertThat(validateClientRegistrationResult.isGood()).isTrue();
        assertThat(validateClientRegistrationResult.getSuspiciousFields()).isEmpty();
    }

    @Test
    @Feature("Registration User")
    @Story("Validate Cleint Data")
    @Description("Проверка данных клиента на ботность, имитация бота")
    @Owner("shulinina.e")
    void success_validate_bot_data() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
        validateClientRegistrationResult = RegistrationManagerController.validateClientRegistration(deviceToken.getDeviceToken(), 10506, "auto3n@error", "EMAIL","123456", true, "Ka te 1", "http://money.com/ret4gt4t", faker.name().nameWithMiddle());
        assertThat(validateClientRegistrationResult.isGood()).isFalse();
        assertThat(validateClientRegistrationResult.getSuspiciousFields()).isNotEmpty();
    }

    @Test
    @Feature("Registration User")
    @Story("Regisration Client")
    @Description("Регистрация клиента по почте")
    @Tag("smoke")
    @Owner("shulinina.e")
    void success_registration_user_by_email() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
        registrationResult = RegistrationManagerController.registerClient(deviceToken.getDeviceToken(), 10506, faker.internet().emailAddress(), faker.phoneNumber().phoneNumber(), "EMAIL", "123456", faker.name().firstName(), faker.name().lastName(), faker.funnyName().name());
        assertThat(registrationResult.getAuthProfileId()).isPositive();
        assertThat(registrationResult.getCounteragentId()).isPositive();
        assertThat(registrationResult.getContractId()).isPositive();
        assertThat(registrationResult.getPersonProfileId()).isPositive();
    }

    @Test
    @Feature("Registration User")
    @Story("Regisration Client")
    @Description("Регистрация клиента по номеру телефона")
    @Tag("smoke")
    @Owner("shulinina.e")
    void success_registration_user_by_phone() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "IOS");
        registrationResult = RegistrationManagerController.registerClient(deviceToken.getDeviceToken(), 10506, faker.internet().emailAddress(), faker.phoneNumber().phoneNumber(), "MOBILE", "123456", faker.name().firstName(), faker.name().lastName(), faker.funnyName().name());
        assertThat(registrationResult.getAuthProfileId()).isPositive();
        assertThat(registrationResult.getCounteragentId()).isPositive();
        assertThat(registrationResult.getContractId()).isPositive();
        assertThat(registrationResult.getPersonProfileId()).isPositive();
    }

}
