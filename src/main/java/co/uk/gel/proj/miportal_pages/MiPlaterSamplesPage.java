package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MiPlaterSamplesPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiPlaterSamplesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }


    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box-header']")
    public WebElement searchBoxHeader;

    @FindBy(xpath = "//input[@data-shinyjs-resettable-id='plater_samples-search-value']")
    public WebElement getPlaterSamplesDate;

    public void fillInThePlaterSamplesDate(String date) {
        try {
            Wait.forElementToBeClickable(driver, getPlaterSamplesDate);
            String actualDate = getPlaterSamplesDate.getAttribute("data-shinyjs-resettable-value");
            if (date.equals("today") && !actualDate.isEmpty()) {
                Debugger.println("today's date " + getPlaterSamplesDate.getAttribute("data-shinyjs-resettable-value"));
            } else if (date.equalsIgnoreCase("future_date")     ){
                String dateToday = TestUtils.todayInDDMMYYYFormat();
                Debugger.println("today's date in dd/mm/yyyy " + dateToday);
                dateToday = dateToday.replace("/", "-");
                Debugger.println("today's date in dd-mm-yyyy " + dateToday);
                String updatedFutureDate =  TestUtils.getDateNineMonthsOrMoreBeforeDoB(dateToday, 1,0, 0); //Add future day +1
                Debugger.println("future date in dd-mm-yyyy " + updatedFutureDate);
                Actions.clickElement(driver, getPlaterSamplesDate);
                Actions.clearInputField(getPlaterSamplesDate);
                Wait.seconds(2);
                Actions.fillInValue(getPlaterSamplesDate,updatedFutureDate);
            } else{
                Wait.seconds(1);
                Actions.clickElement(driver, getPlaterSamplesDate);
                Actions.clearInputField(getPlaterSamplesDate);
                Wait.seconds(2);
                Actions.fillInValue(getPlaterSamplesDate,date);
            }
        } catch (Exception exp) {
            Debugger.println("Exception filling date:" + exp);
            SeleniumLib.takeAScreenShot("UnableToFillDate.jpg");
        }
    }
}