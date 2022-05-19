package orderservice;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.qameta.allure.Description;
import orderservice.billingAccountController.BillingAccount;
import orderservice.billingAccountController.BillingAccountRequest;
import orderservice.billingAccountController.Counteragent;
import orderservice.billingAccountController.RefBillingAccount;
import orderservice.contractTemplateController.*;
import orderservice.documentController.Document;
import orderservice.documentController.DocumentCreateRequest;
import orderservice.loyaltyPolicyController.FirstPartyLoyalPolicy;
import orderservice.loyaltyPolicyController.LoyaltyPolicy;
import orderservice.loyaltyPolicyController.LoyaltyPolicyRequest;
import orderservice.loyaltyPolicyController.RefLoyalPolicy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;
import static orderservice.OrderServiceApiSpecs.request;
import static orderservice.OrderServiceApiSpecs.responseSpec;
import static org.hamcrest.Matchers.is;

@Tag("order")
public class OrderServiceTests {

    Faker faker = new Faker(new Locale("ru"));
    String data = faker.date().birthday().toString();

    @Tag("smoke")
    @Test
    @Description("Get Version")
    void successGetVersion() {
        given()
            .filter(withCustomTemplate())
            .spec(request)
        .when()
            .get("/version")
        .then()
            .statusCode(200);
    }


    @Test
    @Description("Get 'Counteragent' entity")
    void successGetCounteragent() {
        given()
            .filter(withCustomTemplate())
            .spec(request)
        .when()
            .get("/entity/AUTO3N/Counteragent/35949")
        .then()
            .spec(responseSpec)
            .log().body()
            .body("Counteragent.id", is(35949));
    }

    @Test
    @Description("Get 'Order' entity")
    void successGetOrder() {
        given()
            .filter(withCustomTemplate())
            .spec(request)
        .when()
            .get("/entity/AUTO3N/Order/1781356")
        .then()
            .spec(responseSpec)
            .log().body()
            .body("Order.id", is(1781356));
    }

    @Test
    @Description("Get 'Contract' entity")
    void successGetContract() {
        given()
            .filter(withCustomTemplate())
            .spec(request)
        .when()
            .get("/entity/AUTO3N/Contract/390800")
        .then()
            .spec(responseSpec)
            .log().body()
            .body("Contract.id", is(390800));
    }

    @Test
    @Description("Create Contract Template")
    void successCreateContractTemplate() {
        ContractTemplate ContractTemplate = new ContractTemplate();
        FirstParty firstParty = new FirstParty();
        RefContractTemplate refContractTemplateFirstParty = new RefContractTemplate();
        refContractTemplateFirstParty.setName("Шулинина Екатерина");
        refContractTemplateFirstParty.setType("Counteragent");
        refContractTemplateFirstParty.setId(561400L);
        firstParty.setRef(refContractTemplateFirstParty);
        ContractType contractType = new ContractType();
        RefContractTemplate refContractTemplateContractType = new RefContractTemplate();
        refContractTemplateContractType.setId(5L);
        refContractTemplateContractType.setName("С покупателем");
        refContractTemplateContractType.setType("ContractType");
        contractType.setRef(refContractTemplateContractType);

        ContractTemplate.setName("Test " + faker.date().birthday(0,63));
        ContractTemplate.setOwnerAgentId(10056L);
        ContractTemplate.setForRegionAgentId(10056L);
        ContractTemplate.setPriceGroupId(1020L);
        ContractTemplate.setCurrencyId(643L);
        ContractTemplate.setFirstParty(firstParty);
        ContractTemplate.setContractType(contractType);

        ContractTemplateRequest ContractTemplateRequest = new ContractTemplateRequest();
        ContractTemplateRequest.setContractTemplate(ContractTemplate);

        Gson gson = new Gson();

        given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(gson.toJson(ContractTemplateRequest))
        .when()
                .post("/entity/AUTO3N/ContractTemplate")
        .then()
                .spec(responseSpec)
                .log().all();
    }


    @Test
    @Description("Create Billing Account")
    void successCreateBillingAccount() {
        BillingAccount billingAccount = new BillingAccount();
        billingAccount.setAccountantName(faker.company().name());
        billingAccount.setBankName(faker.rockBand().name());
        billingAccount.setBic(faker.finance().bic().substring(0,9));
        billingAccount.setBillingCurrency("643");
        billingAccount.setCompanyAddress(faker.address().fullAddress());
        billingAccount.setCompanyBankAccount(faker.finance().iban("LT"));
        billingAccount.setCompanyName(faker.company().name());
        billingAccount.setFormalCompanyName(faker.company().name());
        billingAccount.setFormalCompanyNameParentalCase(faker.company().name());
        billingAccount.setCorrespondentBankAccount(faker.finance().iban("LT"));
        billingAccount.setDirectorName(faker.name().fullName());
        billingAccount.setDirectorNameParentalCase(faker.name().fullName());

        Counteragent counteragent = new Counteragent();
        RefBillingAccount refCounteragent = new RefBillingAccount();
        refCounteragent.setId(561400L);
        refCounteragent.setName("Шулинина Екатерина");
        refCounteragent.setType("Counteragent");
        counteragent.setRef(refCounteragent);

        billingAccount.setCounteragent(counteragent);

        BillingAccountRequest billingAccountRequest = new BillingAccountRequest();
        billingAccountRequest.setBillingAccount(billingAccount);

        Gson gson = new Gson();

        given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(gson.toJson(billingAccountRequest))
                .when()
                .post("/entity/AUTO3N/BillingAccount")
                .then()
                .spec(responseSpec)
                .log().all();
    }

    @Test
    @Description("Create Document")
    void successCreateDocument() {
        Document document = new Document();
        document.setType("html");
        document.setJsonBody("<h1>Auto3n AutoTest</h1>");
        document.setName("О самозаказе");
        document.setParentObjectType("Counteragent");
        document.setParentObjectId(10056);

        DocumentCreateRequest documentCreateRequest = new DocumentCreateRequest();
        documentCreateRequest.setDocument(document);

        Gson gson = new Gson();

        given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(gson.toJson(documentCreateRequest))
                .when()
                .post("/entity/AUTO3N/Document")
                .then()
                .spec(responseSpec)
                .log().all();
    }

    @Test
    @Description("Create Loyalty Policy")
    void successCreateLoyalPolicy() {
        LoyaltyPolicy loyaltyPolicy = new LoyaltyPolicy();
        loyaltyPolicy.setName(faker.backToTheFuture().date() + faker.backToTheFuture().character());
        loyaltyPolicy.setContractPersonalDiscountLoyaltySystemThreshold(15L);
        loyaltyPolicy.setSelfAndPersonalDiscountLimit(30L);
        loyaltyPolicy.setSelfOrderDiscountPercent(15L);

        RefLoyalPolicy refLoyalPolicy = new RefLoyalPolicy();
        refLoyalPolicy.setId(35949L);
        refLoyalPolicy.setName("Шулинина Катя");
        refLoyalPolicy.setType("Counteragent");

        FirstPartyLoyalPolicy firstPartyLoyalPolicy = new FirstPartyLoyalPolicy();
        firstPartyLoyalPolicy.setRef(refLoyalPolicy);
        loyaltyPolicy.setFirstParty(firstPartyLoyalPolicy);

        LoyaltyPolicyRequest loyaltyPolicyRequest = new LoyaltyPolicyRequest();
        loyaltyPolicyRequest.setLoyaltyPolicy(loyaltyPolicy);

        Gson gson = new Gson();
        given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(gson.toJson(loyaltyPolicyRequest))
                .when()
                .post("/loyalty/entity/AUTO3N/LoyaltyPolicy")
                .then()
                .spec(responseSpec)
                .log().all();

    }



}
