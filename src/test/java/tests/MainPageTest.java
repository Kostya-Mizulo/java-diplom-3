package tests;

import com.codeborne.selenide.WebDriverRunner;
import helpers.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import page_object.MainPage;


/**
 * Тесты класса проверяют переход на главную страницу сайта
 */
public class MainPageTest extends BaseTest {
    User user;


    @Before
    @Step("Создание пользователя для теста и добавление в список для удаления после теста")
    public void createTestUser(){
        user = User.createTestUser();
        addUsersToDeleteAtTearDown(user);
    }


    @Test
    @DisplayName("Переход на главную страницу по кнопке Конструктор")
    public void redirectFromAccountProfileToMainPagePageViaConstructorButtonTest(){
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user)
                .goToAccountProfilePageWhenAuthorized()
                .goToMainPageViaConstructorButton();

        Assert.assertEquals(MainPage.MAIN_PAGE_URL, WebDriverRunner.url());
    }


    @Test
    @DisplayName("Переход на главную страницу по клику на лого сайта")
    public void redirectFromAccountProfileToMainPageViaStellarBurgerLogoTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user)
                .goToAccountProfilePageWhenAuthorized()
                .goToMainPageViaStellarBurgerLogo();

        Assert.assertEquals(MainPage.MAIN_PAGE_URL, WebDriverRunner.url());
    }
}