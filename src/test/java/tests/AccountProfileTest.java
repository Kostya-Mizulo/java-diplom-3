package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import helpers.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_object.MainPage;
import page_object.AccountProfilePage;
import page_object.SignInPage;

public class AccountProfileTest extends BaseTest {
    private User user;
    @Before
    public void createTestUser(){
        user = User.createTestUser();
        addUsersToDeleteAtTearDown(user);
    }

    @Test
    public void ifUnauthorizedWhenGoToAccountProfileRedirectsToSignInPageTest(){
        Assert.assertTrue(new MainPage()
                .goToAccountProfilePageWhenUnauthorized()
                .isSignInButtonDisplayed());
    }

    @Test
    public void ifAuthorizedClickOnAccountProfileButtonGoesToAccountProfilePageTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user)
                .goToAccountProfilePageWhenAuthorized();
        Selenide.Wait().until(ExpectedConditions.urlToBe(AccountProfilePage.ACCOUNT_PROFILE_PAGE_URL));

        Assert.assertEquals(AccountProfilePage.ACCOUNT_PROFILE_PAGE_URL, WebDriverRunner.url());
    }

    @Test
    public void logOutFromSiteTest(){
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user)
                .goToAccountProfilePageWhenAuthorized()
                .pressLogOutButton();

        Selenide.Wait().until(ExpectedConditions.urlToBe(SignInPage.SING_IN_PAGE_URL));
        Assert.assertEquals(SignInPage.SING_IN_PAGE_URL, WebDriverRunner.url());
    }
}