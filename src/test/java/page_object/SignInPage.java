package page_object;

import com.codeborne.selenide.SelenideElement;
import helpers.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


/**
 * Класс страницы https://stellarburgers.nomoreparties.site/login
 */
public class SignInPage {
    public static final String SING_IN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    private SelenideElement emailInputField = $x("//label[contains(text(), 'Email')]/../input");
    private SelenideElement passwordInputField = $x("//label[contains(text(), 'Пароль')]/../input");
    private SelenideElement toRegistrationPageButton = $(byLinkText("Зарегистрироваться"));
    private SelenideElement signInButton = $(byText("Войти"));
    private SelenideElement resetPasswordButton = $(byLinkText("Восстановить пароль"));


    @Step("Переход на страницу регистрации со страницы входа")
    public RegistrationPage clickButtonToRedirectOnRegistrationPage(){
        toRegistrationPageButton.click();

        return new RegistrationPage();
    }


    public String getTextFromEmailField(){
        return emailInputField.getValue();
    }


    @Step("Заполнение полей и вход на странице входа")
    public MainPage signIn(User user) {
        emailInputField.setValue(user.getEmail());
        passwordInputField.setValue(user.getPassword());
        signInButton.click();

        return new MainPage();
    }


    @Step("Переход на страницу сброса пароля со страницы входа")
    public ResetPasswordPage goToResetPasswordPage(){
        resetPasswordButton.click();

        return new ResetPasswordPage();
    }


    public boolean isSignInButtonDisplayed(){
        return signInButton.isDisplayed();
    }
}