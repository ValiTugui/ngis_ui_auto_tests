package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Set;

public class PrintFormsPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    String specificPrintFormDownload = "//ul//span[text()='NHSLastFour']/ancestor::div[@class='css-1qv4t1n']//button";

    public PrintFormsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public boolean downloadSpecificPrintForm(String familyDetails){
        String nhsNumber = "";
        try {
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(familyDetails);
            Set<String> paramsKey = paramNameValue.keySet();
            for (String key : paramsKey) {
                if(key.equalsIgnoreCase("NHSNumber")){
                    nhsNumber = paramNameValue.get(key);
                    break;
                }
            }
            if(nhsNumber == null || nhsNumber.isEmpty()){
                Debugger.println("NHS Number not provided to edit the patient choice.");
                return false;
            }
            //Delete if File already present
            TestUtils.deleteIfFilePresent("SampleForm","");
            String nhsLastFour = nhsNumber.substring(6,nhsNumber.length());//Assuming NHSNumber is always 10 digit.
            By downloadForm = By.xpath(specificPrintFormDownload.replaceAll("NHSLastFour", nhsLastFour));
            WebElement element = driver.findElement(downloadForm);
            element.click();
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from  downloading Print form for specific NHSNumber:"+exp);
            return false;
        }
    }

}//end
