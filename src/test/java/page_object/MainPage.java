package page_object;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


/**
 * Класс страницы https://stellarburgers.nomoreparties.site/
 */
public class MainPage {
    public final static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final SelenideElement signInMainButton = $(byText("Войти в аккаунт"));
    private final SelenideElement createOrderButton = $(byText("Оформить заказ"));
    private final SelenideElement toAccountProfileButton = $(byTagAndText("p", "Личный Кабинет"));
    private final SelenideElement bunsButton = $x("//span[contains(text(), 'Булки')]/..");
    private final SelenideElement saucesButton = $x("//span[contains(text(), 'Соусы')]/..");
    private final SelenideElement fillingsButton = $x("//span[contains(text(), 'Начинки')]/..");
    private final SelenideElement bunsHeaderInMenu = $(byTagAndText("h2", "Булки"));
    private final SelenideElement saucesHeaderInMenu = $(byTagAndText("h2", "Соусы"));
    private final SelenideElement fillingsHeaderInMenu = $(byTagAndText("h2", "Начинки"));


    @Step("Клик по кнопке войти на главной странице для перехода на страницу входа")
    public SignInPage clickOnSignInMainButton(){
        signInMainButton.click();

        return new SignInPage();
    }


    public boolean isOrderButtonDisplayed(){
        createOrderButton.shouldBe(visible);

        return createOrderButton.isDisplayed();
    }


    @Step("Клик по кнопке перехода в личный кабинет из неавторизованной зоны")
    public SignInPage goToAccountProfilePageWhenUnauthorized(){
        toAccountProfileButton.click();

        return new SignInPage();
    }


    @Step("Клик по кнопке перехода в личный кабинет из авторизованной зоны")
    public AccountProfilePage goToAccountProfilePageWhenAuthorized(){
        toAccountProfileButton.click();

        return new AccountProfilePage();
    }


    @Step("Клик по табу Соусы")
    public MainPage selectSaucesTab(){
        saucesButton.click();

        return this;
    }


    @Step("Клик по табу Булки")
    public MainPage selectBunsTab(){
        bunsButton.click();

        return this;
    }


    @Step("Клик по табу Начинки")
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


    public boolean isScrolledToBunsHeaderInMenu(){
        return isScrolledToElement(bunsHeaderInMenu);
    }

    public boolean isScrolledToSaucesHeaderInMenu(){
        return isScrolledToElement(saucesHeaderInMenu);
    }

    public boolean isScrolledToFillingsHeaderInMenu(){
        return isScrolledToElement(fillingsHeaderInMenu);
    }


    /**
     * Метод проверяет скролл в окне меню до нужного раздела (булки, соусы, начинки).
     * В методе использована фиксированная задержка с 1 секунду, так как необходимо дождаться,
     * пока отработает js-скрипт скролла при нажатии на какой-либо таб меню. Не получилось придумать
     * как создать кастомный ExpectedCondition с js-скриптом.
     * @param targetElement элемент, который проверяется, находится ли он под табом "Булочки". Если верх данного элемента
     *                      находится в в пределах -5 -- 30 пикселей от низа таба "Булки" по вертикали,
     *                      считаем что скролл отработал
     * @return возвращает boolean значение находится ли искомый элемент прямо под табом "Булки", если true, значит верно
     *          прошел скролл до элемента, на который планировалось проскроллить окно меню
     */
    @Step("Проверка находится ли нужный элемент под табами (произошел ли скролл до нужного места)")
    private boolean isScrolledToElement(SelenideElement targetElement){
        Selenide.sleep(1000);//жесткая задержка для ожидания пока выполнится js-скрипт скролла окна меню

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