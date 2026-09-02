package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.WebdriverUtility;

public class CreateNewContactPage extends WebdriverUtility {

	WebDriver driver;

	public CreateNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // current obj reference
	}

	@FindBy(name = "lastname")
	private WebElement lastSearchEdt;

	@FindBy(name = "button")
	private WebElement saveBtn;

	@FindBy(xpath = "//input[@name='account_name']/..//img[@src='themes/softed/images/select.gif']")
	private WebElement orgNameseachEdt;

	@FindBy(id = "search_txt")
	private WebElement childSearchOrgNameEdt;

	@FindBy(name = "search")
	private WebElement childSearchOrgBtn;

	public WebElement getLastSearchEdt() {
		return lastSearchEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getOrgNameseachEdt() {
		return orgNameseachEdt;
	}

	public WebElement getChildSearchOrgNameEdt() {
		return childSearchOrgNameEdt;
	}

	public WebElement getChildSearchOrgBtn() {
		return childSearchOrgBtn;
	}

	public void createCon(String lastName) {
		lastSearchEdt.sendKeys(lastName);
		saveBtn.click();
	}

	public void createConWithOrg(String lastName, String orgName) {
		lastSearchEdt.sendKeys(lastName);
		orgNameseachEdt.click();
		switchToTabOnUrl(driver, "module=Accounts");
		childSearchOrgNameEdt.sendKeys(orgName);
		childSearchOrgBtn.click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		switchToTabOnUrl(driver, "module=Contacts");
		saveBtn.click();
	}

}
