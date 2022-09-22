package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.lib.Action;
import co.uk.gel.proj.util.Debugger;;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InterpretationPortalHomePage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public InterpretationPortalHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(name = "loginfmt")
    public WebElement emailAddressField;

    @FindBy(id = "idSIButton9")
    public WebElement nextButton;

    @FindBy(name = "Password")
    public WebElement passwordField;

    @FindBy(id = "submitButton")
    public WebElement signInButton;

    @FindBy(id = "otherTileText")
    public WebElement useAnotherAccount;

    public void loginToInterpretationPortalWithADCredentials(String userName, String password) {
        try {
            Wait.seconds(5);
            if (!Wait.isElementDisplayed(driver,emailAddressField,20)) {//If the element is not displayed, even after the waiting time
                Debugger.println("Email Address Field is not visible, even after the waiting period of 20 seconds");
                if (Wait.isElementDisplayed(driver,useAnotherAccount,20)) {//Click on UseAnotherAccount and Proceed.
                    Debugger.println("Clicking on useAnotherAccount to Proceed.");
                    useAnotherAccount.click();
                    Wait.seconds(3);
                } else {
                    Debugger.println("Email field or UseAnotherAccount option are not available. URL:"+driver.getCurrentUrl());
                    SeleniumLib.takeAScreenShot("EmailOrUserAccountNot.jpg");
                    Assert.fail("Email field or UseAnotherAccount option are not available.");
                }
            }else{
                Debugger.println("emailAddressField Displayed.... Proceeding with Login...via microsoft.");
            }
            try {
                //emailAddressField.sendKeys(AppConfig.getApp_username());
                emailAddressField.sendKeys(userName);
            }catch(Exception exp1){
                //seleniumLib.sendValue(emailAddressField,AppConfig.getApp_username());
                seleniumLib.sendValue(emailAddressField,userName);
            }
            Wait.seconds(4);
            try {
                Wait.forElementToBeDisplayed(driver, nextButton);
                seleniumLib.clickOnWebElement(nextButton);
            }catch(Exception exp1){
                Action.clickElement(driver, nextButton);
            }
            Wait.seconds(4);
            try {
                //passwordField.sendKeys(AppConfig.getApp_password());
                Wait.forElementToBeDisplayed(driver, passwordField);
                passwordField.sendKeys(password);
            }catch(Exception exp1){
                //seleniumLib.sendValue(passwordField,AppConfig.getApp_password());
                seleniumLib.sendValue(passwordField,password);
            }
            Wait.seconds(4);
            try {
                Wait.forElementToBeDisplayed(driver, signInButton);
                seleniumLib.clickOnWebElement(signInButton);
            }catch(Exception exp1){
                Action.clickElement(driver,signInButton);
            }
            Wait.seconds(5);
        }catch(Exception exp){
            Debugger.println("Interpretation Portal : login : Exception:\n"+exp);
            SeleniumLib.takeAScreenShot("IPLogin.jpg");
            Assert.fail("Exception from loginToInterpretationPortalSystemAsStandardUser"+exp);
        }
    }
}
