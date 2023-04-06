package bus.orderservice.tests;

import bus.orderservice.models.Contract;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static bus.orderservice.utils.OrderServiceApiController.createContract;
import static bus.orderservice.utils.OrderServiceApiController.getClientContract;
import static org.assertj.core.api.Assertions.assertThat;
@Tag("order")
public class ContractTests {
    @BeforeEach
    void startUp() {
        //TODO: что подготовить для контракта чтобы были не захардкожены
    }


    @Test
    void successCreateContractForClient() {
        Contract contract = createContract();
        assertThat(contract.getId()).isPositive();
    }

    @Test
    void successGetInfoAboutContractClient() {
        Integer id = 685828;
        Contract contract = getClientContract(id);
        assertThat(contract.getId()).isEqualTo(id);
    }
}
