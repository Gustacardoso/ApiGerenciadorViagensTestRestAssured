package Utils;


import io.restassured.RestAssured;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.*;

public class BaseApi {

    @BeforeClass
    public static void preCondition(){
        baseURI = "http://localhost:8089";
        basePath = "/api";
    }
}
