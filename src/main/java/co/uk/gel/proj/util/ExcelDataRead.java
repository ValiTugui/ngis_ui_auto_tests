package co.uk.gel.proj.util;

import co.uk.gel.lib.SeleniumLib;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExcelDataRead {
    public static String fileName;
    public static final String EXCELFILELOCATION = System.getProperty("user.dir") + File.separator + "/testdata/";
    private static FileInputStream fis;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    WebDriver driver;
    SeleniumLib seleniumLib;

    public static void loadExcel(String fileName) throws Exception {
        String filePath = EXCELFILELOCATION + fileName;
        File file = new File(filePath);
        fis = new FileInputStream(file);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(0);
        fis.close();
    }

    public ExcelDataRead(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public static Map<String, List<String>> readAllData(String fileName) {
        try {
            if (sheet == null) {
                loadExcel(fileName);
            }
            List<Map<String, String>> mapLists = new ArrayList<>();
            int rows = sheet.getLastRowNum();
            row = sheet.getRow(0);
            Map<String, List<String>> myMap = new HashMap<>();
            for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                Row r = CellUtil.getRow(j, sheet);
                String key = r.getCell(1).getStringCellValue();
                String value = r.getCell(3).getStringCellValue();
                if ((myMap.get(key) != null)) {
                    myMap.get(key).add(value);
                } else {
                    List<String> valueList = new ArrayList<>();
                    valueList.add(value);
                    myMap.put(key, valueList);
                }
            }
            return myMap;
        } catch (Exception exp) {
            Debugger.println("Exception from readAllData:" + exp);
            SeleniumLib.takeAScreenShot("readAllDataNotVerified.jpg");
            return null;
        }
    }

    public static List<String> getValue(String key) {
        Map<String, List<String>> myVal = readAllData(fileName);
        List<String> retValue = myVal.get(key);
        return retValue;
    }

    public static List<String> retrieveData(String glhName) throws Exception {

        List<String> listStr = getValue(glhName);
        listStr.sort(String.CASE_INSENSITIVE_ORDER);
        return listStr;
    }

    public static Map<String, Map<String, String>> readAllDataFromAllSheet(String fileName, String expectedSheetName) {
        try {
            //Read and load the excel file provided
            loadExcel(fileName);
            Map<String, Map<String, String>> myMap = new HashMap<>();
            DataFormatter dataFormatter = new DataFormatter();
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                sheet = workbook.getSheetAt(i);
                row = sheet.getRow(0);
                String sheetName = sheet.getSheetName();
                if (sheetName.equalsIgnoreCase("File Submissions")&&expectedSheetName.equalsIgnoreCase("File Submissions")) {
                    for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                        Row dataRow = CellUtil.getRow(j, sheet);
                        //common key for multiple rows in File submissions
                        String key = dataRow.getCell(6).getStringCellValue().trim();
                        //different value in each row in File submissions
                        Map<String, String> valueMap = new HashMap<>();
                        for (int k = 0; k < dataRow.getLastCellNum(); k++) {
                            if (!(row.getCell(k).getStringCellValue().equalsIgnoreCase("Created"))) {
                                String dataKey = row.getCell(k).getStringCellValue();
                                String value = dataFormatter.formatCellValue(dataRow.getCell(k));
                                valueMap.put(dataKey, value);
                            }
                        }
                        myMap.put(key, valueMap);
                    }
                    Debugger.println("Data in File submissions is " + myMap.toString());
                } else if (sheetName.equalsIgnoreCase("Order Tracking")&&expectedSheetName.equalsIgnoreCase("Order Tracking")) {
                    for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                        Row dataRow = CellUtil.getRow(j, sheet);
                        //common key for multiple rows in Order Tracking
                        String key = dataRow.getCell(1).getStringCellValue().trim();
                        //different value in each row in File submissions
                        Map<String, String> valueMap = new HashMap<>();
                        for (int k = 0; k < dataRow.getLastCellNum(); k++) {
                            if (!(row.getCell(k).getStringCellValue().equalsIgnoreCase("GEL1001 Patient NGIS ID"))) {
                                String dataKey = row.getCell(k).getStringCellValue();
                                String value = dataFormatter.formatCellValue(dataRow.getCell(k));
                                if (row.getCell(k).getStringCellValue().equalsIgnoreCase("Date Request Submitted")) {
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
                                    Date date = simpleDateFormat.parse(value);
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                }
                                valueMap.put(dataKey, value);
                            }
                        }
                        if(myMap.containsKey(key)){
                            if(!myMap.get(key).equals(valueMap)){
                                key=key.concat("-");
                            }
                        }
                        myMap.put(key, valueMap);
                    }
                    Debugger.println("Data in Order Tracking is " + myMap.toString());
                } else if (sheetName.equalsIgnoreCase("GLH Samples")&&expectedSheetName.equalsIgnoreCase("GLH Samples")) {
                    for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                        Row dataRow = CellUtil.getRow(j, sheet);
                        //common key for multiple rows in GLH Samples
                        String key = dataRow.getCell(2).getStringCellValue().trim();
                        //different value in each row in GLH Samples
                        Map<String, String> valueMap = new HashMap<>();
                        for (int k = 0; k < dataRow.getLastCellNum(); k++) {
                            if (!(row.getCell(k).getStringCellValue().equalsIgnoreCase("GEL1001 Patient NGIS ID"))) {
                                String dataKey = row.getCell(k).getStringCellValue();
                                String value = dataFormatter.formatCellValue(dataRow.getCell(k));
                                if (row.getCell(k).getStringCellValue().equalsIgnoreCase("GEL1001 GLH OD 260 280")){
                                    String newValue= value;
                                    Float floatValue= Float.parseFloat(newValue);
                                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                                    decimalFormat.setMaximumFractionDigits(2);
                                    value=decimalFormat.format(floatValue);
                                }
                                valueMap.put(dataKey, value);
                            }
                        }
                        if(myMap.containsKey(key)){
                            if(!myMap.get(key).equals(valueMap)){
                                key=key.concat("-");
                            }
                        }
                        myMap.put(key, valueMap);
                    }
                    Debugger.println("Data in GLH Samples is " + myMap.toString());
                } else if (sheetName.equalsIgnoreCase("Plater Samples")&&expectedSheetName.equalsIgnoreCase("Plater Samples")) {
                    for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                        Row dataRow = CellUtil.getRow(j, sheet);
                        //common key for multiple rows in Plater Samples
                        String key = dataRow.getCell(0).getStringCellValue().trim();
                        //different value in each row in Plater Samples
                        Map<String, String> valueMap = new HashMap<>();
                        for (int k = 1; k < dataRow.getLastCellNum(); k++) {
                            if (!(row.getCell(k).getStringCellValue().equalsIgnoreCase("GEL1001 Patient NGIS ID"))) {
                                String dataKey = row.getCell(k).getStringCellValue();
                                String value = dataFormatter.formatCellValue(dataRow.getCell(k));
                                valueMap.put(dataKey, value);
                            }
                        }
                        if(myMap.containsKey(key)){
                            if(!myMap.get(key).equals(valueMap)){
                                key=key.concat("-");
                            }
                        }
                        myMap.put(key, valueMap);
                    }
                    Debugger.println("Data in Plater Samples is " + myMap.toString());
                } else if (sheetName.equalsIgnoreCase("Picklists")&&expectedSheetName.equalsIgnoreCase("Picklists")) {
                    for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                        Row dataRow = CellUtil.getRow(j, sheet);
                        //common key for multiple rows in Picklists
                        String key = dataFormatter.formatCellValue(dataRow.getCell(1));
                        //different value in each row in Picklists
                        Map<String, String> valueMap = new HashMap<>();
                        for (int k = 0; k < dataRow.getLastCellNum(); k++) {
                            if (!(row.getCell(k).getStringCellValue().equals("GEL1008 Participant ID"))) {
                                String dataKey = row.getCell(k).getStringCellValue();
                                String value = dataFormatter.formatCellValue(dataRow.getCell(k));
                                if (row.getCell(k).getStringCellValue().equalsIgnoreCase("GEL1008 Normalised Biorepository Sample Volume")){
                                    String newValue= value;
                                    Float floatValue= Float.parseFloat(newValue);
                                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                                    decimalFormat.setMaximumFractionDigits(2);
                                    value=decimalFormat.format(floatValue);
                                }
                                valueMap.put(dataKey, value);
                            }
                        }
                        if(myMap.containsKey(key)){
                            if(!myMap.get(key).equals(valueMap)){
                                key=key.concat("-");
                            }
                        }
                        myMap.put(key, valueMap);
                    }
                    Debugger.println("Data in Picklists is " + myMap.toString());
                } else if (sheetName.equalsIgnoreCase("Sequencer Samples")&&expectedSheetName.equalsIgnoreCase("Sequencer Samples")) {
                    for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                        Row dataRow = CellUtil.getRow(j, sheet);
                        //common key for multiple rows in Sequencer Samples
                        String key = dataRow.getCell(0).getStringCellValue().trim();
                        //different value in each row in Sequencer Samples
                        Map<String, String> valueMap = new HashMap<>();
                        for (int k = 1; k < dataRow.getLastCellNum(); k++) {
                            if (!(row.getCell(k).getStringCellValue().equals("GEL1009 Patient ID"))) {
                                String dataKey = row.getCell(k).getStringCellValue();
                                String value = dataFormatter.formatCellValue(dataRow.getCell(k));
                                if (row.getCell(k).getStringCellValue().equalsIgnoreCase("GEL1009 Plate Date of Dispatch")) {
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
                                    Date date = simpleDateFormat.parse(value);
                                    value = new SimpleDateFormat("dd/MM/yyyy").format(date);
                                }else if (row.getCell(k).getStringCellValue().equalsIgnoreCase("GEL1009 OD 260 280")){
                                    String newValue= value;
                                    Float floatValue= Float.parseFloat(newValue);
                                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                                    decimalFormat.setMaximumFractionDigits(2);
                                    value=decimalFormat.format(floatValue);
                                }
                                valueMap.put(dataKey, value);
                            }
                        }
                        if(myMap.containsKey(key)){
                            if(!myMap.get(key).equals(valueMap)){
                                key=key.concat("-");
                            }
                        }
                        myMap.put(key, valueMap);
                    }
                    Debugger.println("Data in Sequencer Samples is " + myMap.toString());
                } else if (sheetName.equalsIgnoreCase("New Referrals")&&expectedSheetName.equalsIgnoreCase("New Referrals")) {
                    for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                        Row dataRow = CellUtil.getRow(j, sheet);
                        //common key for multiple rows in New Referrals
                        String key = dataRow.getCell(0).getStringCellValue().trim();
                        //different value in each row in New Referrals
                        Map<String, String> valueMap = new HashMap<>();
                        for (int k = 1; k < dataRow.getLastCellNum(); k++) {
                            if (!(row.getCell(k).getStringCellValue().equals("Referral ID"))) {
                                String dataKey = row.getCell(k).getStringCellValue();
                                String value = dataFormatter.formatCellValue(dataRow.getCell(k));
                                if (row.getCell(k).getStringCellValue().equalsIgnoreCase("Referral Creation Date")) {
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
                                    Date date = simpleDateFormat.parse(value);
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                }else if (row.getCell(k).getStringCellValue().equalsIgnoreCase("Referral Last Submitted")) {
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
                                    Date date = simpleDateFormat.parse(value);
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                }
                                valueMap.put(dataKey, value);
                            }
                        }
                        myMap.put(key, valueMap);
                    }
                    Debugger.println("Data in New Referrals is " + myMap.toString());
                }
            }
            Debugger.println("The value read from excelSheet " + fileName + " is " + myMap.toString());
            return myMap;
        } catch (Exception exp) {
            Debugger.println("Exception from readAllDataFromAllSheet: " + exp);
            return null;
        }

    }
}