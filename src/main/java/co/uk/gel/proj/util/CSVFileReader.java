package co.uk.gel.proj.util;

import co.uk.gel.proj.config.AppConfig;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVFileReader {

    static String mi_portal_test_data_file_location = System.getProperty("user.dir") + File.separator + "testdata";
    private List<MIPortalTestData> mi_portal_data_set = new ArrayList<MIPortalTestData>();

    public boolean readMIPortalTestData(){
        try{
            File file = new File(mi_portal_test_data_file_location+File.separator+ AppConfig.mi_portal_test_data_file);
            if(!file.exists()){
                Debugger.println("MIPortal TestData File:"+AppConfig.mi_portal_test_data_file+" not exists in "+mi_portal_test_data_file_location);
                return false;
            }
            CSVReader csvReader = new CSVReader(new FileReader(file));
            String[] testData = null;
            Map<String, Integer> map = new HashMap<String,Integer>(); //Create map
            int count = 0;
            while((testData = csvReader.readNext()) != null){
               if(count == 0){//First row expected to be title
                    for(int colIx=0; colIx<testData.length; colIx++) { //loop from first to last index
                        map.put(testData[colIx],colIx); //add the cell contents (name of column) and cell index to the map
                    }
                }else {
                    MIPortalTestData mipData = new MIPortalTestData();
                    mipData.setGlh_name(testData[map.get("GLHName")]);
                    mipData.setGlh_code(testData[map.get("GLHCode")]);
                    mipData.setReferral_id(testData[map.get("ReferralID_HumanReadble")]);
                    mipData.setConsignment_number(testData[map.get("ConsignmentNumber")]);
                    mipData.setOrdering_entity(testData[map.get("OrderingEntity")]);
                    mipData.setPatient_ngsid(testData[map.get("PatientNGISID")]);
                    mipData.setTest_type(testData[map.get("TestType")]);
                    mi_portal_data_set.add(mipData);
                }
                count++;
            }
            Debugger.println("No Of MIPORTAL Data Read:"+mi_portal_data_set.size());
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in reading MIPortalTestData:"+exp);
            return false;
        }
    }
    public MIPortalTestData getRandomTestData(){
        try {
            if (mi_portal_data_set.size() == 0) {
                if (!readMIPortalTestData()) {
                    return null;
                }
            }
            return mi_portal_data_set.get(0);
        }catch(Exception exp){
            Debugger.println("Exception in getting RandomMiPortal Test Data:"+exp);
            return null;
        }

    }

}//end
