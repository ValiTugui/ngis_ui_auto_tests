package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
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

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PrintFormsPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    public PrintFormsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    String defaultDownloadLocation = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;

    String probandPrintFormDownloadLocator_e2elatest = "//button[@aria-label='print button']";
    String probandPrintFormDownloadLocator = "//button[@class='css-dixxn2']";
    @FindBy(css = "button[class*='link-button']")
    WebElement showAddressButton;

    @FindBy(css = "span[class*='address__line']")
    List<WebElement> laboratoryAddress;

    @FindBy(xpath = "//strong[contains(text(),'Tumour')]")
    WebElement tumourInfo;

    @FindBy(xpath = "//li[contains(text(),'germline')]")
    WebElement sampleInfo;

    @FindBy(css = "*[class*='participant-list__']")
    public WebElement landingPageList;

    @FindBy(xpath = "//button[contains(@aria-label,'print')]")
    public List<WebElement> formDownloadButtons;

    @FindBy(xpath = "//div[contains(@data-testid,'notification-warning')]")
    WebElement warningMessageOnPrintForms;

    @FindBy(xpath = "//div[contains(@data-testid,'referral-sidebar')]//*[contains(text(),'Print forms')]")
    public WebElement printFormsStage;

    @FindBy(xpath = "//button[contains(text(),'Back')]")
    WebElement backButtonOnPrintFormPage;

    @FindBy(xpath = "//div[contains(@class,'styles_body_')]/h3")
    public List<WebElement> formSection;

    @FindBy(css = "a[class*='btn-secondary']")
    public List<WebElement> downloadButton;

    @FindBy(xpath = "//div[contains(@class,'styles_body')]//div//div")
    public WebElement orderedTestType;

    @FindBy(xpath = "//h2[contains(@class,'panelTitle')]")
    public WebElement selectedLaboratory;

    @FindBy(css = "*[class*='downloads__notice']")
    public WebElement submissionConfirmationBanner;

    @FindBy(xpath = "//button[text()='Start a new referral']")
    WebElement startANewReferralButton;

    @FindBy(xpath = "//div[contains(@class,'notice__text')]")
    public WebElement downloadNotice;

    @FindBy(xpath = "//*[@id='referral__header']//button/span[text()='Submit']")
    public WebElement referralSubmitButton;


    public boolean downloadSpecificPrintForm(int position, String folder) {
        String ngsId = "";
        try {
            //Delete if File already present
            //Debugger.println("Deleting Files if Present...");
            TestUtils.deleteIfFilePresent("SampleForm", folder);
            Wait.forElementToBeDisplayed(driver, landingPageList);
            Click.element(driver, formDownloadButtons.get(position));
            Wait.seconds(10);
            ///Move file to RD folder
            TestUtils.moveDownloadedFileTo("SampleForm", folder, ".pdf");
        } catch (Exception exp) {
            Debugger.println("Exception from  downloading Print form :" + exp);
            return false;
        }
        return true;
    }

    public boolean openAndVerifyPDFContent(NGISPatientModel familyMember, String folder) {
        Debugger.println("Family Member NGSID to be validated in PDF: " + familyMember.getNGIS_ID());
        String ngsId = TestUtils.insertWhiteSpaceAfterEveryNthCharacter(familyMember.getNGIS_ID(), "4");
        String referralId = TestUtils.insertWhiteSpaceAfterEveryNthCharacter(familyMember.getREFERAL_ID(), "4");
        String dob = TestUtils.getDOBInMonthFormat(familyMember.getDATE_OF_BIRTH());
        Debugger.println("NG:" + ngsId + ",REF:" + referralId + ",dob:" + dob);
        String output;
        PDDocument document = null;
        BufferedInputStream fileToParse = null;
        InputStream is = null;
        try {
            if (!SeleniumLib.switchToNewTab()) {
                Debugger.println("Could not switch to new tab for reading print form PDF file content.");
                return false;
            }
            String pathToFile = "";
            if (folder == null || folder.isEmpty()) {
                pathToFile = defaultDownloadLocation + "SampleForm.pdf";
            } else {
                pathToFile = defaultDownloadLocation + folder + File.separator + "SampleForm.pdf";
            }
            //Debugger.println("PDF file location: "+pathToFile);
            // pdf file with full path name
            driver.get("file:///" + pathToFile);
            Wait.seconds(10);//Waiting for 10 seconds to load the PDF file in the browser.
            URL url = new URL(driver.getCurrentUrl());
            //Debugger.println("Opening Inputstream from loaded PDF.");
            is = url.openStream();
            fileToParse = new BufferedInputStream(is);
            document = PDDocument.load(fileToParse);
            //Debugger.println("Reading PDF content....");
            if (familyMember.getREFERAL_ID() == null) {
                Debugger.println("Referral ID Could not read: read as null....need to check it.");
                familyMember.setREFERAL_ID("");
            }
            output = new PDFTextStripper().getText(document);
            if (output.contains(dob) &&
                    output.contains(referralId)) {
                //Close the tab and return.
                SeleniumLib.closeCurrentWindow();
                return true;
            } else {
                Debugger.println("PDF content does not contain ngsid:" + ngsId + ",dob:" + dob + " and referralID:" + referralId + "\n Actual Content:" + output);
                SeleniumLib.closeCurrentWindow();
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("Exception from loading PDF content: " + exp);
            return SeleniumLib.closeCurrentWindow();
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                fileToParse.close();
                is.close();
            } catch (Exception ex) {

            }
        }
    }

    public boolean downloadProbandPrintForm() {
        try {
            //Delete if File already present
            Debugger.println("Deleting Files if Present...");
            TestUtils.deleteIfFilePresent("SampleForm", "");
            Debugger.println("Attempting to download the Proband sample form");

            try {
                By downloadForm = By.xpath(probandPrintFormDownloadLocator);
                WebElement element = driver.findElement(downloadForm);
                if (Wait.isElementDisplayed(driver, element, 50)) {
                    element.click();
                    Wait.seconds(5);//Wait for 5 seconds to ensure file got downloaded.
                } else {
                    //Wait for another 30 seconds more
                    if (Wait.isElementDisplayed(driver, element, 50)) {
                        element.click();
                        Wait.seconds(5);//Wait for 5 seconds to ensure file got downloaded.
                    } else {
                        Debugger.println("Form download option could not locate.");
                        return false;
                    }
                }
            } catch (Exception exp) {
                By downloadForm = By.xpath(probandPrintFormDownloadLocator_e2elatest);
                WebElement element = driver.findElement(downloadForm);
                if (Wait.isElementDisplayed(driver, element, 50)) {
                    element.click();
                    Wait.seconds(5);//Wait for 5 seconds to ensure file got downloaded.
                } else {
                    //Wait for another 30 seconds more
                    if (Wait.isElementDisplayed(driver, element, 50)) {
                        element.click();
                        Wait.seconds(5);//Wait for 5 seconds to ensure file got downloaded.
                    } else {
                        Debugger.println("Form download option could not locate.");
                        return false;
                    }
                }


            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Could not locate the print button ..... " + exp);
            SeleniumLib.takeAScreenShot("PrintFormsDownload.jpg");
            return false;
        }
    }

    public boolean openAndVerifyPDFContent(List<String> expValues) {
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
            Debugger.println("PDF file location: " + pathToFile);
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
            Debugger.println("Actual Data from PDF sample form :\n" + outputData);
            boolean testResult = true;
            if (!outputData.contains(expectedName)) {
                Debugger.println(" Patient Name " + expectedName + " is  NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedDOB)) {
                Debugger.println(" Patient DOB " + expectedDOB + " is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedGender)) {
                Debugger.println(" Patient Gender " + expectedGender + " is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedCI)) {
                Debugger.println(" Patient's Clinical Indication " + expectedCI + " info is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedRequestingOrg)) {
                Debugger.println(" Requesting Organisation " + expectedRequestingOrg + " info is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedResponsibleClinicianName)) {
                Debugger.println(" Responsible Clinician Name " + expectedResponsibleClinicianName + " info is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedResponsibleClinicianEmail)) {
                Debugger.println(" Responsible Clinician Email " + expectedResponsibleClinicianEmail + " info is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedResponsibleClinicianContact)) {
                Debugger.println(" Responsible Clinician Contact " + expectedResponsibleClinicianContact + " info is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedTumour)) {
                Debugger.println(" Tumour info " + expectedTumour + " info is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedSample)) {
                Debugger.println(" Sample info " + expectedSample + " info is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedPatientNGISId)) {
                Debugger.println(" Patient NGIS Id " + expectedPatientNGISId + " info is NOT shown correctly in Sample form");
                testResult = false;
            }
            if (!outputData.contains(expectedPatientReferralId)) {
                Debugger.println(" Patient Referral Id " + expectedPatientReferralId + " info is NOT shown correctly in Sample form");
                testResult = false;
            }


            //Close the tab and return.
            SeleniumLib.closeCurrentWindow();
            return testResult;
        } catch (Exception exp) {
            Debugger.println("Exception from loading PDF content: " + exp);
            SeleniumLib.closeCurrentWindow();
            return false;
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                fileToParse.close();
                is.close();
            } catch (Exception ex) {

            }
        }
    }

    public String getLaboratoryAddress() {
        String address = null;
        Wait.isElementDisplayed(driver, showAddressButton, 10);
        showAddressButton.click();
        Wait.isElementDisplayed(driver, laboratoryAddress.get(0), 5);
        for (WebElement ele : laboratoryAddress) {
            if (address == null) {
                address = Actions.getText(ele) + ", ";
            } else {
                address = address + Actions.getText(ele) + ", ";
            }
        }
        //remove last comma from the address
        address = address.substring(0, address.length() - 2);
        return address;
    }

    public String getTumourInfo() {
        Wait.isElementDisplayed(driver, tumourInfo, 5);
        return Actions.getText(tumourInfo);
    }

    public String getSampleInfo() {
        Wait.isElementDisplayed(driver, sampleInfo, 5);
        return Actions.getText(sampleInfo);
    }

    public boolean verifyWarningMessageOnPrintFormsPage(String warningMessage) {
        try {
            Wait.forElementToBeDisplayed(driver, warningMessageOnPrintForms);
            String actualMessage = warningMessageOnPrintForms.getText();
            if (!warningMessage.equalsIgnoreCase(warningMessageOnPrintForms.getText())) {
                Debugger.println("Expected Message: " + warningMessage + ", but Actual Message is: " + actualMessage);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Print Forms Page:Warning Message: " + exp);
            SeleniumLib.takeAScreenShot("PrintFormsPageWarningMessage.jpg");
            return false;
        }
    }

    public boolean validateLockIconInPrintFormsStage(String lockStatus) {
        try {
            Wait.forElementToBeDisplayed(driver, printFormsStage);
            if (!lockStatus.equals("locked")) {
                String unlockedPrintForms = "//div[contains(@data-testid,'referral-sidebar')]//*[contains(@href,'" + "dummyStage" + "')]";
                String webElementLocator = unlockedPrintForms.replace("dummyStage", "downloads");
                WebElement unlockedPrintFormsStage = driver.findElement(By.xpath(webElementLocator));
                if (unlockedPrintFormsStage != null) {
                    //then procces
                } else {
                    // debugger line
                    return false;
                }
                if (!seleniumLib.isElementPresent(unlockedPrintFormsStage)) {
                    Debugger.println("Print forms stage is not unlocked");
                    //Screen shot
                    SeleniumLib.takeAScreenShot("LockinPrintForms.jpg");
                    return false;
                }
                return true;
            }
            WebElement lockIcon = printFormsStage.findElement(By.xpath(".//*[name()='svg']"));
            if (lockIcon == null) {
                Debugger.println("Could not find the lock Icon element.");
                SeleniumLib.takeAScreenShot("PrintFormsLockIconValidation.jpg");
                return false;
            }
            String lock = lockIcon.getAttribute("data-testid");
            if (!lock.contains(lockStatus)) {
                Debugger.println("Print forms stage: actual " + lock + " expected " + lockStatus);
                SeleniumLib.takeAScreenShot("PrintFormsLockStatus.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PrintForms: validateLockIconInPrintFormsStage: " + exp);
            SeleniumLib.takeAScreenShot("PrintFormsLockIconValidation.jpg");
            return false;
        }
    }

    public boolean backButtonNotPresentOnPrintFormsPage() {
        try {
            Wait.forElementToBeDisplayed(driver, warningMessageOnPrintForms);
            if (seleniumLib.isElementPresent(backButtonOnPrintFormPage)) {
                Debugger.println("Print Forms :Back Button found");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Print Forms :Back Button present" + exp);
            SeleniumLib.takeAScreenShot("backButtonOnPrintForms.jpg");
            return false;
        }
    }

    public boolean validatePDFContent(String expText, String fileName) {
        if (fileName.endsWith(".zip")) {
            if (!TestUtils.extractZipFile(fileName)) {
                Debugger.println("Could not extract the zip file: " + fileName);
                return false;
            }
        }
        PDDocument document = null;
        BufferedInputStream fileToParse = null;
        InputStream is = null;
        String[] textList = null;
        if (!expText.contains(",")) {
            textList = new String[]{expText};
        } else {
            textList = expText.split(",");
        }
        try {
            if (!SeleniumLib.switchToNewTab()) {
                Debugger.println("Could not switch to new tab for reading " + fileName + " PDF form file content.");
                return false;
            }

            //creating path for the downloaded pdf file
            String pathToFile = defaultDownloadLocation + fileName;
            Debugger.println("PDF file location: " + pathToFile);
            // pdf file with full path name
            driver.get("file:///" + pathToFile);
            Wait.seconds(10);//Waiting for 10 seconds to load the PDF file in the browser.
            URL url = new URL(driver.getCurrentUrl());
            Debugger.println("Opening InputStream from loaded PDF.");
            is = url.openStream();
            fileToParse = new BufferedInputStream(is);
            document = PDDocument.load(fileToParse);
            Debugger.println("Reading PDF content....");
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            String outputData = pdfTextStripper.getText(document);
            //Debugger.println("Actual Data from PDF form :\n" + outputData);
            outputData = outputData.replaceAll("\\s+", " ");
            // Debugger.println("Formatted Data from PDF sample form :\n" + outputData);
            boolean testResult = true;
            for (String str : textList) {
                if (!outputData.contains(str)) {
                    Debugger.println(" The expected " + str + " is  NOT shown correctly in the form: " + fileName);
                    testResult = false;
                }
            }
            //Close the tab and return.
            SeleniumLib.closeCurrentWindow();
            return testResult;
        } catch (Exception exp) {
            Debugger.println("Exception from loading PDF content: " + exp);
            SeleniumLib.takeAScreenShot("PrintFormPdfValidation.jpg");
            SeleniumLib.closeCurrentWindow();
            return false;
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                fileToParse.close();
                is.close();
            } catch (Exception exc) {
                Debugger.println("Exception in closing pdf file: " + exc);
            }
        }
    }

    public boolean downloadForm(String fileName, String expectedFormSection) {
        try {
            //Delete if File already present
            TestUtils.deleteIfFilePresent(fileName, "");
            for (int i = 0; i < formSection.size(); i++) {
                String actualText = formSection.get(i).getText();
                if (actualText.equalsIgnoreCase(expectedFormSection)) {
                    seleniumLib.clickOnWebElement(downloadButton.get(i));
                    Wait.seconds(10);//Wait for 5 seconds to ensure file got downloaded.
                    //Debugger.println("Form: " + fileName + " ,downloaded from section: " + actualText);
                    return true;
                }
            }
            return false;
        } catch (Exception exp) {
            Debugger.println("Could not locate the form download button ..... " + exp);
            SeleniumLib.takeAScreenShot("OfflineOrderPrintFormsDownload.jpg");
            return false;
        }
    }

    public String readSelectedTestAndLabDetails(String fieldType) {
        try {
            String returnValue = null;

            if (fieldType.contains("test type")) {
                Wait.forElementToBeDisplayed(driver, orderedTestType);
                String testTypes = orderedTestType.getText();
                String[] tests = testTypes.split("\\n");
                if (tests == null || tests.length < 2) {
                    Debugger.println("No tests present...");
                    return null;
                }
                String[] selectedTestTypes = tests[1].split("\\.");
                if (selectedTestTypes == null || selectedTestTypes.length < 1) {
                    Debugger.println("No test exists...");
                    return null;
                }
                returnValue = selectedTestTypes[0];
            } else if (fieldType.contains("laboratory")) {
                Wait.forElementToBeDisplayed(driver, selectedLaboratory);
                String[] labName = selectedLaboratory.getText().split(" ");
                returnValue = labName[0];
            }
            return returnValue;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: readSelectedTestType: " + exp);
            SeleniumLib.takeAScreenShot("readSelectedTests.jpg");
            return null;
        }
    }

    public boolean startANewReferralButton() {
        try {
            Wait.forElementToBeDisplayed(driver, submissionConfirmationBanner);
            if (!seleniumLib.isElementPresent(startANewReferralButton)) {
                Debugger.println("Referral Submitted :Start New Referral Button Not found");
                SeleniumLib.takeAScreenShot("StartNewReferralButton.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage :startANewReferralButton: " + exp);
            SeleniumLib.takeAScreenShot("StartNewReferralButton.jpg");
            return false;
        }
    }

    public boolean clickOnStartANewReferralButton() {
        try {
            Wait.forElementToBeDisplayed(driver, startANewReferralButton);
            seleniumLib.clickOnWebElement(startANewReferralButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage : clickOnStartANewReferralButton: " + exp);
            SeleniumLib.takeAScreenShot("StartNewReferralButton.jpg");
            return false;
        }
    }

    public boolean validateGuidelinesContent(DataTable noticeText) {
        try {
            boolean isPresent=false;
            List<String> expectedText = noticeText.asList();
            Wait.forElementToBeDisplayed(driver, downloadNotice, 50);
//            Debugger.println("Text present is-- " + downloadNotice.getText());
            String[] actualText = downloadNotice.getText().split("\\n");

           // Debugger.println("Actual: " + actualText[0] + " ,and Expected: " + expectedText.get(0));
            if (actualText[0].equalsIgnoreCase(expectedText.get(0))) {

             //   Debugger.println("Actual: " + actualText[1] + " Expected: " + expectedText.get(1));
                if (actualText[1].equalsIgnoreCase(expectedText.get(1))) {

               //     Debugger.println("Actual: " + actualText[2] + " Expected: " + expectedText.get(2));
                    if (actualText[2].equalsIgnoreCase(expectedText.get(2))) {

                 //       Debugger.println("Actual: " + actualText[3] + " Expected: " + expectedText.get(3));
                        if (actualText[3].equalsIgnoreCase(expectedText.get(3))) {

                   //         Debugger.println("Actual: " + actualText[4] + " Expected: " + expectedText.get(4));
                            if (actualText[4].equalsIgnoreCase(expectedText.get(4))) {

                     //           Debugger.println("Actual: " + actualText[5] + " Expected: " + expectedText.get(5));
                                if (actualText[5].equalsIgnoreCase(expectedText.get(5))) {

                       //             Debugger.println("Actual: " + actualText[6] + " Expected: " + expectedText.get(6));
                                    if (actualText[6].equalsIgnoreCase(expectedText.get(6))) {
                                        Debugger.println("Guidelines notification verified... ");
                                        isPresent= true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Debugger.println("Guidelines notification is not as expected ");
            SeleniumLib.takeAScreenShot("GuidelinesNotice.jpg");
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: validateGuidelinesContent: " + exp);
            SeleniumLib.takeAScreenShot("GuidelinesNotice.jpg");
            return false;
        }
    }

    public boolean referralSubmitButtonStatus() {
        try {
            Wait.forElementToBeDisplayed(driver, referralSubmitButton);
            String referralSubmitButtonBgColor = referralSubmitButton.getCssValue("background-color");
            if (referralSubmitButtonBgColor.equalsIgnoreCase("rgba(240, 240, 240, 1)")) {
                Debugger.println("Actual color : " + referralSubmitButtonBgColor + " is displayed when referral submit button is disabled and not highlighted");
                return false;
            }
            Debugger.println("Actual color : " + referralSubmitButtonBgColor + " is displayed when referral submit button is enabled and highlighted");
            return true;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: Submit referral Button not found. -" + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceReferralSubmitBtn.jpg");
            return false;
        }
    }

    public boolean extractAndValidateZipFile( String fileName) {
        Wait.seconds(10); //wait for zip file download completion
        if (fileName.endsWith(".zip")) {
            if (!TestUtils.extractZipFile(fileName)) {
                Debugger.println("Could not extract the zip file: " + fileName);
                return false;
            }
        }
        String[] nameOfFile = fileName.split("\\.");
        Debugger.println("The file details are " + nameOfFile[0] + " and file type: " + nameOfFile[1]);
        File file = new File(defaultDownloadLocation + nameOfFile[0]);
        if (!file.isDirectory() && file.listFiles().length != 0) {
            Debugger.println("The downloaded file is empty " + file);
            return false;
        }
        Debugger.println("The extracted Zip is not empty");
        return true;
    }

}//end
