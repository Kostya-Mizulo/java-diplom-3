package tests;

import helpers.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import page_object.MainPage;

public class MainPageTest extends BaseTest {
    User user;
    @Before
    public void createTestUser(){
        user = User.createTestUser();
        addUsersToDeleteAtTearDown(user);
    }

    @Test
    public void redirectFromAccountProfileToMainPagePageViaConstructorButtonTest(){
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user)
                .goToAccountProfilePageWhenAuthorized()
                .goToMainPageViaConstructorButton();

        Assert.assertTrue(new MainPage().isCreateBurgerTextElementDisplayed());
    }

    @Test
    public void redirectFromAccountProfileToMainPageViaStellarBurgerLogoTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user)
                .goToAccountProfilePageWhenAuthorized()
                .goToMainPageViaStellarBurgerLogo();

        Assert.assertTrue(new MainPage().isCreateBurgerTextElementDisplayed());
    }
}
