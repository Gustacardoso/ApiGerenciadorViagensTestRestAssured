package Dados;

import Test.authenticationController;
import Utils.DataGenerator;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Data {
    String tokenAdmin = new authenticationController().authenticationControllerAdmin();
    DataGenerator dataGenerator = new DataGenerator();
    Faker faker = new Faker(new Locale("pt-BR"));
    public Map dadaTravel() {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("acompanhante", faker.name().fullName());
        params.put("dataPartida", dataGenerator.geratorDateMatch());
        params.put("dataRetorno", dataGenerator.geratorDateReturn());
        params.put("localDeDestino", faker.address().cityName());
        params.put("regiao", dataGenerator.regions());

     return params;
    }

    public String returnId(){
        Integer id =
                given()
                        .header("Authorization", tokenAdmin)
                        .body(dadaTravel())
                        .contentType(ContentType.JSON)
                        .when()
                        .post("v1/viagens")
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract().path("data.id");


        return id.toString();
    }
}
