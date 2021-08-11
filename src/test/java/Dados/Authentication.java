package Dados;

import java.util.HashMap;
import java.util.Map;

public class Authentication {

    public Map DadosAuthenticationAdmin(){
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("email", "admin@email.com");
        params.put("senha", "654321");

        return params;
    }

    public Map DadosAuthenticationUser(){
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("email", "usuario@email.com");
        params.put("senha", "123456");

        return params;
    }
}
