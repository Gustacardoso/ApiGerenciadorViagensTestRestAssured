package Test;

import Utils.BaseApi;
import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class statusApplicationController extends BaseApi {
    @Test
    public void statusAplicacaoController(){
        given()
                .contentType("application/json")
                .when()
                    .get("/v1/status")
                .then()
                    .statusCode(200)
                    .body(is("Aplicação está funcionando corretamente"));
    }
}
