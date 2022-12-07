package bus.orderservice.utils;

import bus.orderservice.models.*;
import helpers.Ref;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static bus.orderservice.spec.OrderServiceApiSpecs.request;
import static bus.orderservice.spec.OrderServiceApiSpecs.responseSpec;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class OrderServiceApiController {
    @Step("Создание договора")
    public static Contract createContract() {
        Contract contract = new Contract();

        contract.setActive(true);
        contract.setAgentId(10506);

        Ref contractType = new Ref();
        contractType.setId(5);
        contractType.setType("ContractType");
        contractType.setName("С покупателем");
        contract.setContractType(contractType);

        contract.setCurrencyId(643);
        contract.setDeliveryGroupCode("default");

        Ref firstParty = new Ref();
        firstParty.setId(519587);
        firstParty.setType("Counteragent");
        firstParty.setName("АВТО ЗН ООО");
        contract.setFirstParty(firstParty);

        contract.setFromDate("2022-12-07T05:28:53.931+0000");
        contract.setManualDiscountManagement(true);
        contract.setPriceGroupId(1020);

        Ref secondParty = new Ref();
        secondParty.setId(607501);
        secondParty.setType("Counteragent");
        secondParty.setName("Tyrone Carter");
        contract.setSecondParty(secondParty);


        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(contract)
                .post("/entity/AUTO3N/Contract")
                .then()
                .spec(responseSpec)
                .extract().as(Contract.class);
    }

    @Step("Получение договора по идентификатору")
    public static Contract getClientContract(Integer id) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/entity/AUTO3N/Contract/" + id.toString())
                .then()
                .spec(responseSpec)
                .extract().as(Contract.class);
    }


    @Step("Создание заказа для клиента - простой")
    public static Order createOrderForClient() {
        Order order = new Order();
        order.setShipmentAgentId(10521);
        order.setDeliveryCostOverride(0);
        Ref status = new Ref();
        status.setId(5);
        status.setType("OrderStatus");
        order.setStatus(status);
        PersonInfo personInfo = new PersonInfo();
        personInfo.setEmail("test0712+1@bus.ru");
        personInfo.setFirstName("test create order from api");
        order.setCreatedPerson(personInfo);
        Ref contract = new Ref();
        contract.setId(606580);
        contract.setType("Contract");
        order.setContract(contract);
        ShipmentInfo shipmentInfo = new ShipmentInfo();
        shipmentInfo.setAdditionalInfo("{\\\"blockedDetails\\\":null,\\\"blockedCategories\\\":null,\\\"name\\\":null,\\\"shipmentAgentId\\\":10522,\\\"basePrice\\\":null,\\\"additionalFee\\\":null,\\\"totalPrice\\\":null,\\\"deliveryInterval\\\":null,\\\"deliveryTime\\\":null,\\\"boxes\\\":null,\\\"estimatedTransportationDate\\\":null,\\\"estimatedDeliveryDate\\\":null,\\\"currencyId\\\":null,\\\"totalWeight\\\":null,\\\"isApproximateResult\\\":null,\\\"vatRate\\\":null,\\\"vatSum\\\":null,\\\"errorMessage\\\":null,\\\"fromPoint\\\":null,\\\"toPoint\\\":{\\\"id\\\":25469,\\\"name\\\":\\\"Магазин AUTO3N Новосибирск «пр-т Дзержинского»\\\",\\\"address\\\":\\\"630015, г. Новосибирск, пр-т Дзержинского, д. 24\\\",\\\"latitude\\\":null,\\\"longitude\\\":null,\\\"phones\\\":null,\\\"worktime\\\":null,\\\"inDoorNavigation\\\":null,\\\"outDoorNavigation\\\":null,\\\"directions\\\":null,\\\"settlement\\\":null,\\\"transportCompany\\\":null,\\\"cardPayment\\\":null,\\\"cashPayment\\\":null,\\\"openingAllowed\\\":null,\\\"disabled\\\":null,\\\"external_id\\\":25469,\\\"observable\\\":true},\\\"deliveryMethod\\\":{\\\"id\\\":null,\\\"name\\\":null,\\\"transportCompany\\\":null,\\\"carrierType\\\":null,\\\"isCourierDelivery\\\":null,\\\"disabled\\\":null,\\\"description\\\":null,\\\"external_id\\\":null,\\\"observable\\\":true},\\\"fromZipCode\\\":null,\\\"toZipCode\\\":null,\\\"transportCompany\\\":{\\\"id\\\":\\\"5\\\",\\\"type\\\":null,\\\"name\\\":\\\"AUTO3N Самовывоз\\\",\\\"external_id\\\":\\\"5\\\",\\\"observable\\\":true},\\\"toSettlement\\\":{\\\"id\\\":null,\\\"name\\\":null,\\\"canonicalName\\\":null,\\\"displayName\\\":null,\\\"shortName\\\":null,\\\"latitude\\\":null,\\\"longitude\\\":null,\\\"region\\\":null,\\\"area\\\":null,\\\"country\\\":null,\\\"aoguid\\\":null,\\\"disabled\\\":null,\\\"external_id\\\":null,\\\"observable\\\":true},\\\"fromSettlement\\\":null,\\\"isOk\\\":true,\\\"formattedEstimatedDeliveryDate\\\":null,\\\"days\\\":null,\\\"addressString\\\":\\\"630015, г. Новосибирск, пр-т Дзержинского, д. 24\\\",\\\"comment\\\":null,\\\"shipmentInterval\\\":0,\\\"external_id\\\":null,\\\"observable\\\":true}");
        shipmentInfo.setAddress("630015, г. Новосибирск, пр-т Дзержинского, д. 24");
        shipmentInfo.setAdditionalDeliveryCost(0);
        shipmentInfo.setTransportationCompanyId(5);
        shipmentInfo.setTransportationCompanyName("AUTO3N Самовывоз");
        order.setShipmentInfo(shipmentInfo);
        order.setOwnerAgentId(10404);
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOwnerAgentId(10404);
        orderItem1.setBrand("TRW");
        orderItem1.setCurrentPrice(2000);
        orderItem1.setCurrentSumTotal(2000);
        orderItem1.setInitialAmount(1);
        orderItem1.setInitialPrice(2000);
        ProvisionPlan provisionPlan = new ProvisionPlan();
        orderItem1.setInitialProvision(provisionPlan);
        orderItem1.setInitialSumTotal(2000);
        orderItem1.setName("GDB3582_колодки дисковые");
        orderItem1.setOem("GDB3582");
        Ref orderItemStatus = new Ref();
        orderItemStatus.setId(5);
        orderItemStatus.setType("OrderItemStatus");
        orderItemStatus.setName("Создан");
        orderItemStatus.setCode("001");
        orderItem1.setStatus(orderItemStatus);

        orderItems.add(orderItem1);
        order.setOrderItems(orderItems);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(order)
                .post("/entity/AUTO3N/Order")
                .then()
                .spec(responseSpec)
                .extract().as(Order.class);
    }


    @Step("Получение информации о заказе клиента по идентификатору")
    public static Order getOrderContract(Integer id) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/entity/AUTO3N/Order/" + id.toString())
                .then()
                .spec(responseSpec)
                .extract().as(Order.class);
    }

    @Step("Платеж из 1С")
    public static Payment createPaymentForOrder(){
        Payment payment = new Payment();
        Ref contract = new Ref("Contract",606580, "С покупателем 28.09.2021");
        payment.setContract(contract);
        payment.setPaymentTime("2022-12-07T02:03:49.000+0000");
        Ref paymentType = new Ref("PaymentType", 11, "VISA/MASTERCARD");
        payment.setPaymentType(paymentType);
        payment.setSum(100);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(payment)
                .post("/entity/AUTO3N/Payment")
                .then()
                .spec(responseSpec)
                .extract().as(Payment.class);
    }

    @Step("Получение информации о платеже по идентификатору")
    public static Payment getPaymentFromClient(Integer id) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/entity/AUTO3N/Payment/" + id.toString())
                .then()
                .spec(responseSpec)
                .extract().as(Payment.class);
    }


    @Step("Создание шаблона договоров")
    public static ContractTemplate createContractTemplate() {
        ContractTemplate contractTemplate = new ContractTemplate();
        contractTemplate.setName("Тестовый договор от ...");
        contractTemplate.setContractType(new Ref ("ContractType", 5, "С покупателем"));
        contractTemplate.setFirstParty(new Ref("Counteragent", 247870, "Фрик (Омск) Партнер"));

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(contractTemplate)
                .post("/entity/AUTO3N/ContractTemplate")
                .then()
                .spec(responseSpec)
                .extract().as(ContractTemplate.class);
    }

    @Step("Получение шаблона договора по идентификатору")
    public static ContractTemplate getContractTemplate(Integer id) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/entity/AUTO3N/ContractTemplate/" + id.toString())
                .then()
                .spec(responseSpec)
                .extract().as(ContractTemplate.class);
    }

    @Step("Удаление шаблона договора по идентификатору")
    public static ContractTemplate deleteContractTemplate(Integer id) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .delete("/entity/AUTO3N/ContractTemplate/" + id.toString())
                .then()
                .spec(responseSpec)
                .extract().as(ContractTemplate.class);
    }

    @Step("Создание платежного счета для партнера")
    public static BillingAccount createBillingAccount() {
        BillingAccount billingAccount = new BillingAccount();


        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(billingAccount)
                .post("/entity/AUTO3N/BillingAccount")
                .then()
                .spec(responseSpec)
                .extract().as(BillingAccount.class);
    }
}
