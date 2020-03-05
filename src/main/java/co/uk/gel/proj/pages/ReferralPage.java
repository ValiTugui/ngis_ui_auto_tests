package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.security.ssl.Debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.uk.gel.lib.Actions.getText;

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

    @FindBy(css = "strong[class*='header-item__value']")
    public List<WebElement> referralHeaderValues;

    @FindBy(css = "*[class*='referral-success-notification']")
    public WebElement referralSuccessNotification;

    @FindBy(css = "*[class*='downloads__notice']")
    public WebElement submissionConfirmationBanner;

    @FindBy(css = "*[class*='notice__title']")
    public WebElement submissionConfirmationBannerTitle;

    // @FindBy(css = "*[class*='referral-header__badge']")
    @FindBy(css = "*[class*='referral-header__details']")
    public WebElement referralStatus;

    //    @FindBy(css = "*[class*='referral-header__cancel-reason']")
    @FindBy(css = "*[class*='referral-header__cancel']")
    public WebElement referralCancelReason;

    // @FindBy(css = "*[href*='signout']")
    @FindBy(xpath = "//*[text()='Log out']")
    public WebElement logoutButton;

    @FindBy(css = "*[class*='header__user']")
    public WebElement user;

    @FindBy(css = "*[class*='header__right-area']")
    public WebElement headerRightArea;

    //    @FindBy(css = "*[class*='referral-header__cancel']")
    @FindBy(css = "*[class*='cancel__button_']")
    public WebElement cancelReferralLink;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(xpath = "//label[contains(@for,'cancel-options')]//following::div")
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

    @FindBy(xpath = "//h2[(text()='Add information in any order')]")
    public WebElement titleOfToDoList;


    String valuesInReferralHeaderBar = "//*[contains(@class,'referral-header')]//child::li";
    String stageIsMarkedAsMandatoryToDo = "a[href*='" + "dummyStage" + "']";
    String stageIsToDo = "a[href*='" + "dummyStage" + "']";
    String helixIcon = "*[class*='helix']";
    String mandatoryToDOIconLocator = "todo__required-icon";
    String currentStageLocator = "true";
    String stageCompleteLocator1 = "todo--is-complete";
    String stageCompleteLocator = "*[data-testid*='completed-icon']";
    String cancelReferralLocator = "*[class*='button--disabled-clickable']";

    //    @FindBy(xpath = "//div[contains(@class,'notification-bar__text')]")
//    public WebElement notificationSuccessMessage;

    @FindBy(xpath = "//span[contains(text(),'referral has been cancelled')]")
    public WebElement notificationSuccessMessage;

    //For Global Patient Banner Verification
    @FindBy(xpath = "//span[text()='Born']/following::span[contains(@aria-labelledby,'dateOfBirth')]")
    public WebElement familyMemberDob;
    @FindBy(xpath = "//span[text()='Gender']/following::span[contains(@aria-labelledby,'gender')]")
    public WebElement familyMemberGender;
    @FindBy(xpath = "//span[text()='NHS No.']/following::span[contains(@aria-labelledby,'nhsNumber')]")
    public WebElement familyMemberNhsNumbers;
    @FindBy(xpath = "//span[text()='Patient NGIS ID']/following::span[contains(@aria-labelledby,'ngisId')]")
    public WebElement familyMemberNgisId;
    @FindBy(xpath = "//span[text()='NHS No.']/following::span[contains(@aria-labelledby,'nhsNumber')]//span[contains(@class,'_chunk__separator_')]")
    public List<WebElement> nhsChunkSeparators;
    static int xBannerLocation, yBannerLocation;
    @FindBy(xpath = "//p[contains(@class,'card')]//../span/span[contains(@class,'chunk__separator')]")
    public List<WebElement> nhsChunkSeparatorsInPatientRecordCard;

    @FindBy(xpath = "//span[text()='Referral ID']/following::span[contains(@aria-labelledby,'referralId')]//span[contains(@class,'_chunk__separator_')]")
    public List<WebElement> referralIDChunkSeparators;

    @FindBy(xpath = "//span[text()='Patient NGIS ID']/following::span[contains(@aria-labelledby,'ngisId')]//span[contains(@class,'_chunk__separator_')]")
    public List<WebElement> ngisIDChunkSeparators;
    @FindBy(xpath = "//div[contains(@class,'participant-info')]//h2[contains(@class,'css-')]")
    public WebElement familyMemberNames;

    String mandatoryFieldSymbol = "//dummyFieldType[contains(text(),'dummyLabel')]/span";
    String mandatoryFieldLabel = "//label[contains(text(),'dummyLabel')]";
    String mandatoryAsterix = "*[data-testid*='mandatory-icon']";
    String stageCompletedMark = "//a[contains(text(),'dummyStage')]//*[name()='svg' and @data-testid='completed-icon']";
    String referralButtonStatusTitle = "//*[contains(@class,'referral-header__column')]//span[text()='dummyStatus']";

    @FindBy(id = "dialog-title")
    WebElement dialogTitle;
    //Defined new element without the span, as the attribute of the button needs to read for enable/disable status
    @FindBy(xpath = "//*[@id='referral__header']//button")
    public WebElement referralSubmitButton;

    @FindBy(xpath = "//div[@role='dialog']")
    public WebElement dialogBox;

    @FindBy(xpath = "//div[@role='dialog']//h1")
    public WebElement dialogHeader;

    @FindBy(xpath = "//label[@for='cancel-options']")
    public WebElement cancelReasonQuestion;

    @FindBy(xpath = "//label[@for='cancel-options']//following-sibling::div[contains(@class,'_hint_')]")
    public WebElement cancelWarningText;

    @FindBy(xpath = "//div[@role='dialog']//button")
    public WebElement dialogBoxCloseButton;

    @FindBy(xpath = "//span[text()='Cancelled']")
    public WebElement cancelledReferralStatus;

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
            Actions.retryClickAndIgnoreElementInterception(driver, saveAndContinueButton);
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted
            // Click.element(driver, saveAndContinueButton)
            Wait.seconds(5);
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
            Wait.forElementToBeDisplayed(driver, saveAndContinueButton, 200);
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
//            Wait.forNumberOfElementsToBeEqualTo(driver, By.cssSelector(valuesInReferralHeaderBar), 7);
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
            seleniumLib.scrollToElement(titleOfToDoList);
            Wait.forElementToBeDisplayed(driver, toDoList, 100);
            String webElementLocator = stageIsToDo.replace("dummyStage", getPartialUrl(stage));
            referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
            Wait.forElementToBeDisplayed(driver, referralStage);
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

    public boolean stageIsSelected(String stage) {
        Wait.forURLToContainSpecificText(driver, getPartialUrl(stage));
        Wait.forElementToBeDisplayed(driver, toDoList);
        String webElementLocator = stageIsToDo.replace("dummyStage", getPartialUrl(stage));
        WebElement referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
        boolean check1 = referralStage.getAttribute("data-selected").contains(currentStageLocator);
        boolean check2 = Actions.getText(referralStage).contains(stage);

        if (check1 == true && check2 == true) return true;
        else return false;

    }

    public boolean stageIsCompleted(String stage) {
        try {
            Wait.forElementToBeDisplayed(driver, toDoList, 120);
            String webElementLocator = stageIsToDo.replace("dummyStage", getPartialUrl(stage));
            Wait.seconds(2);
            WebElement referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, referralStage);
            Wait.seconds(2);
            List<WebElement> completedIcon = referralStage.findElements(By.cssSelector(stageCompleteLocator));
            if (completedIcon != null && completedIcon.size() > 0) {//Got ArrayIndexOutOfBounds Exception some times, so added this cehck
                Wait.forElementToBeDisplayed(driver, completedIcon.get(0));
                //boolean status = referralStage.getAttribute("class").contains(stageCompleteLocator);
                if (completedIcon.size() == 1) {
                    return true;
                }
            }
            //In case of failure, trying another way
            String completedMark = stageCompletedMark.replaceAll("dummyStage", stage);
            WebElement completedMarkElement = driver.findElement(By.xpath(completedMark));
            if (Wait.isElementDisplayed(driver, completedMarkElement, 30)) {
                return true;
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
                Debugger.println("Exception in Checking Stage Completion Status: " + exp);
                SeleniumLib.takeAScreenShot("StageComplete.jpg");
                return false;
            }
        }
    }

    public boolean stageIsMandatoryToDo(String stage) {
        try {
            Wait.forElementToBeDisplayed(driver, toDoList);
            String webElementLocator = stageIsMarkedAsMandatoryToDo.replace("dummyStage", getPartialUrl(stage));
            WebElement mandatoryAsteriskSymbol = toDoList.findElement(By.xpath(webElementLocator));
            boolean isStageStatusIsToDO = mandatoryAsteriskSymbol.getAttribute("data-testid").contains(mandatoryToDOIconLocator);
            boolean isStageHasAsteriskPresent = mandatoryAsteriskSymbol.isDisplayed();
            if (isStageStatusIsToDO && isStageHasAsteriskPresent) {
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
        // Wait.forElementToBeDisplayed(driver, headerRightArea); // Element not in E2E-Latest
        Actions.clickElement(driver, logoutButton);
    }

    public String logoutSuccessMessageIsDisplayed() {
        Wait.forURLToContainSpecificText(driver, "login.microsoftonline.com");
        return driver.getTitle();
    }

    public String getTheCurrentPageTitle() {
        try {
            By page_Title = By.cssSelector("h1[class*='page-title']");
            Wait.forElementToBePresent(page_Title, 20);
//            Wait.forElementToBeDisplayed(driver, pageTitle, 120);
            return Actions.getText(pageTitle);
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

    public void clickOnTheBackLink() {
        Actions.retryClickAndIgnoreElementInterception(driver, backLink);
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
            }
            noOfErrors = validationErrors.size();
            if (noOfErrors == 0) {

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
            String actualPageTitle = getTheCurrentPageTitle();
            if (actualPageTitle != null && actualPageTitle.equalsIgnoreCase(expTitle)) {
                return true;
            }
            SeleniumLib.takeAScreenShot("PageTitleNotLoaded.jpg");
            //In case of failure trying with another method.
            By pageTitle;
            if (expTitle.contains("\'")) {
                // if the string contains apostrophe character, apply double quotes in the xpath string
                pageTitle = By.xpath("//h1[contains(text(), \"" + expTitle + "\")]");
            } else {
                pageTitle = By.xpath("//h1[contains(text(),'" + expTitle + "')]");
            }
            WebElement titleElement = null;
            try {
                Wait.seconds(5);
                titleElement = driver.findElement(pageTitle);
                return (Wait.isElementDisplayed(driver, titleElement, 100));
            } catch (NoSuchElementException exp) {
                //Wait for 10 seconds and check again. This is introduced based on the failures observed.
                Actions.scrollToTop(driver);
                Wait.seconds(5);
                titleElement = driver.findElement(pageTitle);
                return (Wait.isElementDisplayed(driver, titleElement, 100));
            }

        } catch (Exception exp) {
            Debugger.println("Page with Title: " + expTitle + " not loaded." + exp);
            Actions.scrollToTop(driver);
            SeleniumLib.takeAScreenShot("PageWithTitleNotLoaded.jpg");
            return false;
        }
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

    public void clicksOnCancelReferralLink() {
        try {
            if (Wait.isElementDisplayed(driver, cancelReferralLink, 100)) {
                cancelReferralLink.click();
                Wait.seconds(5);//Waiting for 5 seconds to load the popup dialog.
            }
        } catch (Exception exp) {
            Debugger.println("Exception from Cancelling Referral " + exp);
            SeleniumLib.takeAScreenShot("cancelReferral.jpg");
        }
    }

    public void selectCancellationReason(String reason) {
        try {
            Actions.clickElement(driver, cancelReasonDropdown);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectValueFromDropdown(dropdownValue, reason);
        } catch (Exception exp) {
            Debugger.println("Exception from cancel drop down: " + exp);
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
        } catch (Exception exp) {
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

    public String getPatientNGISId() {
        Wait.isElementDisplayed(driver, referralHeaderPatientNgisId, 3);
        return Actions.getText(referralHeaderPatientNgisId);
    }

    public String getPatientReferralId() {
        Wait.isElementDisplayed(driver, referralHeaderReferralId, 3);
        return Actions.getText(referralHeaderReferralId);
    }

    public String getPatientClinicalIndication() {
        Wait.isElementDisplayed(driver, referralHeaderClinicalId, 3);
        return Actions.getText(referralHeaderClinicalId);
    }

    public void submitCancellation() {
        try {
            Actions.clickElement(driver, cancelReferralButtons.get(1));
            Wait.seconds(5);//Waiting for 5 seconds to load the cancellation message.
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on cancel Referral: " + exp);
            SeleniumLib.takeAScreenShot("cancelRefButton.jpg ");
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

    public boolean verifyNHSDisplayFormat() {
        //Verify the NHS format.
        int noOfNhsSections = nhsChunkSeparators.size();
        //Expected each section in 3,3,4 size
        int nhsExpSection[] = {3, 3, 4};
        int expIdx = 0;
        for (int i = 0; i < noOfNhsSections; i++) {
            if (i != 0 && i % 3 == 0) {
                expIdx = 0;
            }
            if (nhsChunkSeparators.get(i).getText().trim().length() != nhsExpSection[expIdx]) {
                Debugger.println("NHS Display is not in 3-3-4 separation.");
                return false;
            }
            expIdx++;
        }
        return true;
    }

    public boolean verifyNGISIDDisplayFormat() {
        //Verify for the NGIS format
        int noOfNgisSections = ngisIDChunkSeparators.size();
        //Expected each section in 4,4,4 size
        int bgisExpSection[] = {4, 4, 4};
        int expIdx = 0;
        for (int i = 0; i < noOfNgisSections; i++) {
            if (i != 0 && i % 3 == 0) {
                expIdx = 0;
            }
            if (ngisIDChunkSeparators.get(i).getText().trim().length() != bgisExpSection[expIdx]) {
                Debugger.println("NGISID Display is not in 4-4-4 separation.");
                return false;
            }
            expIdx++;
        }
        return true;
    }

    public boolean verifyGlobalPatientInformationBar(NGISPatientModel familyMember) {
        try {
            //Verify  First name and last name
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
            if (!verifyNHSDisplayFormat()) {
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

    //1786 1794

    public boolean validateCancelReferralDialog(DataTable fieldDetails) {
        List<List<String>> fieldsText = fieldDetails.asLists();
        try {
            Wait.forElementToBeDisplayed(driver, dialogBox, 100);
            //Debugger.println("Header:- " + fieldsText.get(1).get(0) + " & " + dialogHeader.getText());
            if (fieldsText.get(1).get(0).equalsIgnoreCase(dialogHeader.getText())) {
                //Debugger.println("Question:- " + fieldsText.get(2).get(0) + " & " + cancelReasonQuestion.getText());
                if (cancelReasonQuestion.getText().contains(fieldsText.get(2).get(0))) {
                    // Debugger.println("Warning:- " + fieldsText.get(3).get(0) + " & " + cancelWarningText.getText());
                    if (fieldsText.get(3).get(0).equalsIgnoreCase(cancelWarningText.getText())) {
                        String button1 = cancelReferralButtons.get(0).getText();
                        String button2 = cancelReferralButtons.get(1).getText();
                        //  Debugger.println("The buttons displayed are- Button1: " + button1 + " & Button2: " + button2);
                        if (cancelReferralButtons.get(0).isDisplayed() && cancelReferralButtons.get(1).isDisplayed()) {
                            if ((button1.equalsIgnoreCase(fieldsText.get(4).get(0))) && (button2.equalsIgnoreCase(fieldsText.get(5).get(0)))) {
                                if (!dialogBoxCloseButton.isDisplayed()) {
                                    Debugger.println("The close button is not present");
                                    return false;
                                }
                                return true;
                            }
                        }
                    }
                }
            }
            Debugger.println("The Cancel referral dialog is not properly displayed");
            return false;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: validateCancelReferralDialog: " + exp);
            SeleniumLib.takeAScreenShot("referralPageCancelDialog.jpg");
            return false;
        }
    }

    public boolean referralCancelledStatusWithReason(String reason) {
        try {
            Wait.forElementToBeDisplayed(driver, referralCancelReason, 10);
            if (!seleniumLib.isElementPresent(cancelledReferralStatus)) {
                Debugger.println("The referral cancelled status not found");
                return false;
            }
            Wait.forElementToBeDisplayed(driver, referralCancelReason, 10);
            String actStatus = referralCancelReason.getText();
            if (!reason.equalsIgnoreCase(actStatus)) {
                Debugger.println("The referral cancellation reason not found");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: CancelledStatus: " + exp);
            SeleniumLib.takeAScreenShot("referralCancelledStatus.jpg");
            return false;
        }
    }

    //1701
    public boolean verifyTheBannerLocationAtSameLocation() {
        try {
            Wait.forElementToBeDisplayed(driver, referralHeader);
            if (!referralHeader.isDisplayed()) {
                Debugger.println("Referral header is not present.");
                return false;
            }
            Point referralBannerLocation = referralHeader.getLocation();
            int xBannerPresentLocation = referralBannerLocation.getX();
            int yBannerPresentLocation = referralBannerLocation.getY();
            if (xBannerPresentLocation != xBannerLocation) {
                Debugger.println("Present Banner 'X' is at " + xBannerPresentLocation + ", Actual Banner location 'X' is at " + xBannerLocation);
                return false;
            }
            if (yBannerPresentLocation != yBannerLocation) {
                Debugger.println("Present Banner 'Y' is at " + yBannerPresentLocation + ", Actual Banner location 'Y' is at " + yBannerLocation);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheBannerLocation, " + exp);
            return false;
        }
    }

    public boolean verifyTheBannerLocation() {
        try {
            Wait.forElementToBeDisplayed(driver, referralHeader);
            if (!referralHeader.isDisplayed()) {
                Debugger.println("Referral header is not present.");
                return false;
            }
            Point referralHeaderLocation = referralHeader.getLocation();
            xBannerLocation = referralHeaderLocation.getX();
            yBannerLocation = referralHeaderLocation.getY();
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheBannerLocation, " + exp);
            return false;
        }
    }

    //new for 1761 below 2
    public boolean verifyNgisIdAndReferralId() {
        String ngisId = referralHeaderPatientNgisId.getText();
        String referralId = referralHeaderReferralId.getText();
        try {
            if (!(ngisId.charAt(0) == 'p' && ngisId.length() == 12)) {
                if (!(referralId.charAt(0) == 'p' && referralId.length() == 12)) {
                    Debugger.println("The ngis id and referral id are not correct");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: verifyNgisIdAndReferralId: exception found" + exp);
            SeleniumLib.takeAScreenShot("ngisIdAndreferralId.jpg");
            return false;
        }
    }


    public boolean verifyTextFromReferralHeaderPatientNgisId() {

        try {
            String webElementText = referralHeaderPatientNgisId.getAttribute("value").toString();
            if (!webElementText.contains(" ")) {
                Debugger.println("Text copied from webelement is different from actual content");
                return false;
            }
            return true;
        } catch (Exception e) {
            Debugger.println("Exception found while copying and verifying the webelement text." + e);
            SeleniumLib.takeAScreenShot("webelementtext.jpg");
            return false;
        }
    }

    public boolean stageIsInCompleted(String stage) {
        try {
            Wait.forElementToBeDisplayed(driver, toDoList);
            String webElementLocator = stageIsToDo.replace("dummyStage", getPartialUrl(stage));
            Wait.seconds(2);
            WebElement referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, referralStage);
            Wait.seconds(2);
            boolean status = referralStage.getAttribute("class").contains(currentStageLocator);
            if (status == true) {
                return true;
            }
            Debugger.println("Status of Stage.." + stage + " is: " + referralStage.getAttribute("class") + ", but expected to be incomplete.");
            SeleniumLib.takeAScreenShot("StageIncomplete.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception in Checking Stage InCompletion Status: " + exp);
            SeleniumLib.takeAScreenShot("StageIncomplete.jpg");
            return false;
        }
    }

    //889
    @FindBy(xpath = "//li[contains(@class,'stage-list-item')]//a[text()='Patient choice']")
    public WebElement stageListPatientChoice;
    @FindBy(xpath = "//li[contains(@class,'stage-list-item')]//a[text()='Requesting organisation']")
    public WebElement stageListsRequestingOrganisation;
    @FindBy(xpath = "//li[contains(@class,'stage-list-item')]//a[text()='Test package']")
    public WebElement stageListTestPackage;
    @FindBy(xpath = "//li[contains(@class,'stage-list-item')]//a[text()='Responsible clinician']")
    public WebElement stageListsResponsibleClinician;
    @FindBy(xpath = "//li[contains(@class,'stage-list-item')]//a[text()='Clinical questions']")
    public WebElement stageListsClinicalQuestions;
    @FindBy(xpath = "aria-labelledby=\"dialog-title\"")
    public WebElement mandatoryStageDialogueBox;

    public boolean validateMandatoryStages(DataTable fieldDetails) {
        List<List<String>> fieldsText = fieldDetails.asLists();
        try {
            // Wait.forElementToBeDisplayed(driver, mandatoryStageDialogueBox, 10);
            if (fieldsText.get(1).get(0).equalsIgnoreCase(stageListsRequestingOrganisation.getText())) {
                if (fieldsText.get(2).get(0).equalsIgnoreCase(stageListTestPackage.getText())) {
                    if (fieldsText.get(3).get(0).equalsIgnoreCase(stageListsResponsibleClinician.getText())) {
                        if (fieldsText.get(4).get(0).equalsIgnoreCase(stageListsClinicalQuestions.getText())) {
                            if (fieldsText.get(5).get(0).equalsIgnoreCase(stageListPatientChoice.getText())) {
                                return true;
                            }
                        }
                    }
                }
            }
            Debugger.println("The mandatory stage  dialog is not properly displayed");
            return false;
        } catch (Exception exp) {
            Debugger.println("ReferralPage: validateMandatoryStages: " + exp);
            SeleniumLib.takeAScreenShot("validateMandatoryStages.jpg");
            return false;
        }
    }


    @FindBy(xpath = "//*[@id='referral__header']//button[text()='Submit']")
    public By disabledsubmitReferralButton;

    public boolean submiButtonStatusAfterSubmission() {

        try {
            seleniumLib.waitForElementVisible(submitReferralButton);
            if (seleniumLib.isElementClickable(disabledsubmitReferralButton)) {

                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: submitButtonStatus: " + exp);
            return false;
        }
    }

    //for E2EUI-1616, by Deepak
    @FindBy(xpath = "//span[contains(.,'Proband')]/ancestor::div[contains(@class,'participant-list')]/div[2]//span[@class='child-element']")
    WebElement relationshipToProbandField;


    public boolean verifyRelationshipToProbandStatus(String realation2) {
        try {
            String relationshipToProbandFieldOnPrintFormSection = relationshipToProbandField.getText();
            if (realation2.equals(relationshipToProbandFieldOnPrintFormSection)) {
                Debugger.println("The relationship to proband status has been updated");
                return true;
            }
            return false;
        } catch (Exception exp) {
            Debugger.println("Referral page : verifyRelationshipToProbandStatus  : exception found  :" + exp);
            SeleniumLib.takeAScreenShot("RelationshipToProbandStatus.jpg");
            return false;
        }
    }

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement savePatientDetailsToNGISButton;

    public boolean highlightedSavePatientDetailsToNGISButton(String button, String buttonColor) {
        try {
            Wait.forElementToBeDisplayed(driver, savePatientDetailsToNGISButton);
            String buttonBgColor = savePatientDetailsToNGISButton.getCssValue("background-color");
            if (!savePatientDetailsToNGISButton.getText().equalsIgnoreCase(button)) {
                Debugger.println("Actual button with name: " + savePatientDetailsToNGISButton.getText());
                Debugger.println("Excepted button with name: " + button);
                return false;
            }
            if (!buttonBgColor.equalsIgnoreCase(StylesUtils.convertFontColourStringToCSSProperty(buttonColor))) {
                Debugger.println("Actual button color is: " + buttonBgColor);
                Debugger.println("Excepted button color is: " + buttonColor);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Save Patient Details To NGIS Button not found. " + exp);
            return false;
        }
    }

//    Prasanjit

    String incompleteStage = "//a[text()='dummyStage']/child::*[@data-testid='completed-icon']";

    @FindBy(xpath = "//a[@data-selected='true']")
    public WebElement activeStageInToDoList;

    public boolean verifyTheActiveStageInToDoList(String activeStage) {
        try {
            Wait.forElementToBeDisplayed(driver, activeStageInToDoList);
            if (!activeStageInToDoList.getText().equalsIgnoreCase(activeStage)) {
                Debugger.println("Actual active stage is " + activeStageInToDoList.getText());
                Debugger.println("Expected active stage is " + activeStage);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("FamilyMemberDetailsPage, verifyTheActiveStageInToDoList " + exp);
            return false;
        }
    }

    public boolean incompleteStagesInTodoList(String stage) {
        try {
            Wait.forElementToBeDisplayed(driver, toDoList, 120);
            String webElementLocator = incompleteStage.replace("dummyStage", getPartialUrl(stage));
            Wait.seconds(2);
            try {
                if (seleniumLib.isElementPresent(driver.findElement(By.xpath(webElementLocator)))) {
                    Debugger.println(stage + " is a completed stage.");
                    return false;
                }
                Debugger.println(stage + " stage is not completed.");
                return true;
            } catch (Exception exp) {
                Debugger.println(stage + " stage is marked as incomplete");
                return true;
            }
        } catch (Exception exp) {
            Debugger.println("Exception from incompleteStagesInTodoList " + exp);
            return false;
        }
    }

    public boolean verifyTheTextInTheCurrentPage(String expInput) {
        try {
            String bodyText = driver.findElement(By.tagName("body")).getText();
            List<WebElement> expElements = driver.findElements(By.xpath("//*[contains(text(),'" + expInput + "')]"));
            String[] text = {"nhs number", "Nhs Number", "nhs no", "Nhs No", "NHS no", "test-order"};

            for (int i = 0; i < text.length; i++) {
                if (bodyText.contains(text[i])) {
                    return false;
                }
            }

            if (expElements.size() == 0) {
                Debugger.println("Nothing present in the current page which contains " + expInput);
                return true;
            }

            for (int i = 0; i < expElements.size(); i++) {
                if (!expElements.get(i).getText().contains(expInput)) {
                    if (expElements.get(i).getText().equalsIgnoreCase("")) {
                        break;
                    }
                    Debugger.println("Actual text is " + expElements.get(i).getText());
                    Debugger.println("Expected text is " + expInput);
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception, ReferralPage:verifyNHSNumberInTheCurrentPage " + exp);
            return false;
        }
    }

    @FindBy(xpath = "//span[text()='Created']")
    WebElement createdBadge;

    public boolean verifyPresenceOfCreatedBadge(String temp) {
        try {
            Wait.forElementToBeDisplayed(driver, createdBadge);
            if (!createdBadge.getText().contains(temp)) {
                Debugger.println("Created badge is not there");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exxception found in : ReferalPage : verifyPresenceOfCreatedBadge" + exp);
            SeleniumLib.takeAScreenShot("CreatedBadge.jpg");
            return false;
        }
    }

    public List getTheFontSizeOfFieldLabelsOnCurrentPage() {
        try {
            Wait.forElementToBeDisplayed(driver, pageTitle);
            List<String> actualErrorMessagesColour = new ArrayList<>();
            for (WebElement errorMessage : genericFieldLabels) {
                actualErrorMessagesColour.add(errorMessage.getCssValue("font-size"));
            }
            Debugger.println("Actual size of field texts " + actualErrorMessagesColour);
            return actualErrorMessagesColour;
        } catch (Exception exp) {
            Debugger.println("Exception from getting field labels size" + exp);
            SeleniumLib.takeAScreenShot("fields-labels.jpg");
            return null;
        }

    }

    public List getColourOfTheFieldsLabelsOnCurrentPage() {

        try {
            Wait.forElementToBeDisplayed(driver, pageTitle);
            List<String> actualErrorMessagesColour = new ArrayList<>();
            for (WebElement errorMessage : genericFieldLabels) {
                actualErrorMessagesColour.add(errorMessage.getCssValue("color"));
            }
            Debugger.println("Actual text label  colours" + actualErrorMessagesColour);
            return actualErrorMessagesColour;
        } catch (Exception exp) {
            Debugger.println("Exception from getting field labels." + exp);
            SeleniumLib.takeAScreenShot("fields-labels.jpg");
            return null;
        }
    }

    public boolean verifyNHSFormat(String format) {
        //Verify the NHS format.
        String[] nhsFormat = format.split(",");
        int size = nhsFormat.length;
        int[] nhsExpSection = new int[size];
        for (int i = 0; i < size; i++) {
            nhsExpSection[i] = Integer.parseInt(nhsFormat[i]);
        }
        int expIdx = 0;
        if (nhsChunkSeparators.size() > 0) {
            for (int i = 0; i < nhsChunkSeparators.size(); i++) {
                if (i != 0 && i % 3 == 0) {
                    expIdx = 0;
                }
                if (nhsChunkSeparators.get(i).getText().trim().length() != nhsExpSection[expIdx]) {
                    Debugger.println("Actual " + nhsChunkSeparators.get(i).getText() + " Excepted length " + nhsExpSection[expIdx]);
                    Debugger.println("NHS Display is not in 3-3-4 separation.");
                    return false;
                }
                expIdx++;
            }
            Debugger.println("NHS Format verified in global card...");
        }

        if (nhsChunkSeparatorsInPatientRecordCard.size() > 0) {
            if (nhsChunkSeparatorsInPatientRecordCard.size() != 3) {
                Debugger.println("Expected NHS format in patient card as 3 sets, but separated in " + nhsChunkSeparatorsInPatientRecordCard.size() + " ways");
                return false;
            }
            for (int i = 0; i < nhsChunkSeparatorsInPatientRecordCard.size(); i++) {
                if (nhsChunkSeparatorsInPatientRecordCard.get(i).getText().trim().length() != nhsExpSection[i]) {
                    Debugger.println("NHS Display in patient card is not in 3-3-4 separation.");
                    return false;
                }
            }
            Debugger.println("NHS Format verified all places...");
        }
        return true;
    }

    public boolean verifyReferralIDFormat(String format) {

        String[] referralIDFormat = format.split(",");
        int size = referralIDFormat.length;
        int[] referralIdExpSection = new int[size];
        for (int i = 0; i < size; i++) {
            referralIdExpSection[i] = Integer.parseInt(referralIDFormat[i]);
        }

        //Verify for the Referral ID format
        int noOfReferralSections = referralIDChunkSeparators.size();
        int expIdx = 0;
        for (int i = 0; i < noOfReferralSections; i++) {
            if (i != 0 && i % 3 == 0) {
                expIdx = 0;
            }
            if (referralIDChunkSeparators.get(i).getText().trim().length() != referralIdExpSection[expIdx]) {
                Debugger.println("Referral ID Display is not in 4-4-4 separation.");
                return false;
            }
            expIdx++;
        }
        Debugger.println("Referral ID verified...");
        return true;
    }

    public boolean verifyNGISIDFormat(String format) {

        String[] referralIdFormat = format.split(",");
        int size = referralIdFormat.length;
        int[] referralIdExpSection = new int[size];
        for (int i = 0; i < size; i++) {
            referralIdExpSection[i] = Integer.parseInt(referralIdFormat[i]);
        }

        int expIdx = 0;
        //Verify for the NGIS format
        int noOfNgisSections = ngisIDChunkSeparators.size();
        for (int i = 0; i < noOfNgisSections; i++) {
            if (i != 0 && i % 3 == 0) {
                expIdx = 0;
            }
            if (ngisIDChunkSeparators.get(i).getText().trim().length() != referralIdExpSection[expIdx]) {
                Debugger.println("NGISID Display is not in 4-4-4 separation.");
                return false;
            }
            expIdx++;
        }
        Debugger.println("NGIS ID verified...");
        return true;
    }

    public boolean referralSubmitButtonStatus(String expectedColor) {
        try {
            Wait.forElementToBeDisplayed(driver, referralSubmitButton);
            String expectedBackground = StylesUtils.convertFontColourStringToCSSProperty(expectedColor);
            String actualColor = referralSubmitButton.getCssValue("background-color");
            Debugger.println("Actual Color: "+actualColor);
            if (!actualColor.equalsIgnoreCase(expectedBackground)) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying Submit Patient Choice Status:" + exp);
            return false;
        }
    }

}//end
