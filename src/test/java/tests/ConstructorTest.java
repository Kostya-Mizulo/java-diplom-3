package tests;

import helpers.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import page_object.MainPage;


/**
 * Тесты класса проверяют работу окна меню
 */
public class ConstructorTest extends BaseTest {
    User user;


    @Before
    @Step("Создание пользователя для теста и добавление в список для удаления после теста")
    public void createTestUser(){
        user = User.createTestUser();
        addUsersToDeleteAtTearDown(user);
    }


    @Test
    @DisplayName("Таб булки выбран по умолчанию")
    public void checkBunsTabOpenedInFirstPlaceTest(){
        Assert.assertTrue(new MainPage().isBunsTabSelected());
    }


    @Test
    @DisplayName("Проверка переключения активного таба на соусы")
    public void checkSaucesTabSelectedIfClickBunsTabTest(){
        new MainPage()
                .selectSaucesTab();

        Assert.assertTrue(new MainPage().isSaucesTabSelected());
    }


    @Test
    @DisplayName("Проверка переключения активного таба на начинки")
    public void checkFillingsTabSelectedIfClickBunsTabTest(){
        new MainPage()
                .selectFillingsTab();

        Assert.assertTrue(new MainPage().isFillingsTabSelected());
    }


    @Test
    @DisplayName("Проверка скролла до булок в меню")
    public void checkScrolledToBunsHeaderIfClickOnBunsTabTest() {
        new MainPage()
                .selectSaucesTab()
                .selectBunsTab();

        Assert.assertTrue(new MainPage().isScrolledToBunsHeaderInMenu());
    }


    @Test
    @DisplayName("Проверка скролла до соусов в меню")
    public void checkScrolledToSaucesHeaderIfClickOnSaucesTabTest() {
        new MainPage()
                .selectSaucesTab();

        Assert.assertTrue(new MainPage().isScrolledToSaucesHeaderInMenu());
    }


    @Test
    @DisplayName("Проверка скролла до начинок в меню")
    public void checkScrolledToFillingsHeaderIfClickOnFillingsTabTest() {
        new MainPage()
                .selectFillingsTab();

        Assert.assertTrue(new MainPage().isScrolledToFillingsHeaderInMenu());
    }
}