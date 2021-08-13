package Utils;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    Random random = new Random();
    int day = random.nextInt(28) + 1;
    int month =  random.nextInt(12) + 1;
    String ano = "2021";

    public String regions (){
        String[] listRegions = {"Norte", "Sul", "Leste", "Oeste"};
        return listRegions[random.nextInt(listRegions.length)];
    }

    public String geratorDateMatch(){

        String date = ano+"-"+month+"-"+ day;

        return date;
    }
    public String geratorDateReturn(){
        String date = ano+"-"+month + 1 +"-"+day+1;
        return date;
    }

}
