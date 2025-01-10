package browser;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;


/**
 * Класс используется для выбора браузера, в котором будут запускаться тесты.
 * Если при запуске указать browserProperty, тесты будут прогоняться в указанном браузере.
 * Тесты можно запускать в CHROME, YANDEX.
 * По умолчания (без указания браузера), тесты будут запускаться в CHROME.
 */
public class Browser {
    @Step("Запуск и настройка драйвера/браузера")
    public static void initDriver(){
        String browserProperty = System.getProperty("testBrowser");
        BrowserType browser = (browserProperty != null) ? BrowserType.valueOf(browserProperty) : BrowserType.CHROME;

        switch (browser){
            case CHROME:
                Configuration.browser = "CHROME";
                break;

            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "D:/YandexDriver/yandexdriver/yandexdriver.exe");
                Configuration.browser = "CHROME";
                break;
        }
    }
}