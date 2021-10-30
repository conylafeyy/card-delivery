import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    @Test
    void shouldTest() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Нижний Новгород");
        $("[type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        String today = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[type='tel']").setValue(today);
        $("[name='name']").setValue("Екатерина Агапова");
        $("[name='phone']").setValue("+70987654321");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(appear, Duration.ofSeconds(15));
        $(withText("Встреча успешно забронирована на")).shouldBe(appear);
        $(withText(today)).shouldBe(appear);

    };
}