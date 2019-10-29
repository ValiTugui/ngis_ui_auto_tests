package co.uk.gel.proj.pages;

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

    public void navigateToDashboardPage() {
        driver.get(AppConfig.getTo_dashboard_url());
        if(!(driver.getCurrentUrl().contains("patient-search")))
        {loginToDashboardAsServiceDeskUser(driver);
        }
    }

    public void loginToDashboardAsServiceDeskUser(WebDriver driver) {
        Wait.forElementToBeClickable(driver, emailAddressField);
        emailAddressField.sendKeys(AppConfig.getApp_username());
        nextButton.click();
        Wait.seconds(2);
        Wait.forElementToBeClickable(driver, passwordField);
        passwordField.sendKeys(AppConfig.getApp_password());
        nextButton.click();
    }

    public void waitUntilDashboardPageResultsContainerIsLoaded() {
        Wait.forElementToBeDisplayed(driver, resultsPanel);
    }

    public void dashboardPageResultsIsLoaded() {
        Wait.forElementToBeClickable(driver, resultsPanel);
    }

    public boolean pageTitleValidation(String titleText) {

        String actual = pageTitle.getText();
        if (actual.equalsIgnoreCase(titleText))
        {return true;
        }
        else
        {
            return false;
        }
    }

}
