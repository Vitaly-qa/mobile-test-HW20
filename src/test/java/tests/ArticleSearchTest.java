package tests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class ArticleSearchTest extends TestBase {

    @Test
    @DisplayName("Тест на проверку статьи")
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
    @DisplayName("Тест на проверку статьи Honda")
    void articleVerificationTest() {
        back();
        step("Поиск статьи по заданному запросу Honda", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Honda");
        });
        step("Проверка первой статьи из списка", () ->
                $(id("org.wikipedia.alpha:id/page_list_item_title")).click());

        step("Переходим в статью и проверяем заголовок об ошибке", () ->
                $(className("android.widget.TextView")).shouldHave(text("An error occurred")));
    }

}



