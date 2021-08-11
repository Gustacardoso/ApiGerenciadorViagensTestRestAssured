package Test;

import Dados.Dados;
import Utils.BaseApi;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.net.HttpCookie;
import java.util.Map;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class gerenciadorViagemController extends BaseApi {

    String tokenAdmin = new authenticationController().authenticationControllerAdmin();
    String tokenUser = new authenticationController().authenticationControllerUser();
    Dados dados = new Dados();
    Map dadosViagem = dados.dadosViagem();
    Map dadosViagemUpdate = dados.dadosViagemUpdate();


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
       int id = 2;
                 given()
                    .contentType("application/json")
                .header("Authorization",tokenAdmin)
                    .body(dadosViagemUpdate)
                .when()
                    .put("/v1/viagens/"+id)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
                 validarUpdateName();

    }
    @Test
    public void deleteViagensId(){
        int id = 1;
        given()
                .log().all()
                    .contentType("application/json")
                .header("Authorization", tokenAdmin)
                    .when()
                .delete("/v1/viagens/"+id)
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
        String name = given()
                .log().all()
                .contentType("application/json")
                .header("Authorization", tokenUser)
                .when()
                .get("v1/viagens")
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("data[0].acompanhante");
        Assert.assertThat(name, Matchers.is("Mario"));

    }



}
