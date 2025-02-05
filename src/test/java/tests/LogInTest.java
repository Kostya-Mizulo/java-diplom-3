package tests;

import helpers.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import page_object.MainPage;


/**
 * Тесты класса проверяют авторизацию тестового пользователя в систему
 */
public class LogInTest extends BaseTest{
    User user;


    @Before
    @Step("Создание пользователя для теста и добавление в список для удаления после теста")
    public void createTestUser(){
        user = User.createTestUser();
        addUsersToDeleteAtTearDown(user);
    }


    @Test
    @DisplayName("Проверка входа по главной кнопке входа на стартовой странице")
    public void signInWithSignInMainButtonTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user);

        Assert.assertTrue(new MainPage().isOrderButtonDisplayed());
    }


    @Test
    @DisplayName("Проверка входа при редиректе из личного кабинете когда не авторизован")
    public void signInWithPersonalAccountSignInPathTest() {
        new MainPage()
                .goToAccountProfilePageWhenUnauthorized()
                .signIn(user);

        Assert.assertTrue(new MainPage().isOrderButtonDisplayed());
    }


    @Test
    @DisplayName("Проверка входа по кнопке со странгицы регистрации")
    public void signInWithSignInButtonInRegistrationPageTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .clickButtonToRedirectOnRegistrationPage()
                .goToLogInPageFromRegistrationPage()
                .signIn(user);

        Assert.assertTrue(new MainPage().isOrderButtonDisplayed());
    }


    @Test
    @DisplayName("Проверка входа по кнопке со страницы сброса пароля")
    public void signInWithSignInButtonInResetPasswordPageTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .goToResetPasswordPage()
                .goToSignInPageFromResetPasswordPage()
                .signIn(user);

        Assert.assertTrue(new MainPage().isOrderButtonDisplayed());
    }
}