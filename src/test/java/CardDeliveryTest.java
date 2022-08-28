import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    //нужна логика для даты
    @Test
    void test() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Кострома");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(4);
        $("[data-test-id='date'] input").val(planningDate);

        $("[data-test-id=name] input").setValue("Мария");
        $("[data-test-id=phone] input").setValue("+79103729929");
        $("[data-test-id=agreement]").click();
        $x("//span[@class='button__text']").click();
        $(".notification__content")
                .shouldBe(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(visible);


    }

}
