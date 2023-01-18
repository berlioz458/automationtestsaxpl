package bus.orderservice.tests;

import bus.orderservice.models.LoyaltyPolicy;
import org.junit.jupiter.api.Test;

import static bus.orderservice.utils.OrderServiceApiController.createLoyalPolicy;
import static bus.orderservice.utils.OrderServiceApiController.getLoyalPolicy;
import static org.assertj.core.api.Assertions.assertThat;

public class LoyalPolicyTests {
    @Test
    void successCreateLoyalPolicyWithoutBonues() {
        LoyaltyPolicy loyaltyPolicy = createLoyalPolicy(15, 35949, "Autotest Create LoyalPolicy", 30, 15);
        assertThat(loyaltyPolicy.getId()).isPositive();
    }

    @Test
    void successGetLoyalPolicyById() {
        Integer id = 67;
        LoyaltyPolicy loyaltyPolicy = getLoyalPolicy(id);
        assertThat(loyaltyPolicy.getId()).isEqualTo(id);
    }
}
