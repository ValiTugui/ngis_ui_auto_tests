package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Pages implements Navigable {

    public final String patientSearchURL = "patient-search";
    public final String testOrderLoginURL = "login.microsoft";
    public final String testOrderURL = "test-order";
    protected String normalUser = "GEL_NORMAL_USER";
    protected String superUser = "GEL_SUPER_USER";

    protected WebDriver driver;

    //We have to initialize all the Pages Created in this class. AppHomePage provided as an example.
    protected HomePage homePage;
    protected ClinicalIndicationsTestSelectPage clinicalIndicationsTestSelect;
    protected ClinicalQuestionsPage clinicalQuestionsPage;
    protected PaperFormPage paperFormPage;
    protected ReferralPage referralPage;
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
    protected FamilyMemberNewPatientPage familyMemberNewPatientPage;
    protected PatientChoicePage patientChoicePage;
    protected PanelsPage panelsPage;
    protected NotesPage notesPage;
    protected PedigreePage pedigreePage;
    protected PrintFormsPage printFormsPage;


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
        patientChoicePage = PageFactory.initElements(driver,PatientChoicePage.class);
        panelsPage = PageFactory.initElements(driver, PanelsPage.class);
        notesPage = PageFactory.initElements(driver,NotesPage.class);
        pedigreePage = PageFactory.initElements(driver,PedigreePage.class);
        printFormsPage = PageFactory.initElements(driver,PrintFormsPage.class);
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
            Debugger.println("Pages:login:Navigating to URL: " + urlToNavigate + ", Page:" + pageToNavigate);
            driver.get(urlToNavigate);
            Wait.seconds(15);//Wait for 15 Seconds
            String navigatedURL = driver.getCurrentUrl();
            //Navigate to Test Directory
            if (navigatedURL.contains("test-selection/clinical-tests")) {
                homePage.waitUntilHomePageResultsContainerIsLoaded();
            }else if (navigatedURL.contains(pageToNavigate)) { // Navigate to specific pages in Test Order
                patientSearchPage.waitForPageTitleDisplayed();
            }else if (navigatedURL.contains("login.microsoft")) {
                if (userType != null) {
                    patientSearchPage.loginToTestOrderingSystem(driver, userType);
                } else {
                    patientSearchPage.loginToTestOrderingSystemAsStandardUser(driver);
                }
            }else {
                //Log out, if not logged out from previous session
                patientSearchPage.logoutFromApplication();
                if (userType != null) {
                    patientSearchPage.loginToTestOrderingSystem(driver, userType);
                }else{
                    patientSearchPage.loginToTestOrderingSystemAsStandardUser(driver);
                }
            }
            navigatedURL = driver.getCurrentUrl();
            Debugger.println("Current URL is :"+navigatedURL);
        }catch(UnhandledAlertException exp){
            Debugger.println("UnhandledAlertException in Navigating to URL: "+urlToNavigate);
            SeleniumLib.takeAScreenShot("UnhandledAlertExp.jpg");
        }catch(Exception exp){
            Debugger.println("Exception in Navigating to URL: "+urlToNavigate+"\nExp:"+exp);
            Assert.assertFalse("Exception in Navigating to URL: "+urlToNavigate+"Exp:"+exp,true);
        }
    }

    @Override
    public void switchToURL(String currentURL) {
        Debugger.println("Switching URL from: " + currentURL);
        Wait.seconds(5);
        try {
            if (currentURL.contains(patientSearchURL)) {
                //  Actions.cleanUpSession(driver);
            } else if (currentURL.contains(testOrderLoginURL) || driver.getCurrentUrl().contains(testOrderURL)) {
                patientSearchPage.loginToTestOrderingSystemAsStandardUser(driver);
            }
            Debugger.println("Switched URL    : " + driver.getCurrentUrl());
        } catch (Exception exp) {
            Debugger.println("Exception from Switch URL: "+exp);
            SeleniumLib.takeAScreenShot("SwitchURLException.jpg");
            Assert.assertFalse("Exception from Switch URL:"+exp,true);
        }
    }
    @Override
    public void switchToURL(String currentURL, String userType) {
        Debugger.println("Switching URL from: " + currentURL);
        Wait.seconds(5);
        try {
            if (currentURL.contains(patientSearchURL)) {
                //  Actions.cleanUpSession(driver);
            } else if (currentURL.contains(testOrderLoginURL) || driver.getCurrentUrl().contains(testOrderURL)) {
                if(userType.equalsIgnoreCase(normalUser)) {
                    patientSearchPage.loginToTestOrderingSystemAsStandardUser(driver);
                }else if(userType.equalsIgnoreCase(superUser)){
                    patientSearchPage.loginToTestOrderingSystem(driver, userType);
                }
            }
            Debugger.println("Switched URL    : " + driver.getCurrentUrl());
        } catch (Exception exp) {
            Debugger.println("Exception from Switch URL: "+exp);
            SeleniumLib.takeAScreenShot("SwitchURLException.jpg");
            Assert.assertFalse("Exception from Switch URL:"+exp,true);
        }
    }
}//end class
