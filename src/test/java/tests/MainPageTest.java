package tests;

import com.codeborne.selenide.As;
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

    @Test
    public void checkBunsTabOpenedInFirstPlaceTest(){
        Assert.assertTrue(new MainPage().isBunsTabSelected());
    }

    @Test
    public void checkSaucesTabSelectedIfClickBunsTabTest(){
        new MainPage().selectSaucesTab();
        Assert.assertTrue(new MainPage().isSaucesTabSelected());
    }

    @Test
    public void checkFillingsTabSelectedIfClickBunsTabTest(){
        new MainPage().selectFillingsTab();
        Assert.assertTrue(new MainPage().isFillingsTabSelected());
    }

    @Test
    public void checkScrolledToBunsHeaderIfClickOnBunsTabTest() {
        new MainPage()
                .selectSaucesTab()
                        .selectBunsTab();
        Assert.assertTrue(new MainPage().isBunsHeaderInMenuVisible());
    }

    @Test
    public void checkScrolledToSaucesHeaderIfClickOnSaucesTabTest() {
        new MainPage()
                .selectSaucesTab();
        Assert.assertTrue(new MainPage().isSaucesHeaderInMenuVisible());
    }

    @Test
    public void checkScrolledToFillingsHeaderIfClickOnFillingsTabTest() {
        new MainPage()
                .selectFillingsTab();
        Assert.assertTrue(new MainPage().isFillingsHeaderInMenuVisible());
    }
}