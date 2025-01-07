package tests;

import com.codeborne.selenide.Configuration;
import helpers.User;
import org.junit.After;
import org.junit.Before;
import page_object.MainPage;

import java.util.ArrayList;

import static browser.Browser.initDriver;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

abstract public class BaseTest {
    private ArrayList<User> usersToDelete = new ArrayList<>();
    @Before
    public void setup(){
        initDriver();
        Configuration.timeout = 4000;
        open(MainPage.MAIN_PAGE_URL);
    }

    @After
    public void tearDown(){
        closeWebDriver();
        User.deleteUsers(usersToDelete);
    }

    public void addUsersToDeleteAtTearDown(User user){
        usersToDelete.add(user);
    }
    public void deleteUserFromList(int position){
        usersToDelete.remove(position);
    }
}
