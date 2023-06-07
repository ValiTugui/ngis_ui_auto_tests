package co.uk.gel.proj.pages;

import co.uk.gel.lib.Action;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ClinicalIndicationsTestSelectPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public ClinicalIndicationsTestSelectPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[class*='tab']")
    public List<WebElement> clinicalIndicationTabs;

    @FindBy(xpath = "//div[@class='styles_grid__o-eMs']/div")
    public List<WebElement> eligibilityCriteriaSections;

    @FindBy(xpath = "//h2/span")
    public List<WebElement> LabsSection;

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

    @FindBy(xpath = "//div[@class='styles_clinical-card__content__1JOHt']")
    public List<WebElement> testOrderCiResultsPanels;

    @FindBy(xpath = "//button[@class='css-fe8tch']")
    public List<WebElement> aboutThisTestCiResultsPanels;

    @FindBy(xpath = "//div[@class='css-v7cv2m']//h3")
    public List<WebElement> modalTabsTitle;

    @FindBy(xpath = "//span[.='Continue test ordering']")
    public WebElement continueTestOrderingBtn;

    @FindBy(xpath = "//*[contains (@class, 'styles_processCardImg')]")
    public List<WebElement> orderProcessResults;

    @FindBy(xpath = "//*[contains(@class,'processCard')]//child::h2")
    public List<WebElement> orderProcesssTitles;

    @FindBy(xpath = "//*/h4")
    public List<WebElement> clinicalIndicationsHeadings;

    @FindBy(css = "div[class*='mainSection']")
    public WebElement clinicalIndicationsSearchValue;

    String clinicalIndicationsHeadingsLocator = "//*/h4";

    @FindBy(xpath = "(//div[@class='styles_clinical-list__container__ZOdZz css-1vu4a8i']//h4)[1]")
    public WebElement ciNameDisplayedOnFirstCardTO;

    @FindBy(xpath = "(//div[contains(@class,'card-multi-line')])[1]")
    public WebElement ciTypeAndCiCodeDisplayedOnFirstCardTO;

    @FindBy(xpath = "(//div[@class='styles_container__3q5IQ']//h4)[1]")
    public WebElement ciNameDisplayedOnFirstCardTD;

    @FindBy(xpath = "(//h5/span[3])[1]")
    public WebElement ciTypeDisplayedOnFirstCardTD;

    @FindBy(xpath = "(//h5/span[5])[1]")
    public WebElement ciCodeDisplayedOnFirstCardTD;

    @FindBy(xpath = "//div[contains(@class,'styles_overlayShow')]")
    public WebElement overlayPage;

    public boolean clickStartTestOrderReferralButton() {
        try {
            if (!Wait.isElementDisplayed(driver, startTestOrderButton, 80)) {
                return false;
            }
            Action.clickElement(driver,startTestOrderButton);
            return true;
        } catch (Exception exp) {
            try{
                seleniumLib.clickOnWebElement(startTestOrderButton);
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception from Starting Referral...." + exp);
                return false;
            }
        }
    }

    public boolean clickBackToSearchButton() {
        try {
            if (!Wait.isElementDisplayed(driver, backToSearch, 10)) {
                Action.scrollToTop(driver);
            }
            Action.clickElement(driver, backToSearch);
            return true;
        }catch(Exception exp){
            try {
               seleniumLib.clickOnWebElement(backToSearch);
               return true;
            }catch(Exception exp1){
                return false;
            }
        }
    }

    public boolean clickGoToTestPageButton() {
        try {
            Wait.forElementToBeClickable(driver, goToTestPageButtonFromPopup);
            Click.element(driver, goToTestPageButtonFromPopup);
            return true;
        }catch(Exception exp){
            try{
                seleniumLib.clickOnWebElement(goToTestPageButtonFromPopup);
                return true;
            }catch(Exception exp1) {
                return false;
            }
        }
    }

    public boolean clickGoToClinicalIndicationButton() {
        try {
            Wait.forElementToBeClickable(driver, goToClinicalIndicationsButtonInPopup);
            Click.element(driver, goToClinicalIndicationsButtonInPopup);
            return true;
        }catch(Exception exp){
            try{
                seleniumLib.clickOnWebElement(goToClinicalIndicationsButtonInPopup);
                return true;
            }catch(Exception exp1) {
                return false;
            }
        }
    }
    public boolean validateIfLoadingWheelIsPresent() {
        return loadingWheel.size() >= 0;
    }

    public boolean validateIfCorrectTextIsDisplayed(String expected) {
        if(!Wait.isElementDisplayed(driver,loadingText,30)){
            return false;
        }
        String actual = loadingText.getText();
        if(actual.equalsIgnoreCase(expected)){
            return true;
        }
        if(actual.contains("Please wait a moment")) {
            //Wait for another 30 seconds more - as observed from jenkins failure
            Wait.seconds(30);
        }else{
            return false;
        }
        actual = loadingText.getText();
        if(!actual.equalsIgnoreCase(expected)){
           return false;
        }
        return true;
    }
    public boolean validateIfCorrectButtonDisplayed(String expected) {
        if(!Wait.isElementDisplayed(driver,startTestOrderButton,10)){
            return false;
        }
        String actual = startTestOrderButton.getText();
        if(!actual.equalsIgnoreCase(expected)){
            return false;
        }
        return true;
    }

    public boolean validateIfWrongTextIsNotDisplayed(String expected) {
        String actual = loadingText.getText();
        if(actual.equalsIgnoreCase(expected)){
            return false;
        }
        return true;
    }

    public boolean checkIfClinicalIndicationsAreLoaded() {
        try {
            if(Wait.isElementDisplayed(driver, clinicalIndicationsResultContainer,180)) {
                return clinicalIndicationsResults.size() >= 0;
            }
            return false;
        }catch(Exception exp){
            Debugger.println("Exception in ClinicalIndicationResultContainer loading.. "+exp+"\n"+driver.getCurrentUrl());
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
        return (LabsSection.size() == 1 && (LabsSection.get(0).getText().contains(sectionName)));
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

    public void clickFirstResultInTestOrderingClinicalIndications() {
        Click.element(driver, testOrderCiResultsPanels.get(0));
    }

    public boolean clinicalIndicationsTabValidation(String buttonName, String sectionName1, String sectionName2) {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.xpath(clinicalIndicationsHeadingsLocator), 0);
        return (((goToClinicalIndicationsButtonInPopup.getText().matches(buttonName)) && (closePopupButton.isDisplayed()) && clinicalIndicationsHeadings.get(1).getText().matches(sectionName1)) && (clinicalIndicationsHeadings.get(2).getText().matches(sectionName2)));
    }

    public void waitUntilFurtherInfoCardsFromFurtherInfoTabAreLoaded() {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.xpath(furtherInfoSectionsLocator), 1);
    }

    public boolean furtherInfoTabValidation(String sectionName1, String sectionName2, String sectionName3) {
        Wait.forPageToBeLoaded(driver);
        waitUntilFurtherInfoCardsFromFurtherInfoTabAreLoaded();
        for (int i = 0; i < furtherInfoSections.size(); i++) {
            Wait.forElementToBeDisplayed(driver, furtherInfoSections.get(i));
        }
        switch (furtherInfoSections.size()) {
            case 1: {
                return ((furtherInfoSections.get(0).findElement(By.tagName("h2")).getText().contains(sectionName1)));
            }
            case 2: {
                return ((furtherInfoSections.get(0).findElement(By.tagName("h2")).getText().contains(sectionName1)) && (furtherInfoSections.get(1).findElement(By.tagName("h2")).getText().contains(sectionName2)));
            }
            case 3: {
                return ((furtherInfoSections.get(0).findElement(By.tagName("h2")).getText().contains(sectionName1)) && (furtherInfoSections.get(1).findElement(By.tagName("h2")).getText().contains(sectionName2)) && (furtherInfoSections.get(2).findElement(By.tagName("h2")).getText().contains(sectionName3)));
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
                return false;
            }
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    public boolean closeClinicalIndicationPopUp() {
        try{
            if(!Wait.isElementDisplayed(driver, closePopupButton,10)){
                return false;
            }
            closePopupButton.click();
            return true;
        }catch (Exception exp){
            return false;
        }
    }
    public boolean clickOnViewMoreIcon(){
        try{
            if(!Wait.isElementDisplayed(driver,testInfoIcon,10)){
                return false;
            }
            Action.clickElement(driver,testInfoIcon);
            return true;
        }catch(Exception exp){
           return false;
        }
    }
}