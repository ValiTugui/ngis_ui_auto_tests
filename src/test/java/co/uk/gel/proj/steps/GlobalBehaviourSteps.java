package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class GlobalBehaviourSteps extends Pages {

    public GlobalBehaviourSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Then("the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link")
    public void theUserCanSeeTheNGISVersionNumberOnTheRightSideBottomOfThePageNextToThePrivacyPolicyLink() {
        Debugger.println("Current page is: " + driver.getCurrentUrl());
        Assert.assertTrue("The NGIS version number is NOT present next to the privacy policy link", globalBehaviourPage.isNGISVersionPresent());
    }

}
