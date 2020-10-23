package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Calendar;
import java.util.List;

public class KibanaPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    public KibanaPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[contains(@class,'euiFormControlLayout__childrenWrapper')])[1]")
    public WebElement searchBox;

    @FindBy(xpath = "//input[@data-test-subj='queryInput']")
    public WebElement searchInput;

    @FindBy(xpath = "//div[@id='QuickSelectPopover']//button")
    public WebElement dateIcon;

    @FindBy(xpath = "//ul[contains(@class,'dscFieldList--popular')]/li//field-name")
    public List<WebElement> popularFilterList;
    @FindBy(xpath = "//ul[contains(@class,'dscFieldList--unpopular')]/li//field-name")
    public List<WebElement> unPopularFilterList;

    @FindBy(xpath = "//button[@class='kuiLocalMenuItem '][text()='Save']")
    public WebElement saveButton;
    @FindBy(xpath = "//input[@data-test-subj='savedObjectTitle']")
    public WebElement reportNameInput;

    @FindBy(xpath = "//button[@data-test-subj='confirmSaveSavedObjectButton']")
    public WebElement confirmSaveButton;
    @FindBy(xpath = "//button[@class='kuiLocalMenuItem '][text()='Share']")
    public WebElement shareButton;
    @FindBy(xpath = "//button[@data-test-subj='sharePanel-CSVReports']")
    public WebElement csvReports;
    @FindBy(xpath = "//button[@data-test-subj='generateReportButton']")
    public WebElement generateCSV;

    String buttonAction = "//span[contains(@class,'euiButton__content')]//span[text()='dummyName']";

    public String loadKibanaPage(String baseURLKibana){
        try{
            String KibanaPage = AppConfig.getPropertyValueFromPropertyFile(baseURLKibana);
            Wait.forPageToBeLoaded(driver);
            driver.get(KibanaPage);
            Wait.seconds(5);
            return "Success";
        }catch(Exception exp){
            return "Exception in accessing Kibana Page:"+exp;
        }

    }

    public String searchReferralId(String refID,String commonLink) {
        try {
            if (!Wait.isElementDisplayed(driver, searchBox, 30)) {
                SeleniumLib.takeAScreenShot("KibanaSearchError.jpg");
                return "The KIBANA tool page search box is not displayed.";
             }
            Debugger.println("The Ref ID to search:" + refID);
            seleniumLib.clickOnWebElement(searchBox);
            seleniumLib.sleepInSeconds(2);
            Debugger.println("Clicked:");
            //searchBox.click();
            seleniumLib.sendValue(searchInput,refID);
            seleniumLib.sleepInSeconds(2);
            if(commonLink == null || commonLink.isEmpty()){
                return "Success";//No specific duration to be selected
            }
            seleniumLib.clickOnWebElement(dateIcon);
            seleniumLib.sleepInSeconds(2);
            //Commonly used link
            if(commonLink.indexOf("-") == -1) {
                By commonUsedLink = By.xpath("//button[@class='euiLink euiLink--primary'][text()='" + commonLink + "']");
                seleniumLib.clickOnElement(commonUsedLink);
                seleniumLib.sleepInSeconds(10);
            }else{
                String[] dates = commonLink.split("-");
                //To be done
            }

            return "Success";
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("KibanaSearchReferralId.jpg");
            return "Exception from searchReferralId:" + exp;
        }
    }

    public boolean clickOnButton(String buttonName) {
        try {
            String button = buttonAction.replaceAll("dummyName", buttonName);
            WebElement buttonElement = driver.findElement(By.xpath(button));
            if (!Wait.isElementDisplayed(driver, buttonElement, 30)) {
                Debugger.println("Could not click on button.");
                SeleniumLib.takeAScreenShot("clickOnButton.jpg");
                return false;
            }
            Actions.clickElement(driver, buttonElement);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clickOnButton:" + exp);
            SeleniumLib.takeAScreenShot("clickOnButton.jpg");
            return false;
        }
    }

    public String selectFilter(String filter) {
        try {
            boolean isPresent = false;
            String actTitle = "";
            WebElement buttoElement = null;
            for (WebElement webElement : popularFilterList) {
                actTitle = webElement.getAttribute("title");
                Debugger.println("Popular: "+actTitle);
                if(actTitle.contains(filter)){
                    isPresent = true;
                    webElement.click();
                    buttoElement = webElement.findElement(By.xpath("./../button"));
                    if(seleniumLib.isElementPresent(buttoElement)) {
                        Debugger.println("Added....");
                        seleniumLib.clickOnWebElement(buttoElement);
                    }
                    break;
                }
                seleniumLib.sleepInSeconds(2);
            }
            if(isPresent){
                return "Success";
            }
            for (WebElement webElement : unPopularFilterList) {
                actTitle = webElement.getAttribute("title");
                Debugger.println("UnPopular: "+actTitle);
                if(actTitle.contains(filter)){
                    isPresent = true;
                    buttoElement = webElement.findElement(By.xpath("./../button"));
                    seleniumLib.clickOnWebElement(buttoElement);
                    break;
                }
                seleniumLib.sleepInSeconds(2);
            }
            if(!isPresent){
                return "Filter "+filter+" could not find.";
            }
            return "Success";
        } catch (Exception exp) {
            Debugger.println("Exception from selectTheDateLink:" + exp);
            SeleniumLib.takeAScreenShot("selectTheDateLink.jpg");
            return "Exception from selectTheDateLink:" + exp;
        }
    }
    public String verifySearchResults(String tableFields){
        try{
            By tableHead = By.xpath("//table[@data-test-subj='docTable']/thead/tr/th/span");
            if(!seleniumLib.isElementPresent(tableHead)){
                if(!seleniumLib.isElementPresent(tableHead)){
                    return "Kibana Search Result not displayed.";
                }
            }
            String colums[] = tableFields.split(",");
            int expCols = colums.length;
            List <WebElement> tableHeads = seleniumLib.getElements(tableHead);
            int count = 0;
            for (WebElement head : tableHeads) {
                //Debugger.println("Head: "+head.getText());
                if(tableFields.contains(head.getText())){
                    count++;
                }
            }
            if(expCols != count){
                return "All the selected fields are not loaded in Kibana Report.";
            }
            Debugger.println("Count: "+expCols+","+count);
            //Table Rows
            List <WebElement> searchResults = seleniumLib.getElements(By.xpath("//table[@data-test-subj='docTable']/tbody/tr"));
            if(searchResults.size() < 1){
                return "No search results have been loaded for the Kibana Filters.:"+tableFields;
            }
            return "Success";

        }catch(Exception exp){
            return "Exception in verifying Kibana Search Results:"+exp;
        }
    }

    public String saveReport(){
        try{
            if(seleniumLib.isElementPresent(saveButton)){
                seleniumLib.clickOnWebElement(saveButton);
                seleniumLib.sleepInSeconds(2);
                seleniumLib.sendValue(reportNameInput,"Report_"+getCurrentTime());
                if(seleniumLib.isElementPresent(confirmSaveButton)){
                    seleniumLib.clickOnWebElement(confirmSaveButton);
                    return "Success";
                }else{
                    return "Could not find Confirm Save Button";
                }
            }else{
                return "Could not find Save Button";
            }
        }catch(Exception exp){
            return "Exception in saving the report."+exp;
        }
    }
    public String shareCSVReport(){
        try{
            if(seleniumLib.isElementPresent(shareButton)){
                seleniumLib.clickOnWebElement(shareButton);
                seleniumLib.sleepInSeconds(2);
                if(seleniumLib.isElementPresent(csvReports)){
                    seleniumLib.clickOnWebElement(csvReports);
                    return "Success";
                }else{
                    return "Could not find CSV under share report";
                }
            }else{
                return "Could not find Share Button";
            }
        }catch(Exception exp){
            return "Exception in sharing the report."+exp;
        }
    }
    public String generateCSVReport(){
        try{
            if(seleniumLib.isElementPresent(generateCSV)){
                seleniumLib.clickOnWebElement(generateCSV);
                seleniumLib.sleepInSeconds(4);
                return "Success";
            }else{
                return "Could not find Generate CSV report";
            }
        }catch(Exception exp){
            return "Exception in Generating the CSV report."+exp;
        }
    }
    public static String getCurrentTime() {
        Calendar today = Calendar.getInstance();
        String year = "";
        String month = "";
        String day = "";
        int iyear = today.get(Calendar.YEAR);
        int imonth = today.get(Calendar.MONTH) + 1;
        int iday = today.get(Calendar.DATE);
        int ihour = today.get(Calendar.HOUR);
        int imin = today.get(Calendar.MINUTE);
        if (imonth < 10) {
            month = "0" + imonth;
        } else {
            month = "" + imonth;
        }
        year = "" + iyear;
        if (iday < 10) {
            day = "0" + iday;
        } else {
            day = "" + iday;
        }
        return day+month+year+ihour+ imin;
    }
}//end
