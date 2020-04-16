package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
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
        globalBehaviourPage.clickPrivacyPolicy();
    }

    @Then("the {string} page should be opened in the next tab")
    public void thePageShouldBeOpenedInTheNextTab(String pageTitle) {
        Assert.assertTrue(driver.getCurrentUrl().contains("privacy-policy"));
        Assert.assertTrue(globalBehaviourPage.checkPrivacyPolicyLinkPage(pageTitle));
        Actions.switchTab(driver);
    }

    @Then("the user should be able to see continue button on landing page")
    public void theUserShouldBeAbleToSeeContinueButtonOnLandingPage() {
        boolean testResult = false;
        testResult = globalBehaviourPage.verifyTheContinueButtonOnLandingPage();
        Assert.assertTrue(testResult);
    }
    //    @And("the user should verify the referral banner present at the top")
//    public void theUserShouldVerifyTheReferralBannerPresentAtTheTop() {
//        boolean testResult = false;
//        testResult = globalBehaviourPage.verifyTheElementsOnReferralBanner();
//        Assert.assertTrue(testResult);
//    }
    @When("the user should see previous labels replaced as current labels")
    public void theUserVerifyTheTextPresentInThePageAs(DataTable labelsList) {
        boolean testResult = false;
        List<List<String>> labels = labelsList.asLists();
        for (int i = 1; i < labels.size(); i++) {
            testResult = globalBehaviourPage.verifyReplacedLabelsInTheCurrentPage(labels.get(i).get(0),labels.get(i).get(1));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
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
}
