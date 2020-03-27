package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


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

    @FindBy(xpath = "//div[contains(@class,'active')]//span[contains(@class,'badge-info')]")
    public WebElement badgeFilterSearchCriteria;

    @FindBy(xpath = "//h3[text()='Search Results']")
    public WebElement searchResultTitle;

    @FindBy(xpath = "//div[contains(@class,'active')]//button[contains(string(), 'Display Options')]")
    public WebElement searchResultDisplayOptionsButton;

    @FindBy(xpath = "//div[contains(@class,'active')]//a[contains(string(),'Download CSV')]")
    public WebElement downloadCSVButton;

    @FindBy(xpath = "//div[contains(@class,'active')]//table[contains(@id,'DataTables_Table')]//tbody/tr")
    public List<WebElement> searchResultTable;

    @FindBy(xpath = "//table[contains(@id,'DataTables_Table')]/thead//tr")
    public WebElement searchResultRowHeader;

    @FindBy(xpath = "//select[contains(@name,'DataTables_Table')]")
    public WebElement searchResultEntryOptionsSelection;

    @FindBy(xpath = "//div[contains(@class,'active')]//label[contains(string(),'Show')]//select")
    public WebElement defaultPaginationEntryOptionsValue;

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

    @FindBy(xpath = "//div[contains(@class,'active')]//ancestor::div[@class='wrapper']/..//div[@class='modal-footer']//button[contains(string(),'Reset')]")
    public WebElement resetHeaderOrderingButton;

    @FindBy(xpath = "//div[contains(@class,'active')]//ancestor::div[@class='wrapper']/..//div[@class='modal-footer']//button[contains(string(),'Save')]")
    public WebElement saveAndCloseHeaderOrderingButton;

    @FindBy(xpath = "//label[text()='Show']")
    public WebElement headerShow;

    @FindBy(xpath = "//label[text()='Hide']")
    public WebElement headerHide;

    @FindBy(xpath = "//button[contains(string(),'Show all')]")
    public WebElement headerShowAll;

    @FindBy(xpath = "//button[contains(string(),'Hide all')]")
    public WebElement headerHideAll;

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
            Wait.forElementToBeDisplayed(driver, mainSearchContainer);
            Wait.forElementToBeDisplayed(driver, searchBoxHeader);
            if (Wait.isElementDisplayed(driver, mainSearchContainer, 10)) {
                Debugger.println("main search container is displayed");
                return true;
            } else {
                Debugger.println("element not found ");
                SeleniumLib.takeAScreenShot("searchcontainerNotFound.jpg");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("main search container is not displayed");
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
            Actions.retryClickAndIgnoreElementInterception(driver, resetButton);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on resetButton:" + exp);
            SeleniumLib.takeAScreenShot("NoResetButton.jpg");
        }
    }

    public void selectSearchValueDropDown(String value,String dropDownButton) {
        try {
            By buttonElement;
            buttonElement = By.xpath("//button[@data-id=\"" + dropDownButton + "\"]");
            Actions.retryClickAndIgnoreElementInterception(driver, driver.findElement(buttonElement));
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
            //Click.element(driver, element);
            Wait.seconds(2);
            Click.element(driver, driver.findElement(By.xpath("//div[contains(@class,'active')]//ul[@class='dropdown-menu inner ']/li//span[text()='" + value + "']")));
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
            Wait.forElementToDisappear(driver,By.xpath(badgeFilterSearchCriteriaBy));
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
        Wait.forElementToBeDisplayed(driver,searchResultDisplayOptionsButton, 10 );
        List<WebElement> expectedElements = new ArrayList<WebElement>();
        expectedElements.add(searchResultTitle);
        expectedElements.add(searchResultDisplayOptionsButton);
        expectedElements.add(searchResultRowHeader);
        expectedElements.add(searchResultEntryOptionsSelection);
        expectedElements.add(downloadCSVButton);
        for (int i = 0; i < expectedElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expectedElements.get(i))) {
                return false;
            }
            Debugger.println("element " + i + " shown");
        }
        return true;
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


    public void clickSearchResultDisplayOptionsButton() {
        try {
            Wait.forElementToBeClickable(driver, searchResultDisplayOptionsButton);
            Click.element(driver, searchResultDisplayOptionsButton);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on displayOptions:" + exp);
            SeleniumLib.takeAScreenShot("NoDisplayOptions.jpg");
        }
    }

    public boolean modalContentIsDisplayed() {
        try {
            if (Wait.isElementDisplayed(driver, displayOptionsModalContent, 10)) {
                Debugger.println("modal-content is displayed as expected");
                return true;
            } else {
                Debugger.println("modal-content is NOT displayed");
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
            Wait.forElementToBeClickable(driver, resetHeaderOrderingButton);
            Actions.retryClickAndIgnoreElementInterception(driver, resetHeaderOrderingButton);
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
            return Actions.getText(defaultPaginationEntryOptionsValue);
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
}

