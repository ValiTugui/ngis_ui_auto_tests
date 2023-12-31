package co.uk.gel.proj.pages;

import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DashBoardPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
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

    @FindBy(xpath = "//*[@alt='NHS Logo' or @class='css-11wgm18']")
    public WebElement testSelectionNhsLogo;

    @FindBy(xpath = "//div[@id='root']//a/child::div[contains(@class,'styles_card__title')]")
    public List<WebElement> nhsTabs;

    @FindBy(xpath = "//div[contains(text(),'Manage samples')]")
    public WebElement ManageSamplesTab;


    @FindBy(xpath = "//*[contains(@class,'styles_container__1q-zX') or @class='css-3xnhly' or @class='css-8gah82' or contains(@class,'styles_nav__19zlJ')]")
    public WebElement nonProdBannerColor;

    @FindBy(xpath = "//nav[contains(@class,'styles_nav__19zlJ')]")
    public WebElement prodBannerColor;

    @FindBy(xpath = "//span[contains(translate(., 'Env', 'env'),'Test environment')]")
    public WebElement testEnvironment;

    @FindBy(xpath = "//*[@class='styles_environment-label__iOJBm' or @class='css-krxhtj' or @class='styles_environment-label__1HJXr']")
    public WebElement getTestEnvBackgroundColor;

    @FindBy(xpath = "//a[@href='/test-selection/' or @href='/test-order']")
    public WebElement genomicsMedicineServiceLogo;

    @FindBy(xpath = "//div[@class='styles_nav--genomics__3ozGE' or @class='\\']")
    public WebElement dashboardGenomicsMedicineServiceLogo;

    @FindBy(xpath = "//span[@class='css-1nceqb7']")
    public WebElement loggedInUserName;

//    public void navigateToDashboardPage() {
//        Debugger.println("before getTo_dashboard_url  .");
//        driver.get(AppConfig.getTo_dashboard_url());
//        Debugger.println("getTo_dashboard_url  Successfully.");
//        new WebDriverWait(driver,10).until(ExpectedConditions.urlContains("login.microsoft"));
//        if ((driver.getCurrentUrl().contains("login.microsoft"))) {
//            Wait.forElementToBeClickable(driver, emailAddressField);
//            emailAddressField.sendKeys(AppConfig.getApp_username());
//            Debugger.println("Logged in as normal user");
//            nextButton.click();
//            Wait.seconds(2);
//            Wait.forElementToBeClickable(driver, passwordField);
//            passwordField.sendKeys(AppConfig.getApp_password());
//            nextButton.click();
//            Wait.seconds(2);
//            if (driver.getCurrentUrl().contains("/Error")){
//                driver.navigate().to(AppConfig.getTo_dashboard_url());
//            }
//        }
//    }
    public void navigateToDashboardPage() {
        driver.get(AppConfig.getTo_dashboard_url());
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("login.microsoft"));
            if ((driver.getCurrentUrl().contains("login.microsoft"))) {
                Wait.forElementToBeClickable(driver, emailAddressField);
                emailAddressField.sendKeys(AppConfig.getApp_username());
                Debugger.println("Logged in as normal user");
                nextButton.click();
                Wait.seconds(2);
                Wait.forElementToBeClickable(driver, passwordField);
                passwordField.sendKeys(AppConfig.getApp_password());
                nextButton.click();
                Wait.seconds(2);
                if (driver.getCurrentUrl().contains("/Error")) {
                    driver.navigate().to(AppConfig.getTo_dashboard_url());
                }
            }
        }catch (TimeoutException exc){
            Debugger.println("Failed in waiting for 'login.microsoft' page:"+exc);
            if(driver.getCurrentUrl().contains("dashboard")){
                Debugger.println("Dashboard is already loaded without Login..");
            }
    }
}

    @FindBy(xpath = "//h1[contains(text(),'Welcome to the National')]")
    public WebElement resultsPanelPageTitle;

    public void waitUntilDashboardPageResultsContainerIsLoaded() {
        try {
            Wait.forElementToBeDisplayed(driver, resultsPanelPageTitle, 60);
        } catch (Exception exp) {
            Debugger.println("Dashboard page not loaded." + exp);
        }
    }

    public boolean dashboardPageResultsIsLoaded() {
        try {
            Wait.forElementToBeDisplayed(driver, resultsPanelPageTitle, 60);
//            Wait.forElementToBeClickable(driver, resultsPanel);
            return true;
        } catch (Exception exp) {
            Debugger.println("Dashboard page not loaded." + exp);
            return false;
        }
    }

    public boolean pageTitleValidation(String titleText) {
        String actual = pageTitle.getText();
        if (actual.equalsIgnoreCase(titleText)) {
            return true;
        } else {
            return false;
        }
    }

    public void clickOrderAGenomicTest() {
        Wait.forElementToBeDisplayed(driver, testOrderLocator);
        Action.clickElement(driver, testOrderLocator);
    }

    public void clickFindAGenomicTest() {
        Wait.forElementToBeDisplayed(driver, testSelectionLocator);
        Action.clickElement(driver, testSelectionLocator);
    }

    public boolean verifyTheNHSLogo() {
        try {
            Wait.forElementToBeDisplayed(driver, NHSLogo);
            if (!NHSLogo.isDisplayed()) {
                Debugger.println("NHS logo not present in the page.");
                return false;
            }
            Point nhsLogoLocation = NHSLogo.getLocation();
            int xLocation = nhsLogoLocation.getX();
            int yLocation = nhsLogoLocation.getY();
            //Default logo position is x-59 and y=0
            if (xLocation != 59 && yLocation != 0) {
                Debugger.println("NHS logo not present in the same location(x=59,y=0)");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception form DashBoardPage, verifyTheNHSLogo " + exp);
            return false;
        }
    }

    public boolean verifyTheDashboardTabs() {
        try {
            Action.isTabClickable(driver, 5, nhsTabs);
            for (int i = 0; i < nhsTabs.size(); i++) {
                switch (i) {
                    case 0:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Find a genomic test")) {
                            Debugger.println(i + ".Actual tab:" + nhsTabs.get(i).getText() + ",Expected:Find a genomic test");
                            return false;
                        }

                        break;
                    case 1:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Order a genomic test")) {
                            Debugger.println(i + ".Actual tab:" + nhsTabs.get(i).getText() + ",Expected:Order a genomic test");
                            return false;
                        }
                        break;
                    case 2:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Manage samples")) {
                            Debugger.println(i + ".Actual tab:" + nhsTabs.get(i).getText() + ",Expected:Manage samples");
                            return false;
                        }
                        break;
                    case 3:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Enter the Interpretation Portal")) {
                            Debugger.println(i + ".Actual tab:" + nhsTabs.get(i).getText() + ",Expected:Enter the Interpretation Portal");
                            return false;
                        }
                        break;
                    case 4:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Open PanelApp")) {
                            Debugger.println(i + ".Actual tab:" + nhsTabs.get(i).getText() + ",Expected: Open PanelApp");
                            return false;
                        }
                        break;
                    case 5:
                        if (!nhsTabs.get(i).getText().equalsIgnoreCase("Access the service admin tool")) {
                            Debugger.println(i + ".Actual tab:" + nhsTabs.get(i).getText() + ",Expected: Access the service admin tool");
                            return false;
                        }
                        break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception form verifyTheDashboardTabs :" + exp);
            return false;
        }
    }

    public boolean clickOnTab(String tabName) {
        try {
            switch (tabName) {
                case "Find a genomic test":
                    Wait.isElementDisplayed(driver, nhsTabs.get(0), 60);
                    nhsTabs.get(0).click();
                    break;
                case "Order a genomic test":
                    Wait.isElementDisplayed(driver, nhsTabs.get(1), 60);
                    nhsTabs.get(1).click();
                    break;
                case "Manage samples":
                    Wait.isElementDisplayed(driver, nhsTabs.get(2), 60);
                    nhsTabs.get(2).click();
                    break;
                case "Enter the Interpretation Portal":
                    Wait.isElementDisplayed(driver, nhsTabs.get(3), 60);
                    nhsTabs.get(3).click();
                    break;
                case "Open PanelApp":
                    Wait.isElementDisplayed(driver, nhsTabs.get(4), 60);
                    nhsTabs.get(4).click();
                    break;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception form DashBoardPage, Click on Tab " + tabName + " " + exp);
            return false;
        }
    }

    public boolean directedToTestSelectionPage() {
        try {
            if (driver.getCurrentUrl().contains("dashboard")) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    public boolean clickOnManageSampleTab() {
        try {
            if (!Wait.isElementDisplayed(driver, ManageSamplesTab, 30)) {
                return false;
            }
            ManageSamplesTab.click();
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    public void navigateToDashboardPageWithSuperUser() {
        driver.get(AppConfig.getTo_dashboard_url());
        new WebDriverWait(driver,10).until(ExpectedConditions.urlContains("login.microsoft"));
        if ((driver.getCurrentUrl().contains("login.microsoft"))) {
            Wait.forElementToBeClickable(driver, emailAddressField);
//            emailAddressField.sendKeys(AppConfig.getApp_username());
            emailAddressField.sendKeys(AppConfig.getApp_superUsername());
            Debugger.println("Logged in as Super user");
            nextButton.click();
            Wait.seconds(2);
            Wait.forElementToBeClickable(driver, passwordField);
            passwordField.sendKeys(AppConfig.getApp_superPassword());
//            passwordField.sendKeys(AppConfig.getApp_password());
            nextButton.click();
        }
    }

    public boolean clickOnTabsAndVerifyTheUrl(String tabName) {
        //3 seconds static wait has been added because sometimes takes a one or two seconds for "Access the service admin tool" to be displayed
        Wait.seconds(3);
        try {
            String url = "";
            switch (tabName) {
                case "Find a genomic test":
                    Wait.isElementDisplayed(driver, nhsTabs.get(0), 60);
                    nhsTabs.get(0).click();
                    SeleniumLib.sleepInSeconds(2);
                    url = driver.getCurrentUrl();
//                    Debugger.println("Current URL " + url);
                    if (!url.contains("test-selection")){
                        Debugger.println("the user in wrong page : "+url);
                        return false;
                    }
                    driver.navigate().to(AppConfig.getTo_dashboard_url());
                    break;
                case "Order a genomic test":
                    Wait.isElementDisplayed(driver, nhsTabs.get(1), 60);
                    nhsTabs.get(1).click();
                    SeleniumLib.sleepInSeconds(2);
                    url = driver.getCurrentUrl();
//                    Debugger.println("Current URL " + url);
                    if (!url.contains("test-order")){
                        Debugger.println("the user in wrong page : "+url);
                        return false;
                    }
                    driver.navigate().to(AppConfig.getTo_dashboard_url());
                    break;
                case "Manage samples":
                    Wait.isElementDisplayed(driver, nhsTabs.get(2), 60);
                    nhsTabs.get(2).click();
                    SeleniumLib.sleepInSeconds(2);
                    url = driver.getCurrentUrl();
//                    Debugger.println("Current URL " + url);
                    if (!url.contains("miportal")){
                        Debugger.println("the user in wrong page : "+url);
                        return false;
                    }
                    driver.navigate().to(AppConfig.getTo_dashboard_url());
                    break;
                case "Enter the Interpretation Portal":
                    Wait.isElementDisplayed(driver, nhsTabs.get(3), 60);
                    nhsTabs.get(3).click();
                    url = driver.getCurrentUrl();
//                    Debugger.println("Current URL " + url);
                    if (!url.contains("cipapi")) {
                        Debugger.println("the user in wrong page : "+url);
                        return false;
                    }
                    driver.navigate().to(AppConfig.getTo_dashboard_url());
                    break;
                case "Open PanelApp":
                    Wait.isElementDisplayed(driver, nhsTabs.get(4), 60);
                    nhsTabs.get(4).click();
                    SeleniumLib.sleepInSeconds(2);
                    url = driver.getCurrentUrl();
//                    Debugger.println("Current URL " + url);
                    if (!url.contains("panelapp")){
                        Debugger.println("the user in wrong page : "+url);
                        return false;
                    }
                    driver.navigate().to(AppConfig.getTo_dashboard_url());
                    break;
                case "Access the service admin tool":
                    Wait.isElementDisplayed(driver, nhsTabs.get(5), 60);
                    nhsTabs.get(5).click();
                    SeleniumLib.sleepInSeconds(2);
                    url = driver.getCurrentUrl();
//                    Debugger.println("Current URL " + url);
                    if (!url.contains("admin-tool")) {
                        Debugger.println("the user in wrong page : "+url);
                        return false;
                    }
                    driver.navigate().to(AppConfig.getTo_dashboard_url());
                    break;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception form DashBoardPage, Click on Tab " + tabName + " " + exp);
            return false;
        }
    }
}
