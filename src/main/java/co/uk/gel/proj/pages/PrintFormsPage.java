package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class PrintFormsPage {

    WebDriver driver;
    SeleniumLib seleniumLib;
    String defaultDownloadLocation = System.getProperty("user.dir") + File.separator +"downloads"+File.separator;

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
            Debugger.println("Deleting Files if Present...");
            TestUtils.deleteIfFilePresent("SampleForm","");
            String nhsLastFour = nhsNumber.substring(6,nhsNumber.length());//Assuming NHSNumber is always 10 digit.
            Debugger.println("Downloading for NHS ends with: "+nhsLastFour);
            By downloadForm = By.xpath(specificPrintFormDownload.replaceAll("NHSLastFour", nhsLastFour));
            WebElement element = driver.findElement(downloadForm);
            if(Wait.isElementDisplayed(driver,element,30)) {
                element.click();
                Wait.seconds(5);//Wait for 5 seconds to ensure file got downloaded.
            }else{
                //Wait for another 30 seconds more
                if(Wait.isElementDisplayed(driver,element,30)) {
                    element.click();
                    Wait.seconds(5);//Wait for 5 seconds to ensure file got downloaded.
                }else{
                    Debugger.println("Form download option could not locate.");
                    return false;
                }
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from  downloading Print form for specific NHSNumber:"+exp);
            return false;
        }
    }
    public boolean openAndVerifyPDFContent(String familyDetails){
        String nhsNumber = "",dateOfBirth="";
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(familyDetails);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            if(key.equalsIgnoreCase("NHSNumber")){
                nhsNumber = paramNameValue.get(key);
            }
            if(key.equalsIgnoreCase("DOB")){
                dateOfBirth = paramNameValue.get(key);
            }
        }
        nhsNumber = TestUtils.getNHSInSplitFormat(nhsNumber);
        dateOfBirth = TestUtils.getDOBInMonthFormat(dateOfBirth);
        Debugger.println("NHS and DOB to be validated in PDF: "+nhsNumber+","+dateOfBirth);
        String output;
        PDDocument document = null;
        BufferedInputStream fileToParse = null;
        InputStream is = null;
        try {
            if (!SeleniumLib.switchToNewTab()) {
                Debugger.println("Could not switch to new tab for reading print form PDF file content.");
                return false;
            }
            String pathToFile = defaultDownloadLocation + "SampleForm.pdf";
            Debugger.println("PDF file location: "+pathToFile);
            // pdf file with full path name
            driver.get("file:///" + pathToFile);
            Wait.seconds(10);//Waiting for 10 seconds to load the PDF file in the browser.
            URL url = new URL(driver.getCurrentUrl());
            Debugger.println("Opening Inputstream from loaded PDF.");
            is = url.openStream();
            fileToParse = new BufferedInputStream(is);
            document = PDDocument.load(fileToParse);
            Debugger.println("Reading PDF content....");
            output = new PDFTextStripper().getText(document);
            if(output.contains(nhsNumber) && output.contains(dateOfBirth)){
                //Close the tab and return.
                SeleniumLib.closeCurrentWindow();
                return true;
            }else{
                Debugger.println("PDF content does not contain:"+nhsNumber+" and "+dateOfBirth+"\n Actual Content:"+output);
                SeleniumLib.closeCurrentWindow();
                return false;
            }
        }catch(Exception exp){
            Debugger.println("Exception from loading PDF content: "+exp);
            SeleniumLib.closeCurrentWindow();
            return false;
        }finally {
            try {
                if (document != null) {
                    document.close();
                }
                fileToParse.close();
                is.close();
            }catch(Exception ex){

            }
        }
    }

}//end
