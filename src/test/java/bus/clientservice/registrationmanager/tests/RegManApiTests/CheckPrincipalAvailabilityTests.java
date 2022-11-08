package bus.clientservice.registrationmanager.tests.RegManApiTests;

import bus.clientservice.registrationmanager.models.DeviceToken;
import bus.clientservice.registrationmanager.models.PrincipalAvailability;
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
@Story("Check principal free or not")
@Owner("shulinina.e")
@Tag("registration")
public class CheckPrincipalAvailabilityTests {
    Faker faker = new Faker();
    PrincipalAvailability principalAvailability;
    DeviceToken deviceToken;

    @BeforeEach
    void startUp() {
        deviceToken = RegistrationManagerController.getDeviceToken(" ", "", "Mozilla FireFox","Test", " ", "WEB");
    }

    @Test
    @Description("Проверка доступность принципала с типом EMAIL, свободного")
    void success_check_email_type_principal_free() {
        principalAvailability = RegistrationManagerController.checkPrincipalAvailability(deviceToken.getDeviceToken(), faker.internet().emailAddress(), "EMAIL", true);
        assertThat(principalAvailability.getAvailable()).isTrue();
        assertThat(principalAvailability.getPrincipalType()).isEqualTo("EMAIL");
    }

    @Test
    @Description("Проверка доступность принципала с типом MOBILE, свободного")
    void success_check_mobile_type_principal_free() {
        principalAvailability = RegistrationManagerController.checkPrincipalAvailability(deviceToken.getDeviceToken(), "+7" + faker.number().randomNumber(10, false), "MOBILE", true);
        assertThat(principalAvailability.getAvailable()).isTrue();
        assertThat(principalAvailability.getPrincipalType()).isEqualTo("MOBILE");
    }

    @Test
    @Description("Проверка доступность принципала с типом EMAIL, не свободного")
    void success_check_email_type_principal_unfree() {
        principalAvailability = RegistrationManagerController.checkPrincipalAvailability(deviceToken.getDeviceToken(), "berlioz458@gmail.com", "EMAIL", true);
        assertThat(principalAvailability.getAvailable()).isFalse();
        assertThat(principalAvailability.getPrincipalType()).isEqualTo("EMAIL");
        assertThat(principalAvailability.getConflictedEntities()).isNotNull();
    }
}
