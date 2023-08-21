package integrationservice.tests;

import com.github.javafaker.Faker;
import helpers.ListInfo;
import integrationservice.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static helpers.CustomAllureListener.withCustomTemplate;
import static integrationservice.spec.IntegrationUserApiSpecs.*;
import static integrationservice.utils.IntegrationUserController.createUser;
import static integrationservice.utils.IntegrationUserController.getUserInfo;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;


public class IntegrationUserApiTests {

    Faker faker = new Faker();
    String userName = faker.name().username();
    String email = userName + "@auto3n.ru";
    String password = faker.internet().password();

    String party = "BPMONLINE";
    Integer agent = 10054;
    List<String> roles = new ArrayList<>();

    @Tag("integration")
    @Tag("smoke")
    @Description("List users without another params")
    @Test
    void successGetUsers() {
        ListInfo<User> userList = getUserInfo();
        assertThat(userList).isNotNull();
    }

    @Tag("integration")
    @Description("List users with query criteria")
    @Test
    void successGetListUsersByCriteria() {
        given()
            .filter(withCustomTemplate())
            .spec(success_request)
        .when()
            .get("/entity/AUTO3N/User?q=%7B%22%24and%22%3A%20%5B%7B%22email%22%3A%22test-2%40prodv.net%22%7D%5D%7D")
        .then()
            .spec(success_responseSpec)
                .body("total", is(1))
                .body("data.User.email", is("test-2@prodv.net"));
    }



    @TmsLink("https://dev.prodv.net/browse/AXPL-4441")
    @Tag("integration")
    @Description("Create new user with manager role")
    @Test
    void successCreateManager() {
        roles.add("Role.Integration.User");
        roles.add("Role.Shop.Manager");
        User user = createUser(userName, password, email, party, agent, roles, true);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getUsername()).isEqualTo(userName);
        assertThat(user.getParty()).isEqualTo(party);
    }

    @TmsLink("https://dev.prodv.net/browse/AXPL-4442")
    @Tag("integration")
    @Description("Change your password yourself")
    @Test
    void successChangePasswordSelf() {
        String str = "{\n" +
                "  \"currentPassword\": \"1234567\",\n" +
                "  \"newPassword\": \"1234567\",\n" +
                "  \"newPassword2\": \"1234567\"\n" +
                "}";
        given()
            .filter(withCustomTemplate())
            .spec(success_request)
            .body(str)
        .when()
            .put("/service/user/change-password")
        .then()
            .spec(success_responseSpec)
            .log().all();
    }

    @TmsLink("https://dev.prodv.net/browse/AXPL-4442")
    @Tag("integration")
    @Description("Change your password yourself")
    @Test
    void errorChangePasswordSelfWithInvalidOldPassword() {
        String str = "{\n" +
                "  \"currentPassword\": \"12345678\",\n" +
                "  \"newPassword\": \"1234567\",\n" +
                "  \"newPassword2\": \"1234567\"\n" +
                "}";
        given()
            .filter(withCustomTemplate())
            .spec(error_request)
            .body(str)
        .when()
            .put("/service/user/change-password")
        .then()
            .spec(error_responseSpec)
            .log().all()
                .body("Error.detail.currentPassword.message", is("Вы неправильно ввели Ваш текущий пароль"));
    }
}
