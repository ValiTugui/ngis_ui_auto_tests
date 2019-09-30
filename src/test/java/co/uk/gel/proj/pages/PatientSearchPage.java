package co.uk.gel.proj.pages;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class PatientSearchPage {

      //WebDriver driver;


    /*public PatientSearchPage(SeleniumDriver driver) {
        super(driver);
    }*/

      /*public PatientSearchPage (WebDriver driver) {
          this.driver = driver;
          PageFactory.initElements(driver, this);
      }*/


    @FindBy(name = "loginfmt")
    public WebElement emailAddressField;

    @FindBy(name = "passwd")
    public WebElement passwordField;

    @FindBy(css = "input[type*='submit']")
    public WebElement nextButton;

    @FindBy(id="nhsNumber")
    public WebElement nhsNumber;

    public WebElement dateDay;
    public WebElement dateMonth;
    public WebElement dateYear;
    public WebElement firstName;
    public WebElement lastName;
    public WebElement familyName;
    public WebElement postcode;

    @FindBy(xpath = "//label[contains(@for,'gender')]//following::div")
    public WebElement genderButton;

    @FindBy(xpath = "//label[contains(@for,'administrativeGender')]//following::div")
    public WebElement administrativeGenderButton;

    @FindBy(css = "div[id*='react-select']")
    public WebElement genderValue;

    @FindBy(xpath = "//button[text()='No']")
    public WebElement noButton;

    @FindBy(css = "button[class*='search']")
    public WebElement searchButton;

    @FindBy(xpath = "//button[contains(string(),'Search')]")
    public WebElement searchButtonByXpath;


    @FindBy(css = "a[class*='patient-card']")
    public WebElement patientCard;

    @FindBy(css = "h3[class*='results__header']")
    public WebElement patientSearchResultsHeader;

    @FindBy(css = "span[class*='badge']")
    public WebElement patientCardBadge;

    @FindBy(css = "p[class*='patient-name']")
    public WebElement patientFullName;

    @FindBy(css = "p[class*='card-line']")  // To get all details of patient at once
    public List<WebElement> patientDetails;

    @FindBy(xpath = "//p[contains(string(),'Born')]")
    public WebElement patientDateOfBirth;

    @FindBy(xpath = "//p[contains(string(),'Gender')]")
    public WebElement patientGender;

    @FindBy(xpath = "//p[contains(string(),'NHS No. ')]")
    public WebElement patientNSNo;

    @FindBy(xpath = "//p[contains(string(),'Address')]")
    public WebElement patientAddress;





    public void fillInValidPatientDetailsUsingNHSNumberAndDOB(String nhsNo, String day, String month, String year) {
        nhsNumber.sendKeys(nhsNo);
        dateDay.sendKeys(day);
        dateMonth.sendKeys(month);
        dateYear.sendKeys(year);
    }

    public void clickNoButton() {
        noButton.click();
    }

    public void clickSearchButton(WebDriver driver) {
        Wait.forElementToBeClickable(driver,searchButton);
        searchButton.click();
    }
    public void clickSearchButtonByXpath(WebDriver driver) {
        Wait.forElementToBeClickable(driver,searchButtonByXpath);
        searchButtonByXpath.click();
    }



    public void checkThatPatientCardIsDisplayed(WebDriver driver, String badgeText) {
        Wait.forElementToBeDisplayed(driver, patientCard);
        Wait.forElementToBeDisplayed(driver, patientSearchResultsHeader);
        System.out.println("The search result is from :" + patientCardBadge.getText());
        Assert.assertEquals(badgeText, patientCardBadge.getText().trim());
    }


    public void loginToTestOrderingSystemAsServiceDeskUser(WebDriver driver) {
        Wait.forElementToBeClickable(driver, emailAddressField);
        emailAddressField.sendKeys(AppConfig.getApp_username());
        nextButton.click();
        Wait.seconds(2);
        Wait.forElementToBeClickable(driver, passwordField);
        passwordField.sendKeys(AppConfig.getApp_password());
        nextButton.click();
    }


    public void patientDetailsAreDisplayedInTheCard() {


        String expectedFirstname = "NELLY";
        String expectedLastname = "STAMBUKDELIFSCHITZ";
        String expectedTitle = "MRS";
        String expectedFullName = expectedLastname + ", " + expectedFirstname + " (" + expectedTitle + ")";
        String actualFullName = patientFullName.getText().trim();

        String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov","Dec"};

        String expectedDayOfBirth = "23";
        String expectedMonthOfBirth = "03";
        String expectedYearOfBirth =  "2011";
        String expectedDateOfBirth =expectedDayOfBirth+"-"+months[Integer.parseInt(expectedMonthOfBirth)-1]+"-"+expectedYearOfBirth;
        System.out.println("Expected date of birth re-formatted from dd-mm-yyyy to dd-mmm-yyyy: " + expectedDateOfBirth);
        String actualFullDOB = patientDateOfBirth.getText().trim();


        String expectedGender = "Female";
        String actualGender = patientGender.getText().trim();

        String expectedNHSNumber = "9449310602";
        String actualNHSNumber = patientNSNo.getText().trim();

        String expectedAddressLine1 ="4 HAYWARD ROAD";
        String expectedAddressLine2 = "THAMES DITTON";
        String expectedAddressLine3 ="SURREY";
        String expectedAddressLine4 ="";
        String expectedPostcode = "KT7 0BE";
        String expectedFullAddress = "Address " + expectedAddressLine1 + ", " + expectedAddressLine2 + ", " +
                expectedAddressLine3 + ", " + expectedPostcode;

        String actualAddress = patientAddress.getText().trim();

        System.out.println("Expected full name = "+expectedFullName  + ", Actual full name "+actualFullName );
        Assert.assertEquals(expectedFullName, actualFullName);

        System.out.println("Expected DOB = "+expectedDateOfBirth  + ", Actual DOB: "+ actualFullDOB );
        //Assert.assertTrue(actualFullDOB.contains("Born " + expectedDayOfBirth));
        Assert.assertTrue(actualFullDOB.contains("Born " + expectedDateOfBirth));

        System.out.println("Expected Gender= "+expectedGender  + ", Actual Gender: "+ actualGender );
        Assert.assertEquals("Gender " + expectedGender, actualGender);

        System.out.println("Expected nhs no = "+expectedNHSNumber  + ", Actual nhs no: "+actualNHSNumber );
        Assert.assertEquals("NHS No. " + expectedNHSNumber, actualNHSNumber);

        System.out.println("Expected address = "+expectedFullAddress  + ", Actual address"+actualAddress );
        Assert.assertEquals(expectedFullAddress, actualAddress);

    }


    public void fillInValidPatientDetailsUsingNOFields(String searchParams) {

        //DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female
        // Extract the patient details from the example-table
        HashMap<String,String> paramNameValue = new HashMap<>();
        String[] allParams = searchParams.split(":");
        for(String s : allParams)
        {
            paramNameValue.put(s.split("=")[0],s.split("=")[1]);
        }

        Set<Map.Entry<String,String>> val = paramNameValue.entrySet();
        for(Map.Entry m : val)
        {
            System.out.println("Key is :"+ m.getKey() +" and value is :"+ m.getValue());
        }
        Set<String> paramsKey= paramNameValue.keySet();
        for(String s:paramsKey)
        {
            switch (s)
            {
                case "DOB":
                {
                    String dobValue = paramNameValue.get(s);
                    String[] dobSplit = dobValue.split("-");
                    dateDay.sendKeys(dobSplit[0]);
                    dateMonth.sendKeys(dobSplit[1]);
                    dateYear.sendKeys(dobSplit[2]);
                    break;
                }
                case "FirstName":
                {
                    firstName.sendKeys(paramNameValue.get(s));
                    break;
                }
                case "LastName":
                {
                    lastName.sendKeys(paramNameValue.get(s));
                    break;
                }
                case "Gender":
                {
                    genderButton.click();
                    genderValue.findElement(By.xpath("//span[text()='" + paramNameValue.get(s) + "']")).click();
                    break;
                }
                case "Postcode":
                {
                    postcode.sendKeys(paramNameValue.get(s));
                    break;
                }
            }
        }


    }

    public void checkSearchResultHeaderIsDisplayed(WebDriver driver, String resultHeader){

        Wait.forElementToBeDisplayed(driver, patientCard);
        Wait.forElementToBeDisplayed(driver, patientSearchResultsHeader);
        System.out.println("The actual search result header is :" + patientSearchResultsHeader.getText());
        Assert.assertEquals(resultHeader, patientSearchResultsHeader.getText().trim());


    }

}
