package unitls;

import com.codeborne.selenide.SelenideElement;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

public class EmailParser {
    //открыть страницу с заглушкой для писем
    static void openMainPage() {
        open("http://bus.stage.gcs.prodv.net:7020/");
    }

    //взять последнее письмо (по идее оно должно быть нужным)
    static @NotNull Integer getLastEmailId() {
        SelenideElement selenideElement = $("[data-message-id]");
        return Integer.valueOf(Objects.requireNonNull(selenideElement.getAttribute("data-message-id")));
    }

    //открыть страницу с письмом
    static void openEmailPage(@NotNull Integer id) {
        open("http://bus.stage.gcs.prodv.net:7020/messages/" + id.toString() + ".html");
    }

    //достать значение токена из кнопки и вернуть его
    static String getActivationToken() {
        SelenideElement element = $("#verifyButton");
        return element.getAttribute("href");
    }


    //открыть страницу с заглушкой для писем
    //взять последнее письмо (по идее оно должно быть нужным)
    //открыть страницу с письмом
    //достать значение токена из кнопки и вернуть его
    public static @NotNull String getTokenFromEmail() {
        openMainPage();
        Integer id = getLastEmailId();
        openEmailPage(id);
        String UrlFromButton = getActivationToken();
        return UrlFromButton.substring(UrlFromButton.indexOf('=') + 1);
    }
}
