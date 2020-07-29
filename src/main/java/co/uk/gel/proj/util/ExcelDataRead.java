package co.uk.gel.proj.util;

import co.uk.gel.lib.SeleniumLib;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
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

    public static List<String> getValue(String key)  {
        Map<String, List<String>> myVal = readAllData(fileName);
        List<String> retValue = myVal.get(key);
        return retValue;
    }

    public static List<String> retrieveData(String glhName) throws Exception {

        List<String> listStr = getValue(glhName);
        listStr.sort(String.CASE_INSENSITIVE_ORDER);
        return listStr;
    }
}


