package page_object;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


/**
 * Класс страницы https://stellarburgers.nomoreparties.site/register
 */
public class RegistrationPage {
    private SelenideElement usernameInputField = $x("//label[contains(text(), 'Имя')]/../input");
    private SelenideElement emailInputField = $x("//label[contains(text(), 'Email')]/../input");
    private SelenideElement passwordInputField = $x("//label[contains(text(), 'Пароль')]/../input");
    private SelenideElement registerButton = $(byText("Зарегистрироваться"));
    private SelenideElement passwordInputErrorText = $(byText("Некорректный пароль"));
    private SelenideElement logInButton = $(byLinkText("Войти"));


    @Step("Заполнение полей и нажатие на кнопку регистрации на странице регистрации")
    public void registerUser(String name, String email, String pass){
        usernameInputField.setValue(name);
        emailInputField.setValue(email);
        passwordInputField.setValue(pass);
        registerButton.click();
    }


    public boolean isPasswordInputErrorTextDisplayed(){
        return passwordInputErrorText.isDisplayed();
    }


    @Step("Переход на страницу входа со страницы регистрации")
    public SignInPage goToLogInPageFromRegistrationPage(){
        logInButton.click();

        return new SignInPage();
    }
}