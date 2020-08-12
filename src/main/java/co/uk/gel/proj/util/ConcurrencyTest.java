package co.uk.gel.proj.util;

import java.io.*;
import java.util.Properties;

public class ConcurrencyTest {

    public static String referral_id;
    public static String referral_base_url;
    static String concurrencyPropertyFile = "Concurrency.properties";
    static String concurrencyController = "ConcurrencyController.txt";

    public static void writeToPropertyFile (String dataToWrite) {
        try {
            FileWriter file = new FileWriter(concurrencyPropertyFile, true);
            file.write(dataToWrite);
            file.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void loadConcurrencyProps() {
        //String configFileName = "%s-appconfig.properties";
        // configFileName = String.format(configFileName, current_environment);
        String configFileName = concurrencyPropertyFile;
        File concFile = new File(configFileName);
        if(!concFile.exists()){
            Debugger.println("Concurrency File Not exists....");
            return;
        }
        Debugger.println("Concurrency File EXISTS..");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(configFileName));
            referral_base_url = properties.getProperty("BASE_URL");
        } catch (Exception exp) {
           Debugger.println("Exception in loading props file: "+exp);
        }
    }

    public static void setReferral_id(String ref_id) {
        referral_id = ref_id;
    }

    public static void setReferral_base_url(String base_url) {
       referral_base_url = base_url;
    }

    public static String getReferral_id() {
        return referral_id;
    }

    public static String getReferral_base_url() {
       if(referral_base_url == null){
           loadConcurrencyProps();
       }
        return referral_base_url;
    }
}//end
