package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.af.Wanneer;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashBoardPage {

    WebDriver driver;

    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String tabTitle = "Genomic Medicine Service | Dashboard - NGIS";

    @FindBy(css = "div[class*='multiCard']")
    public WebElement resultsPanel;

    @FindBy(xpath = "//div/div[1]/h1")
    public WebElement pageTitle;

    @FindBy(name = "loginfmt")
    public WebElement emailAddressField;

    @FindBy(name = "passwd")
    public WebElement passwordField;

    @FindBy(css = "input[type*='submit']")
    public WebElement nextButton;

    @FindBy(css = "a[href*='test-order']")
    public WebElement testOrderLocator;

    @FindBy(css = "a[href*='test-selection']")
    public WebElement testSelectionLocator;

    @FindBy(xpath = "//header//a//*[contains(@xmlns,'http://www.w3.org/')]")
    public WebElement NHSLogo;

    @FindBy(xpath = "//div[@id='root']//a/child::div[contains(@class,'styles_card__title')]")
    public List<WebElement> nhsTabs;

    @FindBy(xpath = "//div[contains(text(),'Manage samples')]")
    public WebElement ManageSamplesTab;

    public void navigateToDashboardPage() {
        driver.get(AppConfig.getTo_dashboard_url());
        if (!(driver.getCurrentUrl().contains("patient-search"))) {
            Pages.login(this.driver, emailAddressField, passwordField, nextButton );
        }
    }

     public void waitUntilDashboardPageResultsContainerIsLoaded() {
        Wait.forElementToBeDisplayed(driver, resultsPanel);
    }

    public void dashboardPageResultsIsLoaded() {
        Wait.forElementToBeClickable(driver, resultsPanel);
    }

    public boolean pageTitleValidation(String titleText) {
        String actual = pageTitle.getText();
        if (actual.equalsIgnoreCase(titleText)) {
            return true;
        } else {
            return false;
        }
    }

    public void clickOrderAGenomicTest(){
        Wait.forElementToBeDisplayed(driver, testOrderLocator);
        Actions.clickElement(driver, testOrderLocator);
    }

    public void clickFindAGenomicTest(){
        Wait.forElementToBeDisplayed(driver, testSelectionLocator);
        Actions.clickElement(driver, testSelectionLocator);
    }

      public boolean verifyTheNHSLogo() {
        try {
            Wait.forElementToBeDisplayed(driver, NHSLogo);
            if (!NHSLogo.isDisplayed()) {
                Debugger.println("NHS logo not present in the page.");
                SeleniumLib.takeAScreenShot("DashboardNHSLogo.jpg");
                return false;
            }
            Point nhsLogoLocation = NHSLogo.getLocation();
            int xLocation = nhsLogoLocation.getX();
            int yLocation = nhsLogoLocation.getY();
            //Default logo position is x-59 and y=0
            if (xLocation != 59 && yLocation != 0) {
                Debugger.println("NHS logo not present in the same location(x=59,y=0)");
                SeleniumLib.takeAScreenShot("DashboardNHSLogo.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception form DashBoardPage, verifyTheNHSLogo " + exp);
            SeleniumLib.takeAScreenShot("DashboardNHSLogo.jpg");
            return false;
        }
    }

    public boolean verifyTheDashboardTabs() {
        try {
            Actions.isTabClickable(driver, 5, nhsTabs);
            for (int i = 0; i < nhsTabs.size(); i++) {
                switch (i) {
                    case 0:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Find a genomic test")) {
                            Debugger.println(i + ".Actual tab:" + nhsTabs.get(i).getText()+",Expected:Find a genomic test");
                            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
                            return false;
                        }
                        break;
                    case 1:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Order a genomic test")) {
                            Debugger.println(i + ".Actual tab:" + nhsTabs.get(i).getText()+",Expected:Order a genomic test");
                            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
                            return false;
                        }
                        break;
                    case 2:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Manage samples")) {
                            Debugger.println(i + ".Actual tab:" + nhsTabs.get(i).getText()+",Expected:Manage samples");
                            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
                            return false;
                        }
                        break;
                    case 3:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Enter the Interpretation Portal")) {
                            Debugger.println(i + ".Actual tab:" + nhsTabs.get(i).getText()+",Expected:Enter the Interpretation Portal");
                            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
                            return false;
                        }
                        break;
                    case 4:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Open PanelApp")) {
                            Debugger.println(i + ".Actual tab:" + nhsTabs.get(i).getText()+",Expected:Open PanelApp");
                            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
                            return false;
                        }
                        break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception form verifyTheDashboardTabs :" + exp);
            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
            return false;
        }
    }

    public boolean clickOnFindAGenomicTestTab() {
        try{
            if(!Wait.isElementDisplayed(driver, testSelectionLocator, 60)){
                Debugger.println("testSelectionLocator button is not displayed");
                SeleniumLib.takeAScreenShot("testSelectionLocator.jpg");
                return false;
            }
            testSelectionLocator.click();
            return true;
        } catch (Exception exp){
            Debugger.println("Exception form DashBoardPage, clickOnFindAGenomicTestTab " + exp);
            SeleniumLib.takeAScreenShot("testSelectionLocator.jpg");
            return false;
        }
    }

    public boolean directedToTestSelectionPage() {
        try {
            if (driver.getCurrentUrl().contains("dashboard")) {
                Debugger.println("Current URL still contains dashboard, which is not expected after re-direction.");
                SeleniumLib.takeAScreenShot("TestSelectionRedirection.jpg");
                return false;
            }
           return true;
        } catch (Exception exp) {
            Debugger.println("Exception form DashBoardPage, test selection page " + exp);
            SeleniumLib.takeAScreenShot("TestSelectionRedirection.jpg");
            return false;
        }
    }

    public boolean clickOnManageSampleTab() {
        try{
            if(!Wait.isElementDisplayed(driver, ManageSamplesTab, 30)){
                Debugger.println("ManageSamplesTab is not displayed");
                SeleniumLib.takeAScreenShot("manageSamplesTabNotFound.jpg");
                return false;
            }
            ManageSamplesTab.click();
            Debugger.println("Manage Samples Tab is selected");
            return true;
        } catch (Exception exp){
            Debugger.println("Exception form DashBoardPage, clickOnManageSampleTab: " + exp);
            SeleniumLib.takeAScreenShot("manageSamplesTabNotFound.jpg");
            return false;
        }
    }
}
