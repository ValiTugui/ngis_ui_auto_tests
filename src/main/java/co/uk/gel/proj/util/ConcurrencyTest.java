package co.uk.gel.proj.util;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class ConcurrencyTest {

    public static String referral_id;
    public static String referral_base_url;
    static String concurrencyController = "ConcurrencyController.txt";

    public static String getReferral_base_url() {
       if(referral_base_url == null){
           checkReferralId();
           if(referral_id != null && !referral_id.isEmpty()){
               referral_base_url = "https://test-ordering.e2e-latest.ngis.io/test-order/referral/"+referral_id;
           }
       }
        return referral_base_url;
    }
    public static void checkReferralId () {
        try {
            File file = new File(concurrencyController);
            Scanner scanner = new Scanner(file);
            String line = "";
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                if(line.startsWith("ReferralId=")){
                    referral_id = line.replaceAll("ReferralId=","");
                    break;
                }
            }
        }catch (Exception exp) {
            Debugger.println("Exception in getReferralId:"+exp);
        }
    }
    public static boolean verifyTextPresence (String dataToVerify) {
        try {
            boolean isPresent = false;
            File file = new File(concurrencyController);
            Scanner scanner = new Scanner(file);
            String line = "";
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                if(line!= null && line.equalsIgnoreCase(dataToVerify)){
                    isPresent = true;
                    break;
                }
            }
            return isPresent;
        }catch (Exception exp) {
            Debugger.println("Exception in verifyTextPresence:"+exp);
            return false;
        }
    }
    public static boolean writeToControllerFile (String dataToWrite) {
        try {
            FileWriter file = new FileWriter(concurrencyController, true);
            file.write(dataToWrite);
            file.write("\n");
            file.close();
            return true;
        }catch (Exception exp) {
           Debugger.println("Exception in writing ConcurrencyData:"+exp);
           return false;
        }
    }
}//end
