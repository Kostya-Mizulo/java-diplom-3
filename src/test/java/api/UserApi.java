package api;

import helpers.User;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {

    private final static String USER_REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/api/auth/register";
    private final static String USER_LOGIN_URL = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private final static String USER_DELETE_URL = "https://stellarburgers.nomoreparties.site/api/auth/user";

    @Step("Send POST request to register user")
    public Response registerUser(User user){
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .and()
                .body(user)
                .when()
                .post(USER_REGISTRATION_URL);

        return response;
    }

    @Step("Send request to login sign-in")
    public Response loginUser(User user){
        user.setName(null);
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .and()
                .body(user)
                .when()
                .post(USER_LOGIN_URL);

        return response;
    }

    @Step("Send request to delete user")
    public Response deleteUser(String token){
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .and()
                .header("Authorization", token)
                .when()
                .delete(USER_DELETE_URL);
        System.out.println(response.body().asString());

        return response;
    }
}