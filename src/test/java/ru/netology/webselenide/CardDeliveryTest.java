package ru.netology.webselenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {




    private String generateDate(int addDays) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }


    @BeforeEach

    public void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");



    }

    @AfterEach
    void tearDown() {
        closeWindow();
    }


    @Test
    void shouldTestSuccessfullyDeliveryCard() {

        $("[data-test-id ='city'] input").setValue("Москва");
        String currentDate = generateDate(7);
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.LEFT_SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id ='date'] input").sendKeys(currentDate);
        $("[data-test-id ='name'] input").setValue("Петров-Иванов Сергей");
        $("[data-test-id ='phone'] input").setValue("+79264542323");
        $("[data-test-id ='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));


    }


}





