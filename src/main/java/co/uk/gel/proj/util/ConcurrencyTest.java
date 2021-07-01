package co.uk.gel.proj.util;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ConcurrencyTest {

    public static String referral_id;
    public static String referral_base_url;
    static String fileLocation = System.getProperty("user.dir") + File.separator + "target" + File.separator + "Concurrency"+ File.separator;

    public static String getReferral_base_url(String filePrefix) {
       if(referral_base_url == null){
           checkReferralId(filePrefix);
           if(referral_id != null && !referral_id.isEmpty()){
               referral_base_url = "https://test-ordering.e2e-latest.ngis.io/test-order/referral/"+referral_id;
           }
       }
        return referral_base_url;
    }
    public static void setReferral_id(String referralId,String filePrefix){
        Debugger.println("Setting ReferralID at: "+referralId);
        referral_id = referralId;
         try{
            File dirPath = new File(fileLocation);
            if(!dirPath.exists()){
                dirPath.mkdirs();
            }
            File file = new File(fileLocation+filePrefix+".txt");
            if(!file.exists()){
                file.createNewFile();
                Debugger.println("File: "+fileLocation+filePrefix+".txt created.");
            }
        }catch(Exception exp){

        }
    }
    public static void checkReferralId (String filePrefix) {
        try {
            Debugger.println("Checking ReferralId IN:"+fileLocation+filePrefix+".txt");
            File file = new File(fileLocation+filePrefix+".txt");
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
            Debugger.println("Exception in getReferralId:Yet to be created.");
        }
    }
    public static boolean verifyTextPresence (String dataToVerify,String filePrefix) {
        try {
            boolean isPresent = false;
            File file = new File(fileLocation+filePrefix+".txt");
            if (!file.exists()) {
                return false;
            }
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
    public static boolean writeToControllerFile (String filePrefix,String dataToWrite) {
        try {
            FileWriter file = new FileWriter(fileLocation+filePrefix+".txt", true);
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
