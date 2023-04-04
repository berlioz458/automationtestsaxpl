package bus.clientservice.core.tests;

import lombok.var;
import org.junit.jupiter.api.Test;

import static bus.clientservice.core.utils.clientController.getTokenByEmail;
import static bus.clientservice.core.utils.clientController.getTokenBySms;

public class ApiTests {

    @Test
    void test() {
        String tokenByEmail = getTokenByEmail("myriam52@hotmail.com");
        String tokenBySms = getTokenBySms("79632225855");
        System.out.println(tokenByEmail);
        System.out.println(tokenBySms);
    }
}
