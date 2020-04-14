package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReferralPage<check> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public ReferralPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(css = "*[class*='child-element']")
    public WebElement getReferralHeaderStatus;

    @FindBy(css = "*[class*='referral-header']")
    public WebElement referralHeader;

    //@FindBy(xpath = "//*[@id='referral__header']//button[text()='Submit']")
    @FindBy(xpath = "//*[@id='referral__header']//button/span[text()='Submit']")
    public WebElement submitReferralButton;

    @FindBy(css = "*[data-testid*='referral-sidebar']")
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

    @FindBy(xpath = "//ul[contains(@class,'referral-header__details')]//span[@aria-labelledby='name_1']")
    public WebElement referralHeaderPatientName;

    //@FindBy(xpath = "//span[text()='Born']/..//strong")
    @FindBy(xpath = "//span[text()='Born']/following-sibling::span")
    public WebElement referralHeaderBorn;

    //@FindBy(xpath = "//span[text()='Gender']/..//strong")
    @FindBy(xpath = "//span[text()='Gender']/following-sibling::span")
    public WebElement referralHeaderGender;

    //@FindBy(xpath = "//span[text()='NHS No.']/..//strong")
    @FindBy(xpath = "//span[text()='NHS No.']/following-sibling::span")
    public WebElement referralHeaderNhsNo;

    //@FindBy(xpath = "//span[text()='Patient NGIS ID']/..//strong")
    @FindBy(xpath = "//span[text()='Patient NGIS ID']/following-sibling::span")
    public WebElement referralHeaderPatientNgisId;

    //@FindBy(xpath = "//span[text()='Clinical Indication']/..//strong")
    @FindBy(xpath = "//span[text()='Clinical Indication']/following-sibling::span")
    public WebElement referralHeaderClinicalId;

    //@FindBy(xpath = "//span[text()='Referral ID']/..//strong")
    @FindBy(xpath = "//span[text()='Referral ID']/following-sibling::span")
    public WebElement referralHeaderReferralId;

    @FindBy(css = "*[class*='downloads__notice']")
    public WebElement submissionConfirmationBanner;

    @FindBy(css = "*[class*='notice__title']")
    public WebElement submissionConfirmationBannerTitle;

    //@FindBy(css = "*[class*='referral-header__details']")
    @FindBy(xpath = "//div[@id='referral__header']//div[@data-testid='spacing']//span[@class='child-element']")
    public WebElement referralStatus;

    //    @FindBy(css = "*[class*='referral-header__cancel-reason']")
    @FindBy(css = "*[class*='referral-header__cancel']")
    public WebElement referralCancelReason;

    // @FindBy(css = "*[href*='signout']")
    @FindBy(xpath = "//*[text()='Log out']")
    public WebElement logoutButton;

    @FindBy(css = "*[class*='header__user']")
    public WebElement user;

    //    @FindBy(css = "*[class*='referral-header__cancel']")
    @FindBy(css = "*[class*='cancel__button_']")
    public WebElement cancelReferralLink;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(xpath = "//label[contains(@for,'cancel-options')]//following::div[contains(@class,'-container')]")
    public WebElement cancelReasonDropdown;

    @FindBy(css = "*[data-testid*='notification-success']")
    public WebElement cancelReferralNotification;

    //    @FindBy(css = "button[class*='modal__action']")
    @FindBy(xpath = "//div[@role='dialog']//div[contains(@class,'actionButtonCss')]//button")
    public List<WebElement> cancelReferralButtons;

    @FindBy(css = "*[class*='error-message']")
    public WebElement errorMessage;

    @FindBy(css = "*[data-testid*='notification-success']")
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

    @FindBy(xpath = "//div[contains(@class,'indicatorContainer')]//*[name()='svg']//*[name()='path']")
    public List<WebElement> clearDropDownValue;

    @FindBy(xpath = "//*[contains(@class,'header')]//p")    //*[contains(@class,'header')]//child::a
    public WebElement genomicMedicineServicelogo;

    @FindBy(xpath = "//*[contains(@class,'header')]//span[contains(@class,'css-d8n')]")
    public WebElement userName;

    @FindBy(xpath = "//*[contains(@href,'signout')]//following::div")
    public WebElement nhsEnglandLogoFromHeader;

    @FindBy(css = "svg[aria-labelledby*='nhsTitle']")
    public WebElement nhsEnglandLogoFromFooter;

    @FindBy(css = "svg[aria-labelledby*='genomicsEnglandTitle']")
    public WebElement genomicsEnglandLogoFromFooter;

    @FindBy(css = "*[href*='jiraservicedesk']")
    public WebElement footerServiceDeskLink;

    @FindBy(css = "*[href*='privacy-policy']")
    public WebElement footerPrivacyPolicyLink;

    @FindBy(xpath = "//span[text()='Clinical content is © NHS England']")
    public WebElement footerCopyrightText;

    String valuesInReferralHeaderBar = "//*[contains(@class,'referral-header')]//child::li";
    String stageIsMarkedAsMandatoryToDo = "a[href*='" + "dummyStage" + "']";
    String stageIsToDo = "a[href*='" + "dummyStage" + "']";
    String helixIcon = "*[class*='helix']";
    String stageCompleteLocator = "*[data-testid*='completed-icon']";
    String cancelReferralLocator = "*[class*='button--disabled-clickable']";

    //    @FindBy(xpath = "//div[contains(@class,'notification-bar__text')]")
    @FindBy(xpath = "//div[@data-testid='notification-success']")
    public WebElement notificationSuccessMessage;

    //For Global Patient Banner Verification - Family Members
    @FindBy(xpath = "//div[contains(@class,'participant-info')]//span[text()='Born']/following::span[contains(@aria-labelledby,'dateOfBirth')]")
    public WebElement familyMemberDob;
    @FindBy(xpath = "//div[contains(@class,'participant-info')]//span[text()='Gender']/following::span[contains(@aria-labelledby,'gender')]")
    public WebElement familyMemberGender;
    @FindBy(xpath = "//div[contains(@class,'participant-info')]//span[text()='NHS No.']/following::span[contains(@aria-labelledby,'nhsNumber')]")
    public WebElement familyMemberNhsNumbers;
    @FindBy(xpath = "//div[contains(@class,'participant-info')]//span[text()='Patient NGIS ID']/following::span[contains(@aria-labelledby,'ngisId')]")
    public WebElement familyMemberNgisId;
    @FindBy(xpath = "//div[contains(@class,'participant-info')]//span[text()='NHS No.']/following::span[contains(@aria-labelledby,'nhsNumber')]//span[contains(@class,'_chunk__separator_')]")
    public List<WebElement> nhsChunkSeparators;
    @FindBy(xpath = "//div[contains(@class,'participant-info')]//span[text()='Patient NGIS ID']/following::span[contains(@aria-labelledby,'ngisId')]//span[contains(@class,'_chunk__separator_')]")
    public List<WebElement> ngisIDChunkSeparators;
    @FindBy(xpath = "//div[contains(@class,'participant-info')]//h2[contains(@class,'css-')]")
    public WebElement familyMemberNames;

    @FindBy(xpath = "//div[@data-testid='referral-sidebar']//a[@data-selected='true']")
    public WebElement activeStage;

    String mandatoryFieldSymbol = "//dummyFieldType[contains(text(),'dummyLabel')]/span";
    String mandatoryFieldLabel = "//label[contains(text(),'dummyLabel')]";
    String mandatoryAsterix = "*[data-testid*='mandatory-icon']";
    String stageCompletedMark = "//a[contains(text(),'dummyStage')]//*[name()='svg' and @data-testid='completed-icon']";

    String referralButtonStatusTitle = "//*[contains(@class,'referral-header__column')]//span[text()='dummyStatus']";

    @FindBy(xpath = "//div[@role='dialog']/h1")
    WebElement dialogTitle;
    //Defined new element without the span, as the attribute of the button needs to read for enable/disable status
    @FindBy(xpath = "//*[@id='referral__header']//button")
    public WebElement referralSubmitButton;

    @FindBy(xpath = "//div[@role='dialog']")
    public WebElement dialogBox;

    @FindBy(xpath = "//label[@for='cancel-options']")
    public WebElement cancelReasonQuestion;

    @FindBy(xpath = "//label[@for='cancel-options']//following-sibling::div[contains(@class,'_hint_')]")
    public WebElement cancelWarningText;

    @FindBy(xpath = "//div[@role='dialog']//button")
    public WebElement dialogBoxCloseButton;

    @FindBy(xpath = "//span[text()='Cancelled']")
    public WebElement cancelledReferralStatus;

    @FindBy(xpath = "//div[@data-testid='referral-card-header']")
    public WebElement referralCardHeader;

    @FindBy(xpath = "//div[@data-testid='referral-card-status-info']")
    public WebElement referralCancelReasonOnCard;

    @FindBy(xpath = "//*[contains(@class,'referral-header__stage-list')]//a")
    List<WebElement> incompleteSection;

    @FindBy(xpath = "//*[@role = 'dialog']")
    WebElement mandatoryStageDialogBox;

    @FindBy(xpath = "//*[@role = 'dialog']//button[contains(@class,'closeButton')]")
    WebElement mandatoryStageDialogBoxCloseButton;

    @FindBy(xpath = "//h2[text()='Add information in any order']")
    public WebElement addInformationInOrderText;

    @FindBy(xpath = "//h1[contains(text(),'Print sample forms')]")
    public WebElement printSampleFormsTitle;

    @FindBy(xpath = "//div[@id='referral__header']")
    public WebElement referralHeaderBanner;

    @FindBy(xpath = "//p[contains(@class,'card')]//../span/span[contains(@class,'chunk__separator')]")
    public List<WebElement> nhsChunkSeparatorsInPatientRecordCard;

    @FindBy(xpath = "//div[@role='dialog']//ul/li/a")
    public List<WebElement> listOfMandatoryStagesOnDialogBox;

    //To click outside the modal dialog
    @FindBy(xpath = "//body")
    public WebElement pageBodyElement;

    private String incompleteStageInDialogBox = "//*[contains(@class,'referral-header__stage-list')]//a[contains(text(),'" + "dummyValue" + "')]";

    static int referralBannerXLocation, referralBannerYLocation;
    @FindBy(xpath = "//a[contains(text(),'Report an issue')]")
    WebElement reportAnIssueOrProvideFeedbackLink;
    @FindBy(xpath = "//a[contains(text(),'Privacy Policy')]")
    WebElement privacyPolicyLink;
    @FindBy(xpath = "//p[text()='NHS No.']//span[contains(@class,'_chunk__separator_')]")
    List<WebElement> patientCardNHSChunks;


    @FindBy(xpath = "//button[contains(text(),'Try again')]")
    public WebElement tryAgain;

    public void checkThatReferalWasSuccessfullyCreated() {
        Wait.forElementToBeDisplayed(driver, referralHeader, 120);
        Wait.forElementToBeDisplayed(driver, toDoList, 120);
        Wait.forElementToBeDisplayed(driver, sectionBody);
        Wait.forNumberOfElementsToBeEqualTo(driver, By.xpath(valuesInReferralHeaderBar), 7);
    }

    public String getText(WebElement element) {
        Wait.forElementToBeDisplayed(driver, element);
        return element.getText();
    }

    public void clickSaveAndContinueButton() {
        try {
            Wait.forElementToBeDisplayed(driver, saveAndContinueButton, 200);
            Wait.forElementToBeClickable(driver, saveAndContinueButton);
            Actions.clickElement(driver, saveAndContinueButton);
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted
            // Click.element(driver, saveAndContinueButton)
            Wait.seconds(5);
            //Some times after clicking on SaveAndContinue, Try again option is coming, click on and continue
            if (Wait.isElementDisplayed(driver, tryAgain, 5)) {
                Actions.clickElement(driver, tryAgain);
            }
            if (helix.size() > 0) {
                try {
                    Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
                } catch (TimeoutException texp) {
                    //Still the helix in action, waiting for another 40 seconds.
                    //Debugger.println("ReferralPage:clickSaveAndContinueButton, Still helix in action, waiting for another 40 seconds:" + texp);
                    Wait.seconds(40);
                    Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
                }
            }
            Wait.seconds(5);//Increased to 5 seconds after clicking on Save and Continue as many places package complete icon validation failing
        } catch (UnhandledAlertException exp) {
            Debugger.println("UnhandledAlertException from ReferralPage:clickSaveAndContinueButton: " + exp);
            seleniumLib.dismissAllert();
        } catch (Exception exp) {
            try{
                Debugger.println("Clicking on Save and Continue Via SeleniumLib....");
                seleniumLib.clickOnWebElement(saveAndContinueButton);
            }catch(Exception exp1) {
                Debugger.println("Exception from ReferralPage:clickSaveAndContinueButton: " + exp1);
                SeleniumLib.takeAScreenShot("RefPageSaveAndContinue.jpg");
                Assert.assertFalse("ReferralPage:clickSaveAndContinueButton:Exception:" + exp, true);
            }
        }
    }

    public void clickSaveAndContinueButtonOnThePatientChoiceComponent() {
        try {
            try {
                Wait.forElementToBeDisplayed(driver, consentDocument, 200);
                Wait.forElementToBeDisplayed(driver, consentDocumentShadow, 200);
                Wait.forElementToBeDisplayed(driver, consentDocumentPrintableForm, 200);
                Wait.forElementToBeDisplayed(driver, consentDocumentHeaderInfo, 200);
            } catch (Exception exp) {
                Debugger.println("Consent Form is not visible ...");
                SeleniumLib.takeAScreenShot("PatientChoiceConsentDocument.jpg");
                //Assert.assertFalse("Consent Form is not visible ...Exception : " + exp, true);
            }

            Wait.forElementToBeDisplayed(driver, saveAndContinueButton, 200);
            Wait.forElementToBeClickable(driver, saveAndContinueButton);
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
            Wait.forElementToBeDisplayed(driver, saveAndContinueButton, 30);
            Wait.forElementToBeClickable(driver, saveAndContinueButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("ReferralPage:Exception from Clicking on saveAndContinueButton:" + exp);
            SeleniumLib.takeAScreenShot("RefSaveAndContinue.jpg");
            return false;
        }
    }


    public boolean checkThatReferralWasSuccessfullyCreated() {
        try {
            // deliberate 3 seconds wait is added to handle the slowness of UI on Jenkins run
            //ReferralPage:checkThatReferralWasSuccessfullyCreated:Exception.org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document
            Wait.seconds(3);
            Wait.forElementToBeDisplayed(driver, getReferralHeaderStatus, 300);
            Wait.forElementToBeDisplayed(driver, referralHeader, 200);
            Wait.forElementToBeDisplayed(driver, toDoList, 200);
            Wait.forElementToBeDisplayed(driver, sectionBody, 200);
            return true;
        } catch (Exception exp) {
            Debugger.println("ReferralPage:checkThatReferralWasSuccessfullyCreated:Exception." + exp);
            SeleniumLib.takeAScreenShot("ReferralNotCreated.jpg");
            //Observed undefined attached in the URL sometime....This is to verify the URL the moment
            Debugger.println("ReferralNotCreated:URL:" + driver.getCurrentUrl());
            Assert.assertFalse("Referral Could not created Successfully. Check ReferralNotCreated.jpg", true);
            return false;
        }
    }

    public boolean checkThatToDoListSuccessfullyLoaded() {
        try {
            Wait.forElementToBeDisplayed(driver, toDoList, 200);
            return Wait.isElementDisplayed(driver, toDoList, 30);
        } catch (Exception exp) {
            Debugger.println("ToDoList not listed even after waiting period...waiting for another 30 seconds...");
            return Wait.isElementDisplayed(driver, toDoList, 30);
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
        WebElement referralStage = null;
        try {
            //200 seconds waiting is too much I think. One minute is more than enough, observed that mainly this can
            //handle by scrolling up/down
            Wait.forElementToBeDisplayed(driver, toDoList, 60);
            String webElementLocator = stageIsToDo.replace("dummyStage", getPartialUrl(stage));
            referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
            if (!Wait.isElementDisplayed(driver, referralStage, 10)) {
                Actions.scrollToTop(driver);
            }
            if (!Wait.isElementDisplayed(driver, referralStage, 10)) {
                Actions.scrollToBottom(driver);
            }
            Actions.retryClickAndIgnoreElementInterception(driver, referralStage);
        } catch (StaleElementReferenceException staleExp) {
            Debugger.println("Stage Click: StaleElementReferenceException: " + staleExp);
            referralStage = driver.findElement(By.xpath("//a[contains(text(),'" + stage + "')]"));
            Actions.retryClickAndIgnoreElementInterception(driver, referralStage);
        } catch (TimeoutException exp) {
            Debugger.println("Stage Click: TimeoutException: " + exp);
            referralStage = driver.findElement(By.xpath("//a[contains(text(),'" + stage + "')]"));
            Actions.retryClickAndIgnoreElementInterception(driver, referralStage);
        } catch (NoSuchElementException exp) {
            Debugger.println("Stage Click: NoSuchElementException: " + exp);
            referralStage = driver.findElement(By.xpath("//a[contains(text(),'" + stage + "')]"));
            Actions.retryClickAndIgnoreElementInterception(driver, referralStage);
        } catch (Exception exp) {
            Debugger.println("Stage Click: Exception: " + exp);
            referralStage = driver.findElement(By.xpath("//a[contains(text(),'" + stage + "')]"));
            Actions.retryClickAndIgnoreElementInterception(driver, referralStage);
        }
    }
    public boolean stageIsSelected(String expStage) {
        try {
            Wait.seconds(2);
            if(!Wait.isElementDisplayed(driver,activeStage,30)){
                Debugger.println("No stage is marked as active.");
                SeleniumLib.takeAScreenShot("ActiveStage.jpg");
                return false;
            }
            String actualStage = activeStage.getText();
            if(!expStage.equalsIgnoreCase(actualStage)){
                Debugger.println("Stage: "+expStage+" expected to be currently active, but not.");
                SeleniumLib.takeAScreenShot("ActiveStage.jpg");
                return false;
            }
            return true;
        } catch (NoSuchElementException nexp) {
            return false;
        }
     }

    public boolean stageIsCompleted(String stage) {
        try {
            if (!Wait.isElementDisplayed(driver, toDoList, 120)) {
                Debugger.println("TODO LIST IS NOT LOADED IN 120 SECONDS !!!!");
                //Do scroll up on page
                Actions.scrollToTop(driver);
            }
            //Swapped the method of verification
            String completedMark = stageCompletedMark.replaceAll("dummyStage", stage);
            WebElement completedMarkElement = driver.findElement(By.xpath(completedMark));
            if (Wait.isElementDisplayed(driver, completedMarkElement, 30)) {
                return true;
            }

            Wait.forElementToBeDisplayed(driver, toDoList, 200);
            String webElementLocator = stageIsToDo.replace("dummyStage", getPartialUrl(stage));
            Wait.seconds(2);
            WebElement referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, referralStage);
            Wait.seconds(2);
            List<WebElement> completedIcon = referralStage.findElements(By.cssSelector(stageCompleteLocator));
            if (completedIcon != null && completedIcon.size() > 0) {//Got ArrayIndexOutOfBounds Exception some times, so added this cehck
                Wait.forElementToBeDisplayed(driver, completedIcon.get(0), 200);
                //boolean status = referralStage.getAttribute("class").contains(stageCompleteLocator);
                if (completedIcon.size() == 1) {
                    return true;
                }
            }
            Debugger.println("Status of Stage.." + stage + " is: " + referralStage.getAttribute("class") + ", but expected to be complete.");
            SeleniumLib.takeAScreenShot("StageComplete.jpg");
            return false;
        } catch (Exception exp) {
            try {
                //In case of failure due to element not found exception, stale exception etc, trying another way with a wait time of 30 seconds
                String completedMark = stageCompletedMark.replaceAll("dummyStage", stage);
                WebElement completedMarkElement = driver.findElement(By.xpath(completedMark));
                if (Wait.isElementDisplayed(driver, completedMarkElement, 30)) {
                    return true;
                }
                Debugger.println("Exception in Checking Stage Completion Status: " + exp);
                SeleniumLib.takeAScreenShot("StageComplete.jpg");
                return false;
            } catch (Exception exp1) {
                Debugger.println("Exception1 in Checking Stage Completion Status: " + exp);
                SeleniumLib.takeAScreenShot("StageComplete.jpg");
                return false;
            }
        }
    }

    public boolean stageIsMandatoryToDo(String stage) {
        try {
            Wait.forElementToBeDisplayed(driver, toDoList);
            String webElementLocator = stageIsMarkedAsMandatoryToDo.replace("dummyStage", getPartialUrl(stage));
            WebElement referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
            List<WebElement> webElementList = referralStage.findElements(By.cssSelector(mandatoryAsterix));
            if (webElementList.size() == 1) {
                return true;
            }
            return false;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: stageIsMandatoryToDo: " + exp);
            SeleniumLib.takeAScreenShot("ReferralPageMandatoryStage.jpg");
            return false;
        }
    }

    public String acknowledgeThePromptAlertPopups(String acknowledgeMessage) {
        String actualAlertText = null;
        if (acknowledgeMessage.equalsIgnoreCase("Accept")) {
            //Wait.forAlertToBePresent(driver);//Some times alert not present, handled with an exception
            Wait.seconds(2);
            try {
                actualAlertText = driver.switchTo().alert().getText();
                Actions.acceptAlert(driver);
            }catch (NoAlertPresentException ex) {
                Debugger.println("Expected alert message, but not present.");
                SeleniumLib.takeAScreenShot("NoAlertPresent.jpg");
            }
            Debugger.println("Accepted the alert message :: " + actualAlertText);
            Debugger.println("URL info after accepting alert :: " + driver.getCurrentUrl());
        } else if (acknowledgeMessage.equalsIgnoreCase("Dismiss")) {
            //Wait.forAlertToBePresent(driver);
            Wait.seconds(2);
            try {
                actualAlertText = Actions.getTextOfAlertMessage(driver);
                Actions.dismissAlert(driver);
            }catch (NoAlertPresentException ex) {
                Debugger.println("Expected alert message, but not present.");
                SeleniumLib.takeAScreenShot("NoAlertPresent.jpg");
            }
            Debugger.println("Dismissed the alert message :: " + actualAlertText);
            Debugger.println("URL info after accepting alert :: " + driver.getCurrentUrl());
        }
        return actualAlertText;
    }

    public void clickLogoutButton() {
        // Wait.forElementToBeDisplayed(driver, headerRightArea); // Element not in E2E-Latest
        Actions.clickElement(driver, logoutButton);
    }

    public String logoutSuccessMessageIsDisplayed() {
        try {
            Wait.forURLToContainSpecificText(driver, "login.microsoftonline.com");
            return driver.getTitle();
        }catch(Exception exp){
            Debugger.println("Exception in logoutSuccessMessageIsDisplayed:"+exp);
            SeleniumLib.takeAScreenShot("logOutMessage.jpg");
            return null;
        }
    }

    public String getTheCurrentPageTitle() {
        try {
            //Reduced the waiting time to 10 seconds from 30 seconds
            if (Wait.isElementDisplayed(driver, pageTitle, 10)){
                return Actions.getText(pageTitle);
            }
            return null;
        } catch (Exception exp) {
            return null;
        }
    }

    public void navigateToFamilyMemberSearchPage() {
        Wait.forElementToBeDisplayed(driver, addFamilyMember);
        Actions.clickElement(driver, addFamilyMember);
    }

    public List<String> getTheListOfHelpHintTextsOnCurrentPage() {
        Wait.forElementToBeDisplayed(driver, pageTitle);
        List<String> actualHelpHintTexts = new ArrayList<String>();

        for (WebElement fieldHelpHintText : hintText) {
            actualHelpHintTexts.add(fieldHelpHintText.getText().trim());
        }
        String currentPage = getTheCurrentPageTitle();
        Debugger.println("Actual Help-Hint Texts on" + ":" + currentPage + ": page :" + actualHelpHintTexts);
        return actualHelpHintTexts;
    }

    public boolean clickOnTheBackLink() {
        try {
            if (!Wait.isElementDisplayed(driver, backLink, 30)) {
                Actions.scrollToBottom(driver);
            }
            Actions.retryClickAndIgnoreElementInterception(driver, backLink);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking in Back Link..." + exp);
            SeleniumLib.takeAScreenShot("BackButtonLinkMissing.jpg");
            return false;
        }
    }

    public String successNotificationIsDisplayed() {
        Wait.forElementToBeDisplayed(driver, genericSuccessNotification);
        return Actions.getText(genericSuccessNotification);
    }

    public boolean verifyTheErrorMessageDisplay(String errorMessage, String fontColor) {
        try {
            Wait.seconds(2);
            int noOfErrors = validationErrors.size();
            if (noOfErrors == 0) {
                clickSaveAndContinueButton();//Click on Save and Continue Again. Some times click not happens on this button
                Wait.seconds(2);
                noOfErrors = validationErrors.size();
            }
            String actualMessage = "";
            String actColor = "";
            String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(fontColor);
            boolean isPresent = false;
            for (int i = 0; i < noOfErrors; i++) {
                actualMessage = validationErrors.get(i).getText();
                if (errorMessage.equalsIgnoreCase(actualMessage)) {
                    actColor = validationErrors.get(0).getCssValue("color");
                    if (expectedFontColor.equalsIgnoreCase(actColor)) {
                        isPresent = true;
                        break;
                    }
                }
            }
            if (!isPresent) {
                Debugger.println("ErrorMessage:" + errorMessage + ", not displayed in :" + expectedFontColor);
                SeleniumLib.takeAScreenShot("NoErrorMessage.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from validating Error Message " + exp);
            return false;
        }
    }

    public boolean verifyThePageTitlePresence(String expTitle) {
        try {
            Wait.seconds(5);//Many places observed the Title loading issue, trying with a 5 seconds forceful wait
            String actualPageTitle = getTheCurrentPageTitle();
            if (actualPageTitle != null && actualPageTitle.equalsIgnoreCase(expTitle)) {
                return true;
            }
            //Added extra below code, as it is observed that the page title path for each element in stage is not same
            List<WebElement> titleElements = driver.findElements(By.xpath("/h1"));
            if (titleElements != null) {
                for (int i = 0; i < titleElements.size(); i++) {
                    if (titleElements.get(i).getText().contains(expTitle)) {
                        return true;
                    }
                }
            }
            //In case of failure again, trying with another method.
            By pageTitle;
            if (expTitle.contains("\'")) {
                // if the string contains apostrophe character, apply double quotes in the xpath string
                pageTitle = By.xpath("//h1[contains(text(), \"" + expTitle + "\")]");
            } else {
                pageTitle = By.xpath("//h1[contains(text(),'" + expTitle + "')]");
            }
            WebElement titleElement = null;
            try {
                Wait.seconds(2);
                titleElement = driver.findElement(pageTitle);
                if (Wait.isElementDisplayed(driver, titleElement, 5)) {
                    return true;
                }
            } catch (NoSuchElementException exp) {
                //Observed from some failure screen shot that, the issue was - previous page Save&Continue not clicked
                //So clicking on save abd continue and trying again.
                clickSaveAndContinueButton();
                Wait.seconds(1);
                actualPageTitle = getTheCurrentPageTitle();
                if (actualPageTitle != null && actualPageTitle.equalsIgnoreCase(expTitle)) {
                    return true;
                }
                Actions.scrollToTop(driver);
                SeleniumLib.takeAScreenShot("PageWithTitleNotLoaded.jpg");
                return false;
            }
            return false;
        } catch (Exception exp) {
            Debugger.println("Page with Title: " + expTitle + " not loaded." + exp);
            Actions.scrollToTop(driver);
            SeleniumLib.takeAScreenShot("PageWithTitleNotLoaded.jpg");
            return false;
        }
    }

    public void clickOnSaveAndContinueButton() {
        try {
            Wait.forElementToBeDisplayed(driver, saveAndContinueButton, 200);
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
        try {
            List<String> actualErrorMessages = new ArrayList<>();
            for (WebElement errorMessage : errorMessages) {
                actualErrorMessages.add(errorMessage.getText().trim());
            }
            Debugger.println("Actual-Error Messages" + actualErrorMessages);
            return actualErrorMessages;
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("fieldsErrorMessages.jpg");
            Debugger.println("Could not find error message...." + exp);
            return null;
        }
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

    public void submitReferral() {
        try {
            if (Wait.isElementDisplayed(driver, submitReferralButton, 100)) {
                Actions.clickElement(driver, submitReferralButton);
                Debugger.println("Referral submitted...");
            }
        } catch (Exception exp) {
            Debugger.println("Exception from submitting Referral " + exp);
            SeleniumLib.takeAScreenShot("submitReferral.jpg");
        }
    }

    public boolean clicksOnCancelReferralLink() {
        try {
            if (!Wait.isElementDisplayed(driver, cancelReferralLink, 100)) {
                Debugger.println("Cancel referral link  not displayed.");
                SeleniumLib.takeAScreenShot("CancelReferralLink.jpg");
                return false;
            }
            cancelReferralLink.click();
            Wait.seconds(5);//Waiting for 5 seconds to load the popup dialog.
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Cancelling Referral " + exp);
            SeleniumLib.takeAScreenShot("CancelReferralLink.jpg");
            return false;
        }
    }

    public boolean selectCancellationReason(String reason) {
        try {
            if (!Wait.isElementDisplayed(driver, cancelReasonDropdown, 10)) {
                Debugger.println("Cancel referral dropdown not present.");
                SeleniumLib.takeAScreenShot("CancelReferral.jpg");
                return false;
            }
            Actions.clickElement(driver, cancelReasonDropdown);
            if (!Wait.isElementDisplayed(driver, dropdownValue, 10)) {
                Debugger.println("Cancel referral dropdown values not loaded.");
                SeleniumLib.takeAScreenShot("CancelReferral.jpg");
                return false;
            }
            Actions.selectValueFromDropdown(dropdownValue, reason);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from selectCancellationReason: " + exp);
            SeleniumLib.takeAScreenShot("CancelReferral.jpg");
            return false;
        }
    }

    public boolean cancelReferralConfirmationIsDisplayed() {
        try {
            Wait.forElementToBeDisplayed(driver, cancelReferralNotification);
            Wait.forElementToDisappear(driver, By.cssSelector(cancelReferralLocator));
            return true;
        } catch (Exception exp) {
            Debugger.println("Cancel Referral notification is not displayed:"+exp);
            SeleniumLib.takeAScreenShot("cancelReferralConfirmationIsDisplayed.jpg");
            return false;
        }

    }

    public boolean cancelReasonMatches(String reason) {
        try {
            if(!Wait.isElementDisplayed(driver,referralCancelReason,30)){
                Debugger.println("referralCancelReason not displayed.");
                SeleniumLib.takeAScreenShot("referralCancelReason.jpg");
                return false;
            }
            String actReason = Actions.getText(referralCancelReason);
            if(!actReason.equalsIgnoreCase(reason)){
                Debugger.println("Expected Reason:"+reason+", but Actual:"+actReason);
                SeleniumLib.takeAScreenShot("referralCancelReason.jpg");
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from cancelReasonMatches:"+exp);
            SeleniumLib.takeAScreenShot("cancelReasonMatches.jpg");
            return false;
        }
    }

    public boolean verifyTheReferralStatus(String expectedStatus) {
        try {
            if(!Wait.isElementDisplayed(driver,referralStatus,30)){
                Debugger.println("referralStatus not displayed.");
                SeleniumLib.takeAScreenShot("referralStatus.jpg");
                return false;
            }
            String actReason = Actions.getText(referralStatus);
            if(!actReason.equalsIgnoreCase(expectedStatus)){
                Debugger.println("Expected Status:"+expectedStatus+", but Actual:"+actReason);
                SeleniumLib.takeAScreenShot("referralStatus.jpg");
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in verifyTheReferralStatus:"+exp);
            SeleniumLib.takeAScreenShot("verifyTheReferralStatus.jpg");
            return false;
        }
    }

    public String getPatientNGISId() {
        Wait.isElementDisplayed(driver, referralHeaderPatientNgisId, 3);
        return Actions.getText(referralHeaderPatientNgisId);
    }

    public String getPatientReferralId() {
        try {
            Wait.isElementDisplayed(driver, referralHeaderReferralId, 3);
            return Actions.getText(referralHeaderReferralId);
        }catch(Exception exp){
            return null;
        }
    }

    public String getPatientClinicalIndication() {
        Wait.isElementDisplayed(driver, referralHeaderClinicalId, 3);
        return Actions.getText(referralHeaderClinicalId);
    }

    public boolean submitCancellation() {
        try {
            if (cancelReferralButtons.size() < 2) {
                Debugger.println("Cancel Referral Dialog/Buttons not present.");
                SeleniumLib.takeAScreenShot("CancelReferral");
                return false;
            }
            Actions.clickElement(driver, cancelReferralButtons.get(1));
            Wait.seconds(5);//Waiting for 5 seconds to load the cancellation message.
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on cancel Referral: " + exp);
            SeleniumLib.takeAScreenShot("cancelRefButton.jpg ");
            return false;
        }
    }

    public boolean verifyCancellationMessage(String expMessage) {
        try {
            Wait.forElementToBeDisplayed(driver, notificationSuccessMessage);
            if (!Wait.isElementDisplayed(driver, notificationSuccessMessage, 30)) {
                String actMessage = notificationSuccessMessage.getText();
                if (!expMessage.equalsIgnoreCase(actMessage)) {
                    Debugger.println("Expected Message: " + expMessage + " not displayed after referral cancellation.ActualMessage:" + actMessage);
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Cancelling Referral." + exp);
            SeleniumLib.takeAScreenShot("cancelRefMessage.jpg");
            return false;
        }
    }

    public String getSubmissionConfirmationMessageIsDisplayed() {
        try {
            Wait.forElementToBeDisplayed(driver, submissionConfirmationBanner, 200);
            return Actions.getText(submissionConfirmationBannerTitle);
        } catch (Exception exp) {
            Debugger.println("Referral submission confirm message not displayed: " + exp);
            SeleniumLib.takeAScreenShot("SubmitConfirmMsg.jpg");
            return null;
        }
    }

    public String getReferralStatus() {
        try {
            Wait.forElementToBeDisplayed(driver, referralStatus);
            return Actions.getText(referralStatus);
        } catch (Exception exp) {
            Debugger.println("Exception in verifying referral Submission status:" + exp);
            return null;
        }
    }

    public boolean verifyReferralButtonStatus(String expectedStatus) {
        try {
            String submitStatus = referralButtonStatusTitle.replaceAll("dummyStatus", expectedStatus);
            return Wait.isElementDisplayed(driver, driver.findElement(By.xpath(submitStatus)), 60);
        } catch (Exception exp) {
            Debugger.println("Exception in verifying referral Submission status:" + exp);
            SeleniumLib.takeAScreenShot("referralButtonStatus.jpg");
            return false;
        }
    }
    //*[contains(@class,'referral-header__column')]//span[text()='Submitted']

    public boolean verifyTheExpectedFieldLabelsWithActualFieldLabels(List<Map<String, String>> expectedLabelList) {
        try {
            List actualFieldsLabels = getTheFieldsLabelsOnCurrentPage();
            Debugger.println("Actual fields labels on page :" + actualFieldsLabels);
            for (int i = 0; i < expectedLabelList.size(); i++) { //i starts from 1 because i=0 represents the header;
                Debugger.println("Expected fields labels on patient  page :" + expectedLabelList.get(i).get("labelHeader") + "\n");
                Assert.assertTrue(actualFieldsLabels.contains(expectedLabelList.get(i).get("labelHeader")));
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from getting field labels." + exp);
            SeleniumLib.takeAScreenShot("fields-labels.jpg");
            return false;
        }
    }


    public List<String> getColourOfTheFieldsErrorMessagesOnCurrentPage() {
        try {
            Wait.forElementToBeDisplayed(driver, pageTitle);
            List<String> actualErrorMessagesColour = new ArrayList<>();
            for (WebElement errorMessage : errorMessages) {
                actualErrorMessagesColour.add(errorMessage.getCssValue("color"));
            }
            Debugger.println("Actual-Error Messages colours" + actualErrorMessagesColour);
            return actualErrorMessagesColour;
        } catch (Exception exp) {
            Debugger.println("Exception from getting field labels." + exp);
            SeleniumLib.takeAScreenShot("fields-labels.jpg");
            return null;
        }
    }

    public boolean verifyNGISIDDisplayFormat() {
        //Verify for the NGIS format
        int noOfNgisSections = ngisIDChunkSeparators.size();
        if (noOfNgisSections != 3) {
            Debugger.println("Expected NGISID format as 3 sets, but separated in " + noOfNgisSections + " ways");
            return false;
        }
        //Expected each section in 4,4,4 size
        int bgisExpSection[] = {4, 4, 4};
        for (int i = 0; i < noOfNgisSections; i++) {
            if (ngisIDChunkSeparators.get(i).getText().trim().length() != bgisExpSection[i]) {
                Debugger.println("NGISID Display is not in 4-4-4 separation.");
                return false;
            }
        }
        return true;
    }

    public boolean verifyGlobalPatientInformationBar(NGISPatientModel familyMember) {
        try {
            //Verify  First name and last name
            if (!Wait.isElementDisplayed(driver, familyMemberNames, 10)) {
                Debugger.println("Global Patient Card not displayed with Patient names..");
                SeleniumLib.takeAScreenShot("GlobalPatientCard.jpg");
                return false;
            }
            String firstNameLastName = familyMemberNames.getText();
            if (!firstNameLastName.equalsIgnoreCase(familyMember.getLAST_NAME() + ", " + familyMember.getFIRST_NAME())) {
                Debugger.println("First Name Last Name: " + familyMember.getLAST_NAME() + ", " + familyMember.getFIRST_NAME() + " not displayed on the banner.");
                SeleniumLib.takeAScreenShot("GlobalPatientCard.jpg");
                return false;
            }
            String bannerGender = familyMemberGender.getText();
            if (!bannerGender.equalsIgnoreCase(familyMember.getGENDER())) {
                Debugger.println("Gender: " + familyMember.getGENDER() + " not displayed on the banner.");
                SeleniumLib.takeAScreenShot("GlobalPatientCard.jpg");
                return false;
            }
            String bannerDob = familyMemberDob.getText();
            if (!bannerDob.startsWith(familyMember.getBORN_DATE())) {
                Debugger.println("Born Date: " + familyMember.getBORN_DATE() + " not displayed on the banner.");
                SeleniumLib.takeAScreenShot("GlobalPatientCard.jpg");
                return false;
            }
            String bannerNhs = familyMemberNhsNumbers.getText();
            if (bannerNhs != null && !bannerNhs.isEmpty()) {
                if (!bannerNhs.equalsIgnoreCase(familyMember.getNHS_NUMBER())) {
                    Debugger.println("NHS Number: " + familyMember.getNHS_NUMBER() + " not displayed on the banner.");
                    SeleniumLib.takeAScreenShot("GlobalPatientCard.jpg");
                    return false;
                }
            }
            if (!verifyNHSDisplayFormat("3,3,4")) {
                Debugger.println("NHS Number display format is not as expected.");
                SeleniumLib.takeAScreenShot("GlobalPatientCard.jpg");
                return false;
            }

            String bannerNGIS = familyMemberNgisId.getText();
            if (familyMember.getNGIS_ID() == null || familyMember.getNGIS_ID().isEmpty()) {
                //NGIS ID is auto generated and will get only after selecting the family member while adding.
                //So initializing here to validate on remaining pages.
                familyMember.setNGIS_ID(bannerNGIS);
                FamilyMemberDetailsPage.updateNGISID(familyMember);
            } else {
                if (!bannerNGIS.equalsIgnoreCase(familyMember.getNGIS_ID())) {
                    Debugger.println("NGSID: " + familyMember.getNGIS_ID() + " not displayed on the banner.");
                    SeleniumLib.takeAScreenShot("GlobalPatientCard.jpg");
                    return false;
                }
            }
            if (!verifyNGISIDDisplayFormat()) {
                Debugger.println("NGSID display format is not as expected.");
                SeleniumLib.takeAScreenShot("GlobalPatientCard.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception verifying GlobalPatientCard Information: " + exp);
            SeleniumLib.takeAScreenShot("GlobalPatientCard.jpg");
            return false;
        }
    }

    public void updatePatientNGSID(NGISPatientModel familyMember) {
        try {
            if (!Wait.isElementDisplayed(driver, familyMemberNgisId, 10)) {
                Debugger.println("Could not locate FM NGSID element.");
                return;
            }
            String bannerNGIS = familyMemberNgisId.getText();
            if (bannerNGIS == null || bannerNGIS.isEmpty()) {
                Debugger.println("NGSID could not read.");
                SeleniumLib.takeAScreenShot("NGISIDCouldNotRead.jpg");
            }
            familyMember.setNGIS_ID(bannerNGIS);
            FamilyMemberDetailsPage.updateNGISID(familyMember);

        } catch (Exception exp) {
            Debugger.println("Exception in updating PatientNGSID for patient with DOB:" + familyMember.getDATE_OF_BIRTH() + "\n" + exp);
        }
    }

    public boolean verifyMandatoryFieldDisplaySymbol(String fieldName, String fieldType, String symbol, String symbolColor) {
        try {
            String fieldPath = mandatoryFieldSymbol.replaceAll("dummyFieldType", fieldType);
            fieldPath = fieldPath.replaceAll("dummyLabel", fieldName);
            WebElement mandatoryField = driver.findElement(By.xpath(fieldPath));
            String actSymbol = mandatoryField.getText();
            String actColor = mandatoryField.getCssValue("color");
            String expColor = StylesUtils.convertFontColourStringToCSSProperty(symbolColor);
            if (symbol.equalsIgnoreCase(actSymbol)) {
                if (expColor.equalsIgnoreCase(actColor)) {
                    return true;
                }
            }
            Debugger.println("Filed: " + fieldName + " not displayed as mandatory field.Actual Symbol:" + actSymbol + ",EXP:" + symbol + ",Actual Color:" + actColor + ",EXP:" + expColor);
            SeleniumLib.takeAScreenShot("MandatoryFieldError.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception in validating Mandatory fields: " + exp);
            SeleniumLib.takeAScreenShot("MandatoryFieldError.jpg");
            return false;
        }
    }

    public boolean verifyBlankMandatoryFieldLabelColor(String fieldLabel, String highlightColor) {
        try {
            Wait.seconds(2);
            String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(highlightColor);
            String fieldLabelPath = mandatoryFieldLabel.replaceAll("dummyLabel", fieldLabel);
            WebElement fieldElement = driver.findElement(By.xpath(fieldLabelPath));
            String actualColor = fieldElement.getCssValue("color");
            if (!expectedFontColor.equalsIgnoreCase(actualColor)) {
                Debugger.println("Field: " + fieldLabel + " not highlighted in :" + expectedFontColor + " as expected. Actual colour is:" + actualColor);
                SeleniumLib.takeAScreenShot("MandatoryLabelColorError.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from validating verifyMandatoryFieldHighlightColor:" + exp);
            SeleniumLib.takeAScreenShot("MandatoryLabelColorError.jpg");
            return false;
        }
    }

    //To log the ReferralId in the Log file.
    public void logTheReferralId() {
        String referralID = getPatientReferralId();
        Debugger.println("ReferralID: " + referralID);
        System.out.println("ReferralID: " + referralID);
    }

    public boolean verifyTheCurrentURLContainsTheDirectoryPathPage(String directoryPath) {
        Wait.forURLToContainSpecificText(driver, directoryPath);
        try {
            String navigatedURL = driver.getCurrentUrl();
            if (navigatedURL.contains(directoryPath)) {
                Debugger.println("url " + navigatedURL);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying the URL addresss:" + exp);
            SeleniumLib.takeAScreenShot("URLAddressVerification.jpg");
            return false;
        }
    }

    public String getGenomicMedicineServiceLogoInHeader() {
        Wait.forElementToBeDisplayed(driver, genomicMedicineServicelogo);
        return getText(genomicMedicineServicelogo);
    }

    public String getExpectedUserNameFromLoginEmailAddress(String userType) {
        String userEmailAddress = "";
        if (userType.equalsIgnoreCase("GEL_NORMAL_USER")) {
            userEmailAddress = AppConfig.getApp_username();
        } else if (userType.equalsIgnoreCase("GEL_SUPER_USER")) {
            userEmailAddress = AppConfig.getApp_superUsername();
        }
        String[] splitedEmailAddress = userEmailAddress.split("@");  // Split the username details from the email domain ClinicalViewer.E2ETest@ngisnonprod.onmicrosoft.com
        String userFullName = splitedEmailAddress[0];
        userFullName = userFullName.replace(".", " ");
        Debugger.println("User full name " + userFullName);
        return userFullName;
    }

    public String getActualLoginUserName() {
        Wait.forElementToBeDisplayed(driver, userName);
        return getText(userName);
    }

    public String getActualLogoutText() {
        Wait.forElementToBeDisplayed(driver, logoutButton);
        return getText(logoutButton);
    }

    public boolean nhsEnglandLogoIsDisplayedInHeader() {
        try {
            Wait.forElementToBeDisplayed(driver, nhsEnglandLogoFromHeader);
            if (Wait.isElementDisplayed(driver, nhsEnglandLogoFromHeader, 10)) {
                Debugger.println("NhsEngland logo is visible");
                return true;
            } else {
                Debugger.println("element not found ");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("nhsEngland logo is not displayed");
            SeleniumLib.takeAScreenShot("NhsEnglandLogoHeaderNotFound.jpg");
            return false;
        }
    }

    public boolean nhsEnglandLogoIsDisplayedInFooter() {
        try {
            Wait.forElementToBeDisplayed(driver, nhsEnglandLogoFromFooter);
            if (Wait.isElementDisplayed(driver, nhsEnglandLogoFromFooter, 10)) {
                Debugger.println("NhsEngland logo is visible");
                return true;
            } else {
                Debugger.println("element not found ");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("nhsEngland logo is not displayed");
            SeleniumLib.takeAScreenShot("NhsEnglandLogoFooterNotFound.jpg");
            return false;
        }
    }

    public boolean genomicsEnglandLogIsDisplayedInFooter() {
        try {
            Wait.forElementToBeDisplayed(driver, genomicsEnglandLogoFromFooter);
            if (Wait.isElementDisplayed(driver, genomicsEnglandLogoFromFooter, 10)) {
                Debugger.println("genomicsEngland logo is visible");
                return true;
            } else {
                Debugger.println("element not found ");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("Genomics England logo is not displayed");
            SeleniumLib.takeAScreenShot("GenomicsEnglandLogoNotFound.jpg");
            return false;
        }
    }

    public boolean serviceDeskReportAndIssueIsDisplayedInTheFooter() {
        try {
            Wait.forElementToBeClickable(driver, footerServiceDeskLink);
            if (Wait.isElementDisplayed(driver, footerServiceDeskLink, 10)) {
                Debugger.println("Report an issue or provide feedback text link is visible");
                return true;
            } else {
                Debugger.println("Report an issue or provide feedback text  not found ");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("Report an issue or provide feedback text is not displayed");
            SeleniumLib.takeAScreenShot("ServiceDeskLinkNotFound.jpg");
            return false;
        }
    }

    public boolean privacyPolicyIsDisplayedInTheFooter() {
        try {
            Wait.forElementToBeClickable(driver, footerPrivacyPolicyLink);
            if (Wait.isElementDisplayed(driver, footerPrivacyPolicyLink, 10)) {
                Debugger.println("privacy policy  text link is visible");
                return true;
            } else {
                Debugger.println("privacy policy text link is not found ");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("privacy policy text link text is not displayed");
            SeleniumLib.takeAScreenShot("PrivacyPolicyLinkNotFound.jpg");
            return false;
        }
    }

    public boolean copyrightTextIsDisplayedInTheFooter() {
        try {
            Wait.forElementToBeClickable(driver, footerCopyrightText);
            if (Wait.isElementDisplayed(driver, footerCopyrightText, 10)) {
                Debugger.println("privacy policy  text link is visible");
                return true;
            } else {
                Debugger.println("privacy policy text link is not found ");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("privacy policy text link text is not displayed");
            SeleniumLib.takeAScreenShot("CopyRightLinkNotFound.jpg");
            return false;
        }
    }

    public boolean checkSubmitReferralIsDisabled() {
        try {
            Wait.forElementToBeDisplayed(driver, submitReferralButton);
            return submitReferralButton.isEnabled();
        } catch (Exception exp) {
            Debugger.println("Exception in submitting Referral " + exp);
            SeleniumLib.takeAScreenShot("submitReferralIsNotDisabledState.jpg");
            return false;
        }
    }

    public boolean verifyTheSubmitDialogTitle(String titleMessage) {

        if (!Wait.isElementDisplayed(driver, dialogTitle, 10)) {
            Debugger.println("Submit Referral Message popup not displayed.");
            SeleniumLib.takeAScreenShot("SubmitReferral.jpg");
            return false;
        }
        if (!dialogTitle.getText().equalsIgnoreCase(titleMessage)) {
            Debugger.println("Submit Referral Message popup Title mismatch.Expected:" + titleMessage + ",Actual:" + dialogTitle.getText());
            SeleniumLib.takeAScreenShot("SubmitReferral.jpg");
            return false;
        }
        return true;
    }

    public boolean referralSubmitButtonStatus(String expectedColor) {
        try {
            Wait.forElementToBeDisplayed(driver, referralSubmitButton);
            String expectedBackground = StylesUtils.convertFontColourStringToCSSProperty(expectedColor);
            String actualColor = referralSubmitButton.getCssValue("background-color");
            Debugger.println("Actual Color: " + actualColor);
            if (!actualColor.equalsIgnoreCase(expectedBackground)) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying Submit Patient Choice Status:" + exp);
            return false;
        }
    }

    public boolean validateCancelReferralDialog(String title, String question, String warning, String button1, String button2) {
        boolean isPresent = false;
        try {
            if (!Wait.isElementDisplayed(driver, dialogBox, 10)) {
                Debugger.println("Cancel Referral Dialog box not displayed.");
                SeleniumLib.takeAScreenShot("CancelReferral.jpg");
                return false;
            }
            //Dialog Title
            if (!title.equalsIgnoreCase(dialogTitle.getText())) {
                Debugger.println("Cancel Referral Dialog title expected:" + title + ",Actual:" + dialogTitle.getText());
                SeleniumLib.takeAScreenShot("CancelReferral.jpg");
                return false;
            }
            //Question
            if (!(cancelReasonQuestion.getText().contains(question))) {
                Debugger.println("Cancel Referral Dialog Question expected:" + title + ",Actual:" + cancelReasonQuestion.getText());
                SeleniumLib.takeAScreenShot("CancelReferral.jpg");
                return false;
            }
            //Warning
            if (!(cancelWarningText.getText().contains(warning))) {
                Debugger.println("Cancel Referral Dialog Warning expected:" + title + ",Actual:" + cancelWarningText.getText());
                SeleniumLib.takeAScreenShot("CancelReferral.jpg");
                return false;
            }
            //Check for Button presence
            if (cancelReferralButtons.size() == 0) {
                Debugger.println("Cancel Referral Buttons notpresent");
                SeleniumLib.takeAScreenShot("CancelReferral.jpg");
                return false;
            }
            if (cancelReferralButtons.size() != 2) {
                Debugger.println("Cancel Referral Two buttons expected");
                SeleniumLib.takeAScreenShot("CancelReferral.jpg");
                return false;
            }
            String buttonLabel = cancelReferralButtons.get(0).getText();
            if (buttonLabel.equalsIgnoreCase(button1)) {
                buttonLabel = cancelReferralButtons.get(1).getText();
                if (buttonLabel.equalsIgnoreCase(button2)) {
                    isPresent = true;
                }
            } else if (buttonLabel.equalsIgnoreCase(button2)) {
                buttonLabel = cancelReferralButtons.get(1).getText();
                if (buttonLabel.equalsIgnoreCase(button1)) {
                    isPresent = true;
                }
            }
            //Close the dialog
            if (!Wait.isElementDisplayed(driver, dialogBoxCloseButton, 5)) {
                Debugger.println("Close button in Cancel referral dialog is not present.");
                SeleniumLib.takeAScreenShot("CancelReferral.jpg");
                return false;
            }
            if (!isPresent) {
                Debugger.println("The Cancel referral dialog buttons not displayed as expected");
                SeleniumLib.takeAScreenShot("CancelReferral.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: validateCancelReferralDialog: " + exp);
            SeleniumLib.takeAScreenShot("CancelReferral.jpg");
            return false;
        }
    }

    public boolean referralCancelledStatusWithReason(String reason) {
        try {
            Wait.forElementToBeDisplayed(driver, referralCancelReason, 100);
            if (!seleniumLib.isElementPresent(cancelledReferralStatus)) {
                Debugger.println("The referral cancelled status not found");
                SeleniumLib.takeAScreenShot("referralCancelledStatus.jpg");
                return false;
            }
            String actStatus = referralCancelReason.getText();
            Debugger.println("The cancellation reason present is " + actStatus + " ,And expected " + reason);
            if (!reason.equalsIgnoreCase(actStatus)) {
                Debugger.println("The referral cancellation reason not found");
                SeleniumLib.takeAScreenShot("referralCancelledStatus.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: CancelledStatus: " + exp);
            SeleniumLib.takeAScreenShot("referralCancelledStatus.jpg");
            return false;
        }
    }

    public boolean verifyReferralCancelledStatusOnPatientCard(String reason) {
        try {
            Wait.forElementToBeDisplayed(driver, referralCardHeader, 60);
            if (!cancelledReferralStatus.isDisplayed()) {
                Debugger.println("The referral cancelled status not found");
                SeleniumLib.takeAScreenShot("referralCancelledStatusOnCard.jpg");
                return false;
            }
            Wait.forElementToBeDisplayed(driver, referralCancelReasonOnCard, 10);
            String actStatus = referralCancelReasonOnCard.getText();
            if (!reason.equalsIgnoreCase(actStatus)) {
                Debugger.println("The referral cancellation reason not found");
                SeleniumLib.takeAScreenShot("referralCancelledStatusOnCard.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: referralCancelledStatusOnPatientCard: " + exp);
            SeleniumLib.takeAScreenShot("referralCancelledStatusOnCard.jpg");
            return false;
        }
    }

    public boolean clickOnCancelledReferralCard() {
        try {
            Wait.forElementToBeDisplayed(driver, referralCancelReasonOnCard, 10);
            if (!Wait.isElementDisplayed(driver, cancelledReferralStatus, 30)) {
                Debugger.println("PatientDetailsPage:clickReferralCard: ReferralCard Not Visible.");
                SeleniumLib.takeAScreenShot("cancelReferralCard.jpg");
                return false;
            }
            Actions.retryClickAndIgnoreElementInterception(driver, cancelledReferralStatus);
            return true;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: clickCancelledReferralCard: " + exp);
            SeleniumLib.takeAScreenShot("cancelReferralCard.jpg");
            return false;
        }
    }

    public boolean validateMandatoryStages(String stageName) {
        boolean isPresent = false;
        try {
            Wait.forElementToBeDisplayed(driver, mandatoryStageDialogBox, 10);
            for (int i = 0; i < incompleteSection.size(); i++) {
                if (incompleteSection.get(i).getText().equalsIgnoreCase(stageName)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("The mandatory stage:" + stageName + " not present in dialog.");
                SeleniumLib.takeAScreenShot("MandatoryStages.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: validateMandatoryStages: " + exp);
            SeleniumLib.takeAScreenShot("MandatoryStages.jpg");
            return false;
        }
    }

    public boolean closeMandatoryStagePopUp() {
        try {
            Wait.forElementToBeDisplayed(driver, mandatoryStageDialogBox);
            if (!mandatoryStageDialogBoxCloseButton.isDisplayed()) {
                Debugger.println("The close button is not present in the mandatory stages dialog box.");
                SeleniumLib.takeAScreenShot("CloseMandatoryStages.jpg");
                return false;
            }
            mandatoryStageDialogBoxCloseButton.click();
            return true;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: closeMandatoryStagePopUp: " + exp);
            SeleniumLib.takeAScreenShot("CloseMandatoryStages.jpg");
            return false;
        }
    }

    public boolean verifyTheCancellationSuccessMsgDoesNotOverlapWithOtherElements() {
        try {
            List<WebElement> elementsNearToSuccessMsgBox = new ArrayList<>();
            elementsNearToSuccessMsgBox.add(addInformationInOrderText);
            elementsNearToSuccessMsgBox.add(printSampleFormsTitle);
            elementsNearToSuccessMsgBox.add(referralHeaderBanner);
            for (WebElement elements : elementsNearToSuccessMsgBox) {
                if (!elements.isDisplayed()) {
                    Debugger.println("The Cancellation success message is overlapping other elements");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PrintForms page:verifyTheCancelletionSuccessMsgDoesnotOverlapWithOtherElements:exception found " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceCancellationSuccessMsg.jpg");
            return false;
        }
    }

    public boolean verifyNHSDisplayFormat(String expectedFormat) {
        //Verify the NHS format.
        String[] nhsFormat = expectedFormat.split(",");
        int size = nhsFormat.length;
        int[] nhsExpSection = new int[size];
        for (int i = 0; i < size; i++) {
            nhsExpSection[i] = Integer.parseInt(nhsFormat[i]);
        }

        if (nhsChunkSeparators.size() < 1) {
            Debugger.println("NO NHS numbers displayed in the Page.");
            //If NHS number not provided while creating Patient, it wont display
            //Verifying the format only when it is present
            return true;
        }
        int expIdx = 0;
        //Verify the format for all NHS displayed in the page - expected to display a chunk of 3 3 4
        for (int i = 0; i < nhsChunkSeparators.size(); i++) {
            if (i != 0 && i % 3 == 0) {
                expIdx = 0;
            }
            if (nhsChunkSeparators.get(i).getText().trim().length() != nhsExpSection[expIdx]) {
                Debugger.println("NHS Number not displayed in specified format 3 3 4:"+nhsChunkSeparators.get(i).getText());
                SeleniumLib.takeAScreenShot("NHSFormat.jpg");
                return false;
            }
            expIdx++;
        }
        return true;
    }
    public boolean verifyNHSDisplayFormatInPatientCard(String expectedFormat) {
        //Verify the NHS format.
        String[] nhsFormat = expectedFormat.split(",");
        int size = nhsFormat.length;
        int[] nhsExpSection = new int[size];
        for (int i = 0; i < size; i++) {
            nhsExpSection[i] = Integer.parseInt(nhsFormat[i]);
        }

        if (patientCardNHSChunks.size() < 1) {
            Debugger.println("NO NHS numbers displayed in the Patient card.");
            Actions.scrollToBottom(driver);
            SeleniumLib.takeAScreenShot("NHSFormatInPatientCard.jpg");
            return false;
        }
        //Verify the format displayed in the patient card - expected a chunk of 3 3 4
        for (int i = 0; i < patientCardNHSChunks.size(); i++) {
            if (patientCardNHSChunks.get(i).getText().trim().length() != nhsExpSection[i]) {
                Debugger.println("NHS Number not displayed in specified format 3 3 4");
                SeleniumLib.takeAScreenShot("NHSFormatInPatientCard.jpg");
                return false;
            }
        }
        return true;
    }

    public boolean mandatoryStageDialogBoxIsDisplayed() {
        try {
            Wait.forElementToBeDisplayed(driver, mandatoryStageDialogBox);
            if (!mandatoryStageDialogBox.isDisplayed()) {
                Debugger.println("the mandatory stages dialog box is not displayed.");
                SeleniumLib.takeAScreenShot("MandatoryStagesDialogBox.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("MandatoryStagePopUp: " + exp);
            SeleniumLib.takeAScreenShot("MandatoryStageDialogBox.jpg");
            return false;
        }
    }

    public List<String> getTheListOfMandatoryStagesOnDialogBox() {
        try {
            List<String> actualListOfMandatoryStages = new ArrayList<>();
            for (WebElement mandatoryStage : listOfMandatoryStagesOnDialogBox) {
                actualListOfMandatoryStages.add(mandatoryStage.getText().trim());
            }
            Debugger.println("Actual-List of MandatoryStages" + actualListOfMandatoryStages);
            return actualListOfMandatoryStages;
        } catch (Exception exp) {
            Debugger.println("ListOfMandatoryStage: " + exp);
            SeleniumLib.takeAScreenShot("ListOfMandatoryStage.jpg");
            return null;
        }
    }

    public boolean clickOnTheMandatoryStageTextLinkInDialogBox(String expectedMandatoryStage) {
        try {
            By mandatoryStage;
            mandatoryStage = By.xpath("//div[@role='dialog']//*[text()= \"" + expectedMandatoryStage + "\"]");
            Wait.forElementToBeDisplayed(driver, driver.findElement(mandatoryStage), 30);
            if (!Wait.isElementDisplayed(driver, driver.findElement(mandatoryStage), 10)) {
                Debugger.println(" Mandatory Stage Link is not displayed even after waiting period...Failing.");
                SeleniumLib.takeAScreenShot("MandatoryStageLink.jpg");
                return false;
            }
            Actions.retryClickAndIgnoreElementInterception(driver, driver.findElement(mandatoryStage));
            Wait.seconds(15);//Wait for 15 seconds to load the selected mandatory stage
            return true;
        } catch (Exception exp) {
            Debugger.println("Mandatory Stage Link is not displayed even after waiting period...Failing." + exp);
            SeleniumLib.takeAScreenShot("MandatoryStageLink.jpg");
            return false;
        }
    }

    public boolean verifyPatientReferralIdInUrl() {
        try {
            String refId = referralHeaderReferralId.getText();
            String getURl = driver.getCurrentUrl();
            if (!getURl.contains("/" + refId + "/")) {
                Debugger.println("Referral ID : " + refId + " URL : " + getURl);
                SeleniumLib.takeAScreenShot("NoReferralIDInURL.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientDetailsPage: verifyPatientIdInUrl: Exception " + exp);
            SeleniumLib.takeAScreenShot("NoReferralIDInURL.jpg");
            return false;
        }
    }

    public boolean clicksOutsideModalDialog() {
        try {
            Wait.forElementToBeClickable(driver, dialogBoxCloseButton);
            pageBodyElement.click();
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in clicksOutsideModalDialog : " + exp);
            return false;
        }
    }

    public boolean clickOnIncompleteStageInDialogBox(String expStage) {
        try {
            String incompleteStagePath = incompleteStageInDialogBox.replace("dummyValue", expStage);
            WebElement selectedStage = driver.findElement(By.xpath(incompleteStagePath));
            if (!Wait.isElementDisplayed(driver, selectedStage, 10)) {
                Debugger.println("The stage " + selectedStage + " is not incomplete");
                SeleniumLib.takeAScreenShot("IncompleteStage.jpg");
                return false;
            }
            Wait.forElementToBeDisplayed(driver, mandatoryStageDialogBox, 20);

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", selectedStage);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on incomplete stage, PatientDetailsPage: clickOnIncompleteStageInTodoList: " + exp);
            SeleniumLib.takeAScreenShot("IncompleteStage.jpg");
            return false;
        }
    }

    public boolean verifyPresenceOfCancelReferralLink() {
        try {
            if (!Wait.isElementDisplayed(driver, cancelReferralLink, 10)) {
                Debugger.println("Cancel Referral link not present as expected.");
                SeleniumLib.takeAScreenShot("CancelReferralLink.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyPresenceOfCancelReferralLink :" + exp);
            SeleniumLib.takeAScreenShot("CancelReferralLink.jpg");
            return false;
        }
    }

    public boolean verifyNgisIdAndReferralId() {
        if (!Wait.isElementDisplayed(driver, referralHeaderPatientNgisId, 10)) {
            Debugger.println("referralHeaderPatientNgisId not present");
            SeleniumLib.takeAScreenShot("ngisIdAndReferralId.jpg");
            return false;
        }
        if (!Wait.isElementDisplayed(driver, referralHeaderReferralId, 10)) {
            Debugger.println("referralHeaderReferralId not present");
            SeleniumLib.takeAScreenShot("ngisIdAndReferralId.jpg");
            return false;
        }
        String ngisId = referralHeaderPatientNgisId.getText();
        String referralId = referralHeaderReferralId.getText();
        if (ngisId == null || referralId == null) {
            Debugger.println("ngsId or referralIs is null");
            SeleniumLib.takeAScreenShot("ngisIdAndReferralId.jpg");
            return false;
        }

        try {
            Wait.forElementToBeDisplayed(driver, referralHeader);
            if (!(ngisId.charAt(0) == 'p' && ngisId.length() == 12)) {
                if (!(referralId.charAt(0) == 'p' && referralId.length() == 12)) {
                    Debugger.println("The ngis id and referral id are not correct");
                    SeleniumLib.takeAScreenShot("ngisIdAndReferralId.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: verifyNgisIdAndReferralId: exception found" + exp);
            SeleniumLib.takeAScreenShot("ngisIdAndReferralId.jpg");
            return false;
        }
    }

    public boolean verifyTextFromReferralHeaderPatientNgisId() {
        try {
            if (!Wait.isElementDisplayed(driver, referralHeaderPatientNgisId, 10)) {
                Debugger.println("referralHeaderPatientNgisId not present");
                SeleniumLib.takeAScreenShot("ngisIdAndReferralId.jpg");
                return false;
            }
            String webElementText = referralHeaderPatientNgisId.getText();
            if (webElementText == null) {
                Debugger.println("NGISIDTextVerification not present.");
                SeleniumLib.takeAScreenShot("NGISIDTextVerification.jpg");
                return false;
            }
            if (webElementText.contains(" ")) {
                Debugger.println("Text copied from webElement is different from actual content");
                SeleniumLib.takeAScreenShot("NGISIDTextVerification.jpg");
                return false;
            }
            return true;
        } catch (Exception e) {
            Debugger.println("Exception found while copying and verifying the webelement text." + e);
            SeleniumLib.takeAScreenShot("NGISIDtextVerification.jpg");
            return false;
        }
    }

    public boolean readTheReferralBannerLocation() {
        try {
            if (!Wait.isElementDisplayed(driver, referralHeader, 20)) {
                Debugger.println("Referral header banner is not present.");
                SeleniumLib.takeAScreenShot("ReferralHeader.jpg");
                return false;
            }
            //Storing the referral header banner location to verify the position in various pages
            Point referralHeaderLocation = referralHeader.getLocation();
            referralBannerXLocation = referralHeaderLocation.getX();
            referralBannerYLocation = referralHeaderLocation.getY();
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheBannerLocation, " + exp);
            SeleniumLib.takeAScreenShot("ReferralHeader.jpg");
            return false;
        }
    }

    public boolean verifyTheBannerLocationAtSameLocation() {
        try {
            if (!Wait.isElementDisplayed(driver, referralHeader, 20)) {
                Debugger.println("Referral header banner is not present.");
                SeleniumLib.takeAScreenShot("ReferralHeader.jpg");
                return false;
            }
            Point referralBannerLocation = referralHeader.getLocation();
            int currentLocationX = referralBannerLocation.getX();
            int currentLocationY = referralBannerLocation.getY();
            if (currentLocationX != referralBannerXLocation) {
                Debugger.println("Referral Banner XLocation has changed...Expected at X:" + referralBannerXLocation + ",Actual X:" + currentLocationX);
                SeleniumLib.takeAScreenShot("ReferralHeaderLocation.jpg");
                return false;
            }
            if (currentLocationY != referralBannerYLocation) {
                Debugger.println("Referral Banner YLocation has changed...Expected at Y:" + referralBannerYLocation + ",Actual Y:" + currentLocationY);
                SeleniumLib.takeAScreenShot("ReferralHeaderLocation.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: verifyTheBannerLocationAtSameLocation: " + exp);
            SeleniumLib.takeAScreenShot("ReferralHeaderLocation.jpg");
            return false;
        }
    }

    public boolean verifyFeedbackLinkFontColor(String colorValue) {
        try {
            if (!Wait.isElementDisplayed(driver, reportAnIssueOrProvideFeedbackLink, 20)) {
                Debugger.println("FeedbackLink/Report an issue present.");
                SeleniumLib.takeAScreenShot("ReportAnIssueLink.jpg");
                return false;
            }
            colorValue = colorValue.replaceAll("\"","");
            String linkColorHexValue = StylesUtils.convertFontColourStringToCSSProperty(colorValue);
            String linkColor = reportAnIssueOrProvideFeedbackLink.getCssValue("color");
            if (!linkColor.equalsIgnoreCase(linkColorHexValue)) {
                Debugger.println("Actual font colour of link: " + linkColor + " ,But Expected is: " + linkColorHexValue);
                SeleniumLib.takeAScreenShot("ReportAnIssueLink.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception verifying verifyFeedbackLinkFontColor:" + exp);
            SeleniumLib.takeAScreenShot("ReportAnIssueLink.jpg");
            return false;
        }
    }

    public boolean verifyPrivacyPolicyLinkFontColor(String colorValue) {
        try {
            if (!Wait.isElementDisplayed(driver, privacyPolicyLink, 20)) {
                Debugger.println("Privacy policy link present.");
                SeleniumLib.takeAScreenShot("PrivacyPolicyLink.jpg");
                return false;
            }
            colorValue = colorValue.replaceAll("\"","");
            String expectedColor = StylesUtils.convertFontColourStringToCSSProperty(colorValue);
            String actualColor = privacyPolicyLink.getCssValue("color");
            if (!expectedColor.equalsIgnoreCase(actualColor)) {
                Debugger.println("Actual link colour is " + actualColor + " ,But Expected " + expectedColor);
                SeleniumLib.takeAScreenShot("PrivacyPolicyLink.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception verifying verifyPrivacyPolicyLinkFontColor:" + exp);
            SeleniumLib.takeAScreenShot("PrivacyPolicyLink.jpg");
            return false;
        }
    }

    @FindBy(name = "loginfmt")
    public WebElement emailAddressField;
    @FindBy(xpath = "//input[contains(@type,'submit')]")
    public WebElement nextButton;
    @FindBy(id = "otherTileText")
    public WebElement useAnotherAccount;

    //new paths for the NHS Login page
    @FindBy(id ="companyLogo")
    public WebElement nhsLogo;
    @FindBy(id="userNameInput")
    public WebElement emailAddressFieldNHSPage;
    @FindBy(id="passwordInput")
    public WebElement passwordFieldNHSPage;
    @FindBy(id="submitButton")
    public WebElement signInNHSPage;

    public void loginToTestOrderingSystemAsNHSUser(WebDriver driver,String userType ) {
        Actions.deleteCookies(driver);
        String nhsMail=AppConfig.getApp_username();
        String nhsPassword=AppConfig.getApp_password();
        Debugger.println("PatientSearchPage: loginToTestOrderingSystemAsNHSTestUser....");
        try {
            Wait.seconds(5);
            if (!Wait.isElementDisplayed(driver,emailAddressField,120)) {//If the element is not displayed, even after the waiting time
                Debugger.println("Email Address Field is not visible, even after the waiting period.");
                if (Wait.isElementDisplayed(driver,useAnotherAccount,120)) {//Click on UseAnotherAccount and Proceed.
                    Debugger.println("Clicking on useAnotherAccount to Proceed.");
                    useAnotherAccount.click();
                    Wait.seconds(3);
                } else {
                    Debugger.println("Email field or UseAnotherAccount option are not available. URL:"+driver.getCurrentUrl());
                    SeleniumLib.takeAScreenShot("EmailOrUserAccountNot.jpg");
                    Assert.assertFalse("Email field or UseAnotherAccount option are not available.", true);
                }
            }else{
                Debugger.println("emailAddressField Displayed.... Proceeding with Login...via NHS Test user mail account.");
            }
            Wait.forElementToBeClickable(driver, emailAddressField);
            emailAddressField.sendKeys(nhsMail);
            nextButton.click();
            Wait.seconds(2);
            //Wait for the NHS Login Page to load, then login through the userid and password fields present there
            if(!Wait.isElementDisplayed(driver,nhsLogo,20)){
                Debugger.println("NHS mail account login page is not displayed.");
                SeleniumLib.takeAScreenShot("NHSLoginPage.jpg");
                Assert.assertFalse("NHS Login Page is not displayed.",true);
            }
            Wait.forElementToBeClickable(driver,emailAddressFieldNHSPage);
            String mailIdPresent=emailAddressFieldNHSPage.getAttribute("value");
            if(!mailIdPresent.equals(nhsMail)){
                emailAddressFieldNHSPage.sendKeys(nhsMail);
            }
            Wait.forElementToBeClickable(driver, passwordFieldNHSPage);
            passwordFieldNHSPage.sendKeys(nhsPassword);
            signInNHSPage.click();
            Debugger.println(" Logging to TO as user type: "+userType+" ,with id: "+nhsMail);
            Wait.seconds(5);
        }catch(Exception exp){
            Debugger.println("Exception in Logging to TO as user type: "+userType+".Exception: "+exp);
            SeleniumLib.takeAScreenShot("NHSLoginPage.jpg");
            Assert.assertFalse("Exception in Logging to TO as user type.",true);
        }
    }

    public void saveReferralID () {
       SeleniumLib.writeToJsonFile(referralHeaderReferralId.getText());

    }

}