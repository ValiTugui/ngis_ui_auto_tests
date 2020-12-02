package co.uk.gel.proj.neatPages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NeatPatientRecordPage {


    @FindBy(xpath = "//table[@data-testid='status-history']//th")
    public List<WebElement> tableFields;
    @FindBy(xpath = "//table[@data-testid='status-history']")
    public WebElement table;
    @FindBy(xpath = "//span[@aria-labelledby='isActive']//span")
    public WebElement recordStatus;
    @FindBy(xpath = "//span[contains(@class,'withErrorContainerCss')]//h2")
    public WebElement patientName;
    @FindBy(xpath = "//ul[contains(@class,'dataListCss')]//li//span[text()='Born']")
    public WebElement born;
    @FindBy(xpath = "//ul[contains(@class,'dataListCss')]//li//span[@aria-labelledby='dateOfBirth_1']")
    public WebElement bornDate;
    @FindBy(xpath = "//ul[contains(@class,'dataListCss')]//li//span[contains(@aria-labelledby,'humanReadableId')]")
    public WebElement patientNGISID;
    @FindBy(xpath = "//ul[contains(@class,'dataListCss')]//li//span[@aria-labelledby='nhsNumber_1']")
    public WebElement nhsNum;
    @FindBy(xpath = "//ul[contains(@class,'dataListCss')]//li//span[@aria-labelledby='address_1']")
    public WebElement patientAddress;
    @FindBy(xpath = "//li//div[2]/span")
    public WebElement reasonStatus;
    @FindBy(xpath = "//span[text()='Current status']/parent::div//span[@class='child-element']")
    public WebElement currentStatus;
    @FindBy(xpath = "//span[text()='Updated status']/parent::div//span[@class='child-element']")
    public WebElement updatedStatus;
    @FindBy(xpath = "//span[contains(text(),'You cannot reactivate this patient record.')]")
    public WebElement warningNotification;
    @FindBy(xpath = "//a[contains(text(),'An active record')]")
    public WebElement anActiveRecordLink;
    WebDriver driver;
    SeleniumLib seleniumLib;
    //WebElements
    String linkPathDummy = "//a[text()='dummyText']";
    String buttonPathDummy = "//button[contains(@class,'sharedByAllButtons')]/span[text()='dummyText']";
    @FindBy(xpath = "//div[@role='dialog']")
    WebElement dialogBox;
    @FindBy(xpath = "//div[@role='dialog']/h1")
    WebElement dialogBoxHeader;
    @FindBy(xpath = "//div/textarea[@id='statusUpdateSource_id']")
    WebElement justificationTextBox;
    @FindBy(xpath = "//div[contains(@data-testid,'notification')]")
    WebElement inactiveNotification;

    @FindBy(xpath = "//span[contains(text(),'Reason')]")
    WebElement reasonMandatoryFieldCheck;
    @FindBy(xpath = "//span[contains(text(),'Justification')]")
    WebElement justificationMandatoryFieldCheck;

    @FindBy(xpath = "(//label)[2]")
    WebElement errorButton;
    @FindBy(xpath = "(//label)[1]")
    WebElement duplicateButton;

    public NeatPatientRecordPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
        PageFactory.initElements(driver, this);
    }

    public Boolean mandatoryfieldvalidation(String fieldlabel, String fieldcolor) {
        try {
            String actColor = "";
            String expColor = "";

            if (fieldlabel.equals(reasonMandatoryFieldCheck.getText())) {
                actColor = reasonMandatoryFieldCheck.getCssValue("color");
                expColor = StylesUtils.convertFontColourStringToCSSProperty(fieldcolor);
            } else if (fieldlabel.equals(justificationMandatoryFieldCheck.getText())) {
                actColor = justificationMandatoryFieldCheck.getCssValue("color");
                expColor = StylesUtils.convertFontColourStringToCSSProperty(fieldcolor);

            }

            if (!actColor.isEmpty() && actColor.equalsIgnoreCase(expColor)) {
                return true;
            }
            Debugger.println(" fields section is not displayed");
            SeleniumLib.takeAScreenShot(fieldlabel + ".jpg");
            return false;

        } catch (Exception exp) {
            Debugger.println("Exception from finding mandatory fields" + exp);
            SeleniumLib.takeAScreenShot("Mandatory fields.jpg");
            return false;
        }
    }

    //Validation Methods
    public boolean clickOnLink(String linkText) {
        try {
            String linkPath = linkPathDummy.replace("dummyText", linkText);
            WebElement linkEle = driver.findElement(By.xpath(linkPath));
            if (!Wait.isElementDisplayed(driver, linkEle, 5)) {
                Debugger.println("The link-" + linkText + ", is not present in the page.");
                SeleniumLib.takeAScreenShot("LinkTextNotLoaded.jpg");
                return false;
            }
            seleniumLib.clickOnWebElement(linkEle);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clickOnLink:" + exp);
            SeleniumLib.takeAScreenShot("ClickLinkException.jpg");
            return false;
        }

    }

    public boolean clickOnButton(String buttonText) {
        try {
            String buttonPath = buttonPathDummy.replace("dummyText", buttonText);
            WebElement button = driver.findElement(By.xpath(buttonPath));
            if (!Wait.isElementDisplayed(driver, button, 5)) {
                Debugger.println("The button-" + buttonText + ", is not present in the page.");
                SeleniumLib.takeAScreenShot("ButtonNotLoaded.jpg");
                return false;
            }
            seleniumLib.clickOnWebElement(button);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clickOnButton:" + exp);
            SeleniumLib.takeAScreenShot("ClickButtonException.jpg");
            return false;
        }
    }

    public boolean verifyDialogBox(String dialogHeader) {
        try {
            if (!Wait.isElementDisplayed(driver, dialogBox, 5)) {
                Debugger.println("The change status to active/inactive confirmation dialog box is not displayed.");
                SeleniumLib.takeAScreenShot("DialogBoxError.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, dialogBoxHeader, 5)) {
                Debugger.println("The dialog box header-" + dialogHeader + ", is not present.");
                SeleniumLib.takeAScreenShot("DialogBoxError.jpg");
                return false;
            }
            String header = dialogBoxHeader.getText();
            if (!header.equalsIgnoreCase(dialogHeader)) {
                Debugger.println("Actual dialog box header is:" + header + ",But expected is:" + dialogHeader);
                SeleniumLib.takeAScreenShot("DialogBoxError.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clickOnButton:" + exp);
            SeleniumLib.takeAScreenShot("ClickButtonException.jpg");
            return false;
        }
    }

    public boolean fillJustificationTextBox(String reason) {
        try {
            if (!Wait.isElementDisplayed(driver, justificationTextBox, 5)) {
                Debugger.println("The justification text box is not present in the page");
                SeleniumLib.takeAScreenShot("FillJustificationBoxError.jpg");
                return false;
            }
            seleniumLib.sendValue(justificationTextBox, reason);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillJustificationTextBox:" + exp);
            SeleniumLib.takeAScreenShot("FillJustificationBox.jpg");
            return false;
        }
    }

    public boolean validateNotification(String notificationText) {
        try {
            if (!Wait.isElementDisplayed(driver, inactiveNotification, 5)) {
                Debugger.println("The notification is not present in the page");
                SeleniumLib.takeAScreenShot("NotificationError.jpg");
                return false;
            }
            String noticeText = inactiveNotification.getText();
            if (!noticeText.equalsIgnoreCase(notificationText)) {
                Debugger.println("Actual message is:" + noticeText + " ,BUt expected:" + notificationText);
                SeleniumLib.takeAScreenShot("NotificationTextMismatch.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillJustificationTextBox:" + exp);
            SeleniumLib.takeAScreenShot("FillJustificationBox.jpg");
            return false;
        }
    }

    public boolean clickReasonButton(String buttonText) {
        try {
            Wait.seconds(3);
            if (buttonText.equalsIgnoreCase("Duplicate")) {
                duplicateButton.click();
            } else {
                errorButton.click();
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clickReasonButton:" + exp);
            SeleniumLib.takeAScreenShot("ClickReasonButtonError.jpg");
            return false;
        }
    }

    public boolean validatePatientRecordStatus(String status) {
        try {
            if (!Wait.isElementDisplayed(driver, recordStatus, 5)) {
                Debugger.println("The record status is not present in the page");
                SeleniumLib.takeAScreenShot("PatientRecordStatusWrong.jpg");
                return false;
            }
            String actualStatus = recordStatus.getText();
            if (!actualStatus.equalsIgnoreCase(status)) {
                Debugger.println("Actual message is:" + actualStatus + " ,BUt expected:" + status);
                SeleniumLib.takeAScreenShot("PatientRecordStatusWrong.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from validatePatientRecordStatus:" + exp);
            SeleniumLib.takeAScreenShot("PatientRecordStatusWrong.jpg");
            return false;
        }

    }

    public boolean verifyPatientRecordHistoryDisplay() {
        try {
            NGISPatientModel patientDetails = new NGISPatientModel();
            if (!Wait.isElementDisplayed(driver, patientName, 20)) {
                Debugger.println("Patient Name not displayed in  record history Page.");
                return false;
            }
            String fullName = patientName.getText();
            String[] names = TestUtils.getPatientSplitNames(fullName);
            patientDetails.setFIRST_NAME(names[0]);
            patientDetails.setLAST_NAME(names[1]);
            patientDetails.setTITLE(names[2]);

            if (!seleniumLib.isElementPresent(born)) {
                Debugger.println("Born Details not displayed in Search Result.");
                return false;
            }
            try {
                String bornText = bornDate.getText();
                patientDetails.setBORN_WITH_AGE(bornText);
                bornText = bornText.substring(0, bornText.indexOf("("));
                bornText = TestUtils.removeAWord(bornText, "Born");
                patientDetails.setBORN_DATE(bornText.trim());
            } catch (Exception exp) {
                Debugger.println("Error in reading born details of patient record history.");
            }
            if (!seleniumLib.isElementPresent(patientNGISID)) {
                Debugger.println("patient NGIS ID not displayed in Search Result.");
                return false;
            }
            if (!seleniumLib.isElementPresent(nhsNum)) {
                Debugger.println("nhs number not displayed in Search Result.");
                return false;
            }
            if (!seleniumLib.isElementPresent(patientAddress)) {
                Debugger.println("Address not displayed in Search Result.");
                return false;
            }
            String address = patientAddress.getText();
            patientDetails.setFULL_ADDRESS(address);

            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyPatientRecordHistoryDisplay:" + exp);
            SeleniumLib.takeAScreenShot("VerifyPatientRecordHistoryDisplay.jpg");
            return false;
        }
    }

    public boolean verifyPatientRecordTable(DataTable fileds) {
        try {
            List<List<String>> tableHeaders = fileds.cells();
            if (!Wait.isElementDisplayed(driver, table, 5)) {
                Debugger.println("The status history table is not loaded properly.");
                SeleniumLib.takeAScreenShot("verifyTableStatusHistory.jpg");
                return false;
            }

            for (int i = 0; i <= tableHeaders.size(); i++) {
                if (!(tableHeaders.get(0).get(i).equalsIgnoreCase(tableFields.get(i).getText()))) {
                    Debugger.println("The headers is:" + tableHeaders.get(i).get(0) + " ,But expected:" + tableFields.get(i).getText());
                    SeleniumLib.takeAScreenShot("verifyPatientRecordTable.jpg");
                    return false;
                }
            }
            return true;

        } catch (Exception exp) {
            Debugger.println("Exception from verifyPatientRecordTable:" + exp);
            SeleniumLib.takeAScreenShot("VerifyPatientRecordTable.jpg");
            return false;
        }
    }

    public boolean verifyPatientRecordStatusField(String status1, String status2) {
        try {
            if (!currentStatus.getText().equalsIgnoreCase(status1)) {
                Debugger.println("The actual Current status is :" + currentStatus.getText() + " but expected is " + status1);
                SeleniumLib.takeAScreenShot("CurrentStatus.jpg");
                return false;
            }

            if (!updatedStatus.getText().equalsIgnoreCase(status2)) {
                Debugger.println("The actual Updated status is :" + updatedStatus.getText() + " but expected is " + status2);
                SeleniumLib.takeAScreenShot("UpdatedStatus.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyPatientRecordStatusField:" + exp);
            SeleniumLib.takeAScreenShot("VerifyPatientRecordStatusField.jpg");
            return false;
        }
    }

    public boolean verifyRecordStatusDetails(String status) {
        try {
            if (!currentStatus.getText().equalsIgnoreCase(status)) {
                Debugger.println("The actual Current status is :" + currentStatus.getText() + " but expected is " + status);
                SeleniumLib.takeAScreenShot("CurrentStatus.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyRecordStatusDetails:" + exp);
            SeleniumLib.takeAScreenShot("VerifyRecordStatusDetails.jpg");
            return false;
        }
    }

    public boolean verifyThePatientRecordReasonOptions() {
        try {
            if (!Wait.isElementDisplayed(driver, duplicateButton, 10)) {
                Debugger.println("Duplicate Button not found.");
                SeleniumLib.takeAScreenShot("VerifyThePatientRecordReasonOptions.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, errorButton, 10)) {
                Debugger.println("Error button not found.");
                SeleniumLib.takeAScreenShot("VerifyThePatientRecordReasonOptions.jpg");
                return false;
            }
            return true;

        } catch (Exception exp) {
            Debugger.println("Exception from Patient Record Reason: Duplicate button and Error button not found" + exp);
            SeleniumLib.takeAScreenShot("VerifyThePatientRecordReasonOptions.jpg");
            return false;
        }
    }

    public boolean verifyTheReasonInPatientRecordStatus(String reason) {
        try {
            if (!Wait.isElementDisplayed(driver, reasonStatus, 3)) {
                Debugger.println("Reason status not displayed.");
                SeleniumLib.takeAScreenShot("VerifyThePatientRecordReasonOptions.jpg");
                return false;
            }
            if (!reasonStatus.getText().equalsIgnoreCase(reason)) {
                Debugger.println("Reason is not found.");
                SeleniumLib.takeAScreenShot("CurrentStatus.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheReasonInPatientRecordStatus" + exp);
            SeleniumLib.takeAScreenShot("VerifyTheReasonInPatientRecordStatus.jpg");
            return false;
        }
    }

    public boolean clickOnActiveRecordLink(String activeRecordLink) {
        try {
            if (!Wait.isElementDisplayed(driver, warningNotification, 5)) {
                Debugger.println("The success notification is not present in the page");
                SeleniumLib.takeAScreenShot("ClickOnActiveRecordLink.jpg");
                return false;
            }
            seleniumLib.clickOnWebElement(anActiveRecordLink);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clickOnActiveRecordLink:" + exp);
            SeleniumLib.takeAScreenShot("ClickOnActiveRecordLink.jpg");
            return false;
        }
    }


}//end class