package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;

public class GlobalBehaviourSteps extends Pages {

    public GlobalBehaviourSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Then("the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link")
    public void theUserCanSeeTheNGISVersionNumberOnTheRightSideBottomOfThePageNextToThePrivacyPolicyLink() {
        Debugger.println("Current page is: " + driver.getCurrentUrl());
        Assert.assertTrue("The NGIS version number is NOT present next to the privacy policy link", globalBehaviourPage.isNGISVersionPresent());
    }

    @Then("the user can see the {string} link at bottom of the page")
    public void theUserCanSeeTheLinkAtBottomOfThePage(String expectedText) {
        Assert.assertTrue(globalBehaviourPage.isPrivacyPolicyLinkPresent(expectedText));
        Assert.assertTrue(globalBehaviourPage.footerLinks.get(1).getAttribute("href").contains("privacy-policy"));
    }

    @When("the user clicks the privacy policy link")
    public void theUserClicksThePrivacyPolicyLink() {
        boolean testResult = globalBehaviourPage.clickPrivacyPolicy();
        if(!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PrivacyPolicy.jpg");
            Assert.fail("Could mot click on privacy policy link:");
        }
    }

    @Then("the {string} page should be opened in the next tab")
    public void thePageShouldBeOpenedInTheNextTab(String pageTitle) {
        Assert.assertTrue(driver.getCurrentUrl().contains("privacy-policy"));
        Assert.assertTrue(globalBehaviourPage.checkPrivacyPolicyLinkPage(pageTitle));
        SeleniumLib.closeCurrentWindow();
    }

    @Then("the user should be able to see continue button on landing page")
    public void theUserShouldBeAbleToSeeContinueButtonOnLandingPage() {
        boolean testResult = false;
        testResult = globalBehaviourPage.verifyTheContinueButtonOnLandingPage();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ContinueButton.jpg");
            Assert.fail("No Continue Button present");
        }
    }

    @When("the user should see previous labels replaced as current labels")
    public void theUserVerifyTheTextPresentInThePageAs(DataTable labelsList) {
        boolean testResult = false;
        List<List<String>> labels = labelsList.asLists();
        for (int i = 1; i < labels.size(); i++) {
            testResult = globalBehaviourPage.verifyReplacedLabelsInTheCurrentPage(labels.get(i).get(0),labels.get(i).get(1));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NHSLabel.jpg");
                Assert.fail("No NHS Label present");
            }
        }
    }

    @And("the user checks for different screen width of {string}")
    public void theUserChecksForDifferentScreenWidthOf(String widthValue) throws InterruptedException {
        boolean testResult = false;
        testResult = globalBehaviourPage.verifyDifferentScreenWidth(Integer.parseInt(widthValue));
        Assert.assertTrue(testResult);
    }

    @And("the user checks the presence of  horizontal scrollbar {string}")
    public void theUserChecksThePresenceOfHorizontalScrollbar(String isPresent) {
        boolean testResult = false;
        testResult = globalBehaviourPage.isHorizontalScrollBarPresent();
        if(isPresent.equalsIgnoreCase("Present")){
            Assert.assertTrue(testResult);
        }else{
            Assert.assertFalse(testResult);
        }
    }

    @And("the user should be able to see NHS logo image")
    public void theUserVerifiesNHSLogoImageFormatAsSVG() {
        boolean testResult = false;
        testResult = globalBehaviourPage.verifyNHSEnglandLogoIsSVG();
        Assert.assertTrue(testResult);
    }

    @And("the user should verify the referral banner present at the top")
    public void theUserShouldVerifyTheReferralBannerPresentAtTheTop() {
        boolean testResult = false;
        testResult = globalBehaviourPage.verifyTheElementsOnReferralBanner();
        Assert.assertTrue(testResult);
    }

    @Given("the user gets the NGIS version")
    public void theUserGetsTheNGISVersion() {
        globalBehaviourPage.getNGISVersion();
    }

    @When("the user login to Test Order with (.*) credential")
    public void theUserFillsUsernameAndPasswordToLogin(String loginType) {
        boolean testResult = false;
        testResult = globalBehaviourPage.loginWithMicrosoftAccount(loginType);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TOLogin.jpg");
            Assert.fail("Could login to TO");
        }
    }
    @Then("the user should be able to see an error message {string}")
    public void theUserShouldBeAbleToSeeAnErrorMessage(String errMessage) {
        boolean testResult = false;
        testResult = globalBehaviourPage.verifyMicrosoftLoginError(errMessage);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_EMessage.jpg");
        }
        Assert.assertTrue(testResult);
    }
    @When("the user provides an invalid referral id in the url {string}")
    public void theUserProvidesAnInvalidReferralIdInTheUrl(String invalidReferralURL) {
        boolean testResult = false;
        testResult = globalBehaviourPage.navigateToURLWithInvalidReferralID(invalidReferralURL);
        Assert.assertTrue(testResult);
    }

    @Given("the user clears all the current session cookies")
    public void theUserClearsAllTheCurrentSessionCookies() {
        driver.get("chrome://settings/clearBrowserData");
        globalBehaviourPage.clearBrowserCache();
    }
}
