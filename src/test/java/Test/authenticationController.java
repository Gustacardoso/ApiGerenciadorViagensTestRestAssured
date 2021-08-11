package Test;

import Dados.Authentication;
import Utils.BaseApi;
import org.apache.http.HttpStatus;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class authenticationController extends BaseApi {

    Authentication authentication = new Authentication();
    Map dadosAuthenticationAdmin = authentication.DadosAuthenticationAdmin();
    Map dadosAuthenticationUser = authentication.DadosAuthenticationUser();
    public String authenticationControllerAdmin(){

      String tokenAdmin =
                given()
                    .log().all()
                    .contentType("application/json")
                .body(dadosAuthenticationAdmin)
                .when()
                    .post("/v1/auth")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("data.token");

        return tokenAdmin;
    }

    public String authenticationControllerUser(){

        String tokenUser =
                given()
                    .log().all()
                    .contentType("application/json")
                    .body(dadosAuthenticationUser)
                .when()
                    .post("/v1/auth")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().path("data.token");

        return tokenUser;
    }

}
