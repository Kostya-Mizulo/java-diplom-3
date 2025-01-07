package tests;

import helpers.User;
import org.junit.Assert;
import org.junit.Test;
import page_object.MainPage;
import page_object.RegistrationPage;
import page_object.SignInPage;

public class RegistrationTest extends BaseTest {
    @Test
    public void checkUserCouldRegistrateOnWebSiteWithValidDataTest(){
        User user = User.generateRandomUserData(true);

        new MainPage()
                .clickOnSignInMainButton()
                .clickButtonToRedirectOnRegistrationPage()
                .registerUser(user.getName(), user.getEmail(), user.getPassword());

        Assert.assertEquals(user.getEmail(), new SignInPage().getTextFromEmailField());
        addUsersToDeleteAtTearDown(user);
    }

    @Test
    public void checkIfPasswordIncorrectErrorTextDisplayedDuringRegistrationTest(){
        User user = User.generateRandomUserData(false);

        new MainPage()
                .clickOnSignInMainButton()
                .clickButtonToRedirectOnRegistrationPage()
                .registerUser(user.getName(), user.getEmail(), user.getPassword());

        Assert.assertTrue(new RegistrationPage().isPasswordInputErrorTextDisplayed());
    }
}
