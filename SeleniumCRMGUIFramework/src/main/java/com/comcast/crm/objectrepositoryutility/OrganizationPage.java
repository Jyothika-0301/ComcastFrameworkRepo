package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {

	WebDriver driver;

	public OrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // current obj reference
	}

	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createNewOrgBtn;
	
	@FindBy(name ="search_text")
	private WebElement searchEdt;
	 
	@FindBy(name= "search_field")
	private WebElement searchDropdown;
	
	@FindBy(name= "submit")
	private WebElement submitBtn;

	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDD() {
		return searchDropdown;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	

}
