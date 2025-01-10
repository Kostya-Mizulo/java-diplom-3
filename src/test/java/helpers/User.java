package helpers;

import api.UserApi;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.ArrayList;


/**
 * Класс нужен для restAssured, в запросах сериализуется в json для отправки к апи сайта.
 */
public class User {
    private String email;
    private String password;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Метод генерирует объект "Пользователь" с рандомными именем, почтой, паролем
     * @param isPassCorrect boolean значение, указывает какой пароль должен сгенериться.
     *                      true - валидный для регистрации, false - невалидный
     */
    @Step("Генерация рандомных данных для тестового пользователя")
    public static User generateRandomUserData(boolean isPassCorrect){
        Faker faker = new Faker();
        User user = new User();
        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        if (isPassCorrect) user.setPassword(faker.internet().password(6, 12));
        if (!isPassCorrect) user.setPassword(faker.internet().password(1, 5));

        return user;
    }


    @Step("Создание тестового пользователя")
    public static User createTestUser(){
        UserApi userApi = new UserApi();
        User user = generateRandomUserData(true);
        userApi.registerUser(user);

        return user;
    }


    @Step("Удаление тестового пользователя")
    public static void deleteUsers(ArrayList<User> users){
        UserApi userApi = new UserApi();
        for (User user : users){
            Response response = userApi.loginUser(user);
            String token = response.jsonPath().getString("accessToken");
            userApi.deleteUser(token);
        }
    }
}