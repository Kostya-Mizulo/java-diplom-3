package helpers;

import api.UserApi;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import tests.BaseTest;

import java.util.ArrayList;

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

    public static User generateRandomUserData(boolean isPassCorrect){
        Faker faker = new Faker();
        User user = new User();
        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        if (isPassCorrect) user.setPassword(faker.internet().password(6, 12));
        if (!isPassCorrect) user.setPassword(faker.internet().password(1, 5));
        return user;
    }

    public static User createTestUser(){
        UserApi userApi = new UserApi();
        User user = generateRandomUserData(true);
        userApi.registerUser(user);

        return user;
    }

    public static void deleteUsers(ArrayList<User> users){
        UserApi userApi = new UserApi();
        for (User user : users){
            Response response = userApi.loginUser(user);
            String token = response.jsonPath().getString("accessToken");
            userApi.deleteUser(token);
        }
    }
}