package co.uk.gel.proj.pages;

import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PatientChoicePage {
    WebDriver driver;

    public PatientChoicePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "*[class*='participant-list__']")
    public WebElement landingPageList;

    @FindBy(css = "*[aria-labelledby*='patientChoiceStatus']")
    public List<WebElement> statuses;

    @FindBy(xpath = "//button[contains(@aria-label,'edit')]")
    public List<WebElement> memberEditButton;

    @FindBy(id = "Patient-0")
    public WebElement adultWithCapacityCategory;

    @FindBy(xpath = "//*[contains(@class,'recordedByContainer')]//child::input")
    public WebElement recordedByField;

    @FindBy(css = ".btn.cli-nxt-btn")
    public WebElement recordedByContinueButton;

    @FindBy(css = ".finish-button.btn")
    public WebElement patientChoicesContinueButton;

    @FindBy(id = "Choices_Q1.0-0")
    public WebElement agreeTestChoice;

    @FindBy(xpath = "//label[@id='Choices_Q1.0-1']")
    public WebElement discussionFormNotAvailable;

    @FindBy(id = "Choices_Q1.0-2")
    public WebElement declineTestChoice;

    @FindBy(xpath = "//label[@id='Choices_Q2.0-1']")
    public WebElement patientChoiceNotRequiredForTheTest;



    @FindBy(id = "Choices_Q2.3-0")
    public WebElement agreeResearchParticipation;

    @FindBy(id = "Choices_Q2.3.1-0")
    public WebElement agreeSampleUsage;

    @FindBy(xpath = "//*[contains(@id,'signature-pad')]//child::canvas")
    public WebElement signatureSection;

    @FindBy(css = "button[class*='submit-signature-button']")
    public WebElement submitSignatureButton;

    @FindBy(css = "button[class*='submit-button']")
    public WebElement submitButton;

    @FindBy(css = "*[class*='confirmation-header']")
    public WebElement patientChoiceConfirmation;

    @FindBy(css = "*[class*='message-line']")
    public WebElement recordAlreadyExistsMessage;


    public void selectMember(int i) {
        Wait.forElementToBeDisplayed(driver, landingPageList);
        Click.element(driver, memberEditButton.get(i));
    }

    public void selectPatientChoiceCategory() {
        Click.element(driver, adultWithCapacityCategory);
    }

    public void selectTestType() {
        Click.element(driver, adultWithCapacityCategory);
    }

    public void enterRecordedByDetails() {
        Wait.forElementToBeDisplayed(driver, recordedByField);
        co.uk.gel.lib.Actions.fillInValue(recordedByField, "Sue");
        Click.element(driver, recordedByContinueButton);
    }

    public void selectChoicesWithPatientChoiceNotRequired() {
        Click.element(driver, discussionFormNotAvailable);
        Click.element(driver, patientChoiceNotRequiredForTheTest);
        Click.element(driver, patientChoicesContinueButton);
    }

    public void selectChoicesWithAgreeingTesting() {
        Click.element(driver, agreeTestChoice);
        Click.element(driver, agreeResearchParticipation);
        Click.element(driver, agreeSampleUsage);
        Click.element(driver, patientChoicesContinueButton);
    }

    public void drawSignature() {
        Wait.forElementToBeDisplayed(driver, signatureSection);
        Click.element(driver, signatureSection);
        Actions builder = new Actions(driver);
        Action drawAction = builder.moveToElement(signatureSection,135,15) //start points x axis and y axis.
                .clickAndHold()
                .moveByOffset(80, 80)
                .moveByOffset(50, 20)
                .release()
                .build();
        drawAction.perform();
        Wait.seconds(1);
    }

    public void submitPatientChoiceWithSignature() {
        Wait.forElementToDisappear(driver, By.cssSelector("button[class*='disabled-submit-signature-button']"));
        Click.element(driver, submitSignatureButton);
        Wait.forElementToBeDisplayed(driver, patientChoiceConfirmation, 100);
    }

    public void submitPatientChoiceWithoutSignature() {
        Click.element(driver, submitButton);
        Wait.forElementToBeDisplayed(driver, patientChoiceConfirmation, 100);
    }

    public boolean statusUpdatedCorrectly(String status, int row) {
        Wait.forElementToBeDisplayed(driver, landingPageList, 100);
        return status.equalsIgnoreCase(statuses.get(row).getText());
    }

    public void messageThatPatientChoiceRecordExistsIsDisplayed() {
        Wait.forElementToBeDisplayed(driver, recordAlreadyExistsMessage);
        Assert.assertTrue(recordAlreadyExistsMessage.getText().contains("A patient choice record currently exists for this test referral ID"));
    }

    public void addPatientChoiceIsDisplayed() {
        Wait.forElementToBeDisplayed(driver, adultWithCapacityCategory);
    }


}
