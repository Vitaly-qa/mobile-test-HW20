package tests;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import drivers.AppiumDriver;
import drivers.BrowserstackDriver;
import io.qameta.allure.selenide.AllureSelenide;


public class TestBase {
    @BeforeAll
    static void beforeAll() {
        switch (System.getProperty("deviceHost")) {
            case "browserstack" -> {
                Configuration.browser = BrowserstackDriver.class.getName();
            }
            case "local" -> {
                Configuration.browser = AppiumDriver.class.getName();
            }
        }
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }


    @AfterEach
    void addAttachments() {
        if (System.getProperty("deviceHost").equals("browserstack")) {
            String sessionId = Selenide.sessionId().toString();
            System.out.println(sessionId);
            Attach.pageSource();
            Attach.addVideo(sessionId);
        }
        closeWebDriver();
    }
}

