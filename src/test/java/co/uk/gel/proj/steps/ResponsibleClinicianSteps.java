package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ResponsibleClinicianSteps extends Pages {
    public ResponsibleClinicianSteps(SeleniumDriver driver) {
        super(driver);
    }

    @When("the user fills in {string} in clinician form fields")
    public void the_user_fills_in_in_clinician_form_fields(String emailValue) {
        responsibleClinicianPage.enterEmail(emailValue);
    }

    @Then("the user sees an {string} message on the page")
    public void the_user_sees_an_message_on_the_page(String errorMessage) {
        Assert.assertTrue(responsibleClinicianPage.verifyInvalidEmailWarningMessage(errorMessage));
    }

    @When("the user fills in invalid phone number value exceeding fifteen digits in clinician form fields")
    public void theUserFillsInInvalidPhoneNumberValueExceedingFifteenDigitsInClinicianFormFields() {
        Faker fakeData = new Faker();
        responsibleClinicianPage.enterPhoneNumber(fakeData.number().digits(16));
    }

    @Then("The user should be restricted to enter more than fifteen digits in the Phone number field.")
    public void theUserShouldBeRestrictedToEnterMoreThanFifteenDigitsInThePhoneNumberField() {
        responsibleClinicianPage.verifyTotalNumberOfDigitsInPhoneNumberField();
    }
}
