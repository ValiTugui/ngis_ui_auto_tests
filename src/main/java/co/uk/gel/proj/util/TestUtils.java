package co.uk.gel.proj.util;

import co.uk.gel.proj.config.AppConfig;
import com.google.common.base.Splitter;
import io.cucumber.java.hu.De;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TestUtils {

    static String defaultDownloadLocation = System.getProperty("user.dir") + File.separator +"downloads"+File.separator;

    public static String dateFormatReverserToYYYYMMDD(String dateInDDMMYYY) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateInYYYYMMDD = LocalDate.parse(dateInDDMMYYY.trim(), formatter).format(formatter2);
        return dateInYYYYMMDD;
    }


    public static HashMap<String, String> splitAndGetParams(String combinedInput) {
        HashMap<String, String> paramNameValue = new HashMap<>();
        String[] allParams = combinedInput.split(":");
        for (String param : allParams) {
            paramNameValue.put(param.split("=")[0], param.split("=")[1]);
        }

        // To print key and value
//        Set<Map.Entry<String, String>> allEntries = paramNameValue.entrySet();
//        for (Map.Entry eachEntry : allEntries) {
//            System.out.println("Key is :" + eachEntry.getKey() + " and value is :" + eachEntry.getValue());
//        }

        return paramNameValue;

    }

    public static String todayInDDMMYYYFormat() {
        SimpleDateFormat expectedFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = expectedFormat.format(new Date());
        return date;
    }

    public static String removeAWord(String sentence, String word) {
        String str = sentence;
        if (sentence.contains(word)) {
            str = sentence.replaceAll(word, "");
        }
        return str;
    }

    public static Map<String, String> splitStringIntoKeyValuePairs(String input) {
        Map<String, String> resultantStr = Splitter.on(':')
                .trimResults()
                .withKeyValueSeparator(
                        Splitter.on('=')
                                .limit(2)
                                .trimResults())
                .split(input);
        return resultantStr;
    }

    //Change month number to equivalent month form e.g 01 = Jan
    public static String convertMonthNumberToMonthForm(String monthBirthNumber) {
        String monthForm = null;
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        if (Integer.parseInt(monthBirthNumber) - 1 > 11 || Integer.parseInt(monthBirthNumber) - 1 < 0 || monthBirthNumber.isEmpty() || monthBirthNumber == null) {
            monthForm = "invalid number";
        } else {
            monthForm = months[Integer.parseInt(monthBirthNumber) - 1];
        }
        return monthForm;
    }

    public static ArrayList<String> convertDOBNumbersToStrings(String dobInNumbers) {
        int strLength = dobInNumbers.length();
        String yearOfBirth = dobInNumbers.substring(0, 4);
        String monthOfBirth = dobInNumbers.substring(4, 6);
        String dayOfBirth = dobInNumbers.substring(6, strLength);

        ArrayList<String> dobInfoAsList = new ArrayList<String>();
        dobInfoAsList.add(0, dayOfBirth);
        dobInfoAsList.add(1, monthOfBirth);
        dobInfoAsList.add(2, yearOfBirth);

        return dobInfoAsList;
    }
    public static void deleteIfFilePresent(String fileName,String downloadLocation){
        if(downloadLocation == null || downloadLocation.isEmpty()){
            downloadLocation = defaultDownloadLocation;
        }
        File location = new File(downloadLocation);
        if(location == null){
            return;
        }
        File[] files = location.listFiles();
        if(files == null || files.length <1){
            return;
        }
        for(int i=0; i<files.length; i++){
            if(files[i].getName().startsWith(fileName)){
                Debugger.println("File:"+files[i].getName()+" deleted.");
                files[i].delete();
                break;
            }
        }
    }
    public static String getDOBInMonthFormat(String dob) {//as dd-mm-yyyy
        try {
            String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String[] dobs = dob.split("-");
            return dobs[0] + "-" + months[(Integer.parseInt(dobs[1]) - 1)] + "-" + dobs[2];
        }catch(Exception exp){
            Debugger.println("Exception DOB IN MONTH format: "+dob);
            return dob;
        }
    }
    public static String getNHSDisplayFormat(String nhs) {//as dd-mm-yyyy
        try {//Assume length of NHS is 10 digit
             return nhs.substring(0,3)+ " " + nhs.substring(3,6) + " " + nhs.substring(6,10);
        }catch(Exception exp){
            Debugger.println("Exception getNHSInSplitFormat: "+nhs);
            return nhs;
        }
    }

    public static String insertWhiteSpaceAfterEveryNthCharacter(String textToBeModified, String position){
        String in = textToBeModified;
        String val = position;
        String result = in.replaceAll("(.{" + val + "})", "$1 ").trim();
        return result;
    }

      public static String getReferralURL(String baseURL, String referralID, String confirmationPage){
          Debugger.println("existingReferralID " + referralID);
          String referralFullUrl = AppConfig.getPropertyValueFromPropertyFile(baseURL) + "/referral/" + referralID + "/" + confirmationPage;
          Debugger.println("referralFullUrl :" + referralFullUrl);
         return referralFullUrl;
        }

}
