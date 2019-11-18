package co.uk.gel.proj.pages;

import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public class Pages implements Navigable {

    public final String patientSearchURL = "patient-search";
    public final String testOrderLoginURL = "login.microsoft";
    public final String testOrderURL = "test-order";

    protected WebDriver driver;

    //We have to initialize all the Pages Created in this class. AppHomePage provided as an example.
    protected HomePage homePage;
    protected ClinicalIndicationsTestSelectPage clinicalIndicationsTestSelect;
    protected ClinicalQuestionsPage clinicalQuestionsPage;
    protected PaperFormPage paperFormPage;
    protected ReferralPage referralPage;
    protected AppHomePage appHomePage;
    protected PatientSearchPage patientSearchPage;
    protected PatientDetailsPage patientDetailsPage;
    protected RequestingOrganisationPage requestingOrganisationPage;
    protected TestPackagePage testPackagePage;
    protected ResponsibleClinicianPage responsibleClinicianPage;
    protected TumoursPage tumoursPage;
    protected SamplesPage samplesPage;
    protected DashBoardPage dashBoardPage;
    protected FamilyMemberSearchPage familyMemberSearchPage;
    protected GlobalBehaviourPage globalBehaviourPage;
    protected FamilyMemberDetailsPage familyMemberDetailsPage;
    protected  FamilyMemberNewPatientPage familyMemberNewPatientPage;

    public Pages(SeleniumDriver driver) {
        this.driver = driver;
        PageObjects();
    }

    public void PageObjects() {

        homePage = PageFactory.initElements(driver, HomePage.class);
        clinicalIndicationsTestSelect = PageFactory.initElements(driver, ClinicalIndicationsTestSelectPage.class);
        clinicalQuestionsPage = PageFactory.initElements(driver, ClinicalQuestionsPage.class);
        paperFormPage = PageFactory.initElements(driver, PaperFormPage.class);
        referralPage = PageFactory.initElements(driver, ReferralPage.class);
        appHomePage = PageFactory.initElements(driver, AppHomePage.class);
        patientSearchPage = PageFactory.initElements(driver, PatientSearchPage.class);
        patientDetailsPage = PageFactory.initElements(driver, PatientDetailsPage.class);
        requestingOrganisationPage = PageFactory.initElements(driver, RequestingOrganisationPage.class);
        testPackagePage = PageFactory.initElements(driver, TestPackagePage.class);
        responsibleClinicianPage = PageFactory.initElements(driver, ResponsibleClinicianPage.class);
        tumoursPage = PageFactory.initElements(driver, TumoursPage.class);
        samplesPage = PageFactory.initElements(driver, SamplesPage.class);
        dashBoardPage = PageFactory.initElements(driver, DashBoardPage.class);
        familyMemberSearchPage = PageFactory.initElements(driver, FamilyMemberSearchPage.class);
        globalBehaviourPage = PageFactory.initElements(driver, GlobalBehaviourPage.class);
        familyMemberDetailsPage = PageFactory.initElements(driver, FamilyMemberDetailsPage.class);
        familyMemberNewPatientPage = PageFactory.initElements(driver,FamilyMemberNewPatientPage.class);
    }

    public static void login(WebDriver driver, WebElement emailAddressField, WebElement passwordField, WebElement nextButton) {
        Wait.forElementToBeClickable(driver, emailAddressField);
        emailAddressField.sendKeys(AppConfig.getApp_username());
        nextButton.click();
        Wait.seconds(2);
        Wait.forElementToBeClickable(driver, passwordField);
        passwordField.sendKeys(AppConfig.getApp_password());
        nextButton.click();
    }


    @Override
    public void NavigateTo(String pageToNavigate) {

    }

    public void NavigateTo(String urlToNavigate, String pageToNavigate) {
        login(urlToNavigate, pageToNavigate, null);
    }

    public void NavigateTo(String urlToNavigate, String pageToNavigate, String userType) {
        login(urlToNavigate, pageToNavigate, userType);
    }

    private void login(String urlToNavigate, String pageToNavigate, String userType) {
        try {
            Debugger.println("Navigating to URL: " + urlToNavigate + ", Page:" + pageToNavigate);
            driver.get(urlToNavigate);
            Debugger.println("Current URL After getting: "+driver.getCurrentUrl());
            //Navigate to Test Directory
            if (driver.getCurrentUrl().contains("test-selection/clinical-tests")) {
                homePage.waitUntilHomePageResultsContainerIsLoaded();
            }
            // Navigate to specific pages in Test Order
            else if (driver.getCurrentUrl().contains(pageToNavigate)) {
                Wait.forElementToBeDisplayed(driver, patientSearchPage.pageTitle);
                Assert.assertTrue(patientSearchPage.pageTitle.isDisplayed());
            } else {
                if (driver.getCurrentUrl().contains("login.microsoft")) {
                    Wait.forElementToBeDisplayed(driver, patientSearchPage.emailAddressField);
                    Assert.assertTrue(patientSearchPage.emailAddressField.isDisplayed());
                    if (userType != null)
                        patientSearchPage.loginToTestOrderingSystem(driver, userType);
                    else
                        patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
                } else {
                    if (patientSearchPage.logout.isDisplayed()) {
                        patientSearchPage.logout.click();
                        if (userType != null)
                            patientSearchPage.loginToTestOrderingSystem(driver, userType);
                        else
                            patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
                    } else
                        Debugger.println(" User is at url " + driver.getCurrentUrl());
                }
            }
            Debugger.println("Done with Navigation:");
        }catch(Exception exp){
            Debugger.println("Exception in Navigating to URL: "+urlToNavigate+"\nExp:"+exp);
        }
    }

    @Override
    public void switchToURL(String currentURL) {
        Debugger.println("CURRENT URL: " + currentURL);
        Debugger.println("Switched URL: "+driver.getCurrentUrl());
        try {
            if (driver.getCurrentUrl().contains(patientSearchURL)) {
                Actions.cleanUpSession(driver);
            } else if (driver.getCurrentUrl().contains(testOrderLoginURL) || driver.getCurrentUrl().contains(testOrderURL)) {
                //Wait.forElementToBeDisplayed(driver, patientSearchPage.emailAddressField);
                //Assert.assertTrue(patientSearchPage.emailAddressField.isDisplayed());
                patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
            }
        }catch(StaleElementReferenceException exp ){
            Debugger.println("Stale Exception: "+exp);

        }
        Debugger.println("NEW URL    : " + driver.getCurrentUrl());
    }
}//end class
