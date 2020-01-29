package co.uk.gel.proj.util;

import co.uk.gel.proj.config.AppConfig;
import com.github.javafaker.Faker;
import com.google.common.base.Splitter;
import io.cucumber.java.hu.De;
import sun.security.ssl.Debug;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TestUtils {

    public static final String PREFIX = "UItest";

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
    public static String[] getPatientSplitNames(String fullname) {//LastName,FirstName (TITLE)
        String[] names = new String[3];
        try{
            String lastName = fullname.substring(0,fullname.indexOf(","));
            String firstName = fullname.substring(fullname.indexOf(",")+1,fullname.indexOf("("));
            String title = fullname.substring(fullname.indexOf("(")+1,fullname.indexOf(")"));
            Debugger.println("First Name: "+firstName+",LastName: "+lastName+", Title: "+title);
            names[0] = firstName.trim();
            names[1] = lastName.trim();
            names[2] = title.trim();
        }catch(Exception exp){
            Debugger.println("Ëxception in Splitting Patient Full name: "+exp);
            names[0] = "";
            names[1] = "";
            names[2] = "";
        }
        return names;
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
          String referralFullUrl = AppConfig.getPropertyValueFromPropertyFile(baseURL) + "referral/" + referralID + "/" + confirmationPage;
          Debugger.println("referralFullUrl :" + referralFullUrl);
         return referralFullUrl;
        }
        //Added new method to get Current day in String array (used in file upload section for patient choice)
        public static String[] getCurrentDay(){
            Calendar today = Calendar.getInstance();
            String year = "";
            String month = "";
            String day = "";
            int iyear = today.get(Calendar.YEAR);
            int imonth = today.get(Calendar.MONTH) + 1;
            int iday = today.get(Calendar.DATE);

            if (imonth < 10) {
                month = "0" + imonth;
            } else {
                month = "" + imonth;
            }
            year = "" + iyear;
            if (iday < 10) {
                day = "0" + iday;
            } else {
                day = "" + iday;
            }
            return new String[]{day,month,year};
        }

    public static String getAgeInYearsAndMonth(String dob){
        try {
            //Debugger.println("Date of Birth: "+dob);
            String[] dobs= dob.split("-");
            LocalDate dob_date = LocalDate.of(Integer.parseInt(dobs[2]),Integer.parseInt(dobs[1]),Integer.parseInt(dobs[0]));
            LocalDate today = LocalDate.of(Integer.parseInt(getCurrentDay()[2]),Integer.parseInt(getCurrentDay()[1]),Integer.parseInt(getCurrentDay()[0]));
            Period diff = Period.between(dob_date,today);
            //Debugger.println("Age in Years and months: "+diff.getYears()+",months:"+diff.getMonths());
            if(diff.getMonths() > 1) {
                return "(" + diff.getYears() + "y " + diff.getMonths() + "m)";
            }else{
                return "(" + diff.getYears() + "y)";
            }
        }catch(Exception exp){
            Debugger.println("Exception from finding Age in Years: "+exp);
            return null;
        }
    }

    // NTS-4483 - prefix firstname and lastname with Uitest
    public static String getRandomFirstName(){
        String someName = new Faker().name().firstName();
        return PREFIX + someName;
    }

    public static String getRandomLastName(){
        String someName = new Faker().name().lastName();
        return PREFIX + someName;
    }
}
