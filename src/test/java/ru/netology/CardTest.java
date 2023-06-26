package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void ShouldTestPositive() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        //SelenideElement form = $("[class='form form_size_m form_theme_alfa-on-white']");
        $("[data-test-id='city'] input").setValue("Самара");
        //$("[class='menu-item menu-item_type_block menu-item_theme_alfa-on-white']").click();
        String todayDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(todayDate);
        $("[data-test-id=name] input").setValue("Ксения Сундукова");
        $("[data-test-id='phone'] input").setValue("+77777777777");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + todayDate));

    }
}