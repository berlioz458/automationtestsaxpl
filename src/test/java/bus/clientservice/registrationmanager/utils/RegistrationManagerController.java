package bus.clientservice.registrationmanager.utils;

import bus.clientservice.registrationmanager.models.*;
import bus.orderservice.models.Counteragent;
import io.qameta.allure.Step;

import static bus.clientservice.registrationmanager.spec.RegistrationManagerApiSpecs.*;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class RegistrationManagerController {
    @Step("Получить deviceToken")
    public static DeviceToken getDeviceToken(String deviceManufacturerId, String deviceUser, String userAgent, String osName, String osVersion, String deviceType) {
        DeviceRegistrationRequest body = new DeviceRegistrationRequest();
        body.setDeviceManufacturerId(deviceManufacturerId);
        body.setDeviceUser(deviceUser);
        body.setUserAgent(userAgent);
        body.setOsName(osName);
        body.setOsVersion(osVersion);
        body.setDeviceType(deviceType);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/registerDevice")
                .then()
                .spec(response)
                .extract().as(DeviceToken.class);
    }


    public static Error getErrorDeviceToken(String deviceManufacturerId, String deviceUser, String userAgent, String osName, String osVersion, String deviceType) {
        DeviceRegistrationRequest body = new DeviceRegistrationRequest();
        body.setDeviceManufacturerId(deviceManufacturerId);
        body.setDeviceUser(deviceUser);
        body.setUserAgent(userAgent);
        body.setOsName(osName);
        body.setOsVersion(osVersion);
        body.setDeviceType(deviceType);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/registerDevice")
                .then()
                .spec(response)
                .extract().as(Error.class);
    }

    public static Error getErrorDeviceToken(String deviceManufacturerId, String deviceUser, String userAgent, String osName, String osVersion) {
        DeviceRegistrationRequest body = new DeviceRegistrationRequest();
        body.setDeviceManufacturerId(deviceManufacturerId);
        body.setDeviceUser(deviceUser);
        body.setUserAgent(userAgent);
        body.setOsName(osName);
        body.setOsVersion(osVersion);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/registerDevice")
                .then()
                .spec(response)
                .extract().as(Error.class);
    }

    public static Error getErrorDeviceToken(String deviceManufacturerId, String deviceUser, String deviceType) {
        DeviceRegistrationRequest body = new DeviceRegistrationRequest();
        body.setDeviceManufacturerId(deviceManufacturerId);
        body.setDeviceUser(deviceUser);
        body.setDeviceType(deviceType);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/registerDevice")
                .then()
                .spec(response)
                .extract().as(Error.class);
    }
    @Step("Проверка принципала на занятость (Возможность зарегестрироваться)")
    public static PrincipalAvailability checkPrincipalAvailability(String deviceToken, String principal, String principalType, Boolean counteragentsBinding) {
        PrincipalInfo body = new PrincipalInfo();
        body.setPrincipal(principal);
        body.setDeviceToken(deviceToken);
        body.setPrincipalType(principalType);
        body.setCounteragentsBinding(counteragentsBinding);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/checkPrincipalAvailability")
                .then()
                .spec(response)
                .extract().as(PrincipalAvailability.class);
    }

    @Step("Проверка на 'ботность' принципала")
    public static ValidateClientRegistrationResult validateClientRegistration(String deviceToken, Integer regionAgentId, String email, String loginType, String password, Boolean counteragentsBinding, String  firstName, String lastName, String middleName){
        RegistrationInfo body = new RegistrationInfo();
        body.setDeviceToken(deviceToken);
        body.setRegionAgentId(regionAgentId);
        body.setEmail(email);
        body.setLoginType(loginType);
        body.setPassword(password);
        body.setCounteragentsBinding(counteragentsBinding);
        body.setFirstName(firstName);
        body.setLastName(lastName);
        body.setMiddleName(middleName);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/validateClientRegistration")
                .then()
                .spec(response)
                .extract().as(ValidateClientRegistrationResult.class);
    }

    @Step("Регистрации клиента")
    public static RegistrationResult registerClient(String deviceToken, Integer regionAgentId, String email, String phone, String loginType, String password, String  firstName, String lastName, String middleName){
        RegistrationInfo body = new RegistrationInfo();
        body.setDeviceToken(deviceToken);
        body.setRegionAgentId(regionAgentId);
        body.setEmail(email);
        body.setMobilePhone(phone);
        body.setLoginType(loginType);
        body.setPassword(password);
        body.setFirstName(firstName);
        body.setLastName(lastName);
        body.setMiddleName(middleName);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/registerClient")
                .then()
                .spec(response)
                .extract().as(RegistrationResult.class);
    }

    @Step("Начать верификацию")
    public static StartVerificationResult startVerification(String deviceToken, String principalType, String principal) {
        StartVerificationRequest body = new StartVerificationRequest();
        body.setDeviceToken(deviceToken);
        body.setPrincipalType(principalType);
        body.setPrincipal(principal);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/startVerification")
                .then()
                .spec(response)
                .extract().as(StartVerificationResult.class);
    }

    @Step("Закончить верификации")
    public static VerificationResult endVerification(String deviceToken, String verificationCode, String secret) {
        VerificationRequest body = new VerificationRequest();
        body.setDeviceToken(deviceToken);
        body.setVerificationCode(verificationCode);
        body.setSecret(secret);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/verify")
                .then()
                .spec(response)
                .extract().as(VerificationResult.class);
    }

    @Step("Получение AuthToken-а (Авторизация)")
    public static AuthToken requestAuthToken(String deviceToken, String principalType, String principal, String password) {
        AuthTokenRequest body = new AuthTokenRequest();
        body.setDeviceToken(deviceToken);
        body.setPrincipalType(principalType);
        body.setPrincipal(principal);
        body.setPassword(password);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/requestAuthToken")
                .then()
                .spec(response)
                .extract().as(AuthToken.class);
    }

    @Step("Создание контрагента")
    public static Counteragent createCounteragent(Boolean active, Integer agentId, String companyRegistrationNumber, String email, String phone, String firstName, String lastName, Boolean legalEntity, String legalName, String name, String taxRegistrationNumber) {
        Counteragent body = new Counteragent();
        body.setActive(active);
        body.setAddress("");
        body.setAgentId(agentId);
        body.setCompanyRegistrationNumber(companyRegistrationNumber);
        body.setEmail(email);
        body.setPhone(phone);
        body.setFirstName(firstName);
        body.setLastName(lastName);
        body.setLegalEntity(legalEntity);
        body.setLegalName(legalName);
        body.setName(name);
        body.setTaxRegistrationNumber(taxRegistrationNumber);


        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/entity/Counteragent")
                .then()
                .spec(response)
                .extract().as(Counteragent.class);
    }

    @Step("Создать AuthProfile для Counteragent-а")
    public static UpgradeCounteragentResult createAuthProfileByCounteragent(Integer counteragentId) {
        UpgradeCounteragentRequest body = new UpgradeCounteragentRequest();
        body.setCounteragentId(counteragentId);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("admin/upgradeCounteragent")
                .then()
                .spec(response)
                .extract().as(UpgradeCounteragentResult.class);

    }

    @Step("Создание кода авторизация для входа в ЛК и отправка по каналам PUSH, EMAIL, SMS")
    public static AuthCodeResult createAuthCode(String deviceToken, String principalType, String principal) {
        AuthCodeRequest body = new AuthCodeRequest(deviceToken, principalType, principal);
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/sendAuthorizationCode")
                .then()
                .spec(response)
                .extract().as(AuthCodeResult.class);
    }

    @Step("Создать новый сеанс пользователя по полученному в SMS, PUSH, EMAIL - коду авторизации")
    public static AuthToken createAuthSessionByCode(String deviceToken, String principal, String principalType, String authCode) {
        AuthTokenByCodeRequest body = new AuthTokenByCodeRequest(deviceToken, principal, principalType, authCode);
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/sendAuthorizationCode")
                .then()
                .spec(response)
                .extract().as(AuthToken.class);
    }
}
