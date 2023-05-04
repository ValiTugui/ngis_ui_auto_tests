package co.uk.gel.proj.pages;

import co.uk.gel.config.BrowserConfig;
import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    @FindBy(xpath = "//div[@class='styles_item__stgKp styles_item__first__2UxVc']")
     public List<WebElement> uploadedFilesList;

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
            if(BrowserConfig.getServerType().equalsIgnoreCase("LOCAL")) {
                if (!seleniumLib.upload(chooseFilesButton, filePath.replaceAll("[\n\r]$", ""))) {
                    Debugger.println("Could not upload the file:" + fileNames);
                    return false;
                }
            }else{
                filePath = "C:\\Users\\hello\\Desktop\\documents\\pdf-sample2.pdf";
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

    public boolean verifyUploadedFiles(List<String> expectedUploadedFiles){
        Wait.isElementDisplayed(driver, uploadedFilesList.get(0), 10);
        Wait.seconds(2);
        List<String> actualUploadedList = Action.getValuesFromDropdown(uploadedFilesList);
        return actualUploadedList.containsAll(expectedUploadedFiles);
    }
}
