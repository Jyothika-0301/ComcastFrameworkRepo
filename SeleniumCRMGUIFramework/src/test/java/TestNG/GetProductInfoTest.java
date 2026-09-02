package TestNG;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtility.ExcelUtility;

public class GetProductInfoTest {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.amazon.in/");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
	
		//capture a product
		String x ="//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div/div[1]/div[1]/a/span/span[2]/span[2])[1]";
		 String price= driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
	}
	@DataProvider 
	public Object[][] getData() throws EncryptedDocumentException, IOException{
		ExcelUtility eu= new ExcelUtility();
		int rowCount=eu.totalNumberOfRows("product");
		
		Object[][] objarr = new Object[rowCount][2];       //3- no of times execution , 2-no of data passing
		
		for(int i=0;i<rowCount;i++) {
		objarr[i][0] =eu.readDataFromExcel("product", i+1, 0);
		objarr[i][1]=eu.readDataFromExcel("product", i+1, 0);
		}
		return objarr;
		

	}
}



