package co.uk.gel.proj.pages;

import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import co.uk.gel.config.SeleniumDriver;

public class Pages implements Navigable{

    protected WebDriver driver;

    //We have to initialize all the Pages Created in this class. AppHomePage provided as an example.
    protected AppHomePage appHomePage;
    protected PatientSearchPage patientSearchPage;
    protected PatientDetailsPage patientDetailsPage;

    public Pages(SeleniumDriver driver) {
        this.driver = driver;
        PageObjects();
    }

    public void PageObjects() {

        appHomePage = PageFactory.initElements(driver, AppHomePage.class);
        patientSearchPage = PageFactory.initElements(driver,PatientSearchPage.class);
        patientDetailsPage = PageFactory.initElements(driver,PatientDetailsPage.class);
    }


    @Override
    public void NavigateTo(String pageToNavigate) {

    }

    public void NavigateTo(String urlToNavigate , String pageToNavigate){
        driver.get(urlToNavigate);

        if (driver.getCurrentUrl().contains(pageToNavigate)) {
            Wait.forElementToBeDisplayed(driver, patientSearchPage.pageTitle);
            Assert.assertTrue(patientSearchPage.pageTitle.isDisplayed());

        } else {
            if (driver.getCurrentUrl().contains("login.microsoft")) {
                Wait.forElementToBeDisplayed(driver, patientSearchPage.emailAddressField);
                Assert.assertTrue(patientSearchPage.emailAddressField.isDisplayed());
                patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
            } else {
                if(patientSearchPage.logout.isDisplayed()) {
                    patientSearchPage.logout.click();
                    patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
                }else
                    Debugger.println(" User is at url "+driver.getCurrentUrl());
            }
        }

    }
}//end class
