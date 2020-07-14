package co.uk.gel.proj.miportal_pages;


import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.apache.bcel.generic.ARETURN;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MiClinicalDataQualityPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiClinicalDataQualityPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//select[@id='clinical_dq-filter_glh']/child::*")
    public List<WebElement> clinical_dq_filter_glh;

    public boolean verifyClinicalDataQualityReport(String exp_value) {
        try {
            By Clinical_dq_Report_Path = By.xpath("//h3[text()='" + exp_value + "']");
            SeleniumLib.waitForElementVisible(Clinical_dq_Report_Path);
            if (!seleniumLib.getText(Clinical_dq_Report_Path).equalsIgnoreCase(exp_value)) {
                Debugger.println("Clinical_dq_Report is not present");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from : verifyClinicalDataQualityReport :  " + exp);
            return false;
        }
    }

    public boolean verifyReportGuidance(String exp_value) {
        try {
            By Report_Guidance_Path = By.xpath("//a[text()='" + exp_value + "']");
            SeleniumLib.waitForElementVisible(Report_Guidance_Path);
            if (!seleniumLib.getText(Report_Guidance_Path).equalsIgnoreCase(exp_value)) {
                Debugger.println("Report_Guidance is not present");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from : verifyReportGuidance :  " + exp);
            return false;
        }
    }

    @FindBy(xpath = "(//div[@class='box']//span[@class='caret'])[1]")
    public WebElement clinical_dq_filter_dropdown;
    public void clickOnClinicalDqFilterGlhDropdown() {
        clinical_dq_filter_dropdown.click();
    }
    public boolean selectClinicalDqFilterGlh(String value) {
        try {
            Wait.seconds(1);
            boolean result = false;
            for (int i = 0; i < clinical_dq_filter_glh.size(); i++) {
                if (clinical_dq_filter_glh.get(i).getText().contains(value)) {
                    result = true;
                    break;
                } else {
                    Debugger.println("Exp value = " + clinical_dq_filter_glh.get(i).getText() + " : ACT value : " + value + " : is not match");
                }
            }
            return result;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalGlhSamples:selectDropDownSearchValue: " + exp);
            SeleniumLib.takeAScreenShot("GlhselectDropDownSearchValue.jpg");
            return false;
        }
    }

    @FindBy(xpath = "//select[@id='clinical_dq-filter_glh']")
    public WebElement GlhDropDown;
    public boolean selectValueInGlhDropDown(String value) {
        try {
            if(!seleniumLib.selectFromListByText(GlhDropDown,value)){
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(GlhDropDown,value);
            }
            Wait.seconds(5);
            return true;
        }
        catch (Exception exp) {
            Debugger.println("Exception in MIPortalClinicalDataQuality:selectGlhDropdownMenu: "+ exp);
            SeleniumLib.takeAScreenShot("GlhDropdownMenu.jpg");
            return false;
        }

    }

    @FindBy(xpath = "(//div[@class='box']//span[@class='caret'])[2]")
    public WebElement OrderingEntityDropdown;
    public void ClickOnOrderingEntityDd(){
        OrderingEntityDropdown.click();
        Wait.seconds(5);
    }

    @FindBy(xpath = "//div[@class='btn-group btn-group-sm btn-block']//button[text()][2]")
    public WebElement clickOnDeselectAllButton;
    public void ClickOnDeselectAllButton() {
        clickOnDeselectAllButton.click();
        Wait.seconds(3);
    }

    @FindBy(xpath = "//div[@class='btn-group btn-group-sm btn-block']//button[text()][1]")
    public WebElement clickOnSelectAllButton;
    public void ClickOnSelectAllButton() {
        clickOnSelectAllButton.click();
        Wait.seconds(5);
    }

    @FindBy(xpath = "//button[@id='clinical_dq-apply_filters']")
    public WebElement ClickOnApplyFiltersButton;
    public void clickOnApplyFiltersButton() {
        ClickOnApplyFiltersButton.click();
        Wait.seconds(15);
    }

    @FindBy(xpath = "//a[text()='Summary']")
    public WebElement SummaryTitle;

    @FindBy(xpath = "//a[text()='Full Output']")
    public WebElement FullOutputTitle;

    @FindBy(xpath = "//a[text()='Streamline Output']")
    public WebElement StreamlineOutputTitle;

    @FindBy(xpath = "//a[text()='Genomic Identity Output']")
    public WebElement GenomicIdentityOutputTitle;

    @FindBy(xpath = "//a[text()='Appendix - all rules']")
    public WebElement AppendixAllRulesTitle;

    public boolean VerifyTheElementsPresentInApplyFiltersSection() {
        try{
            Wait.seconds(10);
            if(!Wait.isElementDisplayed(driver,SummaryTitle,20)){
                Debugger.println("Summary Title is displayed");
                SeleniumLib.takeAScreenShot("SummaryTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,FullOutputTitle,20)){
                Debugger.println("Full Output Title is displayed");
                SeleniumLib.takeAScreenShot("FullOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,StreamlineOutputTitle,20)){
                Debugger.println("Streamline Output Title is displayed");
                SeleniumLib.takeAScreenShot("StreamlineOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,GenomicIdentityOutputTitle,20)){
                Debugger.println("Genomic Identity Output Title is displayed");
                SeleniumLib.takeAScreenShot("GenomicIdentityOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,AppendixAllRulesTitle,20)){
                Debugger.println("Appendix All RulesTitle is displayed");
                SeleniumLib.takeAScreenShot("AppendixAllRulesTitle.jpg");
                return false;
            }
            List<WebElement> expectedElements = new ArrayList<WebElement>();
            expectedElements.add(SummaryTitle);
            expectedElements.add(FullOutputTitle);
            expectedElements.add(StreamlineOutputTitle);
            expectedElements.add(GenomicIdentityOutputTitle);
            expectedElements.add(AppendixAllRulesTitle);
            for (int i=0; i < expectedElements.size(); i++){
                if (!seleniumLib.isElementPresent(expectedElements.get(i))){
                    Debugger.println("Summary result section element not displayed: "+expectedElements.get(i));
                    SeleniumLib.takeAScreenShot("SummaryResultSectionNotFound.jpg");
                    return false;
                }
            } return true;
        } catch (Exception exp){
            Debugger.println("Summary result section is not properly loaded" + exp);
            SeleniumLib.takeAScreenShot("SummaryResultSectionNotFound.jpg");
            return false;
        }
    }
}