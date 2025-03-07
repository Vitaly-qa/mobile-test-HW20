package tests;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import drivers.BrowserstackDriver;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class AndroidSimpleMyTest extends TestBase {

    @Test
    @DisplayName("Проверка наличия статей")
    void successfulSearchTest() {
        step("Поиск статей по заданному запросу", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверка, что перечень статей не пустой", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @DisplayName("Тест на проверку статьи")
    void articleVerificationTest() {
        step("Тапаем на поисковую строку и вводим Honda", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Honda");
        });
        step("Выбираем третью статью из списка", () ->
                $(id("org.wikipedia.alpha:id/page_list_item_title")).click());

        step("Переходим в статью и проверяем заголовок", () ->
                $(className("android.widget.TextView")).shouldHave(text("An error occurred")));
    }

}



