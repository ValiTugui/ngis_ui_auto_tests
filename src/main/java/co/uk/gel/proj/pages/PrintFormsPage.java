package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

public class PrintFormsPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    public PrintFormsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    String defaultDownloadLocation = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;
    @FindBy(xpath = "//button[@aria-label='print button']")
    WebElement probandPrintFormDownloadLocator;

    @FindBy(xpath = "//button[@type='button']/span[text()='Show address']")
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

    @FindBy(xpath = "//div[contains(@data-testid,'referral-sidebar')]//*[contains(text(),'Print forms')]//*[name()='svg'][@data-testid='locked-icon']")
    public WebElement printFormsLockIcon;

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

    @FindBy(xpath = "//span[text()='Start a new referral']")
    WebElement startANewReferralButton;

    @FindBy(xpath = "//div[contains(@class,'notice__text')]")
    public WebElement downloadNotice;

    @FindBy(xpath = "//span[text()='Show address']")
    WebElement showLabAddressLink;
    @FindBy(xpath = "//span[text()='Show address']/../child::*[@class='css-ajdsat-Svg']")
    WebElement showLabAddressLinkButton;

    @FindBy(xpath = "//span[text()='Hide address']")
    public WebElement hideLabAddressLink;

    @FindBy(xpath = "//div[contains(@class,'lab-address')]")
    WebElement detailedAddressText;

    @FindBy(xpath = "//span[contains(.,'Proband')]/ancestor::div[contains(@class,'participant-list')]/div[2]//span[@class='child-element']")
    WebElement relationshipToProbandField;

    String familyFormDownloadButtonPath = "//span[text()='dummyDob']/../../../following-sibling::button";
    @FindBy(xpath = "//h3[contains(@class,'lab-name')]")
    WebElement labNameField;

    public boolean downloadSpecificPrintForm(int position, String folder) {
        String ngsId = "";
        try {
            //Delete if File already present
            //Debugger.println("Deleting Files if Present...");
            TestUtils.deleteIfFilePresent("SampleForm", folder);
            Wait.forElementToBeDisplayed(driver, landingPageList);
            String urlToDownload = formDownloadButtons.get(position).getAttribute("href");
            TestUtils.downloadFile(urlToDownload, "SampleForm.pdf", folder);
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

    public boolean downloadSpecificPrintFormForDOB(NGISPatientModel familyMember, String folder) {
        try {
            String dob = TestUtils.getDOBInMonthFormat(familyMember.getDATE_OF_BIRTH());
            //Debugger.println("Family Member DOB to download form: " + dob);
            String downloadButtonPath = familyFormDownloadButtonPath.replace("dummyDob", dob);
            WebElement familyFormDownloadButton = driver.findElement(By.xpath(downloadButtonPath));
            //Delete if File already present
            //Debugger.println("Deleting Files if Present...");
            TestUtils.deleteIfFilePresent("SampleForm", folder);
            Wait.forElementToBeDisplayed(driver, landingPageList);
            String urlToDownload = familyFormDownloadButton.getAttribute("href");
            TestUtils.downloadFile(urlToDownload, "SampleForm.pdf", folder);
            Click.element(driver, familyFormDownloadButton);
            Wait.seconds(10);
            ///Move file to RD folder
            TestUtils.moveDownloadedFileTo("SampleForm", folder, ".pdf");
        } catch (Exception exp) {
            Debugger.println("Exception from  downloading Family member Print form :" + exp);
            return false;
        }
        return true;
    }

    public boolean openAndVerifyPDFContent(NGISPatientModel familyMember, String folder) {
        //Debugger.println("Family Member NGSID to be validated in PDF: " + familyMember.getNGIS_ID());
        String ngsId = TestUtils.insertWhiteSpaceAfterEveryNthCharacter(familyMember.getNGIS_ID(), "4");
        String referralId = TestUtils.insertWhiteSpaceAfterEveryNthCharacter(familyMember.getREFERAL_ID(), "4");
        String dob = TestUtils.getDOBInMonthFormat(familyMember.getDATE_OF_BIRTH());
        //Debugger.println("NG:" + ngsId + ",REF:" + referralId + ",dob:" + dob);
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
                //Debugger.println("Referral ID Could not read: read as null....need to check it.");
                familyMember.setREFERAL_ID("");
            }
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
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
                Debugger.println("Exception in closing PDF FileDocument.");
            }
        }

    }

    public boolean downloadProbandPrintForm() {
        try {
            //Delete if File already present
            //Debugger.println("Deleting Files if Present...");
            TestUtils.deleteIfFilePresent("SampleForm", "");
            //Debugger.println("Attempting to download the Proband sample form");
            if (!Wait.isElementDisplayed(driver, probandPrintFormDownloadLocator, 30)) {
                //Debugger.println("Proband Pritform download button not displayed.." + driver.getCurrentUrl());
                //SeleniumLib.takeAScreenShot("probandPrintForm.jpg");
                return false;
            }
            Actions.clickElement(driver, probandPrintFormDownloadLocator);
            Wait.seconds(5);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(probandPrintFormDownloadLocator);
                Wait.seconds(5);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Could not locate the print button ..... " + exp);
                //SeleniumLib.takeAScreenShot("ProbandPrintFormsDownload.jpg");
                return false;
            }
        }
    }

    public boolean openAndVerifyPDFContent(List<String> expValues) {
        PDDocument document = null;
        try {

            String pathToFile = defaultDownloadLocation + "SampleForm.pdf";
            //Debugger.println("PDF file location: " + pathToFile);
            // pdf file with full path name
            document = PDDocument.load(new File(pathToFile));
            //Debugger.println("Reading PDF content....");
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String outputData = pdfTextStripper.getText(document);
            // outputData = outputData.replaceAll("/  +/g", " ");
            outputData = outputData.replaceAll("\\s+", " ");
            //Debugger.println("Actual Data from PDF sample form :\n" + outputData);
            boolean testResult = false;
            String expValue;
            for (String value : expValues) {
                expValue = value;
                if (expValue == null) {
                    testResult = true;
                    continue;
                }
                if (!outputData.contains(expValue)) {
                    Debugger.println("Expected Value: " + expValue + ", not contains in PDF content.\n" + outputData);
                    testResult = false;
                    break;
                }
                testResult = true;
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

            } catch (Exception ex) {
                Debugger.println("Exception in closing document.");
            }
        }
    }

    public String getLaboratoryAddress() {
        StringBuilder address = null;
        if (Wait.isElementDisplayed(driver, showAddressButton, 10)) {
            showAddressButton.click();
        }
        if (Wait.isElementDisplayed(driver, laboratoryAddress.get(0), 5)) {
            for (WebElement ele : laboratoryAddress) {
                if (address == null) {
                    address = new StringBuilder(Actions.getText(ele) + ", ");
                } else {
                    address.append(Actions.getText(ele)).append(", ");
                }
            }
        }
        //remove last comma from the address
        try {
            address = new StringBuilder(address.substring(0, address.length() - 2));
        } catch (Exception exp) {
            //Nothing to catch and handle
        }
        return address.toString();
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
            return false;
        }
    }

    public boolean validateLockIconInPrintFormsStage(String lockStatus) {
        try {
            Debugger.println("validateLockIconInPrintFormsStage:Print form: " + driver.getCurrentUrl());
            if (!Wait.isElementDisplayed(driver, printFormsStage, 20)) {
                Debugger.println("printFormsStage not displayed");
                return false;
            }
            if (lockStatus.equalsIgnoreCase("Locked")) {
                if (Wait.isElementDisplayed(driver, printFormsLockIcon, 10)) {
                    return true;
                }
                return false;
            } else {
                if (Wait.isElementDisplayed(driver, printFormsLockIcon, 10)) {
                    return false;
                }
                return true;
            }
        } catch (Exception exp) {
            Debugger.println("PrintForms: validateLockIconInPrintFormsStage: " + exp);
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
            return false;
        }
    }

    public boolean validatePDFContent(String expText, String fileName) {
        PDDocument document = null;
        String[] textList = null;
        try {

            if (SeleniumLib.skipIfBrowserStack("BROWSERSTACK")) {
                //Debugger.println("validatePDFContent: COPYING DOWNLOADED FILE FROM BROWSER STACK..." + fileName);
                JavascriptExecutor javascript = (JavascriptExecutor) driver;
                // get file content. The content is Base64 encoded
                String base64EncodedFile = (String) javascript.executeScript("browserstack_executor: {\"action\": \"getFileContent\", \"arguments\": {\"fileName\": \"" + fileName + "\"}}");
                //decode the content to Base64
                byte[] data = Base64.getDecoder().decode(base64EncodedFile);
                OutputStream stream = new FileOutputStream(defaultDownloadLocation + "SampleForm.pdf");
                stream.write(data);
                stream.close();
//                driver.quit();
            }
            SeleniumLib.sleepInSeconds(5);
            if (fileName.endsWith(".zip")) {
                if (!TestUtils.extractZipFile(fileName)) {
                    Debugger.println("Could not extract the zip file: " + fileName);
                    return false;
                }
            }

            if (!expText.contains(",")) {
                textList = new String[]{expText};
            } else {
                textList = expText.split(",");
            }

            //creating path for the downloaded pdf file
            String pathToFile = defaultDownloadLocation + fileName;
            File fileLocation = new File(pathToFile);
            if (!fileLocation.exists()) {
                pathToFile = defaultDownloadLocation + "RD" + File.separator + fileName;
            }
            //Debugger.println("PDF file location: " + pathToFile);
            document = PDDocument.load(new File(pathToFile));
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String outputData = pdfTextStripper.getText(document);
            //Debugger.println("Actual Data from PDF form :\n" + outputData);
            outputData = outputData.replaceAll("\\s+", " ");
            //Debugger.println("Formatted Data from PDF sample form :\n" + outputData);
            boolean testResult = true;
            for (String str : textList) {
                if (!outputData.contains(str)) {
                    Debugger.println(" The expected " + str + " is  NOT shown correctly in the form: " + fileName);
                    Debugger.println(" PDF CONTENT:" + outputData);
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

    //new
    public boolean validatePDFAddressContent(String fileName) {
        PDDocument document = null;
        try {

            if (SeleniumLib.skipIfBrowserStack("BROWSERSTACK")) {
                //Debugger.println("validatePDFContent: COPYING DOWNLOADED FILE FROM BROWSER STACK..." + fileName);
                JavascriptExecutor javascript = (JavascriptExecutor) driver;
                // get file content. The content is Base64 encoded
                String base64EncodedFile = (String) javascript.executeScript("browserstack_executor: {\"action\": \"getFileContent\", \"arguments\": {\"fileName\": \"" + fileName + "\"}}");
                //decode the content to Base64
                byte[] data = Base64.getDecoder().decode(base64EncodedFile);
                OutputStream stream = new FileOutputStream(defaultDownloadLocation + "SampleForm.pdf");
                stream.write(data);
                stream.close();
            }
            SeleniumLib.sleepInSeconds(5);
            if (fileName.endsWith(".zip")) {
                if (!TestUtils.extractZipFile(fileName)) {
                    Debugger.println("Could not extract the zip file: " + fileName);
                    return false;
                }
            }

            //creating path for the downloaded pdf file
            String pathToFile = defaultDownloadLocation + fileName;
            File fileLocation = new File(pathToFile);
            if (!fileLocation.exists()) {
                pathToFile = defaultDownloadLocation + "RD" + File.separator + fileName;
            }
            //Debugger.println("PDF file location: " + pathToFile);
            document = PDDocument.load(new File(pathToFile));
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String outputData = pdfTextStripper.getText(document);
            //Debugger.println("Actual Data from PDF form :\n" + outputData);
            outputData = outputData.replaceAll("\\s+", " ");
            //Debugger.println("Formatted Data from PDF sample form :\n" + outputData);

            boolean testResult = true;
            for (int i = 1; i < labAddress.length; i++) {
                Debugger.println("Address Line is : " + labAddress[i]);
                if (!outputData.contains(labAddress[i])) {
                    Debugger.println(" The expected " + labAddress[i] + " is  NOT shown correctly in the form: " + fileName);
                    Debugger.println(" PDF CONTENT:" + outputData);
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
            if (formSection.size() == 0) {
                Debugger.println("No Form Sections present to download.");
                //SeleniumLib.takeAScreenShot("OfflinePrintForms.jpg");
                return false;
            }
            boolean isDownloaded = false;
            String urlToDownload = "";
            for (int i = 0; i < formSection.size(); i++) {
                String actualText = formSection.get(i).getText();
                if (actualText.equalsIgnoreCase(expectedFormSection)) {
                    urlToDownload = downloadButton.get(i).getAttribute("href");
                    if (TestUtils.downloadFile(urlToDownload, fileName, "").equalsIgnoreCase("Success")) {
                        isDownloaded = true;
                        Wait.seconds(3);//Wait for 3 seconds to ensure file got downloaded, large file taking time to download
                        break;
                    } else {
                        Actions.retryClickAndIgnoreElementInterception(driver, downloadButton.get(i));
                        isDownloaded = true;
                        Wait.seconds(3);//Wait for 3 seconds to ensure file got downloaded, large file taking time to download
                        break;
                    }
                }
            }
            return isDownloaded;
        } catch (Exception exp) {
            Debugger.println("Could not locate the form download button ..... " + exp);
            return false;
        }
    }

    public String readSelectedTestDetails() {
        try {
            String returnValue = null;
            if (!Wait.isElementDisplayed(driver, orderedTestType, 30)) {
                return null;
            }
            String testTypes = orderedTestType.getText();
            String[] selectedTestTypes = testTypes.split("\\.");
            if (selectedTestTypes == null || selectedTestTypes.length < 1) {
                Debugger.println("No selected test exists...");
                return null;
            }
            returnValue = selectedTestTypes[0];
            return returnValue;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: readSelectedTestType: " + exp);
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
                return null;
            }
            returnValue = labName[0];
            return returnValue;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: readSelectedLabDetails: " + exp);
            return null;
        }
    }

    public boolean startANewReferralButton() {
        try {
            Wait.forElementToBeDisplayed(driver, submissionConfirmationBanner);
            if (!Wait.isElementDisplayed(driver, startANewReferralButton, 30)) {
                Debugger.println("Referral Submitted :Start New Referral Button Not found");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage :startANewReferralButton: " + exp);
            return false;
        }
    }

    public boolean clickOnStartANewReferralButton() {
        try {
            if (!Wait.isElementDisplayed(driver, startANewReferralButton, 30)) {
                Debugger.println("PrintFormsPage : Referral Submitted :Start New Referral Button Not found");
                return false;
            }
            Actions.clickElement(driver, startANewReferralButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage : clickOnStartANewReferralButton: " + exp);
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
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: validateGuidelinesContent: " + exp);
            return false;
        }
    }

    public boolean extractAndValidateZipFile(String fileName) {
        try {
            Wait.seconds(15); //wait for zip file download completion
            if (SeleniumLib.skipIfBrowserStack("BROWSERSTACK")) {
                //Debugger.println("extractAndValidateZipFile: COPYING DOWNLOADED FILE FROM BROWSER STACK..." + fileName);
                JavascriptExecutor javascript = (JavascriptExecutor) driver;
                // get file content. The content is Base64 encoded
                String base64EncodedFile = (String) javascript.executeScript("browserstack_executor: {\"action\": \"getFileContent\", \"arguments\": {\"fileName\": \"" + fileName + "\"}}");
                //decode the content to Base64
                byte[] data = Base64.getDecoder().decode(base64EncodedFile);
                OutputStream stream = new FileOutputStream(defaultDownloadLocation + "SampleForm.pdf");
                stream.write(data);
                stream.close();
//                driver.quit();
            }
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

//    public String readLabAddress(String showAddress) {
//        try {
//            Wait.forElementToBeDisplayed(driver, showLabAddressLink);
//            if (showAddress.equalsIgnoreCase(showLabAddressLink.getText())) {
//                seleniumLib.clickOnWebElement(showLabAddressLink);
//            }
//            Wait.forElementToBeDisplayed(driver, detailedAddressText);
//            String detailedAddress = detailedAddressText.getText();
//            if (detailedAddress == null || detailedAddress.isEmpty()) {
//                Debugger.println("No detailed Address present.");
//                return null;
//            }
//            if (!detailedAddress.contains("Hide address")) {
//                Debugger.println("Laboratory address does not contain Hide Address option");
//                return null;
//            }
//
//            //Debugger.println("The lab address is: " + detailedAddressText.getText());
//            String[] labAddress = detailedAddress.split("\\n");
//            if (labAddress == null || labAddress.length < 2) {
//                Debugger.println("Lab address is Not Shown");
//                return null;
//            }
//            //Debugger.println("lab Address to search in form: " + labAddress[1]);
//            hideLabAddressLink.click();
//            return labAddress[1];
//        } catch (Exception exp) {
//            Debugger.println("PrintFormsPage: readLabAddress: " + exp);
//            return null;
//        }
//    }

    //new
    String[] labAddress = {};

    @FindBy(xpath = "//h3[contains(@class,'downloads__lab-name')]")
    public WebElement labName;

    public String readLabAddress(String showAddress) {
        labAddress = null;
        try {
            Wait.forElementToBeDisplayed(driver, showLabAddressLink);
            if (showAddress.equalsIgnoreCase(showLabAddressLink.getText())) {
                seleniumLib.clickOnWebElement(showLabAddressLinkButton);
            }
            Wait.forElementToBeDisplayed(driver, detailedAddressText);
            String detailedAddress = detailedAddressText.getText()+"\n"+labName.getText();
            if (detailedAddress == null || detailedAddress.isEmpty()) {
                Debugger.println("No detailed Address present.");
                return null;
            }
            if (!detailedAddress.contains("Hide address")) {
                Debugger.println("Laboratory address does not contain Hide Address option");
                return null;
            }

            //Debugger.println("The lab address is: " + detailedAddressText.getText());
//            String[] labAddress = detailedAddress.split("\\n");
            labAddress = detailedAddress.split("\\n");
            if (labAddress == null || labAddress.length < 2) {
                Debugger.println("Lab address is Not Shown");
                return null;
            }

            //Debugger.println("lab Address to search in form: " + labAddress[1]);
            hideLabAddressLink.click();
            return labAddress[1];
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: readLabAddress: " + exp);
            return null;
        }
    }

    public boolean verifyRelationshipToProband(String relationToProband) {
        try {
            Wait.forElementToBeDisplayed(driver, relationshipToProbandField);
            String relationshipToProbandInPrintFormSection = relationshipToProbandField.getText();
            if (!relationToProband.equals(relationshipToProbandInPrintFormSection)) {
                Debugger.println("The relationship to proband is not updated: " + relationshipToProbandInPrintFormSection);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Referral page: verifyRelationshipToProband : " + exp);
            return false;
        }
    }

    public boolean getLabName(String labName) {
        try {
            if (!Wait.isElementDisplayed(driver, labNameField, 10)) {
                Debugger.println("Lab Name not found");
                return false;
            }
            String labNameInPrintFormsStage = labNameField.getText();
            if (!labName.equals(labNameInPrintFormsStage)) {
                Debugger.println("Expected lab name is:" + labName + "but actual present:" + labNameInPrintFormsStage);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage getLabName: " + exp);
            return false;
        }
    }

}//end
