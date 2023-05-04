package co.uk.gel.proj.pages;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.miportal_pages.*;
import co.uk.gel.proj.neatPages.NeatHomePage;
import co.uk.gel.proj.neatPages.NeatPatientRecordPage;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.ExcelDataRead;
import co.uk.gel.proj.util.TestUtils;
import org.junit.Assert;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Pages implements Navigable {

    public final String patientSearchURL = "patient-search";
    public final String testOrderLoginURL = "login.microsoft";
    public final String testOrderURL = "test-order";
    protected String normalUser = "GEL_NORMAL_USER";
    protected String superUser = "GEL_SUPER_USER";
    protected String concurrentUser1 = "CONCURRENT_USER1";
    protected String concurrentUser2 = "CONCURRENT_USER2";
    protected String concurrentUser3 = "CONCURRENT_USER3";
    protected String concurrentUser4 = "CONCURRENT_USER4";
    protected String concurrentUser5 = "CONCURRENT_USER5";

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
    protected TestOrderFormsPage testOrderFormsPage;
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

    protected MiPortalFileSubmissionPage miPortalFileSubmissionPage;
    protected MiPortalHomePage miPortalHomePage;
    protected MiOrderTrackingPage miOrderTrackingPage;
    protected MiGlhSamplesPage miGlhSamplesPage;
    protected MiPlaterSamplesPage miPlaterSamplesPage;
    protected MiPickListsPage miPickListsPage;
    protected MiSequencerSamplesPage miSequencerSamplesPage;
    protected MiNewReferralsPage miNewReferralsPage;
    protected ExcelDataRead excelDataRead;

    protected InterpretationPortalHomePage interpretationPortalHomePage;

    protected NeatHomePage neatHomePage;
    protected NeatPatientRecordPage neatPatientRecordPage;
    protected KibanaPage kibanaPage;
    protected DataDogPage dataDogPage;

    protected NgisStatusPage statusPage;

    public Pages(SeleniumDriver driver) {
        this.driver = driver;
        PageObjects();
    }

    public void PageObjects() {

        homePage                        = PageFactory.initElements(driver, HomePage.class);
        clinicalIndicationsTestSelect   = PageFactory.initElements(driver, ClinicalIndicationsTestSelectPage.class);
        clinicalQuestionsPage           = PageFactory.initElements(driver, ClinicalQuestionsPage.class);
        paperFormPage                   = PageFactory.initElements(driver, PaperFormPage.class);
        referralPage                    = PageFactory.initElements(driver, ReferralPage.class);
        patientSearchPage               = PageFactory.initElements(driver, PatientSearchPage.class);
        patientDetailsPage              = PageFactory.initElements(driver, PatientDetailsPage.class);
        requestingOrganisationPage      = PageFactory.initElements(driver, RequestingOrganisationPage.class);
        testPackagePage                 = PageFactory.initElements(driver, TestPackagePage.class);
        testOrderFormsPage              = PageFactory.initElements(driver, TestOrderFormsPage.class);
        responsibleClinicianPage        = PageFactory.initElements(driver, ResponsibleClinicianPage.class);
        tumoursPage                     = PageFactory.initElements(driver, TumoursPage.class);
        samplesPage                     = PageFactory.initElements(driver, SamplesPage.class);
        dashBoardPage                   = PageFactory.initElements(driver, DashBoardPage.class);
        familyMemberSearchPage          = PageFactory.initElements(driver, FamilyMemberSearchPage.class);
        globalBehaviourPage             = PageFactory.initElements(driver, GlobalBehaviourPage.class);
        familyMemberDetailsPage         = PageFactory.initElements(driver, FamilyMemberDetailsPage.class);
        familyMemberNewPatientPage      = PageFactory.initElements(driver,FamilyMemberNewPatientPage.class);
        patientChoicePage               = PageFactory.initElements(driver,PatientChoicePage.class);
        panelsPage                      = PageFactory.initElements(driver, PanelsPage.class);
        notesPage                       = PageFactory.initElements(driver,NotesPage.class);
        pedigreePage                    = PageFactory.initElements(driver,PedigreePage.class);
        printFormsPage                  = PageFactory.initElements(driver,PrintFormsPage.class);

        // MI-PORTAL PAGES
        miPortalHomePage                = PageFactory.initElements(driver, MiPortalHomePage.class);
        miPortalFileSubmissionPage      = PageFactory.initElements(driver, MiPortalFileSubmissionPage.class);
        miOrderTrackingPage             = PageFactory.initElements(driver, MiOrderTrackingPage.class);
        miGlhSamplesPage                = PageFactory.initElements(driver, MiGlhSamplesPage.class);
        miPlaterSamplesPage             = PageFactory.initElements(driver, MiPlaterSamplesPage.class);
        miPickListsPage                 = PageFactory.initElements(driver, MiPickListsPage.class);
        miSequencerSamplesPage          = PageFactory.initElements(driver, MiSequencerSamplesPage.class);
        miNewReferralsPage              = PageFactory.initElements(driver, MiNewReferralsPage.class);
        excelDataRead                   = PageFactory.initElements(driver, ExcelDataRead.class);
        interpretationPortalHomePage    = PageFactory.initElements(driver, InterpretationPortalHomePage.class);

        //NEAT Admin Tool Pages
        neatHomePage                    = PageFactory.initElements(driver,NeatHomePage.class);
        neatPatientRecordPage           = PageFactory.initElements(driver,NeatPatientRecordPage.class);
        kibanaPage                      = PageFactory.initElements(driver,KibanaPage.class);
        dataDogPage                     = PageFactory.initElements(driver, DataDogPage.class);

        ///Status page for app versions
        statusPage                      = PageFactory.initElements(driver, NgisStatusPage.class);
    }

    @Override
    public void NavigateTo(String pageToNavigate) {

    }

    public void NavigateTo(String urlToNavigate, String pageToNavigate) {
        login(urlToNavigate, pageToNavigate, null);
    }

    public void NavigateTo(String urlToNavigate, String pageToNavigate, String userType) {
        if (urlToNavigate.contains("miportal")) {
            loginToMiPortal(urlToNavigate, pageToNavigate, userType);
        } else {
            login(urlToNavigate, pageToNavigate, userType);
        }
    }

    private void login(String urlToNavigate, String pageToNavigate, String userType) {
        try {
            //Debugger.println("Pages:login:Navigating to URL: " + urlToNavigate + ",\n Page:" + pageToNavigate);
            driver.get(urlToNavigate);
            Wait.seconds(5);//Wait for 5 Seconds
            String navigatedURL = driver.getCurrentUrl();
            Debugger.println("Current URL before LOGIN is :"+navigatedURL);
            //Navigate to Test Directory
            if (navigatedURL.contains("test-selection/clinical-tests")) {
                if(!homePage.waitUntilHomePageResultsContainerIsLoaded()){
                    Assert.fail("Home Page not loaded successfully.");
                }
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
            // we have noticed after login to TOMS, sometimes dashboard page is shown in the TOMS - following code will handle this and redirects to test directory
            if(driver.getCurrentUrl().contains("dashboard")){
                dashBoardPage.clickFindAGenomicTest();
                Wait.seconds(2);
                navigatedURL = driver.getCurrentUrl();
                Debugger.println("Current URL AFTER dashboard page re-direction:"+navigatedURL);
            }
//            Wait.seconds(5);
//            if (driver.getCurrentUrl().contains("login.microsoft")){
//                Debugger.println("Error in Login to TOMS: "+urlToNavigate+"\nCurrentUrl:"+driver.getCurrentUrl());
//                Assert.fail("Error in Login to TOMS: "+urlToNavigate+"\nCurrentUrl:"+driver.getCurrentUrl());
//            }
        }catch(UnhandledAlertException exp){
            Debugger.println("UnhandledAlertException in Navigating to URL: "+urlToNavigate);
        }catch(Exception exp){
            Debugger.println("Exception in Navigating to URL: "+urlToNavigate+"\nExp:"+exp);
            Assert.fail("Exception in Navigating to URL: "+urlToNavigate+"Exp:"+exp);
        }
    }


    private void loginToMiPortal(String urlToNavigate, String pageToNavigate, String userType) {
        try {
            //Debugger.println("Pages:login:Navigating to URL: " + urlToNavigate + ", Page:" + pageToNavigate);
            driver.get(urlToNavigate);
            Wait.seconds(5);//Wait for 15 Seconds
            String navigatedURL = driver.getCurrentUrl();
            //Debugger.println("Current URL before LOGIN is :" + navigatedURL);
            //Navigate to Test Directory
            if (navigatedURL.contains("https://miportal.")) {
                //homePage.waitUntilHomePageResultsContainerIsLoaded();
                Debugger.println("User is in miportal homepage - no login"); //Wait for all page to be loaded
            } else if (navigatedURL.contains("login.microsoft")) {
                if (userType != null) {
                    patientSearchPage.loginToTestOrderingSystem(driver, userType);
                } else {
                    patientSearchPage.loginToTestOrderingSystemAsStandardUser(driver);
                }
            } else {
                //Log out, if not logged out from previous session
                patientSearchPage.logoutFromApplication();
                if (userType != null) {
                    patientSearchPage.loginToTestOrderingSystem(driver, userType);
                } else {
                    patientSearchPage.loginToTestOrderingSystemAsStandardUser(driver);
                }
            }
            navigatedURL = driver.getCurrentUrl();
            //Debugger.println("Current URL after LOGIN to TOMS :" + navigatedURL);
            // we have noticed after login to TOMS, sometimes dashboard page is shown in the TOMS - following code will handle this and redirects to test directory
            if (navigatedURL.contains("dashboard")) {
                dashBoardPage.clickFindAGenomicTest();
                Wait.seconds(2);
                navigatedURL = driver.getCurrentUrl();
                //Debugger.println("Current URL AFTER dashboard page re-direction:" + navigatedURL);
            }

        } catch (UnhandledAlertException exp) {
            Debugger.println("UnhandledAlertException in Navigating to URL: " + urlToNavigate);
        } catch (Exception exp) {
            Debugger.println("Exception in Navigating to URL: " + urlToNavigate + "\nExp:" + exp);
            Assert.fail("Exception in Navigating to URL: " + urlToNavigate + "Exp:" + exp);
        }
    }
    @Override
    public void switchToURL(String currentURL) {
        //Debugger.println("Switching URL from: " + currentURL);
        Wait.seconds(5);
        try {
            if (currentURL.contains(patientSearchURL)) {
                //  Actions.cleanUpSession(driver);
            } else if (currentURL.contains(testOrderLoginURL) || driver.getCurrentUrl().contains(testOrderURL)) {
                patientSearchPage.loginToTestOrderingSystemAsStandardUser(driver);
            }
            //Debugger.println("Switched URL    : " + driver.getCurrentUrl());
        } catch (Exception exp) {
            Debugger.println("Exception from Switch URL: "+exp);
            SeleniumLib.takeAScreenShot("SwitchURLException.jpg");
            Assert.fail("Exception from Switch URL:"+exp);
        }
    }
    @Override
    public void switchToURL(String currentURL, String userType) {
        //Debugger.println("Switching URL..USER: "+userType+",URL:"+currentURL);
        Wait.seconds(5);
        try {
            if (currentURL.contains(patientSearchURL)) {
                Debugger.println("URL Contains: patientSearchURL:"+patientSearchURL);
                //  Actions.cleanUpSession(driver);
            } else if (currentURL.contains(testOrderLoginURL) || driver.getCurrentUrl().contains(testOrderURL)) {
                //Debugger.println("URL Contains: testOrderLoginURL:"+testOrderLoginURL+"\n"+userType);
                if(userType.equalsIgnoreCase(normalUser)) {
                   String email = AppConfig.getApp_username();
                   if(email.contains("nhs.net")){
                       // If the email id contains nhs, proceed with NHS login
                       referralPage.loginToTestOrderingSystemAsNHSUser(driver,userType);
                   }else {
                       //Debugger.println("Login to TOMS as NORMAL_USER");
                       patientSearchPage.loginToTestOrderingSystemAsStandardUser(driver);
                   }
                }else if(userType.equalsIgnoreCase(superUser)){
                    String super_email = AppConfig.getPropertyValueFromPropertyFile("SUPER_USERNAME");
                    if(super_email.contains("nhs.net")){
                        referralPage.loginToTestOrderingSystemAsNHSUser(driver,userType);
                    }else {
                        //Debugger.println("Login to TOMS as SUPER_USER");
                        patientSearchPage.loginToTestOrderingSystem(driver, userType);
                    }
                }else if(userType.startsWith(concurrentUser1) ||
                        userType.startsWith(concurrentUser2) || userType.startsWith(concurrentUser3)) {
                    String userEmail = "";
                    if (userType.startsWith(concurrentUser1)) {
                        userEmail = AppConfig.getConcurrent_user1_username();
                    } else if (userType.startsWith(concurrentUser2)) {
                        userEmail = AppConfig.getConcurrent_user2_username();
                    } else {
                        userEmail = AppConfig.getConcurrent_user3_username();
                    }
                    if (userEmail.contains("nhs.net")) {
                        Debugger.println("Concurrent User... Logging to TestOrder as NHS user");
                        referralPage.loginToTestOrderingSystemAsNHSUser(driver, userType);
                    } else {
                        Debugger.println("Concurrent User... Logging to TestOrder as Standard user");
                        patientSearchPage.loginToTestOrderingSystemAsStandardUser(driver);
                    }
                }
            }else{
                if(userType.startsWith(concurrentUser1) ||
                        userType.startsWith(concurrentUser2) ||
                        userType.startsWith(concurrentUser3) ||
                        userType.startsWith(concurrentUser4) ||
                        userType.startsWith(concurrentUser5)){
                    driver.get(currentURL);
                    String userEmail = "";
                    String userPwd = "";
                    if(userType.startsWith(concurrentUser1)){
                        userEmail = AppConfig.getConcurrent_user1_username();
                        userPwd = AppConfig.getConcurrent_user1_password();
                    }else if(userType.startsWith(concurrentUser2)){
                        userEmail = AppConfig.getConcurrent_user2_username();
                        userPwd = AppConfig.getConcurrent_user2_password();
                    }else if(userType.startsWith(concurrentUser3)){
                        userEmail = AppConfig.getConcurrent_user3_username();
                        userPwd = AppConfig.getConcurrent_user3_password();
                    }else if(userType.startsWith(concurrentUser4)){
                        userEmail = AppConfig.getConcurrent_user4_username();
                        userPwd = AppConfig.getConcurrent_user4_password();
                    }else if(userType.startsWith(concurrentUser5)){
                        userEmail = AppConfig.getConcurrent_user5_username();
                        userPwd = AppConfig.getConcurrent_user5_password();
                    }else{
                        userEmail = AppConfig.getApp_username();
                        userPwd = AppConfig.getApp_password();
                    }
                    TestUtils.currentUser = userEmail;
                    if(userEmail.contains("microsoft")){
                        patientSearchPage.loginToTestOrderingSystem(userEmail,userPwd);
                    }else {
                        referralPage.loginToTestOrderingSystemAsNHSUser(driver, userType);
                    }
                }
            }
            //Added below Section as it is observed that after login sometime page not loading and url redirecting to
            // https://test-ordering.int.ngis.io/test-order/Home/Error
            String switchedURL = driver.getCurrentUrl();
            if(switchedURL.contains("Error")){
                Debugger.println("URL contains Error after login.");
                SeleniumLib.takeAScreenShot("URLError.jpg");
                Assert.fail("URL contains Error after login. Pls check URLError.jpg");
            }
            //Debugger.println("Switched URL    : " + switchedURL);
        } catch (Exception exp) {
            Debugger.println("Exception from Switch URL: "+exp);
            SeleniumLib.takeAScreenShot("SwitchURLException.jpg");
            Assert.fail("Exception from Switch URL:"+exp);
        }
     }

}//end class
