package browser;

import com.codeborne.selenide.Configuration;

public class Browser {
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

            default:
                Configuration.browser = "CHROME";
                break;
        }
    }
}
