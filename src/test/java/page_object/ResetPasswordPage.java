package page_object;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;


/**
 * Класс страницы https://stellarburgers.nomoreparties.site/forgot-password
 */
public class ResetPasswordPage {
    private final SelenideElement signInButton = $(byLinkText("Войти"));


    @Step("Переход на страницу входа со страницы сброса пароля")
    public SignInPage goToSignInPageFromResetPasswordPage(){
        signInButton.click();
        return new SignInPage();
    }
}
