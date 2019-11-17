package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NgisPatientOne;
import co.uk.gel.proj.util.Debugger;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ReferralPage<check> {

    WebDriver driver;

    public ReferralPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement SignOutStatusMessage;

    @FindBy(css = "span.styles_badge__1KpTw.badge--default")
    public WebElement getReferralHeaderStatus;

    @FindBy(css = "*[class*='referral-header']")
    public WebElement referralHeader;

    @FindBy(css = "*[class*='referral-header-details']")
    public WebElement referralHeaderRegion;

    @FindBy(css = "button[class*='referral-header__submit']")
    public WebElement referralSubmitButton;

    @FindBy(className = "todo-list")
    public WebElement toDoList;

    @FindBy(xpath = "//span[contains(string(),'Tumours')]/..")
    public WebElement tumourToDoList;

    @FindBy(css = "div[class*='referral__main']")
    public WebElement sectionBody;

    @FindBy(css = "h1[class*='page-title']")
    public WebElement pageTitle;

    @FindBy(xpath = "//*[contains(@class,'header__content')]//child::a")
    public WebElement genomicMedicineServicelogo;

    @FindBy(css = "button[class*='referral-navigation__continue']")
    public WebElement saveAndContinueButton;

    @FindBy(css = "button[class*='referral-navigation__button--back']")
    public WebElement backLink;

    @FindBy(css = "strong[class*='header-item']")
    public List<WebElement> referralHeaderDetails;

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

    @FindBy(css = "strong[class*='header-item__value']")
    public List<WebElement> referralHeaderValues;

    @FindBy(css = "*[class*='referral-success-notification']")
    public WebElement referralSuccessNotification;

    @FindBy(css = "*[class*='downloads__notice']")
    public WebElement submissionConfirmationBanner;

    @FindBy(css = "*[class*='notice__title']")
    public WebElement submissionConfirmationBannerTitle;

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

    @FindBy(css = "*[class*='footer__nhs-logo']")
    public WebElement nhsEnglandLogoFromFooter;

    @FindBy(css = "*[class*='footer__genomics-logo']")
    public WebElement genomicsEnglandLogoFromFooter;

    @FindBy(css = "*[class*='footer__content-area']")
    public WebElement footerContentArea;

    @FindBy(css = "*[class*='referral-header__cancel']")
    public WebElement cancelReferralLink;

    @FindBy(css = "*[class*='referral-header__cancel']")
    public List<WebElement> cancelReferraButton;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(xpath = "//label[contains(@for,'cancel-options')]//following::div")
    public WebElement cancelReasonDropdown;

    @FindBy(css = "*[class*='notification-bar--success']")
    public WebElement cancelReferralNotification;

    @FindBy(css = "button[class*='modal__action']")
    public List<WebElement> cancelReferralButtons;

    @FindBy(css = "*[class*='modal__body']")
    public WebElement postSubmissionModal;

    @FindBy(xpath = "//*[contains(@class,'stage-list-item')]//child::a")
    public List<WebElement> stagesLinks;

    @FindBy(css = "*[class*='modal__close']")
    public WebElement closeModalButton;

    @FindBy(css = "*[class*='error-message']")
    public WebElement errorMessage;

    @FindBy(css = "*[class*='footer__link']")
    public List<WebElement> footerLinks;

    @FindBy(css = "*[id*='otherTile']")
    public WebElement useAnotherAccountButton;

    @FindBy(css = "*[class*='no-access__title']")
    public WebElement cancelledReferralWarningPageTitle;

    @FindBy(xpath = "//*[contains(@class,'no-access__copy')]//child::a")
    public List<WebElement> cancelledReferralWarningPageLinks;

    @FindBy(css = "*[class*='helix']")
    public List<WebElement> helix;
    //Family Member Search
    @FindBy(xpath = "//button[contains(text(),'Add family member')]")
    public WebElement addFamilyMember;

    @FindBy(xpath = "//p[contains(@class,'hint__text')]")
    public List<WebElement> hintText;

    String valuesInReferralHeaderBar = "strong[class*='header-item']";
    String stageIsMarkedAsMandatoryToDo = "//a[contains(@href,'" + "dummyStage" + "')]//descendant::span[3]";
    String stageIsToDo = "a[href*='" + "dummyStage" + "']";
    String stageName = "//a[contains(@href,'" + "dummyStage" + "')]//child::span[2]";
    String helixIcon = "*[class*='helix']";
    String mandatoryToDOIconLocator = "todo__required-icon";
    String currentStageLocator = "todo--is-current";
    String stageCompleteLocator = "todo--is-complete";


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
        Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
        Click.element(driver, saveAndContinueButton);
        if (helix.size() > 0) {
            Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
        }
    }

    public boolean saveAndContinueButtonIsDisplayed() {
        Wait.forElementToBeClickable(driver, saveAndContinueButton);
        return true;
    }


    public void checkThatReferralWasSuccessfullyCreated() {
        Wait.forElementToBeDisplayed(driver, getReferralHeaderStatus, 200);
        Wait.forElementToBeDisplayed(driver, referralHeader, 100);
        Wait.forElementToBeDisplayed(driver, toDoList, 100);
        Wait.forElementToBeDisplayed(driver, sectionBody);
        Wait.forNumberOfElementsToBeEqualTo(driver, By.cssSelector(valuesInReferralHeaderBar), 7);
    }
    public void checkThatToDoListSuccessfullyLoaded() {
        Wait.forElementToBeDisplayed(driver, toDoList, 300);
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
            Actions.clickElement(driver, referralStage);
        }catch(Exception exp){
            //Sometimes click on stage link on second time gives ElementClickInterceptedException. Below code added to handel that.
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
        Wait.forElementToBeDisplayed(driver, toDoList);
        String webElementLocator = stageIsToDo.replace("dummyStage", getPartialUrl(stage));
        WebElement referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
        Wait.forElementToBeDisplayed(driver, referralStage);
        boolean status = referralStage.getAttribute("class").contains(stageCompleteLocator);
        if (status == true) return true;
        else return false;
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
        return Actions.getText(pageTitle);
    }

    public void pageTitleIsDisplayed(String title) {
        Wait.forElementToBeDisplayed(driver, pageTitle);
        Assert.assertEquals(title, getText(pageTitle));
    }

    public void navigateToFamilyMemberSearchPage(){
        Wait.forElementToBeDisplayed(driver,addFamilyMember);
        Actions.clickElement(driver,addFamilyMember);
    }
    public List<String> getTheListOfHelpHintTextsOnCurrentPage() {
        Wait.forElementToBeDisplayed(driver, pageTitle);
        List<String> actualHelpHintTexts = new ArrayList<>();

        for (WebElement fieldHelpHintText : hintText) {
            actualHelpHintTexts.add(fieldHelpHintText.getText().trim());
        }
        String currentPage = getTheCurrentPageTitle();
        Debugger.println("Actual Help-Hint Texts on" + ":" + currentPage + ": page :"  + actualHelpHintTexts);
        return actualHelpHintTexts;
    }
}
