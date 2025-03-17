package tests;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import drivers.AppiumDriver;
import drivers.BrowserstackDriver;
import io.qameta.allure.selenide.AllureSelenide;


public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
        Configuration.browser = AppiumDriver.class.getName();
        if ("browserstack".equals(System.getProperty("deviceHost"))) {
            Configuration.browser = BrowserstackDriver.class.getName();
        }
        else if ("emulation".equals(System.getProperty("deviceHost"))){
            Configuration.browser = AppiumDriver.class.getName();
        }
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}

