package co.uk.gel.models;

import co.uk.gel.proj.util.Debugger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.gel.models.participant.avro.Referral;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class ReferralFromJson {

    Referral refObject;
    static String testDataLocation = System.getProperty("user.dir") + File.separator + "testdata";

    public String verifyFilePresence(String fileName) {
        try {
            File file = new File(testDataLocation+File.separator+fileName);
            if (!file.exists()) {
                return "Json File : " + fileName+" not present in the testdata folder.";
            }
            return "Success";
        } catch (Exception exp) {
            return "Exception from verifyFilePresence:"+exp.getLocalizedMessage();
        }
    }
    public String readReferralDetailsFromJson(String jsonFileName){
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(testDataLocation+File.separator+jsonFileName));
            ObjectMapper objectMapper = new ObjectMapper();
            refObject = objectMapper.readValue(obj.toString(), Referral.class);
            if(refObject == null){
                return "Could not construct Referral Object from the JSON:"+jsonFileName;
            }
            return "Success";
        } catch (Exception exp) {
            Debugger.println("Exception from readReferralDetailsFromJson:"+exp);
            return "Exception from readReferralDetailsFromJson:"+exp.getLocalizedMessage();
        }
    }
    public Referral getReferralObject(){
        return refObject;
    }
}//end
