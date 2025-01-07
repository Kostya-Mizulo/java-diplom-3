package tests;

import helpers.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import page_object.MainPage;

public class LogInTest extends BaseTest{
    User user;
    @Before
    public void createTestUser(){
        user = User.createTestUser();
        addUsersToDeleteAtTearDown(user);
    }

    @Test
    public void signInWithSignInMainButtonTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user);
        Assert.assertTrue(new MainPage().isOrderButtonDisplayed());
    }

    @Test
    public void signInWithPersonalAccountSignInPathTest() {
        new MainPage()
                .goToAccountProfilePageWhenUnauthorized()
                .signIn(user);

        Assert.assertTrue(new MainPage().isOrderButtonDisplayed());
    }

    @Test
    public void signInWithSignInButtonInRegistrationPageTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .clickButtonToRedirectOnRegistrationPage()
                .goToLogInPageFromRegistrationPage()
                .signIn(user);

        Assert.assertTrue(new MainPage().isOrderButtonDisplayed());
    }

    @Test
    public void signInWithSignInButtonInResetPasswordPageTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .goToResetPasswordPage()
                .goToSignInPageFromResetPasswordPage()
                .signIn(user);

        Assert.assertTrue(new MainPage().isOrderButtonDisplayed());
    }
}
