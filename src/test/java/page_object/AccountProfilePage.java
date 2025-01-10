package page_object;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;


/**
 * Класс страницы https://stellarburgers.nomoreparties.site/account/profile
 */
public class AccountProfilePage {
    public final static String ACCOUNT_PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private final SelenideElement toConstructorButton = $(byTagAndText("p", "Конструктор"));
    private final SelenideElement stellarBurgerLogo = $("a[href='/']");
    private final SelenideElement logOutButton = $(byTagAndText("button", "Выход"));


    @Step("Переход на главную страницу сайта по клику на кнопку конктруктор из личного кабинета")
    public MainPage goToMainPageViaConstructorButton(){
        toConstructorButton.click();

        return new MainPage();
    }


    @Step("Переход на главную страницу сайта по клику на лого сайта из личного кабинета")
    public MainPage goToMainPageViaStellarBurgerLogo(){
        stellarBurgerLogo.click();

        return new MainPage();
    }


    @Step("Клик по кнопке для выхода из системы")
    public SignInPage pressLogOutButton(){
        logOutButton.click();

        return new SignInPage();
    }
}
