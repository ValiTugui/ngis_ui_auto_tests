package co.uk.gel.proj.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestUtils {

    public static String dateFormatReverserToYYYYMMDD(String dateInDDMMYYY){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateInYYYYMMDD = LocalDate.parse(dateInDDMMYYY.trim(), formatter).format(formatter2);
        System.out.println(dateInYYYYMMDD);
        return dateInYYYYMMDD;
    }
}
