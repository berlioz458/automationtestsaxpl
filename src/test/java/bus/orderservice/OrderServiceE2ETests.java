package bus.orderservice;

import bus.orderservice.models.Order;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static bus.orderservice.utils.OrderServiceApiController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceE2ETests {
    //TODO:add e2e test for order (1)
    //1. создать заказ
    //2. получить заказ
    //3. изменить заказ - startWorkImmediately/note
    //4. изменить статус заказа, позиции заказа -


    @Test
    @Description("Заказ клиента")
    void e2eTestForOrders(){
        //1. создние заказа
        Order createOrder = createOrderForClient(10563, "test-2@prodv.net", 606580, false);
        //2. получение информации о созданном заказе, проверим что вернулся именно тот заказ что мы создали
        Order infoOrder = getOrderContract(createOrder.getId());
        assertThat(createOrder.getId()).isEqualTo(infoOrder.getId());
        //3. переведем заказ в работу
        Order inWorkOrder = changeStatusForOrder(infoOrder, 6);
        assertThat(inWorkOrder.getStatus().getId()).isEqualTo(6);
        //4. переведем заказ в отменен
        Order inCancelOrder = changeStatusForOrder(inWorkOrder, 7);
        assertThat(inCancelOrder.getStatus().getId()).isEqualTo(7);
    }
}
