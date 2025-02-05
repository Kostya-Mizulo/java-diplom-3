package api;

import helpers.User;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Класс предоставляет функционал для взаимодействия с апи сайта.
 * Необходим для подготовки тестовых данных и очистки после тестов.
 * Данный класс используется для создания тестовых клиентов и их удаления
 */
public class UserApi {
    private final static String USER_REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/api/auth/register";
    private final static String USER_LOGIN_URL = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private final static String USER_DELETE_URL = "https://stellarburgers.nomoreparties.site/api/auth/user";


    /**
     * Метод служит для отправки запроса к апи сайта для регистрации тестового пользователя
     * @param user это объект с полями, который посредством restassured сериализуется в json для отправки в теле запроса
     * @return возвращает http ответ
     */
    @Step("Отправка запроса к апи сайта для регистрации пользователя")
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


    /**
     * Метод служит для отправки запроса к апи сайта для авторизации пользователя. Необходимо для извлечения accessToken,
     * который используется при удалении тестового пользователя
     * @param user это объект с полями, который посредством restassured сериализуется в json для отправки в теле запроса
     * @return возвращает http ответ
     */
    @Step("Отправка запроса к апи сайта для входа в систему")
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


    /**
     * Метод служит для отправки запроса к апи сайта для удаления пользователя.
     * @param token строка с токеном пользователя. Отправляется в запросе для удаления тестового пользователя
     * @return возвращает http ответ
     */
    @Step("Отправка запроса к апи сайта для удаления пользователя")
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