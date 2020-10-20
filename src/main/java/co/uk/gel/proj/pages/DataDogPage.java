package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    @FindBy(xpath = "//input[@class='ui_form_input-text__input ui_form_validation__element'][@placeholder='Filter values']")
    public WebElement serviceFilterSearch;
    @FindBy(xpath = "//div[@class='ui_facets_facet-value']")
    public List<WebElement> serviceFilters;

    @FindBy(css = "#ui_icons_logs--sprite")
    public WebElement logsSection;

    @FindBy(xpath = "//*[@id=\"react-app\"]/div[1]/div[2]/div[2]/header/div[2]/div/div/div/div/div/div/div/div/svg[3]/g[2]/g/rect[1]")
    public List<WebElement> searchBoxOptionsList;

    @FindBy(xpath = "//span[@class='ui_form_editable-text ui_form_editable-text--md']")
    public WebElement searchBox1;

    @FindBy(xpath = "//div[@class='drillthrough-frame']/table/tbody/tr")
    public WebElement hostHeader;

    @FindBy(xpath = "(//div[contains(text(),'Service')])[2]")
    public WebElement serviceSection;

    @FindBy(xpath = "//span[contains(text(),'e2e-latest-sftp-push')]")
    public WebElement e2eLatestPushOption;

    @FindBy(xpath = "//span[contains(text(),'Only')]")
    public WebElement onlyOptionButton;

    @FindBy(xpath = "//div[@class='ui_facets_facet__facet-title']//div[@class='ui_layout_overflower__original']")
    public List<WebElement> coreSections;


    public String loginToDataDogApplication(WebDriver driver, String userType) {
        try {
            if (!Wait.isElementDisplayed(driver,emailAddressField,60)) { //If the element is not displayed, even after the waiting time
                return "Email Address Field is not visible, even after the waiting period of 60 seconds. Looks like Datadog page is not accessible.";
            }
            seleniumLib.sendValue(emailAddressField,AppConfig.getDataDog_userName());
            seleniumLib.sleepInSeconds(2);
            seleniumLib.sendValue(passwordField,AppConfig.getDataDog_password());
            seleniumLib.sleepInSeconds(2);
            seleniumLib.clickOnWebElement(loginButton);
            seleniumLib.sleepInSeconds(5);
            return "Success";
        } catch (Exception exp){
            SeleniumLib.takeAScreenShot("DatadogLogin.jpg");
            return "Exception from loginToDataDogApplication"+exp;
        }
    }

    public boolean moveToMenuSection(String menu) {
        try{
            By menuItem = By.xpath("//ul[contains(@class,'single-page-app_navbar_navbar-menu')]/li//a//span[text()='"+menu+"']");
            if(seleniumLib.isElementPresent(menuItem)) {
                seleniumLib.moveMouseAndClickOnElement(menuItem);
            }
            seleniumLib.sleepInSeconds(3);

            By closePanel = By.xpath("//a[@class='cancel']");
            if(seleniumLib.isElementPresent(closePanel)){
                seleniumLib.clickOnElement(closePanel );
            }
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
                    seleniumLib.sleepInSeconds(5);
                    coreSection.click();
                    break;
                }
                seleniumLib.sleepInSeconds(3);

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

    public String enterTheSearchValuesInTheSearchBox(String values) {
        try {
            Debugger.println("Searching for: "+values);
            if(!Wait.isElementDisplayed(driver, searchBox1, 15)){
                return "No search box is displayed.";
            }
            seleniumLib.sleepInSeconds(3);
            searchBox1.sendKeys(values);
            seleniumLib.sleepInSeconds(2);
            try {
                Actions.selectValueFromDropdown(dropdownValue, values);
            } catch (Exception exp1) {
                Click.element(driver, searchBoxOptionsList.get(new Random().nextInt(searchBoxOptionsList.size())));
            }
            seleniumLib.sleepInSeconds(10);
            return "Success";
        } catch (Exception exp) {
            return "Exception from enterTheSearchValuesInTheSearchBox:" + exp;
        }
    }

    public String verifySearchResult() {
        try {
            By tableRows = By.xpath("//div[@class='drillthrough-frame']/table/tbody/tr");
            if(seleniumLib.isElementPresent(tableRows)){
                List<WebElement> rowList = seleniumLib.getElements(tableRows);
                if(rowList.size() < 1){
                    return "No search List available";
                }
            }else{
                return "Search Result Table not loaded.";
            }
            return "Success";
        } catch (Exception exp) {
            return "Exception from verifyTheHostHeaderInTheTable:" + exp;
        }
    }



    public String searchAndSelectFilters(String filters) {
        try{
           String expFilters[] = filters.split(",");
           String actTitle = "",checkStatus="";
           WebElement inputCheck = null;
           boolean isSelected = false;
            if(seleniumLib.isElementPresent(serviceFilterSearch)){
                seleniumLib.clickOnWebElement(serviceFilterSearch);
                seleniumLib.sleepInSeconds(2);
                seleniumLib.sendValue(serviceFilterSearch,expFilters[0].substring(0,6));
                seleniumLib.sleepInSeconds(2);
            }
            serviceFilters = driver.findElements(By.xpath("//div[@class='ui_facets_facet-value']"));
            seleniumLib.sleepInSeconds(2);
            int count = 0;
            int consideredCount = 0;
            for (WebElement serviceFilter : serviceFilters) {
                count++;
                actTitle = serviceFilter.getAttribute("title");
                inputCheck = serviceFilter.findElement(By.xpath(".//input"));
                isSelected = false;
                checkStatus = inputCheck.getAttribute("value");
                if(count == 1) {
                   WebElement onlySelect = serviceFilter.findElement(By.xpath("./span/span"));
                   if (seleniumLib.isElementPresent(onlySelect)) {
                       onlySelect.click();
                       seleniumLib.sleepInSeconds(5);
                       Debugger.println("Only selected:");
                       consideredCount++;
                       serviceFilters = driver.findElements(By.xpath("//div[@class='ui_facets_facet-value']"));
                       Debugger.println("Total Filters2....:" + serviceFilters.size());
                   }
                   continue;
               }
               Debugger.println(count+".Filter....:" + actTitle+"-"+checkStatus);
               for(int j=0; j<expFilters.length; j++) {
                    if (actTitle.equalsIgnoreCase(expFilters[j])) {
                        if (checkStatus.equalsIgnoreCase("off")) {
                            try {
                                seleniumLib.clickOnWebElement(inputCheck);//Select
                                Debugger.println(actTitle + " Selected");
                                consideredCount++;
                            } catch (Exception exp) {
                                //Stale element reference
                            }
                        } else {
                            //Already selected
                            Debugger.println(actTitle + " already in selected mode.");
                        }
                    }
               }
               if(count >= consideredCount){
                   break;
               }
               if(actTitle.equalsIgnoreCase("Alert")||actTitle.equalsIgnoreCase("Critical")||
                       actTitle.equalsIgnoreCase("Warn")||actTitle.equalsIgnoreCase("Info")){
                   continue;
               }
                seleniumLib.sleepInSeconds(2);
            }
            return "Success";
        } catch (Exception exp){
            return "Exception from SelectTheServiceCheckBox:" + exp;
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

