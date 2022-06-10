package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static co.uk.gel.lib.Actions.acceptAlert;
import static co.uk.gel.lib.Actions.isAlertPresent;

public class HomePage {

    WebDriver driver;
    SeleniumLib seleniumLib;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
        PageFactory.initElements(driver, this);
    }

    public String tabTitle = "NHS England | Public Genetic Test Directory";

    @FindBy(css = "div[class*='header']")
    public WebElement header;

    @FindBy(xpath = "//*[contains(@class,'searchPanel')]//child::input")
    public WebElement searchField;

    @FindBy(css = "button[class*='searchButton']")
    public WebElement searchIcon;

    @FindBy(xpath = "//div[contains(text(), 'Tests')]")
    public WebElement testsTab;

    @FindBy(xpath = "//div[contains(text(), 'Clinical Indications')]/span")
    public WebElement clinicalIndicationsTabValue;

    @FindBy(xpath = "//div[contains(text(), 'Tests')]/span")
    public WebElement testsTabValue;

    @FindBy(xpath = "//input[@id='Rare and inherited Disease']/..")
    public WebElement rareAndInheritedDiseasesChkBox;

    @FindBy(xpath = "//input[@id='Tumour']/..")
    public WebElement tumourChkBox;

    @FindBy(css = "a[class*='link']")
    public List<WebElement> resultsPanels;

    @FindBy(xpath = "//h4[@class='styles_mainHeader__3n2V4']")
    public List<WebElement> panelsMainHeaders;

    @FindBy(xpath = "//*[contains(@class,'containerInner')]//descendant::button")
    public List<WebElement> cookiesUnderstandButton;

    @FindBy(css = "div[class*='aside']")
    public WebElement filtersPanel2;

    @FindBy(xpath = "//div[contains(@class,'styles_filter__')]")    // //div[contains(@class,'styles_filter__')]
    public WebElement filtersPanel;

    @FindBy(css = "div[class*='main']")
    public WebElement resultsPanel;

    @FindBy(xpath = "//li[contains(@class,'styles_link__22wKg')]/a")
    public List<WebElement> resultsPages;

    @FindBy(xpath = "//li[@class='styles_next__E1BtZ']/a")
    public WebElement nextPageBtn;

    public String closeCookiesButton = "//*[contains(@class,'cta__')]//descendant::button";

    @FindBy(xpath = "//*[contains(@class, 'styles_link')]")
    public List<WebElement> tabResults;

    @FindBy(xpath = "//*[text()='Log out']")
    public WebElement logOutLink;


    public boolean waitUntilHomePageResultsContainerIsLoaded() {
       try {
           if (!Wait.isElementDisplayed(driver, filtersPanel, 120)) {
               Debugger.println("HomePage:filtersPanel not displayed even after waiting period.");
               return false;
           }
           if (!Wait.isElementDisplayed(driver, resultsPanel, 120)) {
               Debugger.println("HomePage:resultsPanel not displayed even after waiting period.");
               return false;
           }
           return true;
       }catch(Exception exp){
           Debugger.println("Exception:HomePage:waitUntilHomePageResultsContainerIsLoaded:"+exp);
           return false;
       }
    }

    public boolean typeInSearchField(String searchTerm) {
        try {
            if(!Wait.isElementDisplayed(driver,searchField,60)){
                Debugger.println("searchField not present even after waiting period: ");
                return false;
            }
            searchField.clear();
            searchField.sendKeys(searchTerm);
            return true;
        }catch(Exception exp){
            try{
                seleniumLib.clickOnWebElement(searchField);
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception from entering the search Term: " + exp1);
                return false;
            }
        }
    }

    public boolean clickSearchIconFromSearchField() {
        try {
            Click.element(driver, searchIcon);
            return true;
        }catch(Exception exp){
            try{
                seleniumLib.clickOnWebElement(searchIcon);
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception from clickSearchIconFromSearchField:" + exp);
                return false;
            }
        }
    }

    public void closeCookiesBannerFromFooter() {
        if (cookiesUnderstandButton.size() > 0) {
            Click.element(driver, cookiesUnderstandButton.get(0));
            Wait.forNumberOfElementsToBeEqualTo(driver, (By.xpath(closeCookiesButton)), 0);
            //Debugger.println("Cookies Banner Closed.");
        }
    }

    public boolean selectFirstEntityFromResultList() {
        if(resultsPanels != null && resultsPanels.size() > 0){
            Click.element(driver, resultsPanels.get(0));
            return true;
        }else{
            Debugger.println("HomePage:selectFirstEntityFromResultList:No Results Loaded for the Search : Waiting for another 30 seconds");
            Wait.seconds(30);//Waiting additional 30 seconds to load the list as it is observed IndexOut exception many times here.
            if(resultsPanels != null && resultsPanels.size() > 0) {
                Click.element(driver, resultsPanels.get(0));
                return true;
            }else{
                Debugger.println("HomePage:selectFirstEntityFromResultList:Still not loaded. Failing. URL:"+driver.getCurrentUrl());
                return false;
            }
        }
    }

    public boolean TestDirectoryHomePageIsDisplayed() {
        try {
//            Wait.forURLToContainSpecificText(driver, "/clinical-tests");
            Wait.forURLToContainSpecificText(driver, "/test-selection");
            Wait.forElementToBeDisplayed(driver, searchField);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from TestDirectoryHomePageIsDisplayed:"+exp);
            SeleniumLib.takeAScreenShot("TestDirectoryHomePageIsDisplayed.jpg");
            return false;
        }
    }

    public long rareAndInheritedDiseasesSearchResult() {
        try {
            if(!Wait.isElementDisplayed(driver,rareAndInheritedDiseasesChkBox,30)){
                Debugger.println("Rare and Inherited Filter box not displayed."+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("rareAndInheritedDiseasesChkBox.jpg");
                return 0;
            }
            Actions.clickElement(driver, rareAndInheritedDiseasesChkBox);
            if(!waitUntilHomePageResultsContainerIsLoaded()){
                Debugger.println("Results Panel not Reloaded."+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("rareAndInheritedDiseasesChkBox.jpg");
                return 0;
            }
            Wait.seconds(10);
            String clinicalIndications = TestUtils.fetchNumberFromAGivenString(clinicalIndicationsTabValue.getText());
            String tests = TestUtils.fetchNumberFromAGivenString(testsTabValue.getText());
            //Debugger.println("RD: ClinicalIndications:"+clinicalIndications+",Tests:"+tests);
            //Deselect
            Actions.clickElement(driver, rareAndInheritedDiseasesChkBox);
            Wait.seconds(2);
            return Integer.parseInt(clinicalIndications) + Integer.parseInt(tests);

        }catch(Exception exp){
            Debugger.println("Exception in rareAndInheritedDiseasesSearchResult:"+exp);
            SeleniumLib.takeAScreenShot("rareAndInheritedDiseasesSearchResult.jpg");
            return 0;
        }
    }

    public long tumorSearchResult() {
        try {
            if(!Wait.isElementDisplayed(driver,tumourChkBox,30)){
                Debugger.println("Tumour Filter box not displayed."+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("tumourChkBox.jpg");
                return 0;
            }
            Actions.clickElement(driver, tumourChkBox);
            if(!waitUntilHomePageResultsContainerIsLoaded()){
                Debugger.println("Results Panel not Reloaded."+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("tumourChkBox.jpg");
                return 0;
            }
            Wait.seconds(10);
            String clinicalIndications = TestUtils.fetchNumberFromAGivenString(clinicalIndicationsTabValue.getText());
            String tests = TestUtils.fetchNumberFromAGivenString(testsTabValue.getText());
            Debugger.println("Tumour ClinicalIndications:"+clinicalIndications+",Tests:"+tests);
            //Deselect
            Actions.clickElement(driver, tumourChkBox);
            Wait.seconds(2);
            return Integer.parseInt(clinicalIndications) + Integer.parseInt(tests);

        }catch(Exception exp){
            Debugger.println("Exception in tumourChkBox:"+exp);
            SeleniumLib.takeAScreenShot("tumourChkBox.jpg");
            return 0;
        }
    }

    public long totalSearchResult() {
        try {
            String clinicalIndications = TestUtils.fetchNumberFromAGivenString(clinicalIndicationsTabValue.getText());
            String tests = TestUtils.fetchNumberFromAGivenString(testsTabValue.getText());
            Debugger.println("ClinicalIndications:"+clinicalIndications+",Tests:"+tests);
            return Integer.parseInt(clinicalIndications) + Integer.parseInt(tests);
        }catch(Exception exp){
            Debugger.println("Exception in totalSearchResult:"+exp);
            SeleniumLib.takeAScreenShot("totalSearchResult.jpg");
            return 0;
        }
    }

    public boolean testResultsAreLoaded() {
        return tabResults.size()>0;
    }

    public void logOutFromApplication(){
        try {
            Debugger.println("Logging Out from Application..");
            if(!Wait.isElementDisplayed(driver,logOutLink,60)){
                Debugger.println("Could not locate Log out Link...");
                return;
            }
            Actions.clickElement(driver,logOutLink);
            Wait.seconds(2);
            if(Actions.isAlertPresent(driver)){
                Actions.acceptAlert(driver);
            }
            Actions.deleteCookies(driver);
            Wait.seconds(15);
        } catch (UnhandledAlertException f) {
            try {
                driver.switchTo().defaultContent();
                Actions.deleteCookies(driver);
            } catch (NoAlertPresentException e) {
                e.printStackTrace();
            }
            Wait.seconds(15);
            Debugger.println("Logged Out Successfully.");
        }catch(Exception exp){
            Debugger.println("Exception from Logging out...."+exp);
        }
    }
    public boolean searchForTheTest(String testName){
        try{
            if(!waitUntilHomePageResultsContainerIsLoaded()){
                return false;
            }
            if(!typeInSearchField(testName)){
                return false;
            }
            if(!clickSearchIconFromSearchField()){
                return false;
            }
            if(!waitUntilHomePageResultsContainerIsLoaded()){
                return false;
            }
            closeCookiesBannerFromFooter();
            if(!selectFirstEntityFromResultList()){
                return false;
            }
            closeCookiesBannerFromFooter();
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from searching the Test:"+exp+"\n"+driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("TestSearchError.jpg");
            return false;
        }
    }

    public String getCiCOdeFromResultsPanels(int panelNumber){
        return driver.findElement(By.xpath("(//div//h5/span[5])["+panelNumber+"]")).getText();
    }

    public void moveToClinicalIndicationsPage(int pageNumber){
        seleniumLib.moveMouseAndClickOnElement(By.xpath("(//li[contains(@class,'styles_link__22wKg')]/a)["+pageNumber+"]"));
    }


}