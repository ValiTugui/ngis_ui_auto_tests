package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import cucumber.api.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ClinicalIndicationsTestSelectPage {

    WebDriver driver;

    public ClinicalIndicationsTestSelectPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[class*='tab']")
    public List<WebElement> clinicalIndicationTabs;

    @FindBy(css = "div[class*='eligibilityCard']")
    public List<WebElement> eligibilityCriteriaSections;

    @FindBy(xpath = "//*[contains(@class,'grid')]//descendant::div/h2/span")
    public List<WebElement> testDetailsSubSections;

    @FindBy(css = ".btn-secondary")
    public WebElement goToClinicalIndicationsButtonInPopup;

    @FindBy(css = "*[class*='testCard']")
    public List<WebElement> testsFromTestPackageList;

    @FindBy(xpath = "//*[contains(@class,'styles_infoIcon')]")
    public WebElement testInfoIcon;

    @FindBy(css = "g[id*='Illustration/blue/test']")
    public WebElement testPackagePopupIcon;

    @FindBy(css = "div[class*='turnAroundTimes']")
    public WebElement testPackagePopupTime;

    @FindBy(css = "div[class*='testPackageProps']")
    public WebElement testPackagePopupProps;

    @FindBy(css = "*[class*='styles_mainHeader']")
    public WebElement testPackagePopupTitle;

    @FindBy(css = ".btn-secondary")
    public WebElement goToTestPageButtonFromPopup;

    @FindBy(xpath = "//*[contains (@class, 'card_')]")
    public List<WebElement> furtherInfoSections;

    String furtherInfoSectionsLocator = "//*[contains (@class, 'card_')]";

    @FindBy(css = ".btn.btn-md.btn-primary")
    public WebElement startTestOrderButton;

    @FindBy(css = "div[class*='closeButton']")
    public WebElement closePopupButton;

    @FindBy(xpath = "//div[contains(@class,'back')]//descendant::a")
    public WebElement backToSearch;

    @FindBy(xpath = "//*[contains(@class,'styles_helix')]")
    public List<WebElement> loadingWheel;

    @FindBy(css = "h2[class*='relatedContainer__header']")
    public WebElement loadingText;

    @FindBy(xpath = "//*[contains (@class, 'styles_relatedContainer')]/ul")
    public WebElement clinicalIndicationsResultContainer;

    @FindBy(xpath = "//*[contains (@class, 'styles_card')]")
    public List<WebElement> clinicalIndicationsResults;

    @FindBy(xpath = "//*[contains (@class, 'styles_processCardImg')]")
    public List<WebElement> orderProcessResults;

    @FindBy(xpath = "//*[contains(@class,'processCard')]//child::h2")
    public List<WebElement> orderProcesssTitles;

    @FindBy(xpath = "//*/h4")
    public List<WebElement> clinicalIndicationsHeadings;

    @FindBy(css = "div[class*='mainSection']")
    public WebElement clinicalIndicationsSearchValue;

    String clinicalIndicationsHeadingsLocator = "//*/h4";

    //@FindBy(xpath = "//div[contains(@class,'styles_container__3_ged')]")
    @FindBy(xpath = "//div[contains(@class,'styles_overlayShow')]")
    public WebElement overlayPage;

    public boolean clickStartTestOrderReferralButton() {
        try {
            Debugger.println("Starting Referral....");
            Wait.forElementToBeDisplayed(driver, startTestOrderButton, 30);
            if (!Wait.isElementDisplayed(driver, startTestOrderButton, 10)) {
                Debugger.println("Start Referral button not displayed even after waiting period...Failing.");
                SeleniumLib.takeAScreenShot("startReferralError.jpg");
                Assert.assertFalse("Start Referral button not displayed even after waiting period...Failing.", true);
            }
            Actions.clickElement(driver,startTestOrderButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Starting Referral...." + exp);
            SeleniumLib.takeAScreenShot("startReferralError.jpg");
            return false;
        }

    }

    public void clickBackToSearchButton() {
        try {
            if (!Wait.isElementDisplayed(driver, backToSearch, 10)) {
                Actions.scrollToTop(driver);
            }
            Actions.retryClickAndIgnoreElementInterception(driver, backToSearch);
        }catch(Exception exp){
            Debugger.println("Exception in clickBackToSearchButton:"+exp);
            SeleniumLib.takeAScreenShot("clickBackToSearchButton.jpg");
        }
    }

    public boolean clickGoToTestPageButton() {
        try {
            Wait.forElementToBeClickable(driver, goToTestPageButtonFromPopup);
            Click.element(driver, goToTestPageButtonFromPopup);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in clickGoToTestPageButton:"+exp);
            SeleniumLib.takeAScreenShot("clickGoToTestPageButton.jpg");
            return false;
        }
    }

    public boolean clickGoToClinicalIndicationButton() {
        try {
            Wait.forElementToBeClickable(driver, goToClinicalIndicationsButtonInPopup);
            Click.element(driver, goToClinicalIndicationsButtonInPopup);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in clickGoToClinicalIndicationButton:"+exp);
            SeleniumLib.takeAScreenShot("clickGoToClinicalIndicationButton.jpg");
            return false;
        }
    }

    public boolean validateIfLoadingWheelIsPresent() {
        return loadingWheel.size() >= 0;
    }

    public boolean validateIfCorrectTextIsDisplayed(String expected) {
        if(!Wait.isElementDisplayed(driver,loadingText,30)){
            Debugger.println("Text element not displayed:"+expected);
            SeleniumLib.takeAScreenShot("validateIfCorrectTextIsDisplayed.jpg");
            return false;
        }
        String actual = loadingText.getText();
        Debugger.println("ActualText:"+actual);
        if(!actual.equalsIgnoreCase(expected)){
            Debugger.println("Expected text:"+expected+",Actual:"+actual);
            SeleniumLib.takeAScreenShot("validateIfCorrectTextIsDisplayed.jpg");
            return false;
        }
        return true;
    }
    public boolean validateIfCorrectButtonDisplayed(String expected) {
        if(!Wait.isElementDisplayed(driver,startTestOrderButton,10)){
            Debugger.println("Text element not displayed:"+expected);
            SeleniumLib.takeAScreenShot("validateIfCorrectButtonDisplayed.jpg");
            return false;
        }
        String actual = startTestOrderButton.getText();
        if(!actual.equalsIgnoreCase(expected)){
            Debugger.println("Expected text:"+expected+",Actual:"+actual);
            SeleniumLib.takeAScreenShot("validateIfCorrectButtonDisplayed.jpg");
            return false;
        }
        return true;
    }

    public boolean validateIfWrongTextIsNotDisplayed(WebElement element, String expected) {
        String actual = element.getText();
        return !actual.equalsIgnoreCase(expected);
    }

    public boolean checkIfClinicalIndicationsAreLoaded() {
        try {
            if(Wait.isElementDisplayed(driver, clinicalIndicationsResultContainer,180)) {
                return clinicalIndicationsResults.size() >= 0;
            }
            Debugger.println("FAILED: ClinicalIndicationResultContainer not loaded.");
            SeleniumLib.takeAScreenShot("clinicalIndicationsResultContainer.jpg");
            return false;
        }catch(Exception exp){
            Debugger.println("Exception in ClinicalIndicationResultContainer loading.. "+exp);
            SeleniumLib.takeAScreenShot("clinicalIndicationsResultContainer.jpg");
            return false;
        }
    }

    public boolean isTabSelected(String tabName) {
        String attribute = "class";
        String select = "activeTab";
        switch (tabName) {
            case "Eligibility Criteria":
            case "Clinical Indications": {
                return clinicalIndicationTabs.get(0).getAttribute(attribute).contains(select);
            }
            case "Test Package":
            case "Test details": {
                return clinicalIndicationTabs.get(1).getAttribute(attribute).contains(select);
            }
            case "Further Info":
            case "Labs": {
                return clinicalIndicationTabs.get(2).getAttribute(attribute).contains(select);
            }
            case "Order process": {
                return clinicalIndicationTabs.get(3).getAttribute(attribute).contains(select);
            }
            default:
                return false;
        }
    }

    public boolean selectTab(String tabName) {
        boolean isSelected  = false;
        try {
            switch (tabName) {
                case "Eligibility Criteria":
                case "Clinical Indications": {
                    Click.element(driver, clinicalIndicationTabs.get(0));
                    isSelected = true;
                    break;
                }
                case "Test Package":
                case "Test details": {
                    Click.element(driver, clinicalIndicationTabs.get(1));
                    isSelected = true;
                    break;
                }
                case "Further Info":
                case "Labs": {
                    Click.element(driver, clinicalIndicationTabs.get(2));
                    isSelected = true;
                    break;
                }
                case "Order process": {
                    Click.element(driver, clinicalIndicationTabs.get(3));
                    isSelected = true;
                    break;
                }
                default:
                    isSelected = false;
                    break;
            }
            return isSelected;
        }catch(Exception exp){
            Debugger.println("Exception from selectTab:"+exp);
            SeleniumLib.takeAScreenShot("selectTab.jpg");
            return false;
        }
    }

    public boolean isTabPresent(Integer tabCount, String tab1, String tab2, String tab3, String tab4) {

        return ((clinicalIndicationTabs.size() == tabCount) && (clinicalIndicationTabs.get(0).getText().matches(tab1)) && (clinicalIndicationTabs.get(1).getText().matches(tab2)) && (clinicalIndicationTabs.get(2).getText().matches(tab3)) && (clinicalIndicationTabs.get(3).getText().matches(tab4)));
    }

    public void testPackagePopUpValidations() {
        testPackagePopupIcon.isDisplayed();
        testPackagePopupProps.isDisplayed();
        testPackagePopupTime.isDisplayed();
        testPackagePopupTitle.isDisplayed();
    }

    public boolean checkTestPagePopUpTitleMatchesSearchedText() {
        return testsFromTestPackageList.get(0).getText().contains(testPackagePopupTitle.getText());
    }

    public boolean checkTestPagePopUpContents() {
        boolean contentField1 = testPackagePopupProps.findElements(By.tagName("h5")).get(0).getText().contains("Technology");
        boolean contentField2 = testPackagePopupProps.findElements(By.tagName("h5")).get(1).getText().contains("Scope");
        boolean contentField3 = testPackagePopupProps.findElements(By.tagName("h5")).get(2).getText().contains("Targeted genes");
        boolean contentField4 = testPackagePopupProps.findElements(By.tagName("h5")).get(3).getText().contains("Sample type & state");
        boolean contentField5;
        boolean contentField6;
        if (testPackagePopupProps.findElements(By.tagName("h5")).size() == 6) {
            contentField5 = testPackagePopupProps.findElements(By.tagName("h5")).get(4).getText().contains("Optimal family structure");
            contentField6 = testPackagePopupProps.findElements(By.tagName("h5")).get(5).getText().contains("Eligibility criteria");
            return (contentField1 && contentField2 && contentField3 && contentField4 && contentField5 && contentField6);
        } else {
            contentField5 = testPackagePopupProps.findElements(By.tagName("h5")).get(4).getText().contains("Eligibility criteria");
            return (contentField1 && contentField2 && contentField3 && contentField4 && contentField5);
        }
    }

    public boolean testDetailsTabValidation(String sectionName1, String sectionName2, String sectionName3) {
        switch (testDetailsSubSections.size()) {
            case 1: {
                return testDetailsSubSections.get(0).getText().matches(sectionName1);
            }
            case 2: {
                return ((testDetailsSubSections.get(0).getText().matches(sectionName1)) && (testDetailsSubSections.get(1).getText().matches(sectionName2)));
            }
            case 3: {
                return ((testDetailsSubSections.get(0).getText().matches(sectionName1)) && (testDetailsSubSections.get(1).getText().matches(sectionName2)) && (testDetailsSubSections.get(2).getText().matches(sectionName3)));
            }
            default:
                throw new IllegalStateException("Mismatch in No of section: " + testDetailsSubSections.size());
        }
    }

    public boolean labsTabValidation(String sectionName) {
        return (eligibilityCriteriaSections.size() == 1 && (eligibilityCriteriaSections.get(0).getText().contains(sectionName)));
    }

    public boolean orderProcessTabValidation(int numOfSection) {
        return orderProcessResults.size() == numOfSection;
    }

    public boolean checkIfBackToSearchButtonPresent(String buttonName) {
        Wait.forElementToBeDisplayed(driver, backToSearch);
        return backToSearch.getText().matches(buttonName);
    }

    public void waitUntilEligibilityCardsFromEligibilityCriteriaTabAreLoaded() {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector("div[class*='eligibilityCard']"), 1);
    }

    public boolean eligibilityCriteriaTabValidation(String sectionName1, String sectionName2, String sectionName3, String sectionName4) {
        waitUntilEligibilityCardsFromEligibilityCriteriaTabAreLoaded();
        switch (eligibilityCriteriaSections.size()) {
            case 2: {
                Wait.forElementToBeDisplayed(driver, eligibilityCriteriaSections.get(1));
                return ((eligibilityCriteriaSections.get(0).findElement(By.tagName("span")).getText().matches(sectionName1)) && (eligibilityCriteriaSections.get(1).findElement(By.tagName("span")).getText().matches(sectionName2)));
            }
            case 4: {
                Wait.forElementToBeDisplayed(driver, eligibilityCriteriaSections.get(3));
                return ((eligibilityCriteriaSections.get(0).findElement(By.tagName("span")).getText().matches(sectionName1)) && (eligibilityCriteriaSections.get(1).findElement(By.tagName("span")).getText().matches(sectionName2)) && (eligibilityCriteriaSections.get(2).findElement(By.tagName("span")).getText().matches(sectionName3)) && (eligibilityCriteriaSections.get(3).findElement(By.tagName("span")).getText().matches(sectionName4)));
            }
            default:
                throw new IllegalStateException("Section Mismatch " + eligibilityCriteriaSections.size());
        }
    }

    public boolean whoCanOrderContentValidation(String whoCanOrderContent) {
        switch (eligibilityCriteriaSections.size()) {
            case 2: {
                return (eligibilityCriteriaSections.get(1).findElement(By.tagName("p")).getText().matches(whoCanOrderContent));
            }
            case 4: {
                return (eligibilityCriteriaSections.get(3).findElement(By.tagName("p")).getText().matches(whoCanOrderContent));
            }
            default:
                throw new IllegalStateException("Section Mismatch: " + eligibilityCriteriaSections.size());
        }
    }

    public void clickFirstResultInClinicalIndications() {
        Click.element(driver, clinicalIndicationsResults.get(0));
    }

    public boolean clinicalIndicationsTabValidation(String buttonName, String sectionName1, String sectionName2) {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.xpath(clinicalIndicationsHeadingsLocator), 0);
        return (((goToClinicalIndicationsButtonInPopup.getText().matches(buttonName)) && (closePopupButton.isDisplayed()) && clinicalIndicationsHeadings.get(1).getText().matches(sectionName1)) && (clinicalIndicationsHeadings.get(2).getText().matches(sectionName2)));
    }

    public void waitUntilFurtherInfoCardsFromFurtherInfoTabAreLoaded() {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.xpath(furtherInfoSectionsLocator), 1);
    }

    public boolean furtherInfoTabValidation(String sectionName1, String sectionName2, String sectionName3, String sectionName4) {
        Wait.forPageToBeLoaded(driver);
        waitUntilFurtherInfoCardsFromFurtherInfoTabAreLoaded();
        for (int i = 0; i < furtherInfoSections.size(); i++) {
            Wait.forElementToBeDisplayed(driver, furtherInfoSections.get(i));
        }
        switch (furtherInfoSections.size()) {
            case 2: {
                return ((furtherInfoSections.get(0).findElement(By.tagName("h2")).getText().contains(sectionName2)) && (furtherInfoSections.get(1).findElement(By.tagName("h2")).getText().contains(sectionName4)));
            }
            case 3: {
                return ((furtherInfoSections.get(0).findElement(By.tagName("h2")).getText().contains(sectionName2)) && (furtherInfoSections.get(1).findElement(By.tagName("h2")).getText().contains(sectionName3)) && (furtherInfoSections.get(2).findElement(By.tagName("h2")).getText().contains(sectionName4)));
            }
            case 4: {
                return ((furtherInfoSections.get(0).findElement(By.tagName("h2")).getText().contains(sectionName1)) && (furtherInfoSections.get(1).findElement(By.tagName("h2")).getText().contains(sectionName2)) && (furtherInfoSections.get(2).findElement(By.tagName("h2")).getText().contains(sectionName3)) && (furtherInfoSections.get(3).findElement(By.tagName("h2")).getText().contains(sectionName4)));
            }
            default:
                throw new IllegalStateException("Section Mismatch: " + furtherInfoSections.size());
        }
    }

    public boolean checkIfClinicalIndicationsSearchValueMatchesTheSearchTermGiven() {
        return clinicalIndicationsSearchValue.getText().contains(AppConfig.getSearchTerm());
    }

    public boolean verifyTheOverlayIsDisplayed() {
        try {
            if(!Wait.isElementDisplayed(driver, overlayPage,10)){
                Debugger.println("Clinical indication page is not covered by an overlay");
                SeleniumLib.takeAScreenShot("OverLayPage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying verifyTheOverlayIsDisplayed" + exp);
            SeleniumLib.takeAScreenShot("OverLayPage.jpg");
            return false;
        }
    }

    public boolean closeClinicalIndicationPopUp() {
        try{
            if(!Wait.isElementDisplayed(driver, closePopupButton,10)){
                Debugger.println("Clinical indication close pop icon not displayed.");
                SeleniumLib.takeAScreenShot("CIClosePopupIcon.jpg");
            }
            closePopupButton.click();
            return true;
        }catch (Exception exp){
            Debugger.println("Exception from closing Clinical Indication: "+exp);
            SeleniumLib.takeAScreenShot("CIClosePopupIcon.jpg");
            return false;
        }
    }
    public boolean clickOnViewMoreIcon(){
        try{
            if(!Wait.isElementDisplayed(driver,testInfoIcon,10)){
                Debugger.println("View More Icon not present.");
                SeleniumLib.takeAScreenShot("clickOnViewMoreIcon.jpg");
                return false;
            }
            Actions.clickElement(driver,testInfoIcon);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from clickOnViewMoreIcon:"+exp);
            SeleniumLib.takeAScreenShot("clickOnViewMoreIcon.jpg");
            return false;
        }
    }
}