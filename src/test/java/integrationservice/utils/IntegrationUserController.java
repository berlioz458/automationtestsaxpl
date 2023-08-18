package integrationservice.utils;

import helpers.ListInfo;
import helpers.Ref;
import integrationservice.model.RealmUser;
import integrationservice.model.User;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import java.util.List;

import static helpers.CustomAllureListener.withCustomTemplate;
import static integrationservice.spec.IntegrationUserApiSpecs.success_request;
import static integrationservice.spec.IntegrationUserApiSpecs.success_responseSpec;
import static io.restassured.RestAssured.given;

public class IntegrationUserController {
    @Step("Получение списка пользователей без параметров фильтрации")
    public static ListInfo<User> getUserInfo() {
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/User")
                .then()
                .spec(success_responseSpec)
                .extract().as(new TypeRef<ListInfo<User>>() {
                });
    }

    @Step("Создание пользователя с доступом менеджера")
    public static User createUser(String userName, String password, String email, String party, Integer agent, List<String> roles, Boolean active) {
        User body = new User();
        body.setUsername(userName);
        body.setPassword(password);
        body.setEmail(email);
        body.setParty(party);
        body.setRealmUser(new RealmUser(new Ref("Agent", agent), roles, null));
        body.setEnabled(active);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .post("/entity/AUTO3N/User")
                .then()
                .spec(success_responseSpec)
                .log().all()
                .extract().as(User.class);
    }
}
