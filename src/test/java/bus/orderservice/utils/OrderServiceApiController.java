package bus.orderservice.utils;

import bus.orderservice.models.*;
import helpers.ItemContextInfo;
import helpers.Ref;
import io.qameta.allure.Step;

import java.text.SimpleDateFormat;
import java.util.*;

import static bus.orderservice.spec.OrderServiceApiSpecs.request;
import static bus.orderservice.spec.OrderServiceApiSpecs.responseSpec;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class OrderServiceApiController {
    static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    static Calendar calendar = new GregorianCalendar();
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


    @Step("Создание заказа")
    public static Order createOrderForClient(Integer shipmentAgent, String emailPerson, Integer contractId, Boolean createByManager, Boolean startWorkImmediately, String delivery) {
        Order order = new Order();
        order.setShipmentAgentId(shipmentAgent);
        order.setDeliveryCostOverride(0);
        Ref status = new Ref();
        status.setId(5);
        status.setType("OrderStatus");
        order.setStatus(status);
        PersonInfo personInfo = new PersonInfo();
        personInfo.setEmail(emailPerson);
        personInfo.setFirstName("test create order from api");
        order.setCreatedPerson(personInfo);
        Ref contract = new Ref();
        contract.setId(contractId);
        contract.setType("Contract");
        order.setContract(contract);
        ShipmentInfo shipmentInfo = new ShipmentInfo();
        if (delivery.equals("SELF")) {
            shipmentInfo.setAdditionalInfo("{\\\"blockedDetails\\\":null,\\\"blockedCategories\\\":null,\\\"name\\\":null,\\\"shipmentAgentId\\\":10522,\\\"basePrice\\\":null,\\\"additionalFee\\\":null,\\\"totalPrice\\\":null,\\\"deliveryInterval\\\":null,\\\"deliveryTime\\\":null,\\\"boxes\\\":null,\\\"estimatedTransportationDate\\\":null,\\\"estimatedDeliveryDate\\\":null,\\\"currencyId\\\":null,\\\"totalWeight\\\":null,\\\"isApproximateResult\\\":null,\\\"vatRate\\\":null,\\\"vatSum\\\":null,\\\"errorMessage\\\":null,\\\"fromPoint\\\":null,\\\"toPoint\\\":{\\\"id\\\":25469,\\\"name\\\":\\\"Магазин AUTO3N Новосибирск «пр-т Дзержинского»\\\",\\\"address\\\":\\\"630015, г. Новосибирск, пр-т Дзержинского, д. 24\\\",\\\"latitude\\\":null,\\\"longitude\\\":null,\\\"phones\\\":null,\\\"worktime\\\":null,\\\"inDoorNavigation\\\":null,\\\"outDoorNavigation\\\":null,\\\"directions\\\":null,\\\"settlement\\\":null,\\\"transportCompany\\\":null,\\\"cardPayment\\\":null,\\\"cashPayment\\\":null,\\\"openingAllowed\\\":null,\\\"disabled\\\":null,\\\"external_id\\\":25469,\\\"observable\\\":true},\\\"deliveryMethod\\\":{\\\"id\\\":null,\\\"name\\\":null,\\\"transportCompany\\\":null,\\\"carrierType\\\":null,\\\"isCourierDelivery\\\":null,\\\"disabled\\\":null,\\\"description\\\":null,\\\"external_id\\\":null,\\\"observable\\\":true},\\\"fromZipCode\\\":null,\\\"toZipCode\\\":null,\\\"transportCompany\\\":{\\\"id\\\":\\\"5\\\",\\\"type\\\":null,\\\"name\\\":\\\"AUTO3N Самовывоз\\\",\\\"external_id\\\":\\\"5\\\",\\\"observable\\\":true},\\\"toSettlement\\\":{\\\"id\\\":null,\\\"name\\\":null,\\\"canonicalName\\\":null,\\\"displayName\\\":null,\\\"shortName\\\":null,\\\"latitude\\\":null,\\\"longitude\\\":null,\\\"region\\\":null,\\\"area\\\":null,\\\"country\\\":null,\\\"aoguid\\\":null,\\\"disabled\\\":null,\\\"external_id\\\":null,\\\"observable\\\":true},\\\"fromSettlement\\\":null,\\\"isOk\\\":true,\\\"formattedEstimatedDeliveryDate\\\":null,\\\"days\\\":null,\\\"addressString\\\":\\\"630015, г. Новосибирск, пр-т Дзержинского, д. 24\\\",\\\"comment\\\":null,\\\"shipmentInterval\\\":0,\\\"external_id\\\":null,\\\"observable\\\":true}");
            shipmentInfo.setAddress("630073, г. Новосибирск, ул. Блюхера, д. 71В");
            shipmentInfo.setAdditionalDeliveryCost(0);
            shipmentInfo.setTransportationCompanyId(5);
            shipmentInfo.setTransportationCompanyName("AUTO3N Самовывоз");
        } else {
            shipmentInfo.setAdditionalInfo("{\"blockedDetails\":null,\"blockedCategories\":null,\"name\":null,\"shipmentAgentId\":10101,\"basePrice\":null,\"additionalFee\":null,\"totalPrice\":null,\"deliveryInterval\":null,\"deliveryTime\":null,\"boxes\":null,\"estimatedTransportationDate\":null,\"estimatedDeliveryDate\":null,\"currencyId\":null,\"totalWeight\":null,\"isApproximateResult\":null,\"vatRate\":null,\"vatSum\":null,\"errorMessage\":null,\"fromPoint\":null,\"toPoint\":null,\"deliveryMethod\":{\"id\":null,\"name\":null,\"transportCompany\":null,\"carrierType\":null,\"isCourierDelivery\":null,\"disabled\":null,\"description\":null,\"external_id\":null,\"observable\":true},\"fromZipCode\":null,\"toZipCode\":null,\"transportCompany\":{\"id\":15,\"type\":null,\"name\":\"Доставка\",\"external_id\":15,\"observable\":true},\"toSettlement\":null,\"fromSettlement\":null,\"isOk\":true,\"formattedEstimatedDeliveryDate\":null,\"days\":null,\"addressString\":\"Россия, Вологодская область, Вологда, ул Зеленая, дом 11\",\"comment\":null,\"shipmentInterval\":0,\"external_id\":null,\"observable\":true}");
            shipmentInfo.setAddress("Россия, Вологодская область, Вологда, ул Зеленая, дом 11");
            shipmentInfo.setAdditionalDeliveryCost(0);
            shipmentInfo.setTransportationCompanyId(15);
            shipmentInfo.setTransportationCompanyName("Доставка");
        }

        order.setShipmentInfo(shipmentInfo);
        order.setOwnerAgentId(10404);
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem1 = createOrderItemForOrder("TRW", "GDB3582", 1, 2000, "колодки дисковые", "OFFER");
        OrderItem orderItem2 = createOrderItemForOrder("VAG", "078115561J", 2, 100, "Фильтр масляный", "OEM");
        OrderItem orderItem3 = createOrderItemForOrder("FEBI", "22548", 2, 599, "Фильтр масляный", "TECDOC");
        OrderItem orderItem4 = createOrderItemForOrder("TRACMAX", "YSTX5R1612", 4, 4975, "Шина летняя 215/60R16 95V X-Privilo TX5 TL", "4TOCHKI");
        OrderItem orderItem5 = createOrderItemForOrder("RENAULT", "7711428132", 1, 1027, "Антифриз концентрированный (1л)", "USEARCH");
        OrderItem orderItem6 = createOrderItemForOrder("FRAM", "G3829", 1, 508, "Фильтр топливный", "GCS");

        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);
        orderItems.add(orderItem4);
        orderItems.add(orderItem5);
        orderItems.add(orderItem6);
        order.setOrderItems(orderItems);

        if (createByManager) {
            order.setCreatedByManager(new Ref("ManagerInfo", 282, "Ваулин Сергей Николаевич"));
        }

        order.setStartWorkImmediately(startWorkImmediately);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(order)
                .post("/entity/AUTO3N/Order")
                .then()
                .spec(responseSpec)
                .extract().as(Order.class);
    }

    private static OrderItem createOrderItemForOrder(String brand, String oem, int amount, int price, String descr, String from) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOwnerAgentId(10404);
        orderItem.setBrand(brand);
        orderItem.setCurrentPrice(price);
        orderItem.setCurrentSumTotal(price * amount);
        orderItem.setInitialAmount(amount);
        orderItem.setInitialPrice(price);
        ProvisionPlan provisionPlan = new ProvisionPlan();
        calendar.add(Calendar.MINUTE, 15);
        provisionPlan.setBrandOriginal(brand);
        provisionPlan.setDescrOriginal(descr);
        provisionPlan.setOemOriginal(oem);
        provisionPlan.setOfferId("PIN:" + oem + ":BRAND:" + brand + ":VKORG:4000:KUNNR_RG:43275565:KEYZAK:MOV0000019:");
        provisionPlan.setActualityDate(formater.format(calendar.getTime()));
        provisionPlan.setPlacementDateTime(formater.format(calendar.getTime()));
        provisionPlan.setSupplierAvailabilityDateTime(formater.format(calendar.getTime()));
        calendar.add(Calendar.HOUR, 48);
        provisionPlan.setDeliveryDateMin(formater.format(calendar.getTime()));
        provisionPlan.setDeliveryDateMax(formater.format(calendar.getTime()));
        calendar.add(Calendar.HOUR, 144);
        provisionPlan.setMaxReturnDate(formater.format(calendar.getTime()));
        orderItem.setInitialProvision(provisionPlan);
        orderItem.setInitialSumTotal(price * amount);
        orderItem.setName(descr);
        orderItem.setOem(oem);
        Ref orderItemStatus = new Ref();
        orderItemStatus.setId(5);
        orderItemStatus.setType("OrderItemStatus");
        orderItemStatus.setName("Создан");
        orderItemStatus.setCode("001");
        orderItem.setStatus(orderItemStatus);
        ItemContextInfo itemContextInfo = new ItemContextInfo();
        OrderItemContextInfo orderItemContextInfo = new OrderItemContextInfo();
        switch (from) {
            case ("OFFER"):
                itemContextInfo.setDetailCode(oem);
                itemContextInfo.setBrand(brand);

                orderItemContextInfo.setItemContextInfo(itemContextInfo);
                orderItem.setContextInfo(orderItemContextInfo);
                break;
            case ("OEM"):
                itemContextInfo.setDetailCode(oem);
                itemContextInfo.setBrand(brand);
                itemContextInfo.setCatalog("AU1489");
                itemContextInfo.setCatalogCodeOnImage("21");
                itemContextInfo.setCatalogUnitId("5672556");
                itemContextInfo.setExternalCatalog("OEM");
                itemContextInfo.setSsd("$*KwHl0cDlgpq1oKe_4JG40b2piY6Q5O_o5bqxpeblr4-npaWT2MXUnpWdlZ6SlJ3a08iFn5-XqYmhoPjAy5aDk5ji4pPiv86t5ufh5eOopLrqwOf1ovPq9Yzz_6zpvu3j5-fl4-Tl8_7qsr6Rq_W08-mswcn1-vOjuvTr8dvT3fX687P17OPlv6y-uvTr8pbg8_-s8__17PPkleTkhcWisPWzsvLt9JCH36Kw9aKy8u30lInUorD1oLi-9ezzhM_V3p_j5ZLm55_iv7Gs4uLz_PWi8-ms1tWZiYKEhZ-Sh6z9AAAAAAi12fc=$");
                itemContextInfo.setVin("WAUBH54B11N111054");

                orderItemContextInfo.setItemContextInfo(itemContextInfo);
                orderItem.setContextInfo(orderItemContextInfo);
                break;
            case ("TECDOC"):
                itemContextInfo.setDetailCode(oem);
                itemContextInfo.setBrand(brand);
                itemContextInfo.setExternalCatalog("TecDocOnline");
                itemContextInfo.setExternalCatalogItemId("47260671");
                itemContextInfo.setExternalCatalogModelId("3395");
                itemContextInfo.setExternalCatalogModelManufacturerName("AUDI");
                itemContextInfo.setExternalCatalogModelName("A6 C5 Avant (4B5)");
                itemContextInfo.setExternalCatalogModificationId("57275");
                itemContextInfo.setExternalCatalogModificationName("3.0");
                itemContextInfo.setExternalCatalogNodeId("103779");
                itemContextInfo.setExternalCatalogNodeName("Масляный фильтр");

                orderItemContextInfo.setItemContextInfo(itemContextInfo);
                orderItem.setContextInfo(orderItemContextInfo);
                break;
            case ("4TOCHKI"):
                itemContextInfo.setDetailCode(oem);
                itemContextInfo.setBrand(brand);
                itemContextInfo.setExternalCatalog("4Tochki");
                itemContextInfo.setExternalCatalogModelId("2307");
                itemContextInfo.setExternalCatalogModelManufacturerName("Subaru");
                itemContextInfo.setExternalCatalogModelName("Forester");
                itemContextInfo.setExternalCatalogModificationId("11965");
                itemContextInfo.setExternalCatalogModificationName("2.0i S-Turbo");

                orderItemContextInfo.setItemContextInfo(itemContextInfo);
                orderItem.setContextInfo(orderItemContextInfo);
                break;
            case ("USEARCH"):
                itemContextInfo.setDetailCode(oem);
                itemContextInfo.setBrand(brand);
                itemContextInfo.setSearchQuery("антифриз зеленый 1л");
                itemContextInfo.setSearchQueryId("166884");

                orderItemContextInfo.setItemContextInfo(itemContextInfo);
                orderItem.setContextInfo(orderItemContextInfo);
                break;
            case ("GCS"):
                itemContextInfo.setDetailCode(oem);
                itemContextInfo.setBrand(brand);
                itemContextInfo.setGcsCategoryId(28L);
        }
        return orderItem;
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

    @Step("Изменение статуса созданного заказа")
    public static Order changeStatusForOrder(Order order, Integer OrderStatusId) {
        Ref status = new Ref();
        status.setId(OrderStatusId);
        order.setStatus(status);
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(order)
                .put("/entity/AUTO3N/Order/" + order.getId().toString())
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
        payment.setMerchantID("1");
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
        billingAccount.setCompanyName("autocreate test");
        billingAccount.setCompanyBankAccount("65484946546546549898");
        billingAccount.setCounteragent(new Ref("Counteragent", 247870, "Фрик (Омск) Партнер"));

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(billingAccount)
                .post("/entity/AUTO3N/BillingAccount")
                .then()
                .spec(responseSpec)
                .extract().as(BillingAccount.class);
    }

    @Step("Получение платежного счета по идентификатору")
    public static BillingAccount getBillingAccount(Integer id) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/entity/AUTO3N/BillingAccount/" + id.toString())
                .then()
                .spec(responseSpec)
                .extract().as(BillingAccount.class);
    }

    @Step("Создание документа")
    public static Document createDocument(String name, String html) {
        Document document = new Document(name, html);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(document)
                .post("/entity/AUTO3N/Document")
                .then()
                .spec(responseSpec)
                .extract().as(Document.class);
    }

    @Step("Получение документа по идентификатору")
    public static Document getDocument(Integer id) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/entity/AUTO3N/Document/" + id.toString())
                .then()
                .spec(responseSpec)
                .extract().as(Document.class);
    }

    @Step("Создание программы лояльности без бонусной программы")
    public static LoyaltyPolicy createLoyalPolicy(Integer threshold, Integer counteragent, String name, Integer limit, Integer selfDiscount) {
        LoyaltyPolicy loyaltyPolicy = new LoyaltyPolicy(threshold,
                new Ref("Counteragent", counteragent, "Name"),
                name,
                limit,
                selfDiscount);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(loyaltyPolicy)
                .post("/loyalty/entity/AUTO3N/LoyaltyPolicy")
                .then()
                .spec(responseSpec)
                .extract().as(LoyaltyPolicy.class);
    }

    @Step("Получение программы лояльности по идентификатору")
    public static LoyaltyPolicy getLoyalPolicy(Integer id) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/loyalty/entity/AUTO3N/LoyaltyPolicy/" + id.toString())
                .then()
                .spec(responseSpec)
                .extract().as(LoyaltyPolicy.class);
    }

    @Step("Создание маркетинговой акции")
    public static MarketingAction createMarketingAction(String activeFrom,
                                                        String activeTo,
                                                        String name,
                                                        Integer discountPercent,
                                                        Integer countOfOrdersToFreezeDiscount,
                                                        Integer extraChargePercent,
                                                        Integer periodToCheckOrdersDays,
                                                        Integer startDiscountPercent, List<PriceListInfo> pricelists) {

        MarketingAction marketingAction = new MarketingAction(
                activeFrom,
                activeTo,
                name,
                discountPercent,
                countOfOrdersToFreezeDiscount,
                extraChargePercent,
                periodToCheckOrdersDays,
                startDiscountPercent, pricelists);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(marketingAction)
                .post("/loyalty/entity/AUTO3N/MarketingAction")
                .then()
                .spec(responseSpec)
                .extract().as(MarketingAction.class);
    }

    @Step("Получение маркетинговой акции по идентификатору")
    public static MarketingAction getMarketingAction(Integer id) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/loyalty/entity/AUTO3N/MarketingAction/" + id.toString())
                .then()
                .spec(responseSpec)
                .extract().as(MarketingAction.class);
    }

    @Step("Создание документа отгрузки")
    public static Shipment createShipment(Integer contractId, Integer status, Integer type, Integer ownerAgentId, Integer countShipmentItems) {
        Shipment shipment = new Shipment();
        shipment.setOwnerAgentId(ownerAgentId);
        shipment.setContract(new Ref("Contract", contractId, "С покупателем №714386 от 1.07.2022"));
        shipment.setDocumentDate(formater.format(calendar.getTime()));
        shipment.setDocumentNumber("documentNumber");
        shipment.setFoundation("foundation");
        shipment.setStatus(new Ref("ShipmentStatus", status, "ShipmentStatus"));
        shipment.setType(new Ref("ShipmentType", type, "ShipmentType"));

        List<ShipmentItem> shipmentItems = createListShipmentItems(countShipmentItems);
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(shipment)
                .post("/entity/AUTO3N/Shipment")
                .then()
                .spec(responseSpec)
                .extract().as(Shipment.class);
    }

    private static List<ShipmentItem> createListShipmentItems(Integer i) {
        List<ShipmentItem> shipmentItemList = null;

        for (int j = 0; j < i; j++) {
            ShipmentItem shipmentItem = new ShipmentItem();
            shipmentItem.setBrand("AUTOPROFI");
            shipmentItem.setDiscountSum(0);
            shipmentItem.setName("Трос");
            shipmentItem.setOem("TRL501" + j);
            shipmentItem.setOrderItem(new Ref("OrderItem", 4847463, "OrderItem"));
            shipmentItem.setPosition(1*(j+1));
            shipmentItem.setPrice(100*(j+1));
            shipmentItem.setSum(100*(j+1));
            shipmentItem.setTaxPercent(20);
            shipmentItem.setTaxSum(20);
            shipmentItem.setTotalSum(100*(j+1));
            shipmentItem.setUnitName("шт");
            shipmentItem.setUnits(1*(j+1));

            shipmentItemList.add(shipmentItem);
        }

        return shipmentItemList;
    }

}
