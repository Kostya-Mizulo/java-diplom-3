package tests;

import com.codeborne.selenide.Configuration;
import helpers.User;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import page_object.MainPage;
import java.util.ArrayList;

import static browser.Browser.initDriver;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


abstract public class BaseTest {
    private ArrayList<User> usersToDelete = new ArrayList<>();


    /**
     * Метод запускает драйвер, открывает окно браузера, расширает его до нужного размера и задает задержку
     * для появления элементом с условиями на 4 секунды
     */
    @Before
    @Step("Запуск драйвера и настройки браузера и селенида")
    public void setup(){
        initDriver();
        Configuration.timeout = 4000;
        Configuration.browserSize = "1920x1080";
        open(MainPage.MAIN_PAGE_URL);
    }


    /**
     * Метод закрывает запущенный для теста драйвер и запускает удаление тестовых данных
     */
    @After
    @Step("Закрытие браузера и удаление тестовых данных")
    public void tearDown(){
        closeWebDriver();
        User.deleteUsers(usersToDelete);
    }


    @Step("Добавление пользователя в список на удаление")
    public void addUsersToDeleteAtTearDown(User user){
        usersToDelete.add(user);
    }
}