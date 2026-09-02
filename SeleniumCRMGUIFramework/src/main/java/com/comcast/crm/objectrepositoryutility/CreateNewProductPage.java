package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewProductPage {
	
		
		WebDriver driver;
		public  CreateNewProductPage(WebDriver driver) {
		
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(name="productname")
		private WebElement productNameEdt;
		
		@FindBy(name="button")
		private WebElement productSaveBtn;
		
		public WebElement getProductNameEdt() {
			return productNameEdt;
		}

		public WebElement getProductSaveBtn() {
			return productSaveBtn;
		}

		public void createProduct(String productName) {
			productNameEdt.sendKeys(productName);
			productSaveBtn.click();
		}
		
		
	}


	