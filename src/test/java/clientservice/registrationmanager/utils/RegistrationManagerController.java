package clientservice.registrationmanager.utils;

import clientservice.registrationmanager.models.*;
import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.A;
import unitls.EmailParser;

import static clientservice.registrationmanager.spec.RegistrationManagerApiSpecs.*;
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

    public static VerificationResult endVerification(String deviceToken, String verificationCode) {
        VerificationRequest body = new VerificationRequest();
        body.setDeviceToken(deviceToken);
        body.setVerificationCode(verificationCode);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/verify")
                .then()
                .spec(response)
                .extract().as(VerificationResult.class);
    }

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

}
