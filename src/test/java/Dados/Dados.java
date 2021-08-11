package Dados;

import com.github.javafaker.Faker;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import org.apache.commons.codec.language.bm.Rule;

import java.util.*;
import java.util.Map;
import java.util.HashMap;
import static javafx.scene.input.KeyCode.Y;

public class Dados {

    public Map dadosViagem() {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("acompanhante", "gustavo");
        params.put("dataPartida", "2020-12-02");
        params.put("dataRetorno", "2020-12-02");
        params.put("localDeDestino", "porto alegre");
        params.put("regiao", "sul");

     return params;
    }

    public Map dadosViagemUpdate() {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("acompanhante", "Mario");
        params.put("dataPartida", "2020-12-23");
        params.put("dataRetorno", "2020-12-12");
        params.put("localDeDestino", "Torres");
        params.put("regiao", "Norte");

        return params;
    }
}
