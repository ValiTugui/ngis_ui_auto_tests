package co.uk.gel.proj.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestUtils {

    public static String dateFormatReverserToYYYYMMDD(String dateInDDMMYYY){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateInYYYYMMDD = LocalDate.parse(dateInDDMMYYY.trim(), formatter).format(formatter2);
        System.out.println(dateInYYYYMMDD);
        return dateInYYYYMMDD;
    }


    public static HashMap<String,String> splitAndGetParams(String combinedInput)
    {
        HashMap<String,String> paramNameValue = new HashMap<>();
        String[] allParams = combinedInput.split(":");
        for(String param : allParams)
        {
            paramNameValue.put(param.split("=")[0],param.split("=")[1]);
        }

        // To print key and value
        Set<Map.Entry<String,String>> val = paramNameValue.entrySet();
        for(Map.Entry m : val)
        {
            System.out.println("Key is :"+ m.getKey() +" and value is :"+ m.getValue());
        }
        return paramNameValue;

    }

}
