package tests;

import helpers.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import page_object.MainPage;
import page_object.RegistrationPage;
import page_object.SignInPage;


/**
 * Тесты класса проверяют функционал регистрации нового пользователя
 */
public class RegistrationTest extends BaseTest {
    @Test
    @DisplayName("Проверка регистрации пользователя")
    public void checkUserCouldRegistrateOnWebSiteWithValidDataTest(){
        User user = User.generateRandomUserData(true);

        new MainPage()
                .clickOnSignInMainButton()
                .clickButtonToRedirectOnRegistrationPage()
                .registerUser(user.getName(),
                        user.getEmail(),
                        user.getPassword()
                );

        addUsersToDeleteAtTearDown(user);

        Assert.assertEquals(user.getEmail(), new SignInPage().getTextFromEmailField());
    }


    @Test
    @DisplayName("Проверка отображения ошибки если введен невалидный пароль на странице регистрации")
    public void checkIfPasswordIncorrectErrorTextDisplayedDuringRegistrationTest(){
        User user = User.generateRandomUserData(false);

        new MainPage()
                .clickOnSignInMainButton()
                .clickButtonToRedirectOnRegistrationPage()
                .registerUser(user.getName(),
                        user.getEmail(),
                        user.getPassword()
                );

        Assert.assertTrue(new RegistrationPage().isPasswordInputErrorTextDisplayed());
    }
}