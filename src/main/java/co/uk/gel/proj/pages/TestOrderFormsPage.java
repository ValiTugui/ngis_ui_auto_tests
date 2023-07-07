package co.uk.gel.proj.pages;

import co.uk.gel.config.BrowserConfig;
import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;

public class TestOrderFormsPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public TestOrderFormsPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//input[@data-testid='test-file-input']")
    public WebElement chooseFilesButton;

    @FindBy(xpath = "//h3[contains(.,'Uploaded')]/..//div[@class='styles_item__stgKp styles_item__first__2UxVc']")
    public List<WebElement> uploadedFilesList;

    @FindBy(xpath = "//h3[contains(.,'Uploading')]/..//div[@class='styles_item__stgKp styles_item__first__2UxVc']")
    public List<WebElement> uploadingFilesList;

    @FindBy(xpath = "//h3[contains(.,'Deleted')]/..//div[@class='styles_item__stgKp styles_item__first__2UxVc']")
    public List<WebElement> deletedFilesList;

    @FindBy(xpath = "//h3[contains(.,'Uploading')]")
    public WebElement uploadingFilesHeader;

    @FindBy(xpath = "//h3[contains(.,'Uploaded')]")
    public WebElement uploadedFilesHeader;
    @FindBy(xpath = "//div[@class='styles_text__1aikh styles_text--4__3fmMD styles_rtc__2cRkG']")
    public WebElement uploadHasFailedBanner;

    @FindBy(xpath = "//div[@class='styles_stage-error__description__1L9UJ']/div[string-length(text()) > 0]")
    public List<WebElement> uploadFailedErrorMessages;


    String uploadFilepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator;

    public boolean clickOnChooseFiles() {
        try {
            if (Wait.isElementDisplayed(driver, chooseFilesButton, 10)) {
                try {
                    Action.clickElement(driver, chooseFilesButton);
                } catch (Exception e) {
                    seleniumLib.clickOnWebElement(chooseFilesButton);
                }
            }
            return true;
        } catch (Exception e) {
            Debugger.println("Exception in clicking on Choose files Button in Test Order Forms: " + e);
            return false;
        }
    }

    public boolean uploadTestOrderForms(List<String> fileNames) {
        String filePath = "";
        for (String fileName : fileNames) {
            filePath += uploadFilepath + fileName + "\n";
        }
        try {
            Wait.forInvisibilityOf(driver, chooseFilesButton, 10);
            if (BrowserConfig.getServerType().equalsIgnoreCase("LOCAL")) {
                if (!seleniumLib.upload(chooseFilesButton, filePath.replaceAll("[\n\r]$", ""))) {
                    Debugger.println("Could not upload the file:" + fileNames);
                    return false;
                }
            } else {
                filePath = uploadFilepath + fileNames.get(0);
                Wait.seconds(2);
                if (!seleniumLib.upload(chooseFilesButton, filePath)) {
                    Debugger.println("Could not upload the file from BS:" + filePath);
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in uploading Referral Form(s) in Test Order Forms: " + exp);
            SeleniumLib.takeAScreenShot("recordTypeUpload.jpg");
            return false;
        }
    }

    public boolean verifyUploadedForms(String listName, List<String> expectedListFiles) {
        List<String> actualListFiles = null;
        if (listName.equalsIgnoreCase("uploaded")) {
            Wait.forElementToBeDisplayed(driver, uploadedFilesList.get(0), 10);
            Wait.seconds(2);
            actualListFiles = Action.getValuesFromDropdown(uploadedFilesList);
        } else if (listName.equalsIgnoreCase("uploading")) {
            Wait.forElementToBeDisplayed(driver, uploadingFilesList.get(0), 10);
            Wait.seconds(2);
            actualListFiles = Action.getValuesFromDropdown(uploadingFilesList);
        } else if (listName.equalsIgnoreCase("deleted")) {
            Wait.forElementToBeDisplayed(driver, deletedFilesList.get(0), 10);
            Wait.seconds(2);
            actualListFiles = Action.getValuesFromDropdown(deletedFilesList);
        } else {
            return false;
        }
        return actualListFiles.containsAll(expectedListFiles);
    }

    public boolean verifyUploadOrderFormsErrors(List<String> expectedErrors) {
        Wait.forElementToBeDisplayed(driver, uploadFailedErrorMessages.get(0), 10);
        List<String> actualErrors = Action.getValuesFromDropdown(uploadFailedErrorMessages);
        return actualErrors.containsAll(expectedErrors);
    }

    public int getTheNumberOfFilesInTheList(String headerName) {
        int numberOfFilesInTheList = 0;

        switch (headerName.toLowerCase()) {
            case "uploading":
                numberOfFilesInTheList = getNumberOfFiles(uploadingFilesHeader);
                break;
            case "uploaded":
                numberOfFilesInTheList = getNumberOfFiles(uploadedFilesHeader);
                break;
            default:
                numberOfFilesInTheList = -1;
        }

        return numberOfFilesInTheList;
    }

    public int getNumberOfFiles(WebElement headerTitle) {
        Wait.forElementToBeDisplayed(driver, headerTitle, 10);
        return Integer.parseInt(headerTitle.getText().split(" ")[1]);
    }

    public boolean downloadTestOrderFormByName(WebElement fileLocator) {
        try {
            //Delete if File already present
            //Debugger.println("Deleting Files if Present...");
            TestUtils.deleteIfFilePresent("TestOrderForm", "");
            //Debugger.println("Attempting to download the Test Order form");
            if (!Wait.isElementDisplayed(driver, fileLocator, 30)) {
                Debugger.println("There is no form with this name: " + fileLocator + driver.getCurrentUrl());
                //SeleniumLib.takeAScreenShot("TestOrderForm.jpg");
                return false;
            }
            Action.clickElement(driver, fileLocator);
            Wait.seconds(5);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(fileLocator);
                Wait.seconds(5);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Could not locate the print button ..... " + exp);
                //SeleniumLib.takeAScreenShot("TestOrderFormDownload.jpg");
                return false;
            }
        }
    }
}
