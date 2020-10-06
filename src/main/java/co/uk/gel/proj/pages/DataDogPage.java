package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import javafx.scene.shape.MoveTo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class DataDogPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    public DataDogPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='username']")
    public WebElement emailAddressField;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    public WebElement loginButton;

    @FindBy(xpath = "//span[contains(text(),'New Stuff!')]")
    public WebElement newStuffHeader;

    @FindBy(xpath = "//li[contains(text(),'Integration Monitor')]")
    public WebElement integrationMonitorText;

    @FindBy(xpath = "/html/body/div[1]/nav/div[2]/ul[1]/li[11]/div[1]/a/div/span")
    public WebElement logsSection1;

    @FindBy(xpath = "/html/body/div[1]/nav/div[2]/ul[1]/li[11]/ul/li[2]/div/a/div/span")
    public WebElement searchOption;

    @FindBy(css = "#ui_icons_logs--sprite")
    public WebElement logsSection;

    @FindBy(xpath = "//*[@id=\"react-app\"]/div[1]/div[2]/div[2]/header/div[2]/div/div/div/div/div/div/div/div/svg[3]/g[2]/g/rect[1]")
    public List<WebElement> searchBoxOptionsList;

    @FindBy(xpath = "//*[@id='react-app']/div[1]/div[2]/div[2]/header/div[1]/div/div[2]/div/div/div/div/span[2]/span/span")
    public WebElement searchBox1;

    @FindBy(xpath = "//*[@id='react-app']/div[1]/div[2]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div/div/table/thead/tr/th[3]/span/div/div[2]/span")
    public WebElement hostHeader;

    String selectedCheckBox = "//div[contains(@title,'dummyName')]//label[contains(@class,'checkbox')]";

    @FindBy(xpath = "(//div[contains(text(),'Service')])[2]")
    public WebElement serviceSection;

    @FindBy(xpath = "//span[contains(text(),'e2e-latest-sftp-push')]")
    public WebElement e2eLatestPushOption;

    @FindBy(xpath = "//span[contains(text(),'Only')]")
    public WebElement onlyOptionButton;

    @FindBy(xpath = "//div[@class='ui_facets_facet__facet-title']//div[@class='ui_layout_overflower__original']")
    public List<WebElement> coreSections;


    public void loginToDataDogApplication(WebDriver driver, String userType) {
        try {
            Wait.seconds(5);
            if (!Wait.isElementDisplayed(driver,emailAddressField,60)) { //If the element is not displayed, even after the waiting time
                Debugger.println("Email Address Field is not visible, even after the waiting period of 60 seconds");
            }else{
                Debugger.println("emailAddressField Displayed.... Proceeding with Login...via microsoft.");
            }
            if (userType.equalsIgnoreCase("DATADOG_USER")) {
                try {
                    seleniumLib.sendValue(emailAddressField,AppConfig.getDataDog_userName());
                }catch(Exception exp1){
                    emailAddressField.sendKeys(AppConfig.getDataDog_userName());
                }
            }
            Wait.seconds(2);
            if (userType.equalsIgnoreCase("DATADOG_USER")) {
                try {
                    seleniumLib.sendValue(passwordField,AppConfig.getDataDog_password());
                }catch(Exception exp1){
                    passwordField.sendKeys(AppConfig.getDataDog_password());
                }
            }
            Wait.seconds(4);
            try {
                Click.element(driver, loginButton);
                Wait.seconds(5);
            }catch(Exception exp1){
                seleniumLib.clickOnWebElement(loginButton);
                Wait.seconds(5);
            }
        } catch (Exception exp){
            Debugger.println("loginToDataDogApplication:Exception:\n"+exp);
            SeleniumLib.takeAScreenShot("DatadogLogin.jpg");
            Assert.fail("Exception from loginToDataDogApplication"+exp);
        }
    }

    public boolean openAndVerifyDataDogPage() {
        try {
            if (!Wait.isElementDisplayed(driver, integrationMonitorText, 5)) {
                Debugger.println("The datadog Tool page is not loaded properly.");
                SeleniumLib.takeAScreenShot("DataDogHomePageError.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from openAndVerifyDataDogPage:" + exp);
            SeleniumLib.takeAScreenShot("DataDogLoginException.jpg");
            return false;
        }
    }

    public boolean moveToMenuSection(String menu) {
        try{
            By menuItem = By.xpath("//ul[contains(@class,'single-page-app_navbar_navbar-menu')]/li//a//span[text()='"+menu+"']");
            if(seleniumLib.isElementPresent(menuItem)) {
                seleniumLib.moveMouseAndClickOnElement(menuItem);
            }
            seleniumLib.sleepInSeconds(3);
//            seleniumLib.moveAndClickOn(logsSection1);
//            try {
//                seleniumLib.clickOnWebElement(searchOption);
//            } catch (Exception exp1) {
//                searchOption.click();
//            }
//            Wait.seconds(5); // to load the logs page
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from moveToLogsSectionAndClickOnSearchOption: " +exp);
            SeleniumLib.takeAScreenShot("MoveLogsException.jpg");
            return false;
        }
    }
    public boolean moveToCoreSection(String menu) {
        try{
            for (WebElement coreSection : coreSections) {
                Debugger.println("Core:"+coreSection.getText());
                if(coreSection.getText().equalsIgnoreCase(menu)){
                    coreSection.click();
                    seleniumLib.sleepInSeconds(2);
                    break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from moveToLogsSectionAndClickOnSearchOption: " +exp);
            SeleniumLib.takeAScreenShot("MoveLogsException.jpg");
            return false;
        }
    }

    public boolean enterUnameAndPwd(String uname, String pwd) {
        try {
            emailAddressField.sendKeys(uname);
            passwordField.sendKeys(pwd);
            loginButton.click();
            Wait.seconds(10);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from EnterUnameandPwd:" + exp);
            SeleniumLib.takeAScreenShot("EnterUnameandPwd.jpg");
            return false;
        }
    }

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    public boolean enterTheSearchValuesInTheSearchBox(String values) {
        try {
            if(!Wait.isElementDisplayed(driver, searchBox1, 15)){
                Debugger.println("No search box is displayed.");
                SeleniumLib.takeAScreenShot("noSearchBox.jpg");
                return false;
            }
            Wait.seconds(3);
            searchBox1.sendKeys(values);
            try {
                Actions.selectValueFromDropdown(dropdownValue, values);
//                Click.element(driver, searchBoxOptionsList.get(new Random().nextInt(searchBoxOptionsList.size())));
            } catch (Exception exp1) {
//                seleniumLib.sendValue(searchBox1, "source:authlog");
                Click.element(driver, searchBoxOptionsList.get(new Random().nextInt(searchBoxOptionsList.size())));
//                Debugger.println("Value is not selected");
            }
            Wait.seconds(10); //to load the table
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from enterTheSearchValuesInTheSearchBox:" + exp);
            SeleniumLib.takeAScreenShot("noSearchValueInSearchBox.jpg");
            return false;
        }
    }

    public boolean verifyTheHostHeaderInTheTable() {
        try {
            if(!Wait.isElementDisplayed(driver, hostHeader, 30)){
                Debugger.println("Host header is not displayed in the table");
                SeleniumLib.takeAScreenShot("noHostHeader.jpg");
            }
            Wait.seconds(5);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheHostHeaderInTheTable:" + exp);
            SeleniumLib.takeAScreenShot("noHostHeader.jpg");
            return false;
        }
    }



    public boolean selectTheServiceCheckBox(String serviceName) {
        try{
            String serName=selectedCheckBox.replaceAll("dummyName", serviceName);
            WebElement checkBox =driver.findElement(By.xpath(serName));
            if (!Wait.isElementDisplayed(driver,checkBox , 10)){
                Debugger.println("Option to click on checkbox not present in Service Options");
                SeleniumLib.takeAScreenShot("SelectCheckBox.jpg");
                return false;
            }
            Actions.clickElement(driver,checkBox);
            Wait.seconds(2);
            return true;
        }
        catch (Exception exp){
            Debugger.println("Exception from SelectTheServiceCheckBox:" + exp);
            SeleniumLib.takeAScreenShot("SelectTheServiceCheckBox.jpg");
            return false;
        }
    }

    public boolean selectTheOptionInServicesSection(String option) {
        try{
            if(!Wait.isElementDisplayed(driver, serviceSection ,30)){
                Debugger.println("Logs section is not opened");
                SeleniumLib.takeAScreenShot("NoLogsSection.jpg");
            }
            Wait.seconds(5);
//            Actions.scrollToBottom(driver);
            SeleniumLib.scrollToElement(e2eLatestPushOption);
            if(e2eLatestPushOption.equals(option)) {
                if (Wait.isElementDisplayed(driver, e2eLatestPushOption, 30 )) {
                    seleniumLib.clickOnWebElement(onlyOptionButton);
                    Debugger.println("Given option is selected");
                    Wait.seconds(5);
                } else {
                    Debugger.println("No such option is displayed");
                    SeleniumLib.takeAScreenShot("OptionIsNotDisplayed.jpg");
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from selectTheOptionInServicesSection: " +exp);
            SeleniumLib.takeAScreenShot("noOptionDisplayed.jpg");
            return false;
        }
    }
}

