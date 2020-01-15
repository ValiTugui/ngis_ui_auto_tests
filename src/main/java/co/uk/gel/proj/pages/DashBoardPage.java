package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
}
