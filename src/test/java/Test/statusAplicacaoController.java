package Test;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class statusAplicacaoController {
    @Test
    public void statusAplicacaoController(){
        given()
                .when()
                    .get("http://localhost:8089/api/v1/status")
                .then()
                    .statusCode(200)
                    .body(is("Aplicação está funcionando corretamente"));
    }
}
