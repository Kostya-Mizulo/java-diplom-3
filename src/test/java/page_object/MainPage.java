package page_object;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Condition.*;
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
    private final SelenideElement bunsButton = $x("//span[contains(text(), 'Булки')]/..");
    private final SelenideElement saucesButton = $x("//span[contains(text(), 'Соусы')]/..");
    private final SelenideElement fillingsButton = $x("//span[contains(text(), 'Начинки')]/..");
    private final SelenideElement bunsHeaderInMenu = $(byTagAndText("h2", "Булки"));
    private final SelenideElement saucesHeaderInMenu = $(byTagAndText("h2", "Соусы"));
    private final SelenideElement fillingsHeaderInMenu = $(byTagAndText("h2", "Начинки"));

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

    public MainPage selectSaucesTab(){
        saucesButton.click();
        return this;
    }

    public MainPage selectBunsTab(){
        bunsButton.click();
        return this;
    }

    public MainPage selectFillingsTab() {
        fillingsButton.click();
        return this;
    }

    public boolean isBunsTabSelected(){
        String classValue = bunsButton.getAttribute("class");
        return classValue.contains("current");
    }

    public boolean isSaucesTabSelected(){
        String classValue = saucesButton.getAttribute("class");
        return classValue.contains("current");
    }

    public boolean isFillingsTabSelected(){
        String classValue = fillingsButton.getAttribute("class");
        return classValue.contains("current");
    }

    public boolean isBunsHeaderInMenuVisible(){
        return isScrolledToElement(bunsHeaderInMenu);
    }

    public boolean isSaucesHeaderInMenuVisible(){
        return isScrolledToElement(saucesHeaderInMenu);
    }

    public boolean isFillingsHeaderInMenuVisible(){
        return isScrolledToElement(fillingsHeaderInMenu);
    }

    private boolean isScrolledToElement(SelenideElement targetElement){
        Selenide.sleep(1000); //жесткая задержка для ожидания пока выполнится js-скрипт скролла окна меню
            return Selenide.executeJavaScript(
                    "var targetElem = arguments[0]; " +
                            "var referenceElem = arguments[1]; " +
                            "var targetRect = targetElem.getBoundingClientRect(); " +
                            "var referenceRect = referenceElem.getBoundingClientRect(); " +
                            "return (targetRect.top +5 >= referenceRect.bottom && (targetRect.top - referenceRect.bottom) <= 30);",
                    targetElement, bunsButton
            );
    }
}