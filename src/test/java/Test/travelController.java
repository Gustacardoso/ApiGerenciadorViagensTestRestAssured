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
    public void postTravel() {

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
    public void getTravel(){
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
    public void getTravelId(){
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
    public void putTravelId() {
             given()
                    .contentType("application/json")
                .header("Authorization",tokenAdmin)
                    .body(dadosViagem)
                .when()
                    .put("v1/viagens/1")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_NO_CONTENT)
                     .body(Matchers.notNullValue());


    }

    @Test
    public void deleteTravelId(){
        given()
                .log().all()
                    .contentType("application/json")
                .header("Authorization", tokenAdmin)
                    .when()
                .delete("/v1/viagens/"+dadaTravel.returnId())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        validateTravelIdDeletado();

    }

    @Test
     public  void validateTravelIdDeletado(){
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
    public  void validateUpdateName(){
        String id = dadaTravel.returnId();
        given()
                .contentType("application/json")
                .header("Authorization",tokenAdmin)
                .body(dadosViagem)
                .when()
                .put("v1/viagens/"+id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        given()
                .log().all()
                .contentType("application/json")
                .header("Authorization", tokenUser)
                .when()
                //get com id nao retorna
                .get("v1/viagens")
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);


    }

}
