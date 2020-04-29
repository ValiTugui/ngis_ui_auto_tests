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
import org.openqa.selenium.NoSuchElementException;
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

    @FindBy(xpath = "//h3[text()='Tests']//following::div[contains(@class,'styles_cellSection')]")
    public WebElement orderedTestType;

    @FindBy(xpath = "//h2[contains(@class,'panelTitle')]")
    public WebElement selectedLaboratory;

    @FindBy(css = "*[class*='downloads__notice']")
    public WebElement submissionConfirmationBanner;

    @FindBy(xpath = "//button[text()='Start a new referral']")
    WebElement startANewReferralButton;

    @FindBy(xpath = "//div[contains(@class,'notice__text')]")
    public WebElement downloadNotice;

    @FindBy(xpath = "//span[text()='Show address']")
    WebElement showLabAddressLink;

    @FindBy(xpath = "//span[text()='Hide address']")
    public WebElement hideLabAddressLink;

    @FindBy(xpath = "//div[contains(@class,'lab-address')]")
    WebElement detailedAddressText;

    @FindBy(xpath = "//span[contains(.,'Proband')]/ancestor::div[contains(@class,'participant-list')]/div[2]//span[@class='child-element']")
    WebElement relationshipToProbandField;

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
        try {

            String pathToFile = "";
            if (folder == null || folder.isEmpty()) {
                pathToFile = defaultDownloadLocation + "SampleForm.pdf";
            } else {
                pathToFile = defaultDownloadLocation + folder + File.separator + "SampleForm.pdf";
            }
            document = PDDocument.load(new File(pathToFile));
            //Debugger.println("Reading PDF content....");
            if (familyMember.getREFERAL_ID() == null) {
                Debugger.println("Referral ID Could not read: read as null....need to check it.");
                familyMember.setREFERAL_ID("");
            }
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            output = pdfTextStripper.getText(document);
            if (output.contains(dob) &&
                    output.contains(referralId)) {
                return true;
            } else {
                Debugger.println("PDF content does not contain ngsid:" + ngsId + ",dob:" + dob + " and referralID:" + referralId + "\n Actual Content:" + output);
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("Exception from loading PDF content: " + exp);
            return false;
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
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

        InputStream is = null;
        try {

            String pathToFile = defaultDownloadLocation + "SampleForm.pdf";
            Debugger.println("PDF file location: " + pathToFile);
            // pdf file with full path name
            document = PDDocument.load(new File(pathToFile));
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
                    SeleniumLib.takeAScreenShot("LockedPrintForms.jpg");
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
        String[] textList = null;
        if (!expText.contains(",")) {
            textList = new String[]{expText};
        } else {
            textList = expText.split(",");
        }
        try {
            //creating path for the downloaded pdf file
            String pathToFile = defaultDownloadLocation + fileName;
            File fileLocation = new File(pathToFile);
            if (!fileLocation.exists()) {
                pathToFile = defaultDownloadLocation + "RD" + File.separator + fileName;
            }
            Debugger.println("PDF file location: " + pathToFile);
            document = PDDocument.load(new File(pathToFile));
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            String outputData = pdfTextStripper.getText(document);
            //Debugger.println("Actual Data from PDF form :\n" + outputData);
            outputData = outputData.replaceAll("\\s+", " ");
            //Debugger.println("Formatted Data from PDF sample form :\n" + outputData);
            boolean testResult = true;
            for (String str : textList) {
                if (!outputData.contains(str)) {
                    Debugger.println(" The expected " + str + " is  NOT shown correctly in the form: " + fileName);
                    Debugger.println(" PDF CONTENT:"+outputData);
                    testResult = false;
                }
            }
            return testResult;
        } catch (Exception exp) {
            Debugger.println("Exception from loading PDF content: " + exp);
            return false;
        } finally {
            try {
                if (document != null) {
                    document.close();
                }

            } catch (Exception exc) {
                Debugger.println("Exception in closing pdf file: " + exc);
            }
        }
    }

    public boolean downloadForm(String fileName, String expectedFormSection) {
        try {
            //Delete if File already present
            TestUtils.deleteIfFilePresent(fileName, "");
            if(formSection.size() == 0){
                Debugger.println("No Form Sections present to download.");
                SeleniumLib.takeAScreenShot("OfflinePrintForms.jpg");
                return false;
            }
            boolean isDownloaded = false;
            for (int i = 0; i < formSection.size(); i++) {
                String actualText = formSection.get(i).getText();
                if (actualText.equalsIgnoreCase(expectedFormSection)) {
                    Actions.clickElement(driver,downloadButton.get(i));
                    isDownloaded = true;
                    Wait.seconds(15);//Wait for 15 seconds to ensure file got downloaded, large file taking time to download
                    break;
                }
            }
            if(!isDownloaded){
                Debugger.println("Could not download the form "+fileName+" for section :"+expectedFormSection);
                SeleniumLib.takeAScreenShot("OfflinePrintForms.jpg");
            }
            return isDownloaded;
        } catch (Exception exp) {
            Debugger.println("Could not locate the form download button ..... " + exp);
            SeleniumLib.takeAScreenShot("OfflinePrintForms.jpg");
            return false;
        }
    }

    public String readSelectedTestDetails() {
        try {
            String returnValue = null;
            Wait.forElementToBeDisplayed(driver, orderedTestType);
            String testTypes = orderedTestType.getText();
            String[] selectedTestTypes = testTypes.split("\\.");
            if (selectedTestTypes == null || selectedTestTypes.length < 1) {
                Debugger.println("No selected test exists...");
                SeleniumLib.takeAScreenShot("readSelectedTests.jpg");
                return null;
            }
            returnValue = selectedTestTypes[0];
            return returnValue;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: readSelectedTestType: " + exp);
            SeleniumLib.takeAScreenShot("readSelectedTests.jpg");
            return null;
        }
    }

    public String readSelectedLabDetails() {
        try {
            String returnValue = null;
            Wait.forElementToBeDisplayed(driver, selectedLaboratory);
            String[] labName = selectedLaboratory.getText().split(" ");
            if (labName == null || labName.length < 1) {
                Debugger.println("No lab details exists...");
                SeleniumLib.takeAScreenShot("readSelectedLab.jpg");
                return null;
            }
            returnValue = labName[0];
            return returnValue;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: readSelectedLabDetails: " + exp);
            SeleniumLib.takeAScreenShot("readSelectedLab.jpg");
            return null;
        }
    }

    public boolean startANewReferralButton() {
        try {
            Wait.forElementToBeDisplayed(driver, submissionConfirmationBanner);
            if (!Wait.isElementDisplayed(driver, startANewReferralButton, 30)) {
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
            Actions.clickElement(driver, startANewReferralButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage : clickOnStartANewReferralButton: " + exp);
            SeleniumLib.takeAScreenShot("StartNewReferralButton.jpg");
            return false;
        }
    }

    public boolean validateGuidelinesContent(String expectedGuideLine) {
        try {
            boolean isPresent = false;
            Wait.forElementToBeDisplayed(driver, downloadNotice, 50);
            String[] actualGuideLines = downloadNotice.getText().split("\\n");
            for (int i = 0; i < actualGuideLines.length; i++) {
                if (actualGuideLines[i].equalsIgnoreCase(expectedGuideLine)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Guideline:" + expectedGuideLine + " Not present in the guideline list ");
                SeleniumLib.takeAScreenShot("GuidelinesNotice.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: validateGuidelinesContent: " + exp);
            SeleniumLib.takeAScreenShot("GuidelinesNotice.jpg");
            return false;
        }
    }

    public boolean extractAndValidateZipFile(String fileName) {
        try {
            Wait.seconds(15); //wait for zip file download completion
            if (fileName.endsWith(".zip")) {
                if (!TestUtils.extractZipFile(fileName)) {
                    Debugger.println("Could not extract the zip file: " + fileName);
                    return false;
                }
            }
            String[] nameOfFile = fileName.split("\\.");
            //Debugger.println("The file details are " + nameOfFile[0] + " and file type: " + nameOfFile[1]);
            File file = new File(defaultDownloadLocation + nameOfFile[0]);
            if (file.isDirectory()) {
                File[] filesList = file.listFiles();
                if (filesList != null && filesList.length > 0) {
                    return true;
                }
            }
            Debugger.println("ZipFile does not contain directory for Files as expected.");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception from extracting and validating zip file." + exp);
            return false;
        }
    }

    public String readLabAddress(String showAddress) {
        try {
                Wait.forElementToBeDisplayed(driver, showLabAddressLink);
                if (showAddress.equalsIgnoreCase(showLabAddressLink.getText())) {
                    seleniumLib.clickOnWebElement(showLabAddressLink);
                }
                Wait.forElementToBeDisplayed(driver, detailedAddressText);
                String detailedAddress = detailedAddressText.getText();
                if(detailedAddress == null || detailedAddress.isEmpty()){
                    Debugger.println("No detailed Address present.");
                    SeleniumLib.takeAScreenShot("LabAddress.jpg");
                    return null;
                }
                if (!detailedAddress.contains("Hide address")) {
                    Debugger.println("Laboratory address does not contain Hide Address option");
                    SeleniumLib.takeAScreenShot("LabAddress.jpg");
                    return null;
                }

                //Debugger.println("The lab address is: " + detailedAddressText.getText());
                String[] labAddress = detailedAddress.split("\\n");
                if (labAddress == null || labAddress.length < 2) {
                    Debugger.println("Lab address is Not Shown");
                    SeleniumLib.takeAScreenShot("LabAddress.jpg");
                    return null;
                }
                //Debugger.println("lab Address to search in form: " + labAddress[1]);
                hideLabAddressLink.click();
                return labAddress[1];
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: readLabAddress: " + exp);
            SeleniumLib.takeAScreenShot("LabAddress.jpg");
            return null;
        }
    }

    public boolean verifyRelationshipToProband(String relationToProband) {
        try {
            Wait.forElementToBeDisplayed(driver, relationshipToProbandField);
            String relationshipToProbandInPrintFormSection = relationshipToProbandField.getText();
            if (!relationToProband.equals(relationshipToProbandInPrintFormSection)) {
                Debugger.println("The relationship to proband is not updated: " + relationshipToProbandInPrintFormSection);
                SeleniumLib.takeAScreenShot("RelationshipToProbandStatus.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Referral page: verifyRelationshipToProband : " + exp);
            SeleniumLib.takeAScreenShot("RelationshipToProbandStatus.jpg");
            return false;
        }
    }

}//end
