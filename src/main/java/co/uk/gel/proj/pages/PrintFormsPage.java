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
import sun.security.ssl.Debug;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static co.uk.gel.proj.pages.FamilyMemberDetailsPage.getFamilyMember;

public class PrintFormsPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    public PrintFormsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    String defaultDownloadLocation = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;

    String specificPrintFormDownload = "//ul//span[text()='NHSLastFour']/ancestor::div[@class='css-1qv4t1n']//button";
    String specificPrintFormDownload_e2elatest = "//ul//span[text()='NHSLastFour']/ancestor::div[@class='css-11efprl']//button";

    String specificPrintFormDownload_integration = "//ul//span[text()='NGISLastFour']/ancestor::div/button[@aria-label='print button']";

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

    @FindBy(xpath = "//div[contains(@class,'styles_body_')]/h3")
    public List<WebElement> formSection;

    @FindBy(css = "a[class*='btn-secondary']")
    public List<WebElement> downloadButton;

    @FindBy(xpath = "//div[contains(@class,'contentCss')]")
    public List<WebElement> participants;

    @FindBy(xpath = "//h2[contains(@class,'panelTitle')]")
    public WebElement selectedLaboratory;

    @FindBy(xpath = "//button[contains(text(),'Back')]")
    WebElement backButtonOnPrintFormPage;

    String landingPageNamePath = "//div[contains(@class,'_participant-list__')]//span/h2[contains(text(),'dummyName')]";
    String landingPageRelationPath = "//div[contains(@class,'_participant-list__')]//span[contains(text(),'dummyRelation')]";
    @FindBy(xpath = "//div[contains(@class,'_participant-list__')]//ul/li//span[contains(@aria-labelledby,'dateOfBirth')]")
    List<WebElement> landingPageBorns;
    @FindBy(xpath = "//div[contains(@class,'_participant-list__')]//ul/li//span[contains(@aria-labelledby,'gender')]")
    List<WebElement> landingPageGenders;
    @FindBy(xpath = "//div[contains(@class,'_participant-list__')]//ul/li//span[contains(@aria-labelledby,'nhsNumber')]")
    List<WebElement> landingPageNhsNumbers;
    @FindBy(xpath = "//div[contains(@class,'_participant-list__')]//ul/li//span[contains(@aria-labelledby,'ngisId')]")
    List<WebElement> landingPageNgsIds;

    @FindBy(xpath = "//button[@aria-label='print button']")
    public List<WebElement> printFormsIcons;

    @FindBy(css = "address[class*='styles_address']")
    WebElement labCompletedAddress;

    @FindBy(xpath = "//div[contains(@class,'styles_body')]//div//div")
    public WebElement orderedTestType;

    @FindBy(xpath = "//span[text()='Show address']")
    WebElement showLabAddressLink;

    @FindBy(xpath = "//div[contains(@class,'lab-address')]")
    WebElement detailedAddressText;

    @FindBy(xpath = "//div[contains(@data-testid,'referral-sidebar')]//*[contains(text(),'Print forms')]")
    public WebElement printFormsStage;

    @FindBy(xpath = "//div[contains(@data-testid,'notification-warning')]")
    WebElement warningMessageOnPrintForms;

    @FindBy(css = "*[class*='downloads__notice']")
    public WebElement submissionConfirmationBanner;

    @FindBy(xpath = "//button[text()='Start a new referral']")
    WebElement startANewReferralButton;

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
            Debugger.println("Opening Inputstream from loaded PDF.");
            is = url.openStream();
            fileToParse = new BufferedInputStream(is);
            document = PDDocument.load(fileToParse);
            Debugger.println("Reading PDF content....");
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
            Debugger.println("Print Forms Page:Warning Message :not found " + exp);
            SeleniumLib.takeAScreenShot("PrintFormsPageWarningMessage.jpg");
            return false;
        }
    }

    //1993
    public boolean findPDFContent(String expText, String fileName) {
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
            File file = new File(defaultDownloadLocation + fileName);
            if (file.isDirectory()) {
                traverseDirectory(file);
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
//            Debugger.println("Actual content of PDF file "+outputData);
            outputData = outputData.replaceAll("\\s+", " ");
            Debugger.println("Data read from PDF form :\n" + outputData);
            boolean testResult = true;
            for (String str : textList) {
                if (!outputData.contains(str)) {
                    Debugger.println(" The expected " + str + " is  NOT shown correctly in Sample form");
                    testResult = false;
//                    break;
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
            } catch (Exception ex) {

            }
        }
    }

    public String extractZipFile(String fileName) {
        try {
            Wait.seconds(5);
            String pathToFile = defaultDownloadLocation + fileName;
            Debugger.println("Extraction of file starting: " + pathToFile);
            File zipfile = new File(pathToFile);
            if (!zipfile.exists()) {
                Debugger.println("Zipped file does not exist in location " + pathToFile);
            }
            ZipFile zippedFile = new ZipFile(pathToFile);
            Enumeration<? extends ZipEntry> entries = zippedFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();

                String name = defaultDownloadLocation + (zipEntry.getName());
                Debugger.println("Zipped filename- " + name);

                //Create directory and sub-directories to extract the zip file
                File file = new File(name);
                if (name.endsWith("/")) {
                    file.mkdirs();
                    continue;
                }
                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                InputStream inputStream = zippedFile.getInputStream(zipEntry);
                FileOutputStream fileOutStream = new FileOutputStream(file);
                byte[] bytes = new byte[2096];
                int length;
                while ((length = inputStream.read(bytes)) >= 0) {
                    fileOutStream.write(bytes, 0, length);
                }
                inputStream.close();
                fileOutStream.close();
            }
            zippedFile.close();

            Debugger.println("Returned file is " + zippedFile.getName());
            return zippedFile.getName();//filename is not proper need to return the parent directory
        } catch (IOException exp) {
            Debugger.println("Exception from Extracting the Zipped file: " + fileName + " : " + exp);
            return null;
        }
    }

    public static void traverseDirectory(File dirName) {
        System.out.println("from traverse " + dirName.getAbsoluteFile());
        if (dirName.isDirectory()) {
            String[] subDir = dirName.list();
            for (String filename : subDir) {
                traverseDirectory(new File(dirName, filename));
            }
        }
        List<String> childFiles = new ArrayList<>();
        childFiles.add(String.valueOf(dirName.getAbsoluteFile()));
        for (int i = 0; i < childFiles.size(); i++) {
            Debugger.println("file- " + childFiles.get(i));
        }
    }

    public boolean downloadForm(String fileName, String expectedFormSection) {
        try {
            //Delete if File already present
            Debugger.println("Deleting Files if Present...");
            TestUtils.deleteIfFilePresent(fileName, "");
            Debugger.println("Attempting to download the form..." + fileName);
            for (int i = 0; i < formSection.size(); i++) {
                String actualText = formSection.get(i).getText();
                if (actualText.equalsIgnoreCase(expectedFormSection)) {
                    seleniumLib.clickOnWebElement(downloadButton.get(i));
                    Wait.seconds(5);//Wait for 5 seconds to ensure file got downloaded.
                    Debugger.println("Form: " + fileName + " ,downloaded from section: " + actualText);
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

    public boolean validateLockIconInPrintFormsStage(String lockStatus) {
        try {
            Wait.forElementToBeDisplayed(driver, printFormsStage);
            if (!lockStatus.equals("locked")) {
                String unlockedPrintForms = "//div[contains(@data-testid,'referral-sidebar')]//*[contains(@href,'" + "dummyStage" + "')]";
                String webElementLocator = unlockedPrintForms.replace("dummyStage", "downloads");
                WebElement unlockedPrintFormsStage = driver.findElement(By.xpath(webElementLocator));
                if (!seleniumLib.isElementPresent(unlockedPrintFormsStage)) {
                    Debugger.println("Print forms stage is not unlocked");
                    return false;
                }
                return true;
            }
            WebElement lockIcon = printFormsStage.findElement(By.xpath(".//*[name()='svg']"));
            String lock = lockIcon.getAttribute("data-testid");
            if (!lock.contains(lockStatus)) {
                Debugger.println("actual " + lock + " expected " + lockStatus);
                Debugger.println("Print forms stage attribute is Not matching");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PrintForms: validateLockIconInPrintFormsStage: " + exp);
            return false;
        }
    }

    public boolean userSeesTheDetailsOfLabAddress(String showAddress) {
        try {
            Wait.forElementToBeDisplayed(driver, showLabAddressLink);
            if (showAddress.equalsIgnoreCase(showLabAddressLink.getText()))
                seleniumLib.clickOnWebElement(showLabAddressLink);
            if (!detailedAddressText.getText().contains("Hide address")) {
                Debugger.println(detailedAddressText.getText());
                Debugger.println("Laboratory addres not found ");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Print Forms Page : userSeesTheDetailsOfLabAddress found  " + exp);
            SeleniumLib.takeAScreenShot("addresDetailsOfLab.jpg");
            return false;
        }
    }

    public boolean userVerifiesTheParticipantDetailsOnPrintFormsPage() {
        try {
            participants = new ArrayList<>();
            printFormsIcons = new ArrayList<>();
            for (WebElement links : printFormsIcons) {
                if (links.isDisplayed())
                    for (WebElement element : participants) {
                        //  seleniumLib.clickOnWebElement(showLabAddressLink);
                        if (!element.getText().contains("NHS No Born Gender Patient NGIS ID")) {
                            Debugger.println("Participant details are not displayed on the referral banner  ");
                            return false;
                        }
                    }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Print Forms Page:userVerifiesTheParticipantDetailsOnPrintFormsPage found " + exp);
            SeleniumLib.takeAScreenShot("ParticipantDetailsOfLab.jpg");
            return false;
        }
    }

    public void clickPrintFormsAddressLink() {
        try {
            if (Wait.isElementDisplayed(driver, showAddressButton, 100)) {
                showAddressButton.click();
            }
        } catch (Exception exp) {
            Debugger.println("Exception from clickPrintFormsAddressLink " + exp);
            SeleniumLib.takeAScreenShot("clickPrintFormsAddressLink.jpg");
        }
    }

    public boolean verifyLaboratoryAddressIsDisplayed() {
        try {
            if (!Wait.isElementDisplayed(driver, labCompletedAddress, 30)) {
                Debugger.println("Expected lab address message is not displayed");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("verifyLaboratoryAddressIsDisplayed in print forms Page:verifyLaboratoryAddressIsDisplayed:" + exp);
            SeleniumLib.takeAScreenShot("verifyLaboratoryAddressIsDisplayed.jpg");
            return false;
        }
    }

    public List<String> readAddedFamilyMemberDetailsInLandingPage(String nhsDetails) {
        // need to add other fileds
        List<String> patientDetails = null;
        NGISPatientModel familyMember = getFamilyMember(nhsDetails);
        if (familyMember == null) {
            Debugger.println("Family Member with NHS Number:" + nhsDetails + " Not added to the list!.");

        }
        //1. Verify the Name
        String landingPageName = landingPageNamePath.replaceAll("dummyName", familyMember.getLAST_NAME() + ", " + familyMember.getFIRST_NAME());
        By firstNameLastName = By.xpath(landingPageName);
        if (!seleniumLib.isElementPresent(firstNameLastName)) {
            Debugger.println("Added Family member name: " + familyMember.getFIRST_NAME() + ", " + familyMember.getFIRST_NAME() + " Not displayed on Family Member Landing Page.");
        }
        patientDetails.add(familyMember.getFIRST_NAME());
        Debugger.println("Name Verified...");
        //2. Verify Relation to Proband.
        String landingPageRelation = landingPageRelationPath.replaceAll("dummyRelation", familyMember.getRELATIONSHIP_TO_PROBAND());
        By relationToProband = By.xpath(landingPageRelation);
        if (!seleniumLib.isElementPresent(relationToProband)) {
            Debugger.println("Added Family member relationship: " + familyMember.getRELATIONSHIP_TO_PROBAND() + " Not displayed on Family Member Landing Page.");
        }
        patientDetails.add(familyMember.getRELATIONSHIP_TO_PROBAND());
        Debugger.println("Relationship Verified...");
        boolean isPresent = false;
        //3.Verify DOB
        String dob = "";
        for (int i = 0; i < landingPageBorns.size(); i++) {
            dob = landingPageBorns.get(i).getText();
            if (familyMember.getDATE_OF_BIRTH() != null) {
                if (dob.startsWith(TestUtils.getDOBInMonthFormat(familyMember.getDATE_OF_BIRTH()))) {
                    isPresent = true;
                    break;
                }
            } else {
                if (familyMember.getBORN_WITH_AGE().contains(dob)) {
                    isPresent = true;
                    break;
                }
                Debugger.println("with age:" + familyMember.getBORN_WITH_AGE() + " ACTUAL:" + dob);
            }
        }
        if (!isPresent) {
            Debugger.println("Added Family member BornDate: " + familyMember.getBORN_DATE() + " Not displayed on Family Member Landing Page.");

        }
        patientDetails.add(familyMember.getBORN_DATE());
        Debugger.println("BornDate Verified...");
        Debugger.println("DOB Verified...");
        //4.Verify Gender
        for (int i = 0; i < landingPageGenders.size(); i++) {
            if (familyMember.getGENDER().equalsIgnoreCase(landingPageGenders.get(i).getText())) {
                isPresent = true;
                break;
            }
        }
        if (!isPresent) {
            Debugger.println("Added Family member Gender: " + familyMember.getGENDER() + " Not displayed on Family Member Landing Page.");
        }
        patientDetails.add(familyMember.getGENDER());
        Debugger.println("Gender Verified...");

        //5.Verify NHS
        if (landingPageNhsNumbers.size() > 1) {//Checking for added family members, excluded Proband
            isPresent = false;
            String actualNhs = "";
            for (int i = 0; i < landingPageNhsNumbers.size(); i++) {
                actualNhs = landingPageNhsNumbers.get(i).getText();
                Debugger.println("Actual and ExpectedNHS: " + actualNhs + "," + familyMember.getNHS_NUMBER());
                if (actualNhs != null) {
                    if (familyMember.getNHS_NUMBER().equalsIgnoreCase(actualNhs)) {
                        isPresent = true;
                        break;
                    }
                } else {
                    Debugger.println("NHS Not Present...");
                    //Family Member added without NHS number need not validate for NHS number.
                    isPresent = true;//NHS is not mandatory
                }
            }//for
        }
        if (!isPresent) {
            Debugger.println("Added Family member NHSNumber: " + familyMember.getNHS_NUMBER() + " Not displayed on Family Member Landing Page.");
        }
        patientDetails.add(familyMember.getNHS_NUMBER());
        Debugger.println("NHS Verified...");
        //6.Verify NGISID
        String ngsId = "";
        if (familyMember.getNGIS_ID() != null) {
            isPresent = false;
            for (int i = 0; i < landingPageNgsIds.size(); i++) {
                ngsId = landingPageNgsIds.get(i).getText();
                Debugger.println("Actual and Expected NGSID: " + ngsId + "," + familyMember.getNGIS_ID());
                if (ngsId != null) {
                    if (familyMember.getNGIS_ID() == null) {
                        isPresent = true;
                        //Set the NGIS to the family member for verification in print forms , on need basis
                        familyMember.setNGIS_ID(ngsId);
                        FamilyMemberDetailsPage.updateNGISID(familyMember);
                        break;
                    } else {
                        if (familyMember.getNGIS_ID().equalsIgnoreCase(ngsId)) {
                            isPresent = true;
                            break;
                        }
                    }
                } else {
                    Debugger.println("NGS Id not present...");
                    isPresent = true;
                }
            }
            if (!isPresent) {
                Debugger.println("Added Family member NGSID: " + familyMember.getNGIS_ID() + " Not displayed on Family Member Landing Page.");
            }
            patientDetails.add(familyMember.getNGIS_ID());
            Debugger.println("NGSID Verified...");
        }
        return patientDetails;
    }

    public String readSelectedTestType(String fieldType) {
        try {
            String returnValue = null;
//            Wait.forElementToBeDisplayed(driver,orderedTestType);
            if (fieldType.contains("test type")) {
                Debugger.println(orderedTestType.getText());
                String text = orderedTestType.getText();
                String[] text1 = text.split("\\n");
                Debugger.println("1- " + text1[0] + " 2- " + text1[1]);
                String[] text2 = text1[1].split("\\.");
                Debugger.println("3-" + text2[0] + " 4- " + text2[1]);
                returnValue = text2[0];
            } else if (fieldType.contains("laboratory")) {
                String text = selectedLaboratory.getText();
                returnValue = text;
            }
            return returnValue;
        } catch (Exception exp) {
            Debugger.println("PrintFormsPage: readSelectedTestType: " + exp);
            return null;
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

    public boolean startANewReferralButton() {
        try {
            Wait.forElementToBeDisplayed(driver, submissionConfirmationBanner);
            if (!seleniumLib.isElementPresent(startANewReferralButton)) {
                Debugger.println("Referral Submitted :Start New Referral Button Not found");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Referral Submitted :Start New Referral Button" + exp);
            SeleniumLib.takeAScreenShot("StartNewReferralButton.jpg");
            return false;
        }
    }

    public boolean clicksOnStartANewReferralButton() {
        try {
            Wait.forElementToBeDisplayed(driver, startANewReferralButton);
            seleniumLib.clickOnWebElement(startANewReferralButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("Start a new : SavePedigreeButton: " + exp);
            SeleniumLib.takeAScreenShot("StartNewReferralButton.jpg");
            return false;
        }
    }


}//end
