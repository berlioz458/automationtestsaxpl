package bus.clientservice.registrationmanager.tests.RegManApiTests;

import bus.clientservice.registrationmanager.models.DeviceToken;
import bus.clientservice.registrationmanager.models.ValidateClientRegistrationResult;
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
@Story("Validate Client Data")
@Tag("registration")
@Owner("shulinina.e")
public class ValidateClientRegistrationTests {
    Faker faker = new Faker();
    DeviceToken deviceToken;
    ValidateClientRegistrationResult validateClientRegistrationResult;

    @BeforeEach
    void startUp() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
    }

    @Test
    @Description("Проверка данных клиента на ботность, валидные данные")
    void success_validate_user_data() {
        validateClientRegistrationResult = RegistrationManagerController.validateClientRegistration(deviceToken.getDeviceToken(), 10506, faker.internet().emailAddress(), "EMAIL","123456", true, faker.name().firstName(), faker.name().lastName(), faker.name().nameWithMiddle());
        assertThat(validateClientRegistrationResult.isGood()).isTrue();
        assertThat(validateClientRegistrationResult.getSuspiciousFields()).isEmpty();
    }

    @Test
    @Description("Проверка данных клиента на ботность, имитация бота")
    void success_validate_bot_data() {
        validateClientRegistrationResult = RegistrationManagerController.validateClientRegistration(deviceToken.getDeviceToken(), 10506, "auto3n@error", "EMAIL","123456", true, "Ka te 1", "http://money.com/ret4gt4t", faker.name().nameWithMiddle());
        assertThat(validateClientRegistrationResult.isGood()).isFalse();
        assertThat(validateClientRegistrationResult.getSuspiciousFields()).isNotEmpty();
    }
}
