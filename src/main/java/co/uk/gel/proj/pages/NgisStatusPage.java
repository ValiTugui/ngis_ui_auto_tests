package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NgisStatusPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//h1")
    WebElement pageTitle;

    @FindBy(xpath = "//table")
    WebElement componentVersionTable;

    @FindBy(xpath = "//table/tbody/tr")
    List<WebElement> versionTableRows;

    static Map<String, String> componentVersionMap = new HashMap<>();

    public NgisStatusPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public String loadNgisStatusPage() {
        try {
            String requiredPage = AppConfig.getPropertyValueFromPropertyFile("NGIS_STATUS_PAGE");
            Debugger.println("Opening the URL: " + requiredPage);
            driver.get(requiredPage);
            Wait.seconds(5);//Wait for the page to load
            return "Success";
        } catch (Exception exp) {
            return "Exception from loading NGIS-Status page" + exp;
        }
    }

    public String getTitleText(String expectedTitle) {
        try {
            if (!Wait.isElementDisplayed(driver, pageTitle, 5)) {
                SeleniumLib.takeAScreenShot("NgisStatusPageError.jpg");
                return ("The NGIS status page title is not displayed.");
            }
            String actualTitle = pageTitle.getText();
            if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
                SeleniumLib.takeAScreenShot("NgisStatusPageTitleMismatch.jpg");
                return ("The Ngis Status page title Does NOT match.." + expectedTitle + " ,with actual- " + actualTitle);
            }
            return "Success";
        } catch (Exception exp) {
            return ("Exception from reading page title: " + exp);
        }
    }

    public String readPageDetails() {
        try {
            if (!Wait.isElementDisplayed(driver, componentVersionTable, 5)) {
                SeleniumLib.takeAScreenShot("VersionTableError.jpg");
                return ("The NGIS status page title is not displayed.");
            }
            for (WebElement dataRow : versionTableRows) {
                String rowData = dataRow.getText();
                String component = dataRow.findElement(By.xpath("./td[1]")).getText();
                String version = dataRow.findElement(By.xpath("./td[2]")).getText();
                componentVersionMap.put(component, version);
            }
            Debugger.println("The list of versions from status page- " + componentVersionMap);
            return "Success";
        } catch (Exception exp) {
            return ("Exception from reading version table data: " + exp);
        }
    }

    public String writeVersionToFile(String components, String fileName) {
        try {
            String[] requiredComponentsVersion;
            if (components.contains(",")) {
                requiredComponentsVersion = components.split(",");
            } else {
                requiredComponentsVersion = new String[]{components};
            }
            Arrays.stream(requiredComponentsVersion).forEach(key -> {
                String versionValue = componentVersionMap.get(key);
                String dataToWrite = key + " = " + versionValue + System.lineSeparator();
                SeleniumLib.writeToTextFileOfName(fileName, dataToWrite);
            });
            return "Success";
        } catch (Exception exp) {
            return "Exception from writing version info in a file: " + exp;
        }
    }

}//end