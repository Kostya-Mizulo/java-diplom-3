package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import helpers.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_object.MainPage;
import page_object.AccountProfilePage;
import page_object.SignInPage;


/**
 * Тесты класса проверяют нажатие на кнопку "Личный кабинет"
 */
public class AccountProfileTest extends BaseTest {
    private User user;


    @Before
    @Step("Создание пользователя для теста и добавление в список для удаления после теста")
    public void createTestUser(){
        user = User.createTestUser();
        addUsersToDeleteAtTearDown(user);
    }


    @Test
    @DisplayName("Происходит редирект на авторизацию при переходе в личный кабинет если не авторизован")
    public void ifUnauthorizedWhenGoToAccountProfileRedirectsToSignInPageTest(){
        Assert.assertTrue(
                new MainPage()
                .goToAccountProfilePageWhenUnauthorized()
                .isSignInButtonDisplayed()
        );
    }


    @Test
    @DisplayName("Происходит переход в личный кабинет если авторизован при нажатии на личный кабинет")
    public void ifAuthorizedClickOnAccountProfileButtonGoesToAccountProfilePageTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user)
                .goToAccountProfilePageWhenAuthorized();

        Selenide.Wait().until(ExpectedConditions.urlToBe(AccountProfilePage.ACCOUNT_PROFILE_PAGE_URL));

        Assert.assertEquals(AccountProfilePage.ACCOUNT_PROFILE_PAGE_URL, WebDriverRunner.url());
    }


    @Test
    @DisplayName("По кнопке выйти клиент выходит из системы")
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