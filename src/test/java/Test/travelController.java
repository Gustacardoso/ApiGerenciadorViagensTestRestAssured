package Test;

import Dados.Data;
import Utils.BaseApi;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class travelController extends BaseApi {

    String tokenAdmin = new authenticationController().authenticationControllerAdmin();
    String tokenUser = new authenticationController().authenticationControllerUser();
    Data dadaTravel = new Data();
    Map dadosViagem = dadaTravel.dadaTravel();

    @Test
    public void postViagens() {

        given()
                .header("Authorization", tokenAdmin)
                .body(dadosViagem)
                .contentType(ContentType.JSON)
                .when()
                .post("v1/viagens")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void getViagens(){
        given()
                .log().all()
                .contentType("application/json")
                .header("Authorization", tokenUser)
                .when()
                .get("v1/viagens")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void putViagensId() {
                 given()
                    .contentType("application/json")
                .header("Authorization",tokenAdmin)
                    .body(dadosViagem)
                .when()
                    .put("/v1/viagens/"+ dadaTravel.returnId())
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
                 validarUpdateName();

    }

    @Test
    public void deleteViagensId(){
        given()
                .log().all()
                    .contentType("application/json")
                .header("Authorization", tokenAdmin)
                    .when()
                .delete("/v1/viagens/"+dadaTravel.returnId())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        validarViagensIdDeletado();

    }

    @Test
     public  void validarViagensIdDeletado(){
               given()
                .log().all()
                .contentType("application/json")
                .header("Authorization", tokenUser)
                .when()
                .get("v1/viagens")
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
               .body(is(notNullValue()));

    }
    @Test
    public  void validarUpdateName(){
        given()
                .log().all()
                .contentType("application/json")
                .header("Authorization", tokenUser)
                .when()
                .get("v1/viagens")
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                ;

    }

}
