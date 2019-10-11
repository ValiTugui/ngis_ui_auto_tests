package co.uk.gel.proj.pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RequestingOrganisationPage {

	WebDriver driver;

	public RequestingOrganisationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(css = "input[class*='search-input']")
	public WebElement searchField;
	
	@FindBy(css = "h2[class*='ordering-entity__previous-header']")
	public WebElement previousOrganisationHeader;

	@FindBy(css = "div[class*='location-result']")
	public WebElement organisationDetailsContainer;
	
	@FindBy(css = "h4[class*='location-result__header']")
	public WebElement organisationName;
	
	@FindBy(css = "p[class*='location-result__detail-heading']")
	public List<WebElement> organisationDetailHeader;
	
	@FindBy(css = "p[class*='location-result__detail-text']")
	public List<WebElement> organisationDetailText;
	

	
}
