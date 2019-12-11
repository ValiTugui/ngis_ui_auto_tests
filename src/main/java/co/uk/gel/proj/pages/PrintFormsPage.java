package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
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
import io.github.jonathanlink.PDFLayoutTextStripper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.Set;

public class PrintFormsPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    public PrintFormsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }
    String defaultDownloadLocation = System.getProperty("user.dir") + File.separator +"downloads"+File.separator;

    String specificPrintFormDownload = "//ul//span[text()='NHSLastFour']/ancestor::div[@class='css-1qv4t1n']//button";
    String probandPrintFormDownloadLocator = "//button[@class='css-u6wg3d']";
    @FindBy(css = "button[class*='link-button']")
    WebElement showAddressButton;

    @FindBy(css = "span[class*='address__line']")
    List<WebElement> laboratoryAddress;

    @FindBy(xpath = "//strong[contains(text(),'Tumour')]")
    WebElement tumourInfo;

    @FindBy(xpath = "//li[contains(text(),'germline')]")
    WebElement sampleInfo;



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
            String nhsLastFour = nhsNumber.substring(6,10);//Assuming NHSNumber is always 10 digit.
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

    public boolean downloadProbandPrintForm(){
            //Delete if File already present
            Debugger.println("Deleting Files if Present...");
            TestUtils.deleteIfFilePresent("SampleForm","");
            Debugger.println("Attempting to download the Proband sample form");
            By downloadForm = By.xpath(probandPrintFormDownloadLocator);
            WebElement element = driver.findElement(downloadForm);
            try {
                if (Wait.isElementDisplayed(driver, element, 30)) {
                    element.click();
                    Wait.seconds(5);//Wait for 5 seconds to ensure file got downloaded.
                } else {
                    //Wait for another 30 seconds more
                    if (Wait.isElementDisplayed(driver, element, 30)) {
                        element.click();
                        Wait.seconds(5);//Wait for 5 seconds to ensure file got downloaded.
                    } else {
                        Debugger.println("Form download option could not locate.");
                        return false;
                    }
                }
                return true;
            }catch(Exception exp){
                Debugger.println("Could not locate the print button ..... " + exp);
                SeleniumLib.takeAScreenShot("PrintFormsDownload.jpg");
                return false;
            }
    }

    public boolean openAndVerifyPDFContent(List<String> expValues){
        String expectedName = expValues.get(0);
        String expectedDOB = expValues.get(1);
        String expectedGender = expValues.get(2);

        String expectedPatientNGISId = expValues.get(3);
        String expectedPatientReferralId = expValues.get(4);

        String expectedCI = expValues.get(5);
        String expectedRequestingOrg = expValues.get(6);

        String expectedResponsibleClinicianName = expValues.get(7);
        String expectedResponsibleClinicianEmail = expValues.get(8);
        String expectedResponsibleClinicianContact = expValues.get(9);

        String expectedTumour = expValues.get(10);
        String expectedSample = expValues.get(11);

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
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            String outputData = pdfTextStripper.getText(document);
            // outputData = outputData.replaceAll("/  +/g", " ");
            outputData = outputData.replaceAll("\\s+", " ");
            Debugger.println("Actual Data from PDF sample form :\n" + outputData );
            boolean testResult = true;
            if (!outputData.contains(expectedName)) {
                Debugger.println(" Patient Name " + expectedName +" is  NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedDOB)) {
                Debugger.println(" Patient DOB " + expectedDOB +" is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedGender)) {
                Debugger.println(" Patient Gender " + expectedGender +" is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedCI)) {
                Debugger.println(" Patient's Clinical Indication "+ expectedCI +" info is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedRequestingOrg)) {
                Debugger.println(" Requesting Organisation "+ expectedRequestingOrg +" info is NOT shown correctly in Sample form");
                testResult = false;
            }
           if (!outputData.contains(expectedResponsibleClinicianName)) {
                Debugger.println(" Responsible Clinician Name "+ expectedResponsibleClinicianName +" info is NOT shown correctly in Sample form");
                testResult = false;
            }
          if (!outputData.contains(expectedResponsibleClinicianEmail)) {
                Debugger.println(" Responsible Clinician Email "+ expectedResponsibleClinicianEmail +" info is NOT shown correctly in Sample form");
                testResult = false;
            }
          if (!outputData.contains(expectedResponsibleClinicianContact)) {
                Debugger.println(" Responsible Clinician Contact "+ expectedResponsibleClinicianContact +" info is NOT shown correctly in Sample form");
                testResult = false;
            }
         if (!outputData.contains(expectedTumour)) {
                Debugger.println(" Tumour info "+ expectedTumour +" info is NOT shown correctly in Sample form");
                testResult = false;
            }
         if (!outputData.contains(expectedSample)) {
             Debugger.println(" Sample info " + expectedSample + " info is NOT shown correctly in Sample form");
             testResult = false;
         }
         if (!outputData.contains(expectedPatientNGISId)) {
                Debugger.println(" Patient NGIS Id "+ expectedPatientNGISId +" info is NOT shown correctly in Sample form");
                testResult = false;
            }
         if (!outputData.contains(expectedPatientReferralId)) {
                Debugger.println(" Patient Referral Id "+ expectedPatientReferralId +" info is NOT shown correctly in Sample form");
                testResult = false;
            }



            //Close the tab and return.
            SeleniumLib.closeCurrentWindow();
            return testResult;
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

    public String getLaboratoryAddress(){
        String address = null;
        Wait.isElementDisplayed(driver, showAddressButton, 10);
        showAddressButton.click();
        Wait.isElementDisplayed(driver, laboratoryAddress.get(0), 5);
        for(WebElement ele : laboratoryAddress) {
            if(address == null) {
                address = Actions.getText(ele) + ", ";
            } else {
                address = address + Actions.getText(ele) + ", ";
            }
        }
        //remove last comma from the address
        address = address.substring(0 , address.length() - 2);
        return address;
    }

    public String getTumourInfo(){
        Wait.isElementDisplayed(driver, tumourInfo, 5);
        return  Actions.getText(tumourInfo);
    }

    public String getSampleInfo(){
        Wait.isElementDisplayed(driver, sampleInfo, 5);
        return  Actions.getText(sampleInfo);
    }

}//end
