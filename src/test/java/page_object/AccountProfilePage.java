package page_object;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class AccountProfilePage {
    public final static String ACCOUNT_PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private final SelenideElement toConstructorButton = $(byTagAndText("p", "Конструктор"));
    private final SelenideElement stellarBurgerLogo = $("a[href='/']");
    private final SelenideElement logOutButton = $(byTagAndText("button", "Выход"));

    public MainPage goToMainPageViaConstructorButton(){
        toConstructorButton.click();

        return new MainPage();
    }

    public MainPage goToMainPageViaStellarBurgerLogo(){
        stellarBurgerLogo.click();

        return new MainPage();
    }

    public SignInPage pressLogOutButton(){
        logOutButton.click();
        return new SignInPage();
    }
}
