package bus.clientservice.registrationmanager.tests.RegManApiTests;

import bus.clientservice.registrationmanager.models.UpgradeCounteragentResult;
import bus.orderservice.models.Counteragent;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static bus.clientservice.registrationmanager.utils.RegistrationManagerController.*;
import static org.assertj.core.api.Assertions.*;


@Feature("Create counteragent")
@Story("Registration client from 1C")
@Owner("shulinina.e")
@Tag("from1C")
public class CounteragentTests {

    Counteragent counteragent = new Counteragent();
    UpgradeCounteragentResult upgradeCounteragentResult = new UpgradeCounteragentResult();
    Faker faker = new Faker();


    @Description("Создание контрагента - физлицо c номером телефона")
    @Test
    public void createCounteragentNaturalWithPhone(){
        String phone = "+7" + faker.number().randomNumber(10, false);
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        counteragent = createCounteragent(
                true,
                10479,
                null,
                null,phone,firstName,lastName,false,firstName + " " + lastName,firstName + " " + lastName,null);

        assertThat(counteragent.getId()).isPositive();
        assertThat(counteragent.getPhone()).isEqualTo(phone);
        assertThat(counteragent.getLegalEntity()).isEqualTo(false);
    }

    @Description("Создание контрагента - физлицо c почтой")
    @Test
    public void createCounteragentNaturalWithEmail(){
        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        counteragent = createCounteragent(
                true,
                10479,
                null,
                email,null,firstName,lastName,false,firstName + " " + lastName,firstName + " " + lastName,null);

        assertThat(counteragent.getId()).isPositive();
        assertThat(counteragent.getEmail()).isEqualTo(email);
        assertThat(counteragent.getLegalEntity()).isEqualTo(false);
    }

    @Description("Создание контрагента - физлицо c номером телефона и почтой")
    @Test
    public void createCounteragentNaturalWithPhoneAndEmail(){
        String email = faker.internet().emailAddress();
        String phone = "+7" + faker.number().randomNumber(10, false);
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        counteragent = createCounteragent(
                true,
                10479,
                null,
                email,phone,firstName,lastName,false,firstName + " " + lastName,firstName + " " + lastName,null);

        assertThat(counteragent.getId()).isPositive();
        assertThat(counteragent.getEmail()).isEqualTo(email);
        assertThat(counteragent.getPhone()).isEqualTo(phone);
        assertThat(counteragent.getLegalEntity()).isEqualTo(false);
    }

    @Disabled
    @Description("Создание контрагента - юрлицо")
    @Test
    public void createCounteragentLegal() {
        String email = faker.internet().emailAddress();
        String phone = "+7" + faker.number().randomNumber(10, false);
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String companyRegistrationNumber = "1401938759";
        String taxRegistrationNumber = "4035474706296";
        counteragent = createCounteragent(
                true,
                10479,
                taxRegistrationNumber,
                email,phone,firstName,lastName,true,firstName + " " + lastName,firstName + " " + lastName,companyRegistrationNumber);
        assertThat(counteragent.getId()).isPositive();
        assertThat(counteragent.getEmail()).isEqualTo(email);
        assertThat(counteragent.getPhone()).isEqualTo(phone);
        assertThat(counteragent.getLegalEntity()).isEqualTo(true);
    }

    @Description("Создать для Counteragent-а AuthProfile, если для этого Counteragent-а еще не создан AuthProfile. В AuthRecord-ы вносятся поля email и phone Counteragent-а, если они имеют правильный формат.")
    @Test
    public void updateCounteragent() {
        String email = faker.internet().emailAddress();
        String phone = "+7" + faker.number().randomNumber(10, false);
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        counteragent = createCounteragent(
                true,
                10479,
                null,
                email,phone,firstName,lastName,false,firstName + " " + lastName,firstName + " " + lastName,null);

        upgradeCounteragentResult = createAuthProfileByCounteragent(counteragent.getId());

        assertThat(upgradeCounteragentResult.getAuthProfileId()).isPositive();
        assertThat(upgradeCounteragentResult.getPersonProfileId()).isPositive();
        assertThat(upgradeCounteragentResult.getNewAuth()).isEqualTo(true);

    }
}
