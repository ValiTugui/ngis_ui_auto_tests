package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
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
                if (!nhsTabs.get(i).isDisplayed()) {
                    Debugger.println("Dashboard tab is not present.");
                    SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
                    return false;
                }
                switch (i) {
                    case 0:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Find a genomic test")) {
                            Debugger.println(i + " Actual tab is " + nhsTabs.get(i).getText());
                            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
                            return false;
                        }
                        break;
                    case 1:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Order a genomic test")) {
                            Debugger.println(i + " Actual tab is " + nhsTabs.get(i).getText());
                            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
                            return false;
                        }
                        break;
                    case 2:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Manage samples")) {
                            Debugger.println(i + " Actual tab is " + nhsTabs.get(i).getText());
                            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
                            return false;
                        }
                        break;
                    case 3:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Enter the Interpretation Portal")) {
                            Debugger.println(i + " Actual tab is " + nhsTabs.get(i).getText());
                            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
                            return false;
                        }
                        break;
                    case 4:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Open PanelApp")) {
                            Debugger.println(i + " Actual tab is " + nhsTabs.get(i).getText());
                            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
                            return false;
                        }
                        break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception form DashBoardPage, verifyTheDashboardTabs " + exp);
            SeleniumLib.takeAScreenShot("DashboardTabs.jpg");
            return false;
        }
    }

    public boolean clickOnFindAGenomicTestTab() {
        Wait.seconds(5);
        testSelectionLocator.click();
        return true;
    }

    public boolean directedToTestSelectionPage() {
        Wait.seconds(5);
        try {
            if (driver.getCurrentUrl().contains("dashboard")) {
                Wait.seconds(2);
                String navigatedURL = driver.getCurrentUrl();
                Debugger.println("Current URL AFTER dashboard page re-direction:" + navigatedURL);
            } return true;
        } catch (Exception exp) {
            Debugger.println("Exception form DashBoardPage, test selection page " + exp);
            SeleniumLib.takeAScreenShot("TestSelection.jpg");
            return false;
        }
    }

    public boolean clickOnManageSampleTab() {
        Wait.seconds(10);
        ManageSamplesTab.click();
        return true;
    }
}
