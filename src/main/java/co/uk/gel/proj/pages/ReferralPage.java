package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.security.ssl.Debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ReferralPage<check> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public ReferralPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(css = "span.styles_badge__1KpTw.badge--default")
    public WebElement getReferralHeaderStatus;

    @FindBy(css = "*[class*='referral-header']")
    public WebElement referralHeader;

    @FindBy(xpath = "//*[@id='referral__header']//button[text()='Submit']")
    public WebElement submitReferralButton;

    @FindBy(className = "todo-list")
    public WebElement toDoList;

    @FindBy(xpath = "//span[contains(string(),'Tumours')]/..")
    public WebElement tumourToDoList;

    @FindBy(css = "div[class*='referral__main']")
    public WebElement sectionBody;

    @FindBy(css = "h1[class*='page-title']")
    public WebElement pageTitle;

    @FindBy(css = "button[class*='referral-navigation__continue']")
    public WebElement saveAndContinueButton;

    @FindBy(css = "button[class*='referral-navigation__button--back']")
    public WebElement backLink;


    @FindBy(xpath = "//ul[contains(@class,'referral-header-details')]/li[1]/strong")
    public WebElement referralHeaderPatientName;

    //span[text()='Born']/..//strong
    @FindBy(xpath = "//span[text()='Born']/..//strong")
    public WebElement referralHeaderBorn;

    @FindBy(xpath = "//span[text()='Gender']/..//strong")
    public WebElement referralHeaderGender;

    @FindBy(xpath = "//span[text()='NHS No.']/..//strong")
    public WebElement referralHeaderNhsNo;

    @FindBy(xpath = "//span[text()='Patient NGIS ID']/..//strong")
    public WebElement referralHeaderPatientNgisId;

    @FindBy(xpath = "//span[text()='Clinical Indication']/..//strong")
    public WebElement referralHeaderClinicalId;

    @FindBy(xpath = "//span[text()='Referral ID']/..//strong")
    public WebElement referralHeaderReferralId;

    @FindBy(css = "*[class*='referral-header__badge']")
    public WebElement referralStatus;

    @FindBy(css = "*[class*='referral-header__cancel-reason']")
    public WebElement referralCancelReason;

    @FindBy(css = "*[class*='logout-button']")
    public WebElement logoutButton;

    @FindBy(css = "*[class*='header__user']")
    public WebElement user;

    @FindBy(css = "*[class*='header__right-area']")
    public WebElement headerRightArea;

    @FindBy(css = "*[class*='referral-header__cancel']")
    public WebElement cancelReferralLink;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(xpath = "//label[contains(@for,'cancel-options')]//following::div")
    public WebElement cancelReasonDropdown;

    @FindBy(css = "*[class*='notification-bar--success']")
    public WebElement cancelReferralNotification;

    @FindBy(css = "button[class*='modal__action']")
    public List<WebElement> cancelReferralButtons;

    @FindBy(css = "*[class*='error-message']")
    public WebElement errorMessage;

    @FindBy(xpath = "//div[contains(@class,'notification--success')]/div[2]")
    public WebElement genericSuccessNotification;

    @FindBy(css = "*[class*='helix']")
    public List<WebElement> helix;
    //Family Member Search
    @FindBy(xpath = "//button[contains(text(),'Add family member')]")
    public WebElement addFamilyMember;

    @FindBy(xpath = "//p[contains(@class,'hint__text')]")
    public List<WebElement> hintText;

    @FindBy(css = "*[class*='error-message__text']")
    public List<WebElement> errorMessages;

    @FindBy(css = "*[class*='styles_field-label__2Ymo6']")
    public List<WebElement> genericFieldLabels;

    @FindBy(css = "div[class*='error-message__text']")
    public List<WebElement> validationErrors;

    @FindBy(xpath = "//table/thead/tr/th[text()!='']")
    public List<WebElement> tableColumnHeaders;

    @FindBy(css = "*[class*=consent-page-full]")
    public WebElement consentDocument;
    @FindBy(css = "*[class*=shadow]")
    public WebElement consentDocumentShadow;
    @FindBy(id = "printable-form-id")
    public WebElement consentDocumentPrintableForm;
    @FindBy(css = "*[class*=summary-header-container]")
    public WebElement consentDocumentHeaderInfo;


    String valuesInReferralHeaderBar = "strong[class*='header-item']";
    String stageIsMarkedAsMandatoryToDo = "//a[contains(@href,'" + "dummyStage" + "')]//descendant::span[3]";
    String stageIsToDo = "a[href*='" + "dummyStage" + "']";
    String stageName = "//a[contains(@href,'" + "dummyStage" + "')]//child::span[2]";
    String helixIcon = "*[class*='helix']";
    String mandatoryToDOIconLocator = "todo__required-icon";
    String currentStageLocator = "todo--is-current";
    String stageCompleteLocator = "todo--is-complete";
    String cancelReferralLocator = "*[class*='button--disabled-clickable']";

    @FindBy(xpath = "//div[contains(@class,'notification-bar__text')]")
    public WebElement notificationSuccessMessage;

    public void checkThatReferalWasSuccessfullyCreated() {
        Wait.forElementToBeDisplayed(driver, referralHeader, 100);
        Wait.forElementToBeDisplayed(driver, toDoList, 100);
        Wait.forElementToBeDisplayed(driver, sectionBody);
        Wait.forNumberOfElementsToBeEqualTo(driver, By.cssSelector(valuesInReferralHeaderBar), 7);
    }

    public String getText(WebElement element) {
        Wait.forElementToBeDisplayed(driver, element);
        return element.getText();
    }

    public void clickSaveAndContinueButton() {
        try {
            Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
            Wait.forElementToBeClickable(driver,saveAndContinueButton);
            Actions.retryClickAndIgnoreElementInterception(driver, saveAndContinueButton);
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted
            // Click.element(driver, saveAndContinueButton)
            if (helix.size() > 0) {
                try {
                    Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
                } catch (TimeoutException texp) {
                    //Still the helix in action, waiting for another 30 seconds.
                    Debugger.println("ReferralPage:clickSaveAndContinueButton, Still helix in action, waiting for another 30 seconds:" + texp);
                    Wait.seconds(30);
                    Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
                }
            }
        } catch (UnhandledAlertException exp) {
            Debugger.println("UnhandledAlertException from ReferralPage:clickSaveAndContinueButton: " + exp);
            seleniumLib.dismissAllert();
        }catch (Exception exp) {
            Debugger.println("Exception from ReferralPage:clickSaveAndContinueButton: " + exp);
            SeleniumLib.takeAScreenShot("RefPageSaveAndContinue.jpg");
            Assert.assertFalse("ReferralPage:clickSaveAndContinueButton:Exception:" + exp, true);
        }
    }
    public void clickSaveAndContinueButtonOnThePatientChoiceComponent() {
        try {
            try {
                Wait.forElementToBeDisplayed(driver, consentDocument, 200);
                Wait.forElementToBeDisplayed(driver, consentDocumentShadow, 200);
                Wait.forElementToBeDisplayed(driver, consentDocumentPrintableForm, 200);
                Wait.forElementToBeDisplayed(driver, consentDocumentHeaderInfo, 200);
            }catch (Exception exp){
                Debugger.println("Consent Form is not visible ...");
                SeleniumLib.takeAScreenShot("PatientChoiceConsentDocument.jpg");
                //Assert.assertFalse("Consent Form is not visible ...Exception : " + exp, true);
            }

            Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
            Wait.forElementToBeClickable(driver,saveAndContinueButton);
            Actions.retryClickAndIgnoreElementInterception(driver, saveAndContinueButton);
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted
            // Click.element(driver, saveAndContinueButton)
            if (helix.size() > 0) {
                try {
                    Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
                } catch (TimeoutException texp) {
                    //Still the helix in action, waiting for another 30 seconds.
                    Debugger.println("ReferralPage:clickSaveAndContinueButton, Still helix in action, waiting for another 30 seconds:" + texp);
                    Wait.seconds(30);
                    Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
                }
            }
        } catch (Exception exp) {
            Debugger.println("Exception from ReferralPage:clickSaveAndContinueButton: " + exp);
            SeleniumLib.takeAScreenShot("RefPageSaveAndContinue.jpg");
            seleniumLib.clickOnWebElement(saveAndContinueButton);
            //Assert.assertFalse("ReferralPage:clickSaveAndContinueButton:Exception:" + exp, true);
        }
    }

    public boolean saveAndContinueButtonIsDisplayed() {
        try {
            Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
            Wait.forElementToBeClickable(driver, saveAndContinueButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("ReferralPage:Exception from Clicking on saveAndContinueButton:" + exp);
            SeleniumLib.takeAScreenShot("RefSaveAndContinue.jpg");
            return false;
        }
    }


    public void checkThatReferralWasSuccessfullyCreated() {
        try {
            Wait.forElementToBeDisplayed(driver, getReferralHeaderStatus, 200);
            Wait.forElementToBeDisplayed(driver, referralHeader, 100);
            Wait.forElementToBeDisplayed(driver, toDoList, 100);
            Wait.forElementToBeDisplayed(driver, sectionBody);
            Wait.forNumberOfElementsToBeEqualTo(driver, By.cssSelector(valuesInReferralHeaderBar), 7);
        } catch (Exception exp) {
            Debugger.println("ReferralPage:checkThatReferralWasSuccessfullyCreated:Exception." + exp);
            SeleniumLib.takeAScreenShot("ReferralNotCreated.jpg");
            Assert.assertFalse("Referral Could not created Successfully. Check ReferralNotCreated.jpg", true);
        }
    }

    public boolean checkThatToDoListSuccessfullyLoaded() {
        try {
            Wait.forElementToBeDisplayed(driver, toDoList, 200);
            return Wait.isElementDisplayed(driver, toDoList, 30);
        }catch(Exception exp){
            Debugger.println("ToDoList not listed even after waiting period...waiting for another 30 seconds...");
            return Wait.isElementDisplayed(driver,toDoList,30);
        }
    }

    public String getPartialUrl(String stage) {
        String partialUrl = null;
        HashMap<String, String> partialUrls = new HashMap<String, String>();
        partialUrls.put("Patient details", "patient-details");
        partialUrls.put("Requesting organisation", "ordering-entity");
        partialUrls.put("Test package", "test-package");
        partialUrls.put("Responsible clinician", "clinical-details");
        partialUrls.put("Clinical questions", "clinical-questions");
        partialUrls.put("Notes", "notes");
        partialUrls.put("Print forms", "downloads");
        partialUrls.put("Family members", "family-members");
        partialUrls.put("Tumours", "tumours");
        partialUrls.put("Samples", "samples");
        partialUrls.put("Panels", "panels");
        partialUrls.put("Patient choice", "patient-choice");
        partialUrls.put("Pedigree", "pedigree");
        if (partialUrls.containsKey(stage)) {
            partialUrl = partialUrls.get(stage);
        }
        return partialUrl;
    }

    public void navigateToStage(String stage) {
        Wait.forElementToBeDisplayed(driver, toDoList, 100);
        String webElementLocator = stageIsToDo.replace("dummyStage", getPartialUrl(stage));
        WebElement referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
        Wait.forElementToBeDisplayed(driver, referralStage);
        try {
            seleniumLib.scrollToElement(referralStage);
            Actions.clickElement(driver, referralStage);
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("navigateToStage.jpg");
            //Sometimes click on stage link on second time gives ElementClickInterceptedException.
            // Below code added to handle that.
            Actions.scrollToTop(driver);
            Actions.clickElement(driver, referralStage);
        }

    }

    public boolean stageIsSelected(String stage) {
        Wait.forURLToContainSpecificText(driver, getPartialUrl(stage));
        Wait.forElementToBeDisplayed(driver, toDoList);
        String webElementLocator = stageIsToDo.replace("dummyStage", getPartialUrl(stage));
        WebElement referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
        webElementLocator = "";
        webElementLocator = stageName.replace("dummyStage", getPartialUrl(stage));
        WebElement stageName = toDoList.findElement(By.xpath(webElementLocator));

        boolean check1 = referralStage.getAttribute("class").contains(currentStageLocator);
        boolean check2 = getText(stageName).contains(stage);

        if (check1 == true && check2 == true) return true;
        else return false;

    }

    public boolean stageIsCompleted(String stage) {
        try {
            Wait.forElementToBeDisplayed(driver, toDoList);
            String webElementLocator = stageIsToDo.replace("dummyStage", getPartialUrl(stage));
            Wait.seconds(2);
            WebElement referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, referralStage);
            Wait.seconds(2);
            boolean status = referralStage.getAttribute("class").contains(stageCompleteLocator);
            if (status == true) {
                return true;
            }
            Debugger.println("Status of Stage.." + stage + " is: " + referralStage.getAttribute("class") + ", but expected to be complete.");
            SeleniumLib.takeAScreenShot("StageComplete.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception in Checking Stage Completion Status: " + exp);
            SeleniumLib.takeAScreenShot("StageComplete.jpg");
            return false;
        }
    }

    public boolean stageIsMandatoryToDo(String stage) {
        Wait.forElementToBeDisplayed(driver, toDoList);
        String webElementLocator = stageIsMarkedAsMandatoryToDo.replace("dummyStage", getPartialUrl(stage));
        List<WebElement> mandatoryAsteriskSymbol = toDoList.findElements(By.xpath(webElementLocator));
        boolean isStageStatusIsToDO = mandatoryAsteriskSymbol.get(0).getAttribute("class").contains(mandatoryToDOIconLocator);
        boolean isStageHasAsteriskPresent = mandatoryAsteriskSymbol.size() == 1;
        if (isStageStatusIsToDO && isStageHasAsteriskPresent) {
            return true;
        } else {
            return false;
        }
    }

    public String acknowledgeThePromptAlertPopups(String acknowledgeMessage) {
        String actualAlertText = null;
        if (acknowledgeMessage.equalsIgnoreCase("Accept")) {
            Wait.forAlertToBePresent(driver);
            Wait.seconds(2);
            actualAlertText = driver.switchTo().alert().getText();
            Actions.acceptAlert(driver);
            Debugger.println("The alert message :: " + actualAlertText);
            Debugger.println("URL info after accepting alert :: " + driver.getCurrentUrl());
        } else if (acknowledgeMessage.equalsIgnoreCase("Dismiss")) {
            Wait.forAlertToBePresent(driver);
            Wait.seconds(2);
            actualAlertText = Actions.getTextOfAlertMessage(driver);
            Actions.dismissAlert(driver);
            Debugger.println("The alert message :: " + actualAlertText);
            Debugger.println("URL info after accepting alert :: " + driver.getCurrentUrl());
        }
        return actualAlertText;
    }

    public void clickLogoutButton() {
        Wait.forElementToBeDisplayed(driver, headerRightArea);
        Actions.clickElement(driver, logoutButton);
    }


    public String getTheCurrentPageTitle() {
        Wait.forElementToBeDisplayed(driver, pageTitle);
        return Actions.getText(pageTitle);
    }

    public void navigateToFamilyMemberSearchPage() {
        Wait.forElementToBeDisplayed(driver, addFamilyMember);
        Actions.clickElement(driver, addFamilyMember);
    }

    public List<String> getTheListOfHelpHintTextsOnCurrentPage() {
        Wait.forElementToBeDisplayed(driver, pageTitle);
        List<String> actualHelpHintTexts = new ArrayList<>();

        for (WebElement fieldHelpHintText : hintText) {
            actualHelpHintTexts.add(fieldHelpHintText.getText().trim());
        }
        String currentPage = getTheCurrentPageTitle();
        Debugger.println("Actual Help-Hint Texts on" + ":" + currentPage + ": page :" + actualHelpHintTexts);
        return actualHelpHintTexts;
    }

    public void clickOnTheBackLink() {
        Actions.retryClickAndIgnoreElementInterception(driver, backLink);
    }

    public String successNotificationIsDisplayed() {
        Wait.forElementToBeDisplayed(driver, genericSuccessNotification);
        return Actions.getText(genericSuccessNotification);
    }

    public boolean verifyTheErrorMessageDisplay(String errorMessage, String fontColor) {
        try {
            Wait.seconds(5);
            if (validationErrors.size() > 0) {
                String actualMessage = validationErrors.get(0).getText();
                if (!errorMessage.equalsIgnoreCase(actualMessage)) {
                    Debugger.println("Expected Message: " + errorMessage + ", but Actual Message: " + actualMessage);
                    return false;
                }
                String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(fontColor);
                String actColor = validationErrors.get(0).getCssValue("color");
                if (!expectedFontColor.equalsIgnoreCase(actColor)) {
                    Debugger.println("Expected Color: " + expectedFontColor + ", but Actual Color: " + actColor);
                    return false;
                }

            }else{
                Debugger.println("Expected Error Message: "+errorMessage+", but no error message displayed. Check NoErrorMessage.jpg");
                SeleniumLib.takeAScreenShot("NoErrorMessage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from validating Error Message " + exp);
            return false;
        }
    }

    public boolean verifyThePageTitlePresence(String expTitle) {
        By pageTitle = By.xpath("//h1[contains(text(),'" + expTitle + "')]");
        if (!seleniumLib.isElementPresent(pageTitle)) {
            Wait.forElementToBeDisplayed(driver, driver.findElement(pageTitle));
            if (!seleniumLib.isElementPresent(pageTitle)) {
                Debugger.println("Expected title :" + expTitle + " not loaded in the page.");
                return false;
            }
        }
        return true;
    }

    public void clickOnSaveAndContinueButton() {
        try {
            Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
            if (!Wait.isElementDisplayed(driver, saveAndContinueButton, 30)) {
                Debugger.println("Save and Continue Button not displayed even after wait period.");
            }
            Wait.forElementToBeClickable(driver, saveAndContinueButton);
            Wait.seconds(2);
            Click.element(driver, saveAndContinueButton);
            Wait.seconds(5);
            if (helix.size() > 0) {
                Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
            }
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("FamilyDetailsSaveContinue.jpg");
            Debugger.println("Could not click on Save and Continue...." + exp);
        }
    }

    public List<String> getTheListOfFieldsErrorMessagesOnCurrentPage() {

        Wait.forElementToBeDisplayed(driver, pageTitle);
        List<String> actualErrorMessages = new ArrayList<>();
        for (WebElement errorMessage : errorMessages) {
            actualErrorMessages.add(errorMessage.getText().trim());
        }
        Debugger.println("Actual-Error Messages" + actualErrorMessages);
        return actualErrorMessages;
    }

    public List<String> getTheFieldsLabelsOnCurrentPage() {
        Wait.forElementToBeDisplayed(driver, pageTitle);
        List<String> actualFieldLabels = new ArrayList<>();

        for (WebElement fieldLabel : genericFieldLabels) {
            actualFieldLabels.add(fieldLabel.getText().trim());
        }
        Debugger.println("`Actual field labels " + actualFieldLabels);
        return actualFieldLabels;
    }

    public List<String> getTableColumnHeaders() {
        List<String> actualTableHeaders = new ArrayList<>();
        for (WebElement header : tableColumnHeaders) {
            actualTableHeaders.add(header.getText().trim());
        }
        return actualTableHeaders;
    }
    public void submitReferral(){
        try{
            if(Wait.isElementDisplayed(driver,submitReferralButton,100)){
                submitReferralButton.click();
            }
        }catch(Exception exp){
            Debugger.println("Exception from submitting Referral "+exp);
            SeleniumLib.takeAScreenShot("submitReferral.jpg");
        }
    }
    public void clicksOnCancelReferralLink(){
        try{
            if(Wait.isElementDisplayed(driver,cancelReferralLink,100)){
                cancelReferralLink.click();
                Wait.seconds(5);//Waiting for 5 seconds to load the popup dialog.
            }
        }catch(Exception exp){
            Debugger.println("Exception from Cancelling Referral "+exp);
            SeleniumLib.takeAScreenShot("cancelReferral.jpg");
        }
    }
    public void selectCancellationReason(String reason) {
        try {
            Actions.clickElement(driver, cancelReasonDropdown);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectValueFromDropdown(dropdownValue, reason);
        }catch(Exception exp){
            Debugger.println("Exception from cancel drop down: "+exp);
            SeleniumLib.takeAScreenShot("cancelReferralDD.jpg");
        }
    }

    public void clickCancelReferralLink() {
        Actions.clickElement(driver, cancelReferralLink);
    }
    public boolean cancelReferralConfirmationIsDisplayed() {
        try {
            Wait.forElementToBeDisplayed(driver, cancelReferralNotification);
            Wait.forElementToDisappear(driver, By.cssSelector(cancelReferralLocator));
            return true;
        } catch (Exception exp){
            Debugger.println("Cancel Referral notification is not displayed");
            return false;
        }

    }

    public boolean cancelReasonMatches(String reason) {
        return reason.equalsIgnoreCase(Actions.getText(referralCancelReason));
    }

    public boolean verifyTheReferralStatus(String expectedStatus) {
        Wait.forElementToBeDisplayed(driver, referralStatus);
        return Actions.getText(referralStatus).contains(expectedStatus);
    }

    public String getPatientNGISId(){
        Wait.isElementDisplayed(driver, referralHeaderPatientNgisId, 3);
        return Actions.getText(referralHeaderPatientNgisId);
    }

    public String getPatientReferralId(){
        Wait.isElementDisplayed(driver, referralHeaderReferralId, 3);
        return Actions.getText(referralHeaderReferralId);
    }

    public String getPatientClinicalIndication(){
        Wait.isElementDisplayed(driver, referralHeaderClinicalId, 3);
        return Actions.getText(referralHeaderClinicalId);
    }
    public void submitCancellation() {
        try {
            Actions.clickElement(driver, cancelReferralButtons.get(1));
            Wait.seconds(5);//Waiting for 5 seconds to load the cancellation message.
        }catch(Exception exp){
            Debugger.println("Exception from Clicking on cancel Referral: "+exp);
            SeleniumLib.takeAScreenShot("cancelRefButton.jpg ");
        }
    }
    public boolean verifyCancellationMessage(String expMessage){
        try{
             Wait.forElementToBeDisplayed(driver,notificationSuccessMessage);
            if(!Wait.isElementDisplayed(driver,notificationSuccessMessage,30)){
                String actMessage = notificationSuccessMessage.getText();
                if(!expMessage.equalsIgnoreCase(actMessage)) {
                    Debugger.println("Expected Message: " + expMessage + " not displayed after referral cancellation.ActualMessage:"+actMessage);
                    return false;
                }
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from Cancelling Referral."+exp);
            SeleniumLib.takeAScreenShot("cancelRefMessage.jpg");
            return false;
        }
    }
}
