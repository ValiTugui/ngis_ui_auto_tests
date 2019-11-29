package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class FamilyMemberNewPatientPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//label[contains(text(),'Title')]")
    public WebElement pageTitleLabel;

    @FindBy(id = "title")
    public WebElement pageTitle;

    @FindBy(xpath = "//label[contains(text(),'First name')]")
    public WebElement firstNameLabel;

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(xpath = "//label[contains(text(),'Last name')]")
    public WebElement lastNameLabel;

    @FindBy(id = "familyName")
    public WebElement lastName;

    @FindBy(xpath = "//label[contains(text(),'Date of birth')]")
    public WebElement dobLabel;

    @FindBy(id = "dateOfBirth")
    public WebElement dobOfFamilyMember;

    @FindBy(xpath = "//label[text()='Gender']")
    public WebElement genderLabel;

    @FindBy(xpath = "//label[text()='Gender']//following::div")
    public WebElement gender;

    @FindBy(xpath = "//label[contains(text(),'Life status')]")
    public WebElement lifeStatusLabel;

    @FindBy(xpath = "//label[contains(text(),'Life status')]//following::div[1]")
    public WebElement lifeStatus;

    @FindBy(xpath = "//label[contains(text(),'Ethnicity')]")
    public WebElement ethnicityLabel;

    @FindBy(xpath = "//label[contains(text(),'Ethnicity')]//following::div[1]")
    public WebElement ethnicity;

    @FindBy(xpath = "//label[contains(text(),'Relationship to proband')]")
    public WebElement relationshipToProbandLabel;

    @FindBy(xpath = "//label[text()='Relationship to proband']//following::div[1]")
    public WebElement relationshipToProband;

    @FindBy(xpath = "//label[contains(text(),'Hospital number')]")
    public WebElement hospitalNumberLabel;

    @FindBy(id = "hospitalNumber")
    public WebElement hospitalNumber;

    @FindBy(xpath = "//label[contains(text(),'Reason NHS Number is missing')]")
    public WebElement noNHSNumberLabel;

    @FindBy(name = "noNhsNumberReason")
    public WebElement noNHSNumber;

    @FindBy(xpath = "//button[contains(text(),'Add new patient to referral')]")
    public WebElement addReferralButton;

    @FindBy(css = "div[class*='error-message__text']")
    public List<WebElement> validationErrors;

    public FamilyMemberNewPatientPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }
    public boolean verifyTheElementsOnFamilyMemberNewPatientPage() {
        //First name, Last name, Date of birth, Gender, Life status,
        //Reason NHS Number is missing, Hospital number and Relationship to proband
        Wait.forElementToBeDisplayed(driver, pageTitleLabel);
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(pageTitleLabel);
        expElements.add(pageTitle);
        expElements.add(firstNameLabel);
        expElements.add(firstName);
        expElements.add(lastName);
        expElements.add(lastNameLabel);
        expElements.add(dobLabel);
        expElements.add(dobOfFamilyMember);
        expElements.add(genderLabel);
        expElements.add(gender);
        expElements.add(lifeStatusLabel);
        expElements.add(lifeStatus);
        expElements.add(ethnicityLabel);
        expElements.add(ethnicity);
        expElements.add(relationshipToProbandLabel);
        expElements.add(relationshipToProband);
        expElements.add(hospitalNumberLabel);
        expElements.add(hospitalNumber);
        expElements.add(noNHSNumberLabel);
        expElements.add(noNHSNumber);
        for(int i=0; i<expElements.size(); i++){
            if(!seleniumLib.isElementPresent(expElements.get(i))){
                return false;
            }
        }
        return true;
    }

    public void clearFieldsInFamilyMemberNewPatientPage(String clearToDropdown){
        Actions.clearField(firstName);
        Actions.clearField(lastName);
        Actions.clearField(dobOfFamilyMember);
        String[] expInputs = null;
        expInputs = clearToDropdown.split(",");
        String pathToElement = "";
        By xpathElement = null;
        for(int i=0; i<expInputs.length;i++) {
            pathToElement = "//label[text()='"+expInputs[i]+"']//following::div[@class='css-16pqwjk-indicatorContainer'][1]";
            xpathElement = By.xpath(pathToElement);
            if(!seleniumLib.isElementPresent(xpathElement)){
                Debugger.println("Path :"+pathToElement+" Could not locate");
                break;
            }
            try {
                seleniumLib.clickOnElement(xpathElement);
            }catch(Exception exp){
                //seleniumLib.moveMouseAndClickOnElement(xpathElement);
            }
        }
    }
    public void clickOnAddNewPatientToReferral(){
        Wait.forElementToBeDisplayed(driver, addReferralButton);
        Click.element(driver, addReferralButton);
    }
    public boolean checkTheErrorMessageForMandatoryFields(String errorMessage, String fontColor) {
        try {
            String[] expMessages = null;
            if(errorMessage.indexOf(",") == -1){
                expMessages = new String[]{errorMessage};
            }else{
                expMessages = errorMessage.split(",");
            }
            String actualMessage = "";
            String actColor = "";
            String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(fontColor);
            for(int i=0; i<expMessages.length;i++) {
                actualMessage = Actions.getText(validationErrors.get(i));
                if (!expMessages[i].equalsIgnoreCase(actualMessage)) {
                    Debugger.println("Expected Message: " + errorMessage + ", but Actual Message: " + actualMessage);
                    return false;
                }
                actColor = validationErrors.get(i).getCssValue("color");
                if (!expectedFontColor.equalsIgnoreCase(actColor)) {
                    Debugger.println("Expected Color: " + expectedFontColor + ", but Actual Color: " + actColor);
                    return false;
                }
            }
            validationErrors.clear();
            return true;

        }catch(Exception exp){
            Debugger.println("FamilyMemberSearchPage:Exception from validating Error Message "+exp);
            return false;
        }

    }

}//end
