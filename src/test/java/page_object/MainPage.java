package page_object;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    MainPage mainPage;
    public final static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    private final SelenideElement signInMainButton = $(byText("Войти в аккаунт"));
    private final SelenideElement createOrderButton = $(byText("Оформить заказ"));
    private final SelenideElement toAccountProfileButton = $(byTagAndText("p", "Личный Кабинет"));
    private final SelenideElement createBurgerTextElement = $(byTagAndText("h1", "Соберите бургер"));
    public MainPage(){
    }

    public SignInPage clickOnSignInMainButton(){
        signInMainButton.click();
        return new SignInPage();
    }

    public boolean isOrderButtonDisplayed(){
        createOrderButton.shouldBe(visible);
        return createOrderButton.isDisplayed();
    }

    public SignInPage goToAccountProfilePageWhenUnauthorized(){
        toAccountProfileButton.click();
        return new SignInPage();
    }

    public AccountProfilePage goToAccountProfilePageWhenAuthorized(){
        toAccountProfileButton.click();
        return new AccountProfilePage();
    }

    public boolean isCreateBurgerTextElementDisplayed(){
        return createBurgerTextElement.isDisplayed();
    }
}