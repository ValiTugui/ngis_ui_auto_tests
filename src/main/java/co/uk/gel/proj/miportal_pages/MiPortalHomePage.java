package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MiPortalHomePage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiPortalHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }


    @FindBy(xpath = "//a[contains(string(),'File Submissions')]")
    public WebElement genericNavigation;

    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box box-solid box-primary']")
    public WebElement mainSearchContainer;

    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box-header']")
    public WebElement searchBoxHeader;

    @FindBy(xpath = "//div[@class='tab-pane active']//h3[@class='box-title']")
    public WebElement searchTitle;

    @FindBy(xpath = "//div[@class='inner open'and@aria-expanded='true']//li//span")
    public List<WebElement> genericDropDropDownValues1;

    //enhanced for multiple drop-down value selection
    @FindBy(xpath = "//div[@class='inner open'and@aria-expanded='true']//li//span[@class='text']")
    public List<WebElement> genericDropDropDownValues;

    @FindBy(xpath = "//select[@id='file_submissions-search-col']/option")
    public List<WebElement> dropDownFileSubmissionsSearchDropValues;

    //sidebarCollapsed
    @FindBy(id = "sidebarCollapsed")
    public WebElement mainSideBar;     //User attribute to get the value - data-collapsed="false"

    @FindBy(xpath = "//ul[contains(string(),'Sample Processing')]")
    public WebElement sampleProcessing;

    @FindBy(xpath = "//ul[contains(string(),'Sample Processing')]//ul")
    public WebElement sampleProcessing2;

    @FindBy(xpath = "//ul[contains(string(),'Sample Processing')]//ul/li/a")
    public List <WebElement> subMenusOfSimpleProcessing;

    @FindBy(xpath = "//a[@class='sidebar-toggle']")
    public WebElement sideBarToggle;

    @FindBy(xpath = "//div[contains(@class,'active')]//button[contains(string(),'Add')]")
    public WebElement addButton;

    @FindBy(xpath = "//div[contains(@class,'active')]//button[contains(string(),'Search')]")
    public WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class,'active')]//button[contains(string(),'Reset')]")
    public WebElement resetButton;

//    @FindBy(xpath = "//div[contains(@class,'active')]//span[contains(@class,'badge-info')]")
//    public WebElement badgeFilterSearchCriteria;

    @FindBy(xpath = "//h3[text()='Search Results']")
    public WebElement searchResultTitle;

    @FindBy(xpath = "//div[contains(@class,'active')]//button[contains(string(), 'Display Options')]")
    public WebElement searchResultDisplayOptionsButton;

    @FindBy(xpath = "//div[contains(@class,'active')]//a[contains(string(),'Download CSV')]")
    public WebElement downloadCSVButton;

//    @FindBy(xpath = "//div[contains(@class,'active')]//table[contains(@id,'DataTables_Table')]//tbody/tr")
//    public List<WebElement> searchResultTable;

    @FindBy(xpath = "//table[contains(@id,'DataTables_Table')]/thead//tr")
    public WebElement searchResultRowHeader;

//   By searchResultTableHeading = By.xpath("//div[contains(@class,'active')]//table[contains(@id,'DataTables_Table')]//thead");

    @FindBy(xpath = "//select[contains(@name,'DataTables_Table')]")
    public WebElement searchResultEntryOptionsSelection;

//    @FindBy(xpath = "//div[contains(@class,'active')]//label[contains(string(),'Show')]//select")
//    public WebElement defaultPaginationEntryOptionsValue;

    @FindBy(css = "div[class*='modal-content']")
    public WebElement displayOptionsModalContent;

    // //span[text()='Compact grid']/../input
    @FindBy(xpath = "//span[text()='Compact grid']/../input")
    public WebElement compactGridCheckBox;

    @FindBy(xpath = "//span[text()='Compact grid']")
    public WebElement compactGridCheckBoxLabel;

    @FindBy(xpath = "//span[text()='Truncate columns']/../input")
    public WebElement truncateColumnsCheckBox;

    @FindBy(xpath = "//span[text()='Truncate columns']")
    public WebElement truncateColumnsCheckBoxLabel;

    @FindBy(xpath = "//h3[text()='Column ordering']")
    public WebElement headerColumnOrdering;

//    @FindBy(xpath = "//button[@id='file_submissions-display-reset']")
    @FindBy(xpath = "//button[contains(@id,'-display-reset')]")
    public WebElement resetHeaderOrdering;

    @FindBy(xpath = "//label[text()='Show']")
    public WebElement headerShow;

    @FindBy(xpath = "//label[text()='Hide']")
    public WebElement headerHide;

    @FindBy(xpath = "//div[contains(@class,'active')]//span[contains(@class,'badge-info')]")
    public WebElement badgeFilterSearchCriteria;

    @FindBy(xpath = "//div[contains(@class,'active')]//table[contains(@id,'DataTables_Table')]//tbody/tr")
    public List<WebElement> searchResultTable;

    By searchResultTableHeading = By.xpath("//div[contains(@class,'active')]//table[contains(@id,'DataTables_Table')]//thead//th");

    @FindBy(xpath = "//div[contains(@class,'active')]//label[contains(string(),'Show')]//select")
    public WebElement defaultPaginationEntryOptionsValue;

    @FindBy(xpath = "//div[contains(@class,'active')]//ancestor::div[@class='wrapper']/..//div[@class='modal-footer']//button[contains(string(),'Reset')]")
    public WebElement resetHeaderOrderingButton;

    @FindBy(xpath = "//div[contains(@class,'active')]//ancestor::div[@class='wrapper']/..//div[@class='modal-footer']//button[contains(string(),'Save')]")
    public WebElement saveAndCloseHeaderOrderingButton;

    @FindBy(xpath = "//button[contains(string(),'Show all')]")
    public WebElement headerShowAll;

    @FindBy(xpath = "//button[contains(string(),'Hide all')]")
    public WebElement headerHideAll;

    @FindBy(xpath = "//table[contains(@id,'DataTables_Table')]")
    public WebElement displayedResultTable;

    @FindBy(xpath="//p[contains(.,'Select a valid choice. That choice is not one of the available choices.')]")
    public WebElement errorMessageElement;


    String badgeFilterSearchCriteriaBy = "//div[contains(@class,'active')]//span[contains(@class,'badge-info')]";


    public boolean navigateToMiPage(String expectedMipage) {
        try {
            By miPage;
            miPage = By.xpath("//a[contains(string(),\"" + expectedMipage + "\")]");
            Wait.forElementToBeDisplayed(driver, driver.findElement(miPage), 30);
            if (!Wait.isElementDisplayed(driver, driver.findElement(miPage), 10)) {
                Debugger.println(" Mandatory page Link is not displayed even after waiting period...Failing.");
                SeleniumLib.takeAScreenShot("MandatoryPageLink.jpg");
                return false;
            }
            Actions.retryClickAndIgnoreElementInterception(driver, driver.findElement(miPage));
            return true;
        } catch (Exception exp) {
            Debugger.println("Mandatory Page Link is not displayed even after waiting period...Failing." + exp);
            SeleniumLib.takeAScreenShot("MandatoryStageLink.jpg");
            return false;
        }
    }//end

    public boolean searchBoxContainerIsDisplayed() {
        try {
//            Wait.forElementToBeDisplayed(driver, mainSearchContainer);
//            Wait.forElementToBeDisplayed(driver, searchBoxHeader);
            if(!Wait.isElementDisplayed(driver,searchBoxHeader,15)){
                Debugger.println("Search Terms header is not displayed");
                SeleniumLib.takeAScreenShot("searchcontainerNotFound.jpg");
                return false;
            }
            if (Wait.isElementDisplayed(driver, mainSearchContainer, 5)) {
                Debugger.println("Main search container is displayed");
                return true;
            } else {
                Debugger.println("Element not found ");
                SeleniumLib.takeAScreenShot("searchcontainerNotFound.jpg");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("Main search container is not displayed");
            SeleniumLib.takeAScreenShot("searchcontainerNotFound.jpg");
            return false;
        }
    }

    public void clickAddButton() {
        try {
            Wait.forElementToBeClickable(driver, addButton);
            Click.element(driver, addButton);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on addButton:" + exp);
            SeleniumLib.takeAScreenShot("NoaddButton.jpg");
        }
    }

    public void clickSearchButton() {
        try {
            Wait.forElementToBeClickable(driver, searchButton);
            Click.element(driver, searchButton);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on searchButton:" + exp);
            SeleniumLib.takeAScreenShot("NoSearchButton.jpg");
        }
    }

    public void clickResetButton() {
        try {
            Wait.forElementToBeClickable(driver, resetButton);
            try {
            Click.element(driver, resetButton);
                Wait.seconds(2);
            } catch (ElementClickInterceptedException exc) { //to handle the 3rd dropdown selection box overlay intercepting sometimes.
                Debugger.println("Click on Reset Intercepted, clicking on body first and trying to reset again...."+exc);
                WebElement body = driver.findElement(By.xpath("//body"));
                body.click();
                Wait.seconds(2);
            }
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on resetButton:" + exp);
            SeleniumLib.takeAScreenShot("NoResetButton.jpg");
        }
    }

    public void selectSearchValueDropDown(String value,String dropDownButton) {
        try {
            Wait.seconds(2);
            By buttonElement;
            buttonElement = By.xpath("//button[@data-id=\"" + dropDownButton + "\"]");
            Actions.retryClickAndIgnoreElementInterception(driver, driver.findElement(buttonElement));
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
            //Click.element(driver, element);

            String[] valueList = null;
            if (value.indexOf(",") == -1) {
                valueList = new String[]{value};
            } else {
                valueList = value.split(",");
            }
            for (int i = 0; i < valueList.length; i++) {
            Wait.seconds(2);
                Click.element(driver, driver.findElement(By.xpath("//div[contains(@class,'active')]//ul[@class='dropdown-menu inner ']/li//span[text()='" + valueList[i] + "']")));
            }
        } catch (Exception exp) {
            Debugger.println("Oops unable to locate drop-down element value : " + value + ":" + exp);
        }
    }

    public List<String> getDropDownValues(String dropDownButton) {
        try {
            By buttonElement;
            //button[@data-id='file_submissions-search-operator']
            Wait.seconds(3);
            buttonElement = By.xpath("//button[@data-id=\"" + dropDownButton + "\"]");
            Actions.clickElement(driver, driver.findElement(buttonElement));
            List<String> actualDropDownValues = new ArrayList<>();
            for (WebElement actualValue : genericDropDropDownValues) {
                actualDropDownValues.add(actualValue.getText().trim());
            }
            Debugger.println("Actual values: " + actualDropDownValues);
            return actualDropDownValues;
        } catch (Exception exp) {
            Debugger.println("Actual values are not displayed");
            SeleniumLib.takeAScreenShot("dropDownValuesAreNotFound.jpg");
            return null;
        }
    }

    public boolean badgeFilterSearchCriteriaIsDisplayed() {
        try {
            Wait.forElementToBeDisplayed(driver, mainSearchContainer);
            Wait.forElementToBeDisplayed(driver, searchBoxHeader);
            if (Wait.isElementDisplayed(driver, badgeFilterSearchCriteria, 10)) {
                Debugger.println("badge search criteria is displayed");
                return true;
            } else {
                Debugger.println("badge search criteria element is not found");
                SeleniumLib.takeAScreenShot("badgeSearchIsNotFound.jpg");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("badge search criteria element is not found");
            SeleniumLib.takeAScreenShot("badgeSearchIsNotFound.jpg");
            return false;
        }
    }

    public boolean badgeFilterSearchCriteriaIsNotDisplayed() {
        Wait.seconds(2);
        try {
            Wait.forElementToDisappear(driver, By.xpath(badgeFilterSearchCriteriaBy));
            if (!Wait.isElementDisplayed(driver, badgeFilterSearchCriteria, 10)) {
                Debugger.println("badge search criteria is NOT displayed as expected");
                return true;
            } else {
                Debugger.println("badge search criteria element is found");
                SeleniumLib.takeAScreenShot("badgeSearchIsFound.jpg");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("badge search criteria element is found");
            SeleniumLib.takeAScreenShot("badgeSearchIsFound.jpg");
            return false;
        }
    }

    public boolean searchResultTableIsDisplayed() {
      Wait.seconds(2);
        try {
            Wait.forElementToBeDisplayed(driver, searchResultDisplayOptionsButton);
            if (Wait.isElementDisplayed(driver, searchResultRowHeader, 10)) {
                Debugger.println("search result table is displayed as expected");
                return true;
            } else {
                Debugger.println("search result table is not found");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("search result table is not found");
            SeleniumLib.takeAScreenShot("searchResultTableNotFound.jpg");
            return false;
        }
    }

    public boolean verifyNoSearchResultMessage(String noResultMessage) {
        try {
            By displayedMessage;
            if (noResultMessage.contains("\'")) {
                // if the string contains apostrophe character, apply double quotes in the xpath string
                displayedMessage = By.xpath("//p[text()= \"" + noResultMessage + "\"]");
            } else {
                displayedMessage = By.xpath("//p[text()='" + noResultMessage + "']");
            }
            Wait.forElementToBeDisplayed(driver, driver.findElement(displayedMessage), 30);
            if (!Wait.isElementDisplayed(driver, driver.findElement(displayedMessage), 10)) {
                Debugger.println(" no result message is shown...Failing.");
                SeleniumLib.takeAScreenShot("noResultMessage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("no result message is shown...Failing...Failing." + exp);
            SeleniumLib.takeAScreenShot("noResultMessage.jpg");
            return false;
        }
    }//end


    public boolean verifyTheElementsInTheSearchResultSection() {
        try {
            Wait.seconds(1);
            if (!Wait.isElementDisplayed(driver, searchResultDisplayOptionsButton, 20)) {
                Debugger.println("Search result display option is not displayed.");
                SeleniumLib.takeAScreenShot("SearchResultSectionNotFound.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, displayedResultTable, 10)) {
                Debugger.println("Search result is not displayed as a table format.");
                SeleniumLib.takeAScreenShot("SearchResultSectionNotFound.jpg");
                return false;
            }
//            Wait.forElementToBeDisplayed(driver, searchResultDisplayOptionsButton, 20);
        List<WebElement> expectedElements = new ArrayList<WebElement>();
        expectedElements.add(searchResultTitle);
        expectedElements.add(searchResultDisplayOptionsButton);
        expectedElements.add(searchResultRowHeader);
        expectedElements.add(searchResultEntryOptionsSelection);
        expectedElements.add(downloadCSVButton);
        for (int i = 0; i < expectedElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expectedElements.get(i))) {
                    Debugger.println("Search result section element not displayed: "+expectedElements.get(i));
                    SeleniumLib.takeAScreenShot("SearchResultSectionNotFound.jpg");
                return false;
            }
                Debugger.println("Element " + i + " shown");
        }
        return true;
        } catch (Exception exp) {
            Debugger.println("Search result section is not properly loaded"+exp);
            SeleniumLib.takeAScreenShot("SearchResultSectionNotFound.jpg");
            return false;
        }
    }


    public boolean downloadMiCSVFile(String fileName) {
        try {
            //Delete if File already present
            TestUtils.deleteIfFilePresent(fileName, "");
            seleniumLib.clickOnWebElement(downloadCSVButton);
            Wait.seconds(15);//Wait for 15 seconds to ensure file got downloaded, large file taking time to download
            Debugger.println("Form: " + fileName + " ,downloaded from section: " + fileName);
            return true;
        } catch (Exception exp) {
            Debugger.println("Could not locate the form download button ..... " + exp);
            SeleniumLib.takeAScreenShot("OfflineOrderPrintFormsDownload.jpg");
            return false;
        }
    }

    public boolean clickSearchResultDisplayOptionsButton() {
        try {
            if(!Wait.isElementDisplayed(driver, searchResultDisplayOptionsButton,10)){
                Debugger.println("The Display Options button is not displayed");
                SeleniumLib.takeAScreenShot("NoDisplayOptions.jpg");
                return false;
            }
            Click.element(driver, searchResultDisplayOptionsButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on displayOptions:" + exp);
            SeleniumLib.takeAScreenShot("NoDisplayOptions.jpg");
            return false;
        }
    }

    public boolean modalContentIsDisplayed() {
        try {
            if (Wait.isElementDisplayed(driver, displayOptionsModalContent, 10)) {
                Debugger.println("modal-content is displayed as expected");
                return true;
            } else {
                Debugger.println("modal-content is NOT displayed");
                SeleniumLib.takeAScreenShot("modalContentNotShown.jpg");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("modal-content is NOT displayed");
            SeleniumLib.takeAScreenShot("modalContentNotShown.jpg");
            return false;
        }
    }

    public boolean verifyTheCheckBoxesDisplayedOnModalContent() {
        Wait.forElementToBeDisplayed(driver,displayOptionsModalContent, 10 );
        List<WebElement> expectedElements = new ArrayList<WebElement>();
        expectedElements.add(compactGridCheckBox);
        expectedElements.add(truncateColumnsCheckBox);
        for (int i = 0; i < expectedElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expectedElements.get(i))) {
                return false;
            }
            Debugger.println("element " + i + " shown");
        }
        return true;
    }


    public void clickResetButtonOnModalContent() {
        Wait.seconds(2);
        try {
            Wait.forElementToBeClickable(driver, resetHeaderOrdering);
            Click.element(driver, resetHeaderOrdering);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on resetHeaderOrderingButton:" + exp);
            SeleniumLib.takeAScreenShot("NoResetHeaderOrderingButton.jpg");
        }
    }

    public List<String> getColumnOrderShowHideLabelsOnDisplayedModal() {
        try {
            Wait.forElementToBeDisplayed(driver, headerColumnOrdering);
            if (!Wait.isElementDisplayed(driver, headerColumnOrdering, 10)){
                Debugger.println("No headerColumn element shown.");
                SeleniumLib.takeAScreenShot("noHeaderColumnHeader.jpg");
                return null;
            }
            List<String> actualColumnOrderShowHideLabels = new ArrayList<>();
            actualColumnOrderShowHideLabels.add(Actions.getText(headerColumnOrdering));
            actualColumnOrderShowHideLabels.add(Actions.getText(headerShow));
            actualColumnOrderShowHideLabels.add(Actions.getText(headerHide));
            return actualColumnOrderShowHideLabels;
        } catch (Exception exp) {
            Debugger.println("No headerColumn,show or hide element shown:" + exp);
            SeleniumLib.takeAScreenShot("noHeaderColumnHeader.jpg");
            return null;
        }
    }


    public String getHeaderShowOrHideLabel(String headerColumnStatus) {
        try {
            By columnElement;
            Wait.forElementToBeDisplayed(driver, headerColumnOrdering);
            columnElement = By.xpath("//label[text()=\"" + headerColumnStatus + "\"]");
            if (!Wait.isElementDisplayed(driver,driver.findElement(columnElement) , 10)){
                Debugger.println("No " + columnElement +  "element shown.");
                SeleniumLib.takeAScreenShot("noHeaderColumnHeader.jpg");
                return null;
            }
            return Actions.getText(driver.findElement(columnElement));
        } catch (Exception exp) {
            Debugger.println("No element shown.");
            SeleniumLib.takeAScreenShot("dropDownValuesAreNotFound.jpg");
            return null;
        }
    }

    public List<String> getListOfColumnsInHeaderShowOrHidden(String headerColumnStatus) {
        try {
            By columnStatusElement;
            By columnStatusElementList;
            String columnStatusHeaderLocator = "//div[contains(@class,'active')]//ancestor::div[@class='wrapper']/..//div[contains(@id,\"" + headerColumnStatus + "\")]";
            columnStatusElement = By.xpath(columnStatusHeaderLocator);
            Wait.forElementToBeDisplayed(driver, driver.findElement(columnStatusElement), 10);
            if (!Wait.isElementDisplayed(driver, driver.findElement(columnStatusElement), 10)) {
                Debugger.println("No " + columnStatusElement + "element shown.");
                SeleniumLib.takeAScreenShot("noHeaderColumnHeader.jpg");
                return null;
            }
            columnStatusElementList = By.xpath(columnStatusHeaderLocator + "/div");
            List<WebElement> listOfHeaderValueElements = driver.findElements(columnStatusElementList);
            List<String> actualHeaderValueList = new ArrayList<>();
            for (WebElement headerValue : listOfHeaderValueElements) {
                actualHeaderValueList.add(headerValue.getText().trim());
            }
            Debugger.println("Method Actual actualHeaderValueList " + ":" + actualHeaderValueList.size() + ":" + actualHeaderValueList);
            return actualHeaderValueList;
        } catch (Exception exp) {
            Debugger.println("No element shown.");
            SeleniumLib.takeAScreenShot("dropDownValuesAreNotFound.jpg");
            return null;
        }
    }


    public String getTheSelectedPaginationEntryValue() {
        try {
            Wait.forElementToBeDisplayed(driver, searchResultEntryOptionsSelection);
            Wait.forElementToBeDisplayed(driver, defaultPaginationEntryOptionsValue);
            if (!Wait.isElementDisplayed(driver, defaultPaginationEntryOptionsValue, 10)) {
                Debugger.println("No defaultPaginationEntryOptionsValue element shown.");
                SeleniumLib.takeAScreenShot("NodDefaultPaginationEntryOptionsElement.jpg");
                return null;
            }
            Select select = new Select(defaultPaginationEntryOptionsValue);
            return select.getFirstSelectedOption().getText();
        } catch (Exception exp) {
            Debugger.println("No element shown.");
            SeleniumLib.takeAScreenShot("dropDownValuesAreNotFound.jpg");
            return null;
        }
    }

    public List<String> getAllThePaginationEntryDropDownValues() {
        Wait.seconds(3);
        Wait.forElementToBeDisplayed(driver, defaultPaginationEntryOptionsValue);
        Select paginationSelect = new Select(defaultPaginationEntryOptionsValue);
        List<WebElement> allOptionsElement = paginationSelect.getOptions();
        List<String> allOptions = new ArrayList<>();
        for (WebElement optionElement : allOptionsElement) {
            allOptions.add(optionElement.getText());
        }
        Debugger.println("Options are " + allOptions);
        return allOptions;
    }

    public void selectValueInPagination(String valueToSelect) {
        Wait.seconds(5);
        Wait.forElementToBeDisplayed(driver, defaultPaginationEntryOptionsValue);
        Select paginationSelect = new Select(defaultPaginationEntryOptionsValue);
        paginationSelect.selectByVisibleText(valueToSelect);
    }

    public boolean getTheTotalNumberOfSearchResult(int size) {
        try {
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, searchResultRowHeader);
            if (!(searchResultTable.size() <= size)) {
                Debugger.println("Pagination is not working");
                SeleniumLib.takeAScreenShot("dropDownValuesAreNotFound.jpg");
                return false;
            }
            Debugger.println("The total search result is " + searchResultTable.size());
            return true;
        } catch (Exception exp) {
            Debugger.println("No search result");
            SeleniumLib.takeAScreenShot("NoSearchResultShown.jpg");
            return false;
        }
    }

    public boolean verifyTheButtonsShowAllAndHideAllAreDisplayedOnModalContent() {
        Wait.forElementToBeDisplayed(driver, displayOptionsModalContent, 10);
        List<WebElement> expectedElements = new ArrayList<WebElement>();
        expectedElements.add(headerShowAll);
        expectedElements.add(headerHideAll);
        for (int i = 0; i < expectedElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expectedElements.get(i))) {
                SeleniumLib.takeAScreenShot(" element" + i + "is NOT shown.jpg");
                return false;
            }
            Debugger.println("element " + i + " shown");
        }
        return true;
    }

    public void clickShowAllOrHideAllButton(String headerDisplayButton) {
        try {
            By buttonElement;
            Wait.forElementToBeDisplayed(driver, headerColumnOrdering);
            buttonElement = By.xpath("//button[contains(string(),\"" + headerDisplayButton + "\")]");
            Wait.forElementToBeDisplayed(driver, driver.findElement(buttonElement));
            if (!Wait.isElementDisplayed(driver, driver.findElement(buttonElement), 10)) {
                Debugger.println("No " + headerDisplayButton + "element shown.");
                SeleniumLib.takeAScreenShot("no" + headerDisplayButton + " button.jpg");
            }
            Actions.clickElement(driver, driver.findElement(buttonElement));
        } catch (Exception exp) {
            Debugger.println("No noShowAllOrHideAllButtonShown shown.");
            SeleniumLib.takeAScreenShot("noShowAllOrHideAllButtonShown.jpg");
        }
    }

    public boolean saveAndCloseButtonIsDisabled() {
        try {
            if (!Wait.isElementDisplayed(driver, saveAndCloseHeaderOrderingButton, 10)) {
                Debugger.println("No saveAndCloseHeaderOrderingButton element is shown.");
                SeleniumLib.takeAScreenShot("noSaveAndCloseHeaderOrderingButtonElement.jpg");
                return false;
            }
            Wait.seconds(3);
            Debugger.println("Save and Close Header Ordering button is disabled :  " + saveAndCloseHeaderOrderingButton.isEnabled());
            return saveAndCloseHeaderOrderingButton.isEnabled();
        } catch (Exception exp) {
            Debugger.println("No noShowAllOrHideAllButtonShown shown.");
            SeleniumLib.takeAScreenShot("noSaveAndCloseHeaderOrderingButton.jpg");
            return false;
        }
    }

    public boolean dragAndDropAColumnHeaderBetweenShowAndHide(String columnHeader, String fromSection, String toSection) {
        try {
            String sectionLocator = "//div[contains(@class,'active')]//ancestor::div[@class='wrapper']/..//div[contains(@id,'dummySection')]";
            String columnHeaderLocator = "//div[text()=\"" + columnHeader + "\"]";
            String toSectionLocator = "";
            List actualListOfColumnHeaders = null;

            // first check the column header is in 'From Section'
            if (fromSection.equalsIgnoreCase("Show")) {
                actualListOfColumnHeaders = getListOfColumnsInHeaderShowOrHidden("visible");
            } else if (fromSection.equalsIgnoreCase("Hide")) {
                actualListOfColumnHeaders = getListOfColumnsInHeaderShowOrHidden("hidden");
            }
            assert actualListOfColumnHeaders != null;

            if (!actualListOfColumnHeaders.contains(columnHeader)) {
                Debugger.println("No saveAndCloseHeaderOrderingButton element is shown.");
                SeleniumLib.takeAScreenShot("columnHeaderNotFoundInColumnHeaderList.jpg");
                return false;
            }
            // Check the element locator for the 'To section'
            if (toSection.equalsIgnoreCase("Show")) {
                toSectionLocator = sectionLocator.replace("dummySection", "visible");
                Debugger.println("Show section locator " + toSectionLocator);
            } else if (toSection.equalsIgnoreCase("Hide")) {
                toSectionLocator = sectionLocator.replace("dummySection", "hidden");
                Debugger.println("Hide section locator " + toSectionLocator);
            }
            WebElement columnHeaderElement = driver.findElement(By.xpath(columnHeaderLocator));
            WebElement toSectionElement = driver.findElement(By.xpath(toSectionLocator));
            org.openqa.selenium.interactions.Actions action = new org.openqa.selenium.interactions.Actions(driver);
            action.moveToElement(headerShowAll); //added as drag n drop was not happening, now scrolling to bottom of pop-up menu.
            action.dragAndDrop(columnHeaderElement, toSectionElement).build().perform();
            return true;
        } catch (Exception exp) {
            Debugger.println("unable to move column header between show and hide.");
            SeleniumLib.takeAScreenShot("unableToMoveColumnHeaderBetweenShowAndHide.jpg");
            return false;
        }
    }

    public void clickSaveAndCloseButtonOnModalContent() {
        try {
            if (!Wait.isElementDisplayed(driver, saveAndCloseHeaderOrderingButton, 10)) {
                Debugger.println("No saveAndCloseHeaderOrderingButton element is shown.");
                SeleniumLib.takeAScreenShot("noSaveAndCloseHeaderOrderingButtonElement.jpg");
            }
            //Wait.forElementToBeClickable(driver, saveAndCloseHeaderOrderingButton);
            Actions.retryClickAndIgnoreElementInterception(driver, saveAndCloseHeaderOrderingButton);
            //Click.element(driver, resetHeaderOrderingButton);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on saveAndCloseHeaderOrderingButton:" + exp);
            SeleniumLib.takeAScreenShot("saveAndCloseHeaderOrderingButton.jpg");
        }
    }

    public void clickOnCheckBoxOptionsForSaveSpaceOnScreen(String saveSpaceCompactorTruncate) {
        try {
            String saveSpaceScreen = "//span[text()='dummySaveSpace']/../input";
            String saveSpaceScreenLocator = saveSpaceScreen.replace("dummySaveSpace", saveSpaceCompactorTruncate);
            WebElement saveSpaceScreenElement = driver.findElement(By.xpath(saveSpaceScreenLocator));
            if (!Wait.isElementDisplayed(driver, saveSpaceScreenElement, 10)) {
                Debugger.println("No saveSpaceCompactOrTruncate element is shown.");
                SeleniumLib.takeAScreenShot("noSaveSpaceCompactOrTruncate.jpg");
            }
            Actions.retryClickAndIgnoreElementInterception(driver, saveSpaceScreenElement);
        } catch (Exception exp) {
            Debugger.println("No saveSpaceCompactOrTruncate element shown.");
            SeleniumLib.takeAScreenShot("noSaveSpaceCompactOrTruncate.jpg");
        }
    }

    public boolean verifyThePresenceOfColumnHeader(String columnName) {
        try {
            if (!Wait.isElementDisplayed(driver, searchResultRowHeader, 30)) {
                Debugger.println("MI Search Result table is not loaded.");
                SeleniumLib.takeAScreenShot("MISearchResultTable.jpg");
                return false;
            }
            Wait.seconds(3); //As observed it takes time to load the results table
            int colIndex = seleniumLib.getColumnIndex(searchResultTableHeading, columnName);
            if (colIndex == -1) {
                Debugger.println("Expected Column: " + columnName + " not present in the search result table");
                SeleniumLib.takeAScreenShot("MISearchResultTable.jpg");
                return false;
            }
            return true;
//            Wait.seconds(3);//As observed it take time to load
//            if (displayOptionsTableHeaders.size() == 0) {
//                Debugger.println("Nothing present in the table header.");
//                SeleniumLib.takeAScreenShot("TableHeaderNotPresent.jpg");
//                return false;
//            }
//            for (int i = 0; i < displayOptionsTableHeaders.size(); i++) {
//                if (displayOptionsTableHeaders.get(i).getText().equalsIgnoreCase(selectedOption)) {
//                    int cellNumber = i + 1;
//                    String expectedDataValue = resultData.replaceAll("dummyValue", String.valueOf(cellNumber));
//                    Wait.seconds(2);
//                    List<WebElement> dataCell = driver.findElements(By.xpath(expectedDataValue));
//                    Wait.seconds(2);//As observed it take time to load
//                    if (dataCell.size() == 0) {
//                        Debugger.println("Nothing present in the selected table cell.");
//                        SeleniumLib.takeAScreenShot("TableCellNotPresent.jpg");
//                        return false;
//                    }
//                    for (int j = 0; j < dataCell.size(); j++) {
//                        //As per the TC, checking the elements are present or not
//                        if (!seleniumLib.isElementPresent(dataCell.get(j))) {
//                            Debugger.println("Not present any data in cell number " + j);
//                            SeleniumLib.takeAScreenShot("TableCellNotPresent.jpg");
//                            return false;
//                        }
//                    }
//                }
//            }
//            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyThePresenceOfColumnHeader " + exp);
            SeleniumLib.takeAScreenShot("MISearchResultTable.jpg");
            return false;
        }
    }

    // new
    @FindBy(xpath = "//button[contains(@id,'display-save_close')]")
    public WebElement saveAndCloseButton;

    public boolean clickOnSaveAndCloseButton() {
        try {
            if (!Wait.isElementDisplayed(driver, saveAndCloseButton, 10)) {
                Debugger.println("Save And Close Button Is Not Available");
                SeleniumLib.takeAScreenShot("SaveAndCloseButton.jpg");
                return false;
            }
            Actions.clickElement(driver, saveAndCloseButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in: MiOrderTrackingPage : clickOnSaveAndCloseButton : " + exp);
            SeleniumLib.takeAScreenShot("SaveAndCloseButton.jpg");
            return false;
        }
    }

    @FindBy(xpath = "//body")
    public WebElement pageBody;
    @FindBy(xpath = "//footer")
    public WebElement menuFooter;

    public boolean verifyPresenceOfFeedbackLink() {
        try {
            if (!Wait.isElementDisplayed(driver, menuFooter, 15)) {
                Debugger.println("Footer element is Not displayed in the menu bottom");
                SeleniumLib.takeAScreenShot("FeedbackLink.jpg");
                return false;
            }
            String footerText = menuFooter.getText();
            String bodyText = pageBody.getText();
//            Debugger.println("Body text:- "+bodyText);
//            Debugger.println("Footer text:- "+footerText);
            if (footerText.contains("feedback")) {
                Debugger.println("Feedback is present in the menu bottom");
                SeleniumLib.takeAScreenShot("FeedbackLink.jpg");
                return false;
            }
            if (bodyText.contains("feedback")) {
                Debugger.println("Feedback is present in the page");
                SeleniumLib.takeAScreenShot("FeedbackLink.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from checking feedback link. " + exp);
            SeleniumLib.takeAScreenShot("FeedbackLink.jpg");
            return false;
        }
    }

    @FindBy(xpath = "//header[contains(@class,'main-header')]//*[contains(@class,'logo')]")
    public WebElement portalNameAndEnv;

    public boolean verifyPortalLogoAndEnvName(String logoName) {
        try {
            if (!Wait.isElementDisplayed(driver, portalNameAndEnv, 20)) {
                Debugger.println("The portal name and environment element is not displayed.");
                SeleniumLib.takeAScreenShot("PortalLogo.jpg");
                return false;
            }
            String logoAndEnv = portalNameAndEnv.getText();
            if (logoAndEnv == null || logoAndEnv.isEmpty()) {
                Debugger.println("MI Portal logo and environment name not present");
                SeleniumLib.takeAScreenShot("PortalLogo.jpg");
                return false;
            }
            String currentUrl = driver.getCurrentUrl();
            String[] url = currentUrl.split("\\.");
            String[] logo = logoAndEnv.split("\\s+");
            Point location = portalNameAndEnv.getLocation();
            if (location.getX() == 0 && location.getY() == 0) {
                if (!logoAndEnv.contains(logoName)) {
                    Debugger.println("Portal logo does not contain MI Portal");
                    SeleniumLib.takeAScreenShot("PortalLogo.jpg");
                    return false;
                } else if (!logo[2].equalsIgnoreCase(url[1])) {
                    Debugger.println("Portal logo does not contain correct Environment name as in Url");
                    SeleniumLib.takeAScreenShot("PortalLogo.jpg");
                    return false;
                }
                return true;
            }
            Debugger.println("The MI Portal Logo is not present at top position");
            SeleniumLib.takeAScreenShot("PortalLogo.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception from checking the MiPortal Logo presence :" + exp);
            SeleniumLib.takeAScreenShot("PortalLogo.jpg");
            return false;
        }
    }

    public boolean verifySelectedDropDownValue(String value, String dropDownField) {
        try {
            Wait.seconds(2);
            By buttonElement;
            buttonElement = By.xpath("//button[@data-id=\"" + dropDownField + "\"]//div[contains(@class,'filter-option-inner-inner')]");
            String selectedOption = driver.findElement(buttonElement).getText();
            if (selectedOption == null || selectedOption.isEmpty()) {
                Debugger.println("No options are selected in " + dropDownField + " field");
                SeleniumLib.takeAScreenShot("SelectedDropDownOption.jpg");
                return false;
            }
            selectedOption = selectedOption.replace(", ", ",");
            if (!selectedOption.equalsIgnoreCase(value)) {
                Debugger.println("The options selected are: " + selectedOption + ";But expected: " + value + ",in " + dropDownField + " field");
                SeleniumLib.takeAScreenShot("SelectedDropDownOption.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying the selected options in the 3rd drop down box " + exp);
            SeleniumLib.takeAScreenShot("SelectedDropDownOption.jpg");
            return false;
        }
    }

    public boolean verifyTheButtonsAddSearchResetAreDisplayed() {
        try {
            if (!Wait.isElementDisplayed(driver, resetButton, 10)) {
                Debugger.println("Home Page Buttons are not display.");
                SeleniumLib.takeAScreenShot("Button.jpg");
                return false;
            }
            List<WebElement> expectedElements = new ArrayList<WebElement>();
            expectedElements.add(addButton);
            expectedElements.add(searchButton);
            expectedElements.add(resetButton);

            for (int i = 0; i < expectedElements.size(); i++) {
                if (!seleniumLib.isElementPresent(expectedElements.get(i))) {
                    SeleniumLib.takeAScreenShot(" element" + i + "is NOT shown.jpg");
                    return false;
                }
                Debugger.println("element " + i + " shown");
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheButtonsAddSearchResetAreDisplayed " + exp);
            SeleniumLib.takeAScreenShot("verifyTheButtonsAddSearchResetAreDisplayed.jpg");
            return false;
        }
    }

    public boolean verifyTheButtonsResetSaveAndCloseAreDisplayed() {
        try {
            if (!Wait.isElementDisplayed(driver, displayOptionsModalContent, 10)) {
                Debugger.println("display Options Modal Content is not display.");
                SeleniumLib.takeAScreenShot("displayOptionsModalContent.jpg");
                return false;
            }
            List<WebElement> expectedElements = new ArrayList<WebElement>();
            expectedElements.add(resetHeaderOrderingButton);
            expectedElements.add(saveAndCloseHeaderOrderingButton);
            for (int i = 0; i < expectedElements.size(); i++) {
                if (!seleniumLib.isElementPresent(expectedElements.get(i))) {
                    SeleniumLib.takeAScreenShot(" element" + i + "is NOT shown.jpg");
                    return false;
                }
                Debugger.println("element " + i + " shown");
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheButtonsResetSaveAndCloseAreDisplayed " + exp);
            SeleniumLib.takeAScreenShot("verifyTheButtonsResetSaveAndCloseAreDisplayed.jpg");
            return false;
        }
    }

    public boolean verifyHorizontalScrollBar() {
        try {
            JavascriptExecutor javascript = (JavascriptExecutor) driver;
            Boolean presence = (Boolean) javascript.executeScript("return document.documentElement.scrollWidth>document.documentElement.clientWidth;");
            if (presence == true) {
                Debugger.println("Horizontal Scroll Bar Present");
                SeleniumLib.takeAScreenShot("HorizontalScrollBarPresentafterSelecting .jpg");
                return false;
            }
            Debugger.println("Horizontal Scroll Bar is not present");
            return true;
        } catch (Exception exp) {
            Debugger.println("MiportalhomePage : verifyHorizontalScrollBar : " + exp);
            SeleniumLib.takeAScreenShot("verifyHorizontalScrollBar.jpg");
            return false;

        }
    }

    String searchBoxPath = "//input[@id='dummyValue']";

    public boolean fillValueInTheTextSearchBox(String value, String searchBox) {
        try {
            Wait.seconds(2);
            String textSearchBoxPath = searchBoxPath.replaceAll("dummyValue", searchBox);
            WebElement textSearchBox = driver.findElement(By.xpath(textSearchBoxPath));
            if (!Wait.isElementDisplayed(driver, textSearchBox, 10)) {
                Debugger.println("The text search box is Not displayed");
                SeleniumLib.takeAScreenShot("TextSearchBox.jpg");
                return false;
            }
            if (value == null || value.isEmpty()) {
                Debugger.println("The value provided to search in the text search box is not correct");
                SeleniumLib.takeAScreenShot("TextSearchBox.jpg");
                return false;
            }
            textSearchBox.sendKeys(value);
            return true;
        } catch (Exception exp) {
            Debugger.println("Oops unable to locate search box : " + searchBox + " :" + exp);
            SeleniumLib.takeAScreenShot("TextSearchBox.jpg");
            return false;
        }
    }

    public boolean verifyThePresenceOfSampleProcessingMenu() {
        try {
            if (!Wait.isElementDisplayed(driver, sampleProcessing, 10)) {
                Debugger.println("Sample processing section header is not displayed.");
                SeleniumLib.takeAScreenShot("SampleProcessingSection.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyThePresenceOfSampleProcessingMenu:" + exp);
            SeleniumLib.takeAScreenShot("SampleProcessingMenu.jpg");
            return false;
        }
    }

    public boolean verifyThePresenceOfSectionHeader(String sectionHeader) {
        try {
            boolean isPresent = false;
            if (!Wait.isElementDisplayed(driver, sampleProcessing2, 10)) {
                Debugger.println("Sample processing section header field is not displayed.");
                SeleniumLib.takeAScreenShot("SampleProcessingHeaderSection.jpg");
                return isPresent;
            }
            for (int i = 0; i < subMenusOfSimpleProcessing.size(); i++) {
                if (subMenusOfSimpleProcessing.get(i).getText().equalsIgnoreCase(sectionHeader)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Section header " + sectionHeader + " not present under the sample processing section.");
                SeleniumLib.takeAScreenShot("SectionHeaderNotPresent.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyThePresenceOfSectionHeader:" + exp);
            SeleniumLib.takeAScreenShot("SampleProcessingHeaderNotPresent.jpg");
            return false;
        }
    }

    //New From Stag
    @FindBy(xpath = "//input[contains(@data-date-format,'dd-mm-yyyy')]")
    public WebElement dateSearchField;

    String searchBox = "//div[contains(@id,'dummySection')]//button[@role='button']";

    @FindBy(xpath = "//table[contains(@id,'DataTables_Table')]/thead//th")
    public List<WebElement> displayOptionsTableHeaders;

    String resultData = "//table[contains(@id,'DataTables_Table')]/tbody//tr//td[dummyValue]";

    public boolean verifyThePresenceOfSearchBoxes(String numberOfSearchField, String section) {
        try {
            Wait.seconds(3);
            Wait.forElementToBeDisplayed(driver, addButton, 10);
            if (!Wait.isElementDisplayed(driver, searchTitle, 10)) {
                Debugger.println("Search title is not displayed.");
                SeleniumLib.takeAScreenShot("SearchTitleNotPresent.jpg");
                return false;
            }
            Wait.seconds(5);//As observed page is taking time to load all elements
            String searchBoxes = searchBox.replace("dummySection", section);
            List<WebElement> searchBoxFields = driver.findElements(By.xpath(searchBoxes));
            int searchBoxSize = Integer.parseInt(numberOfSearchField);
            if (searchBoxFields.size() == searchBoxSize) {
                return true;
            }
            if (searchBoxFields.size() == 2) {
                if (seleniumLib.isElementPresent(dateSearchField)) {
                    return true;
                }
            }
            Debugger.println("Actual search box size :" + searchBoxFields.size() + ", Excepted search box size :" + searchBoxSize);
            SeleniumLib.takeAScreenShot("PresenceOfSearchBoxes.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyThePresenceOfSearchBoxes, " + exp);
            SeleniumLib.takeAScreenShot("PresenceOfSearchBoxes.jpg");
            return false;
        }
    }

    public boolean verifyTheDifferentDataBetweenColumns(String firstColumnName, String secondColumnName) {
        try {
            if (!Wait.isElementDisplayed(driver, searchResultRowHeader, 10)) {
                Debugger.println("Table Header is not display.");
                SeleniumLib.takeAScreenShot("TableHeaderNotPresent.jpg");
                return false;
            }
            Wait.seconds(3);//As observed it take time to load
            if (displayOptionsTableHeaders.size() == 0) {
                Debugger.println("Nothing present in the table header.");
                SeleniumLib.takeAScreenShot("TableHeaderNotPresent.jpg");
                return false;
            }
            int firstColumnIndex = 0;
            for (int i = 0; i < displayOptionsTableHeaders.size(); i++) {
                if (displayOptionsTableHeaders.get(i).getText().equalsIgnoreCase(firstColumnName)) {
                    firstColumnIndex = 1 + i;
                    break;
                }
            }
            if (firstColumnIndex == 0) {
                Debugger.println(firstColumnName + " not present in the table header.");
                SeleniumLib.takeAScreenShot("TableHeaderNotPresent.jpg");
                return false;
            }
            for (int i = 0; i < displayOptionsTableHeaders.size(); i++) {
                if (displayOptionsTableHeaders.get(i).getText().equalsIgnoreCase(secondColumnName)) {
                    int cellIndex = i + 1;
                    String firstColumnData = resultData.replaceAll("dummyValue", String.valueOf(cellIndex));
                    String secondColumnData = resultData.replaceAll("dummyValue", String.valueOf(firstColumnIndex));
                    Wait.seconds(2);
                    List<WebElement> firstColumnValues = driver.findElements(By.xpath(firstColumnData));
                    List<WebElement> secondColumnValues = driver.findElements(By.xpath(secondColumnData));
                    Wait.seconds(2);//As observed it take time to load
                    if (firstColumnValues.size() == 0 || secondColumnValues.size() == 0) {
                        Debugger.println("Nothing present in the selected table cell.");
                        SeleniumLib.takeAScreenShot("TableCellNotPresent.jpg");
                        return false;
                    }
                    for (int j = 0; j < firstColumnValues.size(); j++) {
                        if (firstColumnValues.get(j).getText().equalsIgnoreCase("") && secondColumnValues.get(j).getText().equalsIgnoreCase("")) {
                            continue;
                        }
                        if (firstColumnValues.get(j).getText().equalsIgnoreCase(secondColumnValues.get(j).getText())) {
                            Debugger.println("Both values are same in the index number " + j);
                            SeleniumLib.takeAScreenShot("ColumnValueMatched.jpg");
                            return false;
                        }
                    }
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheDifferentDataBetweenColumns " + exp);
            SeleniumLib.takeAScreenShot("TableDataNotPresent.jpg");
            return false;
        }
    }

    String inputData = "//label[text()='dummyLabel']/following::div[contains(@id,'display-column_order')][1]/div";

    public boolean verifyThePresenceOfDataInShowHideLabel(String emptyData, String data) {
        try {
            if (!Wait.isElementDisplayed(driver, displayOptionsModalContent, 10)) {
                Debugger.println("modal-content is displayed as expected");
                SeleniumLib.takeAScreenShot("DisplayOptionsModalContent.jpg");
                return false;
            }
            String emptyColumn = inputData.replace("dummyLabel", emptyData);
            String dataColumn = inputData.replace("dummyLabel", data);
            Wait.seconds(3);
            List<WebElement> emptyColumnElements = driver.findElements(By.xpath(emptyColumn));
            List<WebElement> dataColumnElements = driver.findElements(By.xpath(dataColumn));
            if (emptyColumnElements.size() != 0) {
                Debugger.println("Data present in the " + emptyData + " label.");
                SeleniumLib.takeAScreenShot("EmptyColumnNotPresent.jpg");
                return false;
            }
            if (dataColumnElements.size() == 0) {
                Debugger.println("No data present in the " + data + " label.");
                SeleniumLib.takeAScreenShot("EmptyColumn.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyThePresenceOfDataInShowHideLabel " + exp);
            SeleniumLib.takeAScreenShot("DisplayOptionsModalContent.jpg");
            return false;
        }
    }

    public boolean verifySaveAndCloseButtonStatus() {
        try {
            if (!Wait.isElementDisplayed(driver, saveAndCloseButton, 10)) {
                Debugger.println("Save And Close Button Is Not Available");
                SeleniumLib.takeAScreenShot("SaveAndCloseButton.jpg");
                return false;
            }
            if (!saveAndCloseButton.isEnabled()) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying Save and Close button Status:" + exp);
            SeleniumLib.takeAScreenShot("SaveAndCloseButton.jpg");
            return false;
        }
    }

    @FindBy(id = "lsid2find")
    public WebElement searchField;

    public boolean clickOnSearchAndPassLSIDRefNo(int LSIDRefNo) {
        try {
            if (!Wait.isElementDisplayed(driver, searchField, 30)) {
                Debugger.println("searchField for present even after waiting period: ");
                SeleniumLib.takeAScreenShot("SearchAndPassLSIDRefNo.jpg");
                return false;
            }
            searchField.sendKeys(String.valueOf(LSIDRefNo));
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from entering the search Term: " + exp);
            SeleniumLib.takeAScreenShot("SearchAndPassLSIDRefNo.jpg");
            return false;
        }
    }

    @FindBy(id = "findLSID")
    public WebElement findLsid;

    public boolean clickOnFindLSID() {
        try {
            if (!Wait.isElementDisplayed(driver, findLsid, 30)) {
                Debugger.println("Find LSID is not present even after waiting period: ");
                SeleniumLib.takeAScreenShot("FindLSID.jpg");
                return false;
            }
            Actions.clickElement(driver, findLsid);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on FindLSID:" + exp);
            SeleniumLib.takeAScreenShot("FindLSID.jpg");
            return false;
        }
    }

    public boolean verifyThePresenceOfExplanationForTheCellData(String explanationHeader, String cellValue, String columnHeader) {
        try {
            if (!Wait.isElementDisplayed(driver, searchResultRowHeader, 10)) {
                Debugger.println("Table Header is not display.");
                SeleniumLib.takeAScreenShot("TableHeaderNotPresent.jpg");
                return false;
            }
            Wait.seconds(3);//As observed it take time to load
            if (displayOptionsTableHeaders.size() == 0) {
                Debugger.println("Nothing present in the table header.");
                SeleniumLib.takeAScreenShot("TableHeaderNotPresent.jpg");
                return false;
            }
            for (int i = 0; i < displayOptionsTableHeaders.size(); i++) {
                if (displayOptionsTableHeaders.get(i).getText().equalsIgnoreCase(columnHeader)) {
                    int cellNumber = i + 1;
                    String expectedDataValue = resultData.replaceAll("dummyValue", String.valueOf(cellNumber));
                    Wait.seconds(2);
                    List<WebElement> dataCell = driver.findElements(By.xpath(expectedDataValue));
                    Wait.seconds(2);//As observed it take time to load
                    if (dataCell.size() == 0) {
                        Debugger.println("Nothing present in the selected table cell.");
                        SeleniumLib.takeAScreenShot("TableCellNotPresent.jpg");
                        return false;
                    }
                    for (int j = 0; j < dataCell.size(); j++) {
                        if (dataCell.get(j).getText().equalsIgnoreCase(cellValue)) {
                            for (int k = 0; k < displayOptionsTableHeaders.size(); k++) {
                                if (displayOptionsTableHeaders.get(k).getText().equalsIgnoreCase(explanationHeader)) {
                                    int explanationCellNumber = k + 1;
                                    // Below lines are creating explanation path for  a particular cellValue
                                    String explanationData = "[text()='" + cellValue + "']/ancestor::tr/td[dummyValue]";
                                    String data = explanationData.replaceAll("dummyValue", String.valueOf(explanationCellNumber));
                                    Wait.seconds(2);
                                    String explanationDataValue = expectedDataValue.concat(data);
                                    Wait.seconds(2);
                                    List<WebElement> explanationDataCells = driver.findElements(By.xpath(explanationDataValue));
                                    for (int m = 0; m < explanationDataCells.size(); m++) {
                                        if (explanationDataCells.get(m).getText().equalsIgnoreCase("")) {
                                            Debugger.println("Explanation not present in the cell number " + m);
                                            SeleniumLib.takeAScreenShot("TableExplanationCellNotPresent.jpg");
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyThePresenceOfExplanationForTheCellData " + exp);
            SeleniumLib.takeAScreenShot("TableDataNotPresent.jpg");
            return false;
        }
    }

    public boolean verifyThePresenceOfEmptyExplanationForTheEmptyCell(String explanationColumn, String inputColumn) {
        try {
            if (!Wait.isElementDisplayed(driver, searchResultRowHeader, 10)) {
                Debugger.println("Table Header is not display.");
                SeleniumLib.takeAScreenShot("TableHeaderNotPresent.jpg");
                return false;
            }
            Wait.seconds(3);//As observed it take time to load
            if (displayOptionsTableHeaders.size() == 0) {
                Debugger.println("Nothing present in the table header.");
                SeleniumLib.takeAScreenShot("TableHeaderNotPresent.jpg");
                return false;
            }
            int explanationCellNumber = 0, cellNumber = 0;
            for (int i = 0; i < displayOptionsTableHeaders.size(); i++) {
                if (displayOptionsTableHeaders.get(i).getText().equalsIgnoreCase(explanationColumn)) {
                    explanationCellNumber = i + 1;
                    break;
                }
            }
            for (int i = 0; i < displayOptionsTableHeaders.size(); i++) {
                if (displayOptionsTableHeaders.get(i).getText().equalsIgnoreCase(inputColumn)) {
                    cellNumber = i + 1;
                    String expectedDataValue = resultData.replaceAll("dummyValue", String.valueOf(cellNumber));
                    Wait.seconds(2);
                    List<WebElement> dataCell = driver.findElements(By.xpath(expectedDataValue));

                    String explanationDataValue = resultData.replaceAll("dummyValue", String.valueOf(explanationCellNumber));
                    Wait.seconds(2);
                    List<WebElement> explanationDataCell = driver.findElements(By.xpath(explanationDataValue));
                    Wait.seconds(2);//As observed it take time to load
                    if (dataCell.size() == 0) {
                        Debugger.println("Nothing present in the selected table cell.");
                        SeleniumLib.takeAScreenShot("TableCellNotPresent.jpg");
                        return false;
                    }
                    for (int j = 0; j < dataCell.size(); j++) {
                        if (dataCell.get(j).getText().equalsIgnoreCase("")) {
                            if (!explanationDataCell.get(j).getText().equalsIgnoreCase("")) {
                                Debugger.println("Explanation cell text is " + explanationDataCell.get(j).getText());
                                SeleniumLib.takeAScreenShot("ExplanationCellPresent.jpg");
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyThePresenceOfEmptyExplanationForTheEmptyCell " + exp);
            SeleniumLib.takeAScreenShot("TableDataNotPresent.jpg");
            return false;
        }
    }

    public boolean verifyThePresenceOfNonEmptyColumn(String columnName) {
        try {
            if (!Wait.isElementDisplayed(driver, searchResultRowHeader, 20)) {
                Debugger.println("Table Header is not display.");
                SeleniumLib.takeAScreenShot("TableHeaderNotPresent.jpg");
                return false;
            }
            Wait.seconds(3);//As observed it take time to load
            if (displayOptionsTableHeaders.size() == 0) {
                Debugger.println("Nothing present in the table header.");
                SeleniumLib.takeAScreenShot("TableHeaderNotPresent.jpg");
                return false;
            }
            for (int i = 0; i < displayOptionsTableHeaders.size(); i++) {
                if (displayOptionsTableHeaders.get(i).getText().equalsIgnoreCase(columnName)) {
                    int cellNumber = i + 1;
                    String expectedDataValue = resultData.replaceAll("dummyValue", String.valueOf(cellNumber));
                    Wait.seconds(2);
                    List<WebElement> dataCell = driver.findElements(By.xpath(expectedDataValue));
                    Wait.seconds(2);//As observed it take time to load
                    for (int j = 0; j < dataCell.size(); j++) {
                        if (dataCell.get(j).getText().equalsIgnoreCase("")) {
                            Debugger.println("Not present any data in cell number " + j);
                            SeleniumLib.takeAScreenShot("TableCellNotPresent.jpg");
                            return false;
                        }
                    }
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyThePresenceOfNonEmptyColumn " + exp);
            SeleniumLib.takeAScreenShot("TableDataNotPresent.jpg");
            return false;
        }
    }

    public boolean verifyDateFormat() {
        try {
            List<WebElement> allHeaders = driver.findElements(By.xpath("//table[contains(@id,'DataTables_Table')]/tbody/tr/td[14]"));
            List<String> headers = new ArrayList<>();
            for (WebElement elementHeader : allHeaders) {
                String header = elementHeader.getText();
                headers.add(header);
            }
            Debugger.println("All headers" + headers);
            //formate : dd/mm/yyyy
            String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
            Pattern p = Pattern.compile(regex);
            for (String date : headers) {
                Matcher matcher = p.matcher(date);
                Debugger.println(date + " : " + matcher.matches());
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verify Date Format " + exp);
            SeleniumLib.takeAScreenShot("verifyDateFormat.jpg");
            return false;
        }
    }

    public boolean verifyThePresenceOfResultInTableFormatWithDisplayOptions() {
        try {
            Wait.seconds(2);
            if (!Wait.isElementDisplayed(driver, searchResultDisplayOptionsButton, 20)) {
                Debugger.println("Search result display option is not displayed.");
                SeleniumLib.takeAScreenShot("DisplayOptionsButton.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, displayedResultTable, 10)) {
                Debugger.println("Search result is not displayed as a table format.");
                SeleniumLib.takeAScreenShot("SearchResultTableNotFound.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("search result table is not found");
            SeleniumLib.takeAScreenShot("SearchResultTableNotFound.jpg");
            return false;
        }
    }

    public boolean verifyErrorMessage(String expErrorMessage) {
        try{
            if (Wait.isElementDisplayed(driver,errorMessageElement,5)){
                if(!errorMessageElement.getText().contains(expErrorMessage)){
                    Debugger.println("Actual error message : "+errorMessageElement.getText()+"  is not matching with the expected error message :"+expErrorMessage);
                    SeleniumLib.takeAScreenShot("ErrorMessage.jpg");
                    return false;
                }
                return true;
            }
            Debugger.println("Error message not found :");
            SeleniumLib.takeAScreenShot("ErrorMessage.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("MiportalhomePage : verifyErrorMessage : "+exp);
            SeleniumLib.takeAScreenShot("ErrorMessage.jpg");
            return false;
        }
    }
}

