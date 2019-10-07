package co.uk.gel.proj.util;

import com.google.common.base.Splitter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class TestUtils {

    public static String dateFormatReverserToYYYYMMDD(String dateInDDMMYYY){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateInYYYYMMDD = LocalDate.parse(dateInDDMMYYY.trim(), formatter).format(formatter2);
        return dateInYYYYMMDD;
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

    public static Map<String , String > splitStringIntoKeyValuePairs(String input){
        Map<String, String> resultantStr = Splitter.on(':')
                .trimResults()
                .withKeyValueSeparator(
                        Splitter.on('=')
                                .limit(2)
                                .trimResults())
                .split(input);
        return resultantStr;
    }
}
