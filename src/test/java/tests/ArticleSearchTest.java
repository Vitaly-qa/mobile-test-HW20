package tests;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
    @Tag("android")
    @DisplayName("Open article from Search")
    void openArticleFromSearchTest() {
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Open first article", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_container")).first().click());
        step("Check opening", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text"))
                        .shouldHave(text("An error occurred")));

    }
}



