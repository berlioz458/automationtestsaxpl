package bus.orderservice.tests;

import bus.orderservice.models.Document;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static bus.orderservice.utils.OrderServiceApiController.*;
import static org.assertj.core.api.Assertions.assertThat;
@Tag("order")
public class DocumentTests {
    @Test
    void successCreateDocument() {
        Document document = createDocument("Доставка", "<h1>AutoTest create</h1>");
        assertThat(document.getId()).isPositive();
    }

    @Test
    void successGetDocument() {
        Document document = getDocument(127);
        assertThat(document.getId()).isEqualTo(127);
    }
}
