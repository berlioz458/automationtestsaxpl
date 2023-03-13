package bus.orderservice.tests;

import bus.orderservice.models.BillingAccount;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static bus.orderservice.utils.OrderServiceApiController.createBillingAccount;
import static bus.orderservice.utils.OrderServiceApiController.getBillingAccount;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("order")
public class BillingAccountTests {

    @Test
    void successCreateBillingAccount() {
        BillingAccount billingAccount = createBillingAccount();
        assertThat(billingAccount.getId()).isPositive();
    }

    @Test
    void successGetBillingAccount() {
        BillingAccount billingAccount = getBillingAccount(127);
        assertThat(billingAccount.getId()).isEqualTo(127);
    }
}
