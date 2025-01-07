package page_object;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;

public class ResetPasswordPage {
    private final SelenideElement signInButton = $(byLinkText("Войти"));

    public SignInPage goToSignInPageFromResetPasswordPage(){
        signInButton.click();
        return new SignInPage();
    }
}
