package integrationservice;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplate;
import static integrationservice.IntegrationUserApiSpecs.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class IntegrationUserApiTests {

    Faker faker = new Faker();
    String userName = faker.name().username();
    String email = faker.internet().emailAddress();

    @Tag("integration")
    @Description("List users without another params")
    @Test
    void successGetUsers() {
        given()
            .filter(withCustomTemplate())
            .spec(success_request)
        .when()
            .get("/entity/AUTO3N/User")
        .then()
                .spec(success_responseSpec);
    }

    @TmsLink("https://dev.prodv.net/browse/AXPL-4441")
    @Tag("integration")
    @Description("Create new user with manager role")
    @Test
    void successCreateManager() {
        String str = "{\n" +
                "  \"User\": {\n" +
                "    \"username\": " + userName + ",\n" +
                "    \"email\": " + email + ",\n" +
                "    \"enabled\": true,\n" +
                "    \"isExternalAuth\": false,\n" +
                "    \"password\": \"123456\",\n" +
                "    \"realmUser\": {\n" +
                "      \"RealmUser\": {\n" +
                "        \"roles\": [\n" +
                "          \"Role.Integration.User\",\n" +
                "          \"Role.Shop.Manager\"\n" +
                "        ],\n" +
                "        \"agent\": {\n" +
                "          \"#ref\": {\n" +
                "            \"id\": 10054,\n" +
                "            \"type\": \"Agent\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        given()
            .filter(withCustomTemplate())
            .spec(success_request)
            .body(str)
        .when()
            .post("/entity/AUTO3N/User")
        .then()
            .spec(success_responseSpec)
            .log().all();
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
