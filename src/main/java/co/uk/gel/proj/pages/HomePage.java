package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static co.uk.gel.lib.Actions.acceptAlert;
import static co.uk.gel.lib.Actions.isAlertPresent;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
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

    @FindBy(xpath = "//div/ul/li[1]/label")
    public WebElement rareAndInheritedDiseasesChkBox;

    @FindBy(xpath = "//div/ul/li[3]/label")
    public WebElement tumorChkBox;

    @FindBy(css = "a[class*='link']")
    public List<WebElement> resultsPanels;

    @FindBy(xpath = "//*[contains(@class,'containerInner')]//descendant::button")
    public List<WebElement> cookiesUnderstandButton;

    @FindBy(css = "div[class*='aside']")
    public WebElement filtersPanel2;

    @FindBy(xpath = "//div[contains(@class,'styles_filter__')]")    // //div[contains(@class,'styles_filter__')]
    public WebElement filtersPanel;

    @FindBy(css = "div[class*='main']")
    public WebElement resultsPanel;

    public String closeCookiesButton = "//*[contains(@class,'cta__')]//descendant::button";

    @FindBy(xpath = "//*[contains(@class, 'styles_link')]")
    public List<WebElement> tabResults;

    @FindBy(xpath = "//*[text()='Log out']")
    public WebElement logOutLink;


    public void waitUntilHomePageResultsContainerIsLoaded() {
       try {
           if (!Wait.isElementDisplayed(driver, filtersPanel, 200)) {
               Debugger.println("HomePage:filtersPanel not displayed even after waiting period.");
               SeleniumLib.takeAScreenShot("HomePageFilterPanel.jpg");
               Assert.assertFalse("HomePage:filtersPanel not displayed even after waiting period(120s).",true);
           }
           if (!Wait.isElementDisplayed(driver, resultsPanel, 200)) {
               Debugger.println("HomePage:resultsPanel not displayed even after waiting period.");
               SeleniumLib.takeAScreenShot("HomePageResultPanel.jpg");
               Assert.assertFalse("HomePage:resultsPanel not displayed even after waiting period(120s).",true);
           }
       }catch(Exception exp){
           Debugger.println("Exception:HomePage:waitUntilHomePageResultsContainerIsLoaded:"+exp);
           SeleniumLib.takeAScreenShot("HomePagePanels.jpg");
           Assert.assertFalse("Exception from Home Page Loading: "+exp,true);
       }
    }

    public void typeInSearchField(String searchTerm) {
        try {
            Wait.forElementToBeDisplayed(driver, searchField);
            if(!Wait.isElementDisplayed(driver,searchField,10)){
                Debugger.println("searchField for present even after waiting period: ");
                Assert.assertFalse("searchField for present even after waiting period: ",true);
            }
            searchField.sendKeys(searchTerm);
        }catch(Exception exp){
            Debugger.println("Exception from entering the search Term: "+exp);
            Assert.assertFalse("Exception from entering the search Term: "+exp,true);
        }
    }

    public void clickSearchIconFromSearchField() {
        Click.element(driver, searchIcon);
    }

    public void closeCookiesBannerFromFooter() {
        if (cookiesUnderstandButton.size() > 0) {
            Click.element(driver, cookiesUnderstandButton.get(0));
            Wait.forNumberOfElementsToBeEqualTo(driver, (By.xpath(closeCookiesButton)), 0);
            Debugger.println("Cookies Banner Closed.");
        }
    }

    public void selectFirstEntityFromResultList() {

         waitUntilHomePageResultsContainerIsLoaded(); //Already invoked
        if(resultsPanels != null && resultsPanels.size() > 0){
            Click.element(driver, resultsPanels.get(0));
        }else{
            Debugger.println("HomePage:selectFirstEntityFromResultList:No Results Loaded for the Search : Waiting for another 30 seconds");
            Wait.seconds(30);//Waiting additional 30 seconds to load the list as it is observed IndexOut exception many times here.
            if(resultsPanels != null && resultsPanels.size() > 0) {
                Click.element(driver, resultsPanels.get(0));
            }else{
                Debugger.println("HomePage:selectFirstEntityFromResultList:Still not loaded. Failing. URL:"+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("NoResultPanel.jpg");
                Assert.assertFalse("HomePage:selectFirstEntityFromResultList:Still not loaded. Failing. See Screen shot:NoResultPanel.jpg",true);
            }
        }

    }

    public void TestDirectoryHomePageIsDisplayed() {
        Wait.forURLToContainSpecificText(driver, "/clinical-tests");
        Wait.forElementToBeDisplayed(driver, searchField);
    }

    public long rareAndInheritedDiseasesSearchResult() {
        rareAndInheritedDiseasesChkBox.click();
        waitUntilHomePageResultsContainerIsLoaded();
        Wait.seconds(1);
        String a = clinicalIndicationsTabValue.getText();
        String b = testsTabValue.getText();
        a = a.replaceAll("\\(", "").replaceAll("\\)", "");
        b = b.replaceAll("\\(", "").replaceAll("\\)", "");
        Debugger.println("Rare is " + (Integer.parseInt(a) + Integer.parseInt(b)));
        rareAndInheritedDiseasesChkBox.click();
        Wait.seconds(1);
        return Integer.parseInt(a) + Integer.parseInt(b);
    }

    public long tumorSearchResult() {
        tumorChkBox.click();
        waitUntilHomePageResultsContainerIsLoaded();
        Wait.seconds(1);
        String a = clinicalIndicationsTabValue.getText();
        String b = testsTabValue.getText();
        a = a.replaceAll("\\(", "").replaceAll("\\)", "");
        b = b.replaceAll("\\(", "").replaceAll("\\)", "");
        Debugger.println("Tumor is " + (Integer.parseInt(a) + Integer.parseInt(b)));
        tumorChkBox.click();
        waitUntilHomePageResultsContainerIsLoaded();
        return Integer.parseInt(a) + Integer.parseInt(b);
    }

    public long totalSearchResult() {
        String a = clinicalIndicationsTabValue.getText();
        String b = testsTabValue.getText();
        a = a.replaceAll("\\(", "").replaceAll("\\)", "");
        b = b.replaceAll("\\(", "").replaceAll("\\)", "");
        Debugger.println("Total is " + (Integer.parseInt(a) + Integer.parseInt(b)));
        return Integer.parseInt(a) + Integer.parseInt(b);
    }

    public boolean testResultsAreLoaded() {
        return tabResults.size()>0;
    }

    public void logOutFromApplication(){
        try {
            Debugger.println("Logging Out from Application..");
            if(!Wait.isElementDisplayed(driver,logOutLink,60)){
                Debugger.println("Could not locate Log out Link...");
                SeleniumLib.takeAScreenShot("NoLogOutLink.jpg");
                return;
            }
            Actions.clickElement(driver,logOutLink);
            Wait.seconds(2);
            if(Actions.isAlertPresent(driver)){
                Actions.acceptAlert(driver);
            }
            Actions.deleteCookies(driver);
        } catch (UnhandledAlertException f) {
            try {
                driver.switchTo().defaultContent();
                Actions.deleteCookies(driver);

            } catch (NoAlertPresentException e) {
                e.printStackTrace();
            }
            Wait.seconds(5);
            Debugger.println("Logged Out Successfully.");
        }catch(Exception exp){
            Debugger.println("Exception from Logging out...."+exp);
        }
    }
    public boolean searchForTheTest(String testName){
        try{
            waitUntilHomePageResultsContainerIsLoaded();
            typeInSearchField(testName);
            clickSearchIconFromSearchField();
            waitUntilHomePageResultsContainerIsLoaded();
            closeCookiesBannerFromFooter();
            selectFirstEntityFromResultList();
            closeCookiesBannerFromFooter();
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from searching the Test:"+exp);
            SeleniumLib.takeAScreenShot("TestSearchError.jpg");
            return false;
        }
    }
}