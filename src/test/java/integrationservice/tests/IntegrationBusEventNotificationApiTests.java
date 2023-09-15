package integrationservice.tests;

import helpers.ListInfo;
import integrationservice.model.BusEventNotification;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static integrationservice.utils.IntegrationBusEventNotificationController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationBusEventNotificationApiTests {
    @Description("List BusEventNotification")
    @ParameterizedTest(name = "{index} - {0} Postbox BusEventNotification")
    @ValueSource(ints = {10002,10128, 10005, 10062, 10139, 10151})
    void successGetListBusEventNotification(Integer postBox) {
        ListInfo<BusEventNotification> busEventNotificationList= getBusEventNotificationList(postBox);
        assertThat(busEventNotificationList).isNotNull();
    }
}
