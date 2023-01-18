package bus.orderservice.tests;

import bus.orderservice.models.ContractTemplate;
import org.junit.jupiter.api.Test;

import static bus.orderservice.utils.OrderServiceApiController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ContractTemplateTests {
    @Test
    void successCreateContractTemplate() {
        ContractTemplate contractTemplate = createContractTemplate();
        assertThat(contractTemplate.getId()).isPositive();
    }

    @Test
    void successGetInfoAboutContractTemplate() {
        Integer id = 121;
        ContractTemplate contractTemplate = getContractTemplate(id);
        assertThat(contractTemplate.getId()).isEqualTo(id);
    }

    @Test
    void successDeleteContractTemplate() {
        ContractTemplate contractTemplate = createContractTemplate();
        Integer id = contractTemplate.getId();
        contractTemplate = deleteContractTemplate(id);
        assertThat(contractTemplate.getDeleted()).isEqualTo(true);
    }
}
