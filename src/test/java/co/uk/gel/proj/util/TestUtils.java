package co.uk.gel.proj.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Date;

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
        Set<Map.Entry<String,String>> allEntries = paramNameValue.entrySet();
        for(Map.Entry eachEntry : allEntries)
        {
            System.out.println("Key is :"+ eachEntry.getKey() +" and value is :"+ eachEntry.getValue());
        }

        return paramNameValue;

    }


    public static String todayInDDMMYYYFormat(){
        SimpleDateFormat expectedFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = expectedFormat.format(new Date());
        return date;
    }

    public static String removeAWord(String sentence, String word){
        String str = sentence;
        if (sentence.contains(word)) {
            str = sentence.replaceAll(word, "");
        }
        return str;
    }
}
